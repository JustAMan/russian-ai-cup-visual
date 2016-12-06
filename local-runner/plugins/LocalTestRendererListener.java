import java.awt.*;

import static java.lang.StrictMath.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import model.*;

public final class LocalTestRendererListener {
	private final static String LOGFILE_NAME = "visualizer-plugin.err"; 
	private static void reportException(Exception exc)
	{
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(LOGFILE_NAME, true));
			exc.printStackTrace(writer);
			writer.close();
		} catch (FileNotFoundException e1) {
			return;
		} catch (UnsupportedEncodingException e1) {
			return;
		} catch (IOException e1) {
			return;
		}
	}
	private final class Message
	{
		public final static String CIRCLE = "circle";
		public final static String RECT = "rect";
		public final static String ARC = "arc";
		public final static String LINE = "line";
		public final static String TEXT = "text";
		public final static String FILL_CIRCLE = "fill_circle";
		public final static String FILL_RECT = "fill_rect";
		public final static String FILL_ARC = "fill_arc";
		public final static String UNKNOWN = "unknown";
		
		private double x1, y1, x2, y2, radius, startAngle, arcAngle;
		private Color color;
		private String type, text;
		
		public Message(String line)
		{
			String[] tokens = line.split(" ");
			int colorPos = 2;
			type = tokens[1];
			if (type.equals(CIRCLE) || type.equals(FILL_CIRCLE))
			{
				x1 = Double.parseDouble(tokens[2]);
				y1 = Double.parseDouble(tokens[3]);
				x2 = Double.parseDouble(tokens[4]);
				colorPos = 5;
			}
			else if (type.equals(RECT) || type.equals(LINE) || type.equals(FILL_RECT))
			{
				x1 = Double.parseDouble(tokens[2]);
				y1 = Double.parseDouble(tokens[3]);
				x2 = Double.parseDouble(tokens[4]);
				y2 = Double.parseDouble(tokens[5]);
				colorPos = 6;
			}
			else if (type.equals(TEXT))
			{
				x1 = Double.parseDouble(tokens[2]);
				y1 = Double.parseDouble(tokens[3]);
				StringBuilder sb = new StringBuilder();
				for (int i = 4; i < tokens.length - 3; i++)
				{
					sb.append(tokens[i]);
					if (i < tokens.length - 4)
					{
						sb.append(" ");
					}
				}
				text = sb.toString();
				colorPos = tokens.length - 3;
			}
			else if (type.equals(ARC) || type.equals(FILL_ARC))
			{
				x1 = Double.parseDouble(tokens[2]);
				y1 = Double.parseDouble(tokens[3]);
				radius = Double.parseDouble(tokens[4]);
				startAngle = -Double.parseDouble(tokens[5]);  // Graphics.drawArc() считает против часовой стрелки
				arcAngle =   -Double.parseDouble(tokens[6]);  // Graphics.drawArc() считает против часовой стрелки
				colorPos = 7;
			}
			else
			{
				type = UNKNOWN;
				return;
			}
			
			float r = Float.parseFloat(tokens[colorPos]);
			float g = Float.parseFloat(tokens[colorPos + 1]);
			float b = Float.parseFloat(tokens[colorPos + 2]);
			color = new Color(r, g, b);
		}
		
		public void draw(Graphics graphics, LocalTestRendererListener listner, boolean useAbsCoords)
		{
			if (type == UNKNOWN) return;
			graphics.setColor(color);
			if (type.equals(CIRCLE)) listner.drawCircle(x1, y1, x2, useAbsCoords);
			if (type.equals(FILL_CIRCLE)) listner.fillCircle(x1, y1, x2, useAbsCoords);
			if (type.equals(RECT)) listner.drawRect(x1, y1, x2 - x1, y2 - y1, useAbsCoords);
			if (type.equals(ARC)) listner.drawArc(x1, y1, radius, startAngle, arcAngle, useAbsCoords);
			if (type.equals(FILL_RECT)) listner.fillRect(x1, y1, x2 - x1, y2 - y1, useAbsCoords);
			if (type.equals(FILL_ARC)) listner.fillArc(x1, y1, radius, startAngle, arcAngle, useAbsCoords);
			if (type.equals(LINE)) listner.drawLine(x1, y1, x2, y2, useAbsCoords);
			if (type.equals(TEXT)) listner.showText(x1, y1, text, useAbsCoords);
		}
	}

    enum TargetQueue {PRE, POST, ABS, NONE};

    private final class ThreadListener extends Thread
    {
		public static final String PRE = "pre";
		public static final String POST = "post";
		public static final String ABS = "abs";
		public static final String DRAW_END = "draw_end";
    	public static final String SYNC = "sync";
    	public static final String ACKNOWLEDGE = "ack";

    	private static final int BUFFER_SIZE_BYTES = 1 << 20;
        
    	private ServerSocket socket;
    	private ArrayList<Message> messagesPre, messagesPost, messagesAbs, lastMessagesPre, lastMessagesPost, lastMessagesAbs;
    	private TargetQueue queue;
    	private Lock lock;
    	private OutputStream outputStream;
    	private OutputStreamWriter outputWriter;
    	private SynchronousQueue<String> acknowledgeQueue;
    	public ThreadListener(int port) throws IOException
    	{
    		socket = new ServerSocket(port);
    		messagesPre = new ArrayList<Message>();
    		messagesPost = new ArrayList<Message>();
            messagesAbs = new ArrayList<Message>();
            lastMessagesPre = new ArrayList<Message>();
            lastMessagesPost = new ArrayList<Message>();
            lastMessagesAbs = new ArrayList<Message>();

    		queue = TargetQueue.NONE;
    		lock = new ReentrantLock();
    		outputStream = null;
    		outputWriter = null;
    		acknowledgeQueue = new SynchronousQueue<String>(true);
    	}
    	
    	public boolean isReady()
    	{
    		return outputWriter != null;
    	}
    	
    	public boolean syncronize(int tick) throws IOException, InterruptedException
    	{
    		outputWriter.write(SYNC + " " + tick + "\n");
    		outputWriter.flush();
    		outputStream.flush();
    		return acknowledgeQueue.take().equals(ACKNOWLEDGE);
    	}
    	
    	public void run()
    	{
    		try
    		{
				Socket client = socket.accept();
				client.setSendBufferSize(BUFFER_SIZE_BYTES);
				client.setReceiveBufferSize(BUFFER_SIZE_BYTES);
				client.setTcpNoDelay(true);
				BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				outputStream = client.getOutputStream();
				outputWriter = new OutputStreamWriter(outputStream);
	    		while (true)
	    		{
	    			String line = null;
	    			try {
	    				line = reader.readLine();
	    			} catch (IOException e)
	    			{
	    				reportException(e);
	    				return;
	    			}
	    			if (line == null) return;
					lock.lock();
					if (line.equals(ACKNOWLEDGE))
					{
						acknowledgeQueue.put(line);
					}
					else if (line.equals(DRAW_END))
					{
						// Swap lists
						ArrayList<Message> tmp = lastMessagesPre;
						lastMessagesPre = messagesPre;
						messagesPre = tmp;
						messagesPre.clear();

						tmp = lastMessagesPost;
						lastMessagesPost = messagesPost;
						messagesPost = tmp;
						messagesPost.clear();

						tmp = lastMessagesAbs;
						lastMessagesAbs = messagesAbs;
						messagesAbs = tmp;
						messagesAbs.clear();
					}
					else if (line.startsWith(PRE))
	    			{
						Message msg = null;
						try
						{
							msg = new Message(line);
							messagesPre.add(msg);
						}
						catch (Exception e)
						{
							reportException(e);
						}
	    			}
	    			else if (line.startsWith(POST))
	    			{
						Message msg = null;
						try
						{
							msg = new Message(line);
							messagesPost.add(msg);
						}
						catch (Exception e)
						{
							reportException(e);
						}
	    			}
                    else if (line.startsWith(ABS))
                    {
						Message msg = null;
						try
						{
							msg = new Message(line);
							messagesAbs.add(msg);
						}
						catch (Exception e)
						{
							reportException(e);
						}
                    }
					lock.unlock();
	    		}
    		}
    		catch (Exception e)
    		{
    			reportException(e);
    		}
    	}
		public void draw(Graphics graphics, LocalTestRendererListener listner, TargetQueue target)
		{
			ArrayList<Message> messages;
			lock.lock();
            switch (target)
            {
                case PRE:
                    messages = lastMessagesPre;
                    break;
                case POST:
                    messages = lastMessagesPost;
                    break;
                case ABS:
                    messages = lastMessagesAbs;
                    break;
                default:
                    lock.unlock();
                    return;
            }
			
			Color oldColor = graphics.getColor();
			for (int i = 0; i < messages.size(); i++)
			{
				messages.get(i).draw(graphics, listner, target.equals(TargetQueue.ABS));
			}
			lock.unlock();
			graphics.setColor(oldColor);
		}

    }
    
    enum SyncMode {DISABLED, ENABLED, AUTO};
    private static final String LOCAL_STRATEGY_NAME = "MyStrategy";

    private Graphics graphics;
    private World world;
    private Game game;

    private int canvasWidth;
    private int canvasHeight;

    private double left;
    private double top;
    private double width;
    private double height;
    
    private final int PLUGIN_PORT_NUMBER = 13579;
    private final SyncMode PLUGIN_DO_SYNC_DEFAULT = SyncMode.DISABLED;
    private ThreadListener listener;
    private int port;
    private Font textFont;
    private SyncMode doSync;
    
    private void loadProperties() throws IOException
    {
    	Properties properties = new Properties();
    	try
    	{
			properties.load(new FileInputStream("visualizer-plugin.properties"));
    	}
    	catch (FileNotFoundException e)
    	{
    		// no properties file, use defaults
    		port = PLUGIN_PORT_NUMBER;
    		return;
    	}

    	String portNo = properties.getProperty("plugin-port-number");
    	port = Integer.parseInt(portNo);
    	String doSyncStr = properties.getProperty("plugin-do-tick-sync");
    	if (doSyncStr.equalsIgnoreCase("true"))
    	{
    		doSync = SyncMode.ENABLED;
    	}
    	else if (doSyncStr.equalsIgnoreCase("auto"))
    	{
    		doSync = SyncMode.AUTO;
    	}
    	else
    	{
    		doSync = SyncMode.DISABLED;
    	}
    }
    
    public LocalTestRendererListener()
    {
    	File logfile = new File(LOGFILE_NAME);
    	if (logfile.exists())
    	{
    		logfile.delete();
    	}
	
    	// load defaults
    	port = PLUGIN_PORT_NUMBER;
    	doSync = PLUGIN_DO_SYNC_DEFAULT;
    	// now try loading from properties
    	try {
			loadProperties();
		} catch (Exception e1) {
			reportException(e1);
			return;
		}
    	
    	textFont = null;
    	
    	try {
			listener = new ThreadListener(port);
			listener.start();
		} catch (IOException e) {
			reportException(e);
			listener = null;
		}
    }

    public void beforeDrawScene(Graphics graphics, World world, Game game, int canvasWidth, int canvasHeight,
                                double left, double top, double width, double height) {
        updateFields(graphics, world, game, canvasWidth, canvasHeight, left, top, width, height);
        if (listener != null)
    	{
        	if (doSync == SyncMode.AUTO)
        	{
        		doSync = SyncMode.ENABLED;
        		for (int i = 0; i < world.getPlayers().length; i++)
        		{
        			if (world.getPlayers()[i].getName().startsWith(LOCAL_STRATEGY_NAME))
        			{
        				doSync = SyncMode.DISABLED;
        				break;
        			}
        		}
        	}

        	if (doSync == SyncMode.ENABLED)
        	{
        		while (!listener.isReady())
    			{
        			try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						reportException(e);
					}
        			// do nothing waiting for debug client
    			};
	        	try {
					listener.syncronize(world.getTickIndex());
				} catch (IOException | InterruptedException e) {
					reportException(e);
				}
        	}
        	listener.draw(graphics, this, TargetQueue.PRE);
    	}
    }

    public void afterDrawScene(Graphics graphics, World world, Game game, int canvasWidth, int canvasHeight,
                               double left, double top, double width, double height) {
        updateFields(graphics, world, game, canvasWidth, canvasHeight, left, top, width, height);
        if (listener != null) {
            listener.draw(graphics, this, TargetQueue.POST);
            listener.draw(graphics, this, TargetQueue.ABS);
        }
    }

    private void updateFields(Graphics graphics, World world, Game game, int canvasWidth, int canvasHeight,
                              double left, double top, double width, double height) {
        this.graphics = graphics;
        this.world = world;
        this.game = game;

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    private void drawLine(double x1, double y1, double x2, double y2, boolean useAbsCoords) {
        Point2I lineBegin = useAbsCoords ? new Point2I(x1, y1) : toCanvasPosition(x1, y1);
        Point2I lineEnd = useAbsCoords ? new Point2I(x2, y2) : toCanvasPosition(x2, y2);

        graphics.drawLine(lineBegin.getX(), lineBegin.getY(), lineEnd.getX(), lineEnd.getY());
    }

    private void fillCircle(double centerX, double centerY, double radius, boolean useAbsCoords) {
        Point2I topLeft = useAbsCoords ? new Point2I(centerX - radius, centerY - radius) : toCanvasPosition(centerX - radius, centerY - radius);
        Point2I size = useAbsCoords ? new Point2I(2.0D * radius, 2.0D * radius) : toCanvasOffset(2.0D * radius, 2.0D * radius);

        graphics.fillOval(topLeft.getX(), topLeft.getY(), size.getX(), size.getY());
    }

    private void drawCircle(double centerX, double centerY, double radius, boolean useAbsCoords) {
        Point2I topLeft = useAbsCoords ? new Point2I(centerX - radius, centerY - radius) : toCanvasPosition(centerX - radius, centerY - radius);
        Point2I size = useAbsCoords ? new Point2I(2.0D * radius, 2.0D * radius) : toCanvasOffset(2.0D * radius, 2.0D * radius);

        graphics.drawOval(topLeft.getX(), topLeft.getY(), size.getX(), size.getY());
    }
    
    private void showText(double X, double Y, String text, boolean useAbsCoords)
    {
    	Point2I position = useAbsCoords ? new Point2I(X, Y) : toCanvasPosition(X, Y);
    	Font oldFont = graphics.getFont();
    	if (textFont == null)
    	{
    		textFont = new Font(oldFont.getFamily(), Font.PLAIN, 10);
    	}
    	graphics.setFont(textFont);
    	graphics.drawString(text, position.getX(), position.getY());
    	graphics.setFont(oldFont);
    }

	private void fillArc(double centerX, double centerY, double radius, double startAngle, double arcAngle, boolean useAbsCoords) {
		Point2I topLeft = useAbsCoords ? new Point2I(centerX - radius, centerY - radius) : toCanvasPosition(centerX - radius, centerY - radius);
		Point2I size = useAbsCoords ? new Point2I(2.0D * radius, 2.0D * radius) : toCanvasOffset(2.0D * radius, 2.0D * radius);

		// Convert from radians to degrees
		int startAngleInt = (int) (Math.round(Math.toDegrees(startAngle)));
		int arcAngleInt = (int) (Math.round(Math.toDegrees(arcAngle)));

		graphics.fillArc(topLeft.getX(), topLeft.getY(), size.getX(), size.getY(), startAngleInt, arcAngleInt);
	}

	private void drawArc(double centerX, double centerY, double radius, double startAngle, double arcAngle, boolean useAbsCoords) {
		Point2I topLeft = useAbsCoords ? new Point2I(centerX - radius, centerY - radius) : toCanvasPosition(centerX - radius, centerY - radius);
		Point2I size = useAbsCoords ? new Point2I(2.0D * radius, 2.0D * radius) : toCanvasOffset(2.0D * radius, 2.0D * radius);

		// Convert from radians to degrees
		int startAngleInt = (int) (Math.round(Math.toDegrees(startAngle)));
		int arcAngleInt = (int) (Math.round(Math.toDegrees(arcAngle)));

		graphics.drawArc(topLeft.getX(), topLeft.getY(), size.getX(), size.getY(), startAngleInt, arcAngleInt);
	}

    private void fillRect(double left, double top, double width, double height, boolean useAbsCoords) {
        Point2I topLeft = useAbsCoords ? new Point2I(left, top) : toCanvasPosition(left, top);
        Point2I size = useAbsCoords ? new Point2I(width, height) : toCanvasOffset(width, height);

        graphics.fillRect(topLeft.getX(), topLeft.getY(), size.getX(), size.getY());
    }

    private void drawRect(double left, double top, double width, double height, boolean useAbsCoords) {
        Point2I topLeft = useAbsCoords ? new Point2I(left, top) : toCanvasPosition(left, top);
        Point2I size = useAbsCoords ? new Point2I(width, height) : toCanvasOffset(width, height);

        graphics.drawRect(topLeft.getX(), topLeft.getY(), size.getX(), size.getY());
    }

    private void drawPolygon(boolean useAbsCoords, Point2D... points) {
        int pointCount = points.length;

        for (int pointIndex = 1; pointIndex < pointCount; ++pointIndex) {
            Point2D pointA = points[pointIndex];
            Point2D pointB = points[pointIndex - 1];
            drawLine(pointA.getX(), pointA.getY(), pointB.getX(), pointB.getY(), useAbsCoords);
        }

        Point2D pointA = points[0];
        Point2D pointB = points[pointCount - 1];
        drawLine(pointA.getX(), pointA.getY(), pointB.getX(), pointB.getY(), useAbsCoords);
    }

    private Point2I toCanvasOffset(double x, double y) {
        return new Point2I(x * canvasWidth / width, y * canvasHeight / height);
    }

    private Point2I toCanvasPosition(double x, double y) {
        return new Point2I((x - left) * canvasWidth / width, (y - top) * canvasHeight / height);
    }

    private static final class Point2I {
        private int x;
        private int y;

        private Point2I(double x, double y) {
            this.x = toInt(round(x));
            this.y = toInt(round(y));
        }

        private Point2I(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private Point2I() {
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
        
        private static int toInt(double value) {
            @SuppressWarnings("NumericCastThatLosesPrecision") int intValue = (int) value;
            if (abs((double) intValue - value) < 1.0D) {
                return intValue;
            }
            throw new IllegalArgumentException("Can't convert double " + value + " to int.");
        }
    }

    private static final class Point2D {
        private double x;
        private double y;

        private Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }

        private Point2D() {
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }
    }
}
