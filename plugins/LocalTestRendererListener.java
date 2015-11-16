import java.awt.*;

import static java.lang.StrictMath.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.Properties;
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
		public final static String LINE = "line";
		public final static String TEXT = "text";
		public final static String FILL_CIRCLE = "fill_circle";
		public final static String FILL_RECT = "fill_rect";
		public final static String UNKNOWN = "unknown";
		
		private double x1, y1, x2, y2;
		private Color color;
		private String type, text;
		
		public Message(String line)
		{
			String[] tokens = line.split(" ");
			int colorPos = 1;
			type = tokens[0];
			if (type.equals(CIRCLE) || type.equals(FILL_CIRCLE))
			{
				x1 = Double.parseDouble(tokens[1]);
				y1 = Double.parseDouble(tokens[2]);
				x2 = Double.parseDouble(tokens[3]);
				colorPos = 4;
			}
			else if (type.equals(RECT) || type.equals(LINE) || type.equals(FILL_RECT))
			{
				x1 = Double.parseDouble(tokens[1]);
				y1 = Double.parseDouble(tokens[2]);
				x2 = Double.parseDouble(tokens[3]);
				y2 = Double.parseDouble(tokens[4]);
				colorPos = 5;
			}
			else if (type.equals(TEXT))
			{
				x1 = Double.parseDouble(tokens[1]);
				y1 = Double.parseDouble(tokens[2]);
				text = tokens[3];
				colorPos = 4;
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
		
		public void draw(Graphics graphics, LocalTestRendererListener listner)
		{
			if (type == UNKNOWN) return;
			graphics.setColor(color);
			if (type.equals(CIRCLE)) listner.drawCircle(x1, y1, x2);
			if (type.equals(FILL_CIRCLE)) listner.fillCircle(x1, y1, x2);
			if (type.equals(RECT)) listner.drawRect(x1, y1, x2, y2);
			if (type.equals(FILL_RECT)) listner.fillRect(x1, y1, x2, y2);
			if (type.equals(LINE)) listner.drawLine(x1, y1, x2, y2);
			if (type.equals(TEXT)) listner.showText(x1, y1, text);
		}
	}
    private final class ThreadListener extends Thread
    {
    	public static final String BEGIN_PRE = "begin pre";
    	public static final String END_PRE = "end pre";
    	public static final String BEGIN_POST = "begin post";
    	public static final String END_POST = "end post";

    	private ServerSocket socket;
    	private ArrayList<Message> messagesPre, messagesPost;
    	private boolean readyPre, readyPost;
    	private int queue;
    	private Lock lock;
    	public ThreadListener(int port) throws IOException
    	{
    		socket = new ServerSocket(port);
    		messagesPre = new ArrayList<Message>();
    		messagesPost = new ArrayList<Message>();
    		readyPre = readyPost = false;
    		queue = 0;
    		lock = new ReentrantLock();
    	}
    	
    	public void run()
    	{
    		try
    		{
				Socket client = socket.accept();;
				BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
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
	    			if (line.equals(BEGIN_PRE))
	    			{
	    	    		readyPre = false;
	    	    		messagesPre.clear();
	    	    		queue = -1;
	    			}
	    			else if (line.equals(END_PRE))
	    			{
	    				readyPre = true;
	    				queue = 0;
	    			}
	    			else if (line.equals(BEGIN_POST))
	    			{
	    				readyPost = false;
	    				messagesPost.clear();
	    				queue = 1;
	    			}
	    			else if (line.equals(END_POST))
	    			{
	    				readyPost = true;
	    				queue = 0;
	    			}
	    			else if (queue != 0)
	    			{
	    				Message msg = null;
	    				try
	    				{
	    					msg = new Message(line);
	    				}
	    				catch (Exception e)
	    				{
	    					reportException(e);
	    				}
	    				if (msg != null)
	    				{
		    				if (queue == 1) 
							{
		    					messagesPost.add(msg);
							}
		    				else if (queue == -1)
		    				{
		    					messagesPre.add(msg);
		    				}
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
		public void draw(Graphics graphics, LocalTestRendererListener listner, boolean isPre)
		{
			ArrayList<Message> messages;
			lock.lock();
			if (isPre && readyPre)
			{
				messages = messagesPre;
			}
			else if (!isPre && readyPost)
			{
				messages = messagesPost;
			}
			else
			{
				lock.unlock();
				return;
			}
			
			Color oldColor = graphics.getColor();
			for (int i = 0; i < messages.size(); i++)
			{
				messages.get(i).draw(graphics, listner);
			}
			lock.unlock();
			graphics.setColor(oldColor);
		}

    }

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
    private ThreadListener listener;
    private int port;
    
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
    	}

    	String portNo = properties.getProperty("plugin-port-number");
    	port = Integer.parseInt(portNo);
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
    	// now try loading from properties
    	try {
			loadProperties();
		} catch (Exception e1) {
			reportException(e1);
			return;
		}
    	
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
        if (listener != null) listener.draw(graphics, this, true);
    }

    public void afterDrawScene(Graphics graphics, World world, Game game, int canvasWidth, int canvasHeight,
                               double left, double top, double width, double height) {
        updateFields(graphics, world, game, canvasWidth, canvasHeight, left, top, width, height);
        if (listener != null) listener.draw(graphics, this, false);
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

    private void drawLine(double x1, double y1, double x2, double y2) {
        Point2I lineBegin = toCanvasPosition(x1, y1);
        Point2I lineEnd = toCanvasPosition(x2, y2);

        graphics.drawLine(lineBegin.getX(), lineBegin.getY(), lineEnd.getX(), lineEnd.getY());
    }

    private void fillCircle(double centerX, double centerY, double radius) {
        Point2I topLeft = toCanvasPosition(centerX - radius, centerY - radius);
        Point2I size = toCanvasOffset(2.0D * radius, 2.0D * radius);

        graphics.fillOval(topLeft.getX(), topLeft.getY(), size.getX(), size.getY());
    }

    private void drawCircle(double centerX, double centerY, double radius) {
        Point2I topLeft = toCanvasPosition(centerX - radius, centerY - radius);
        Point2I size = toCanvasOffset(2.0D * radius, 2.0D * radius);

        graphics.drawOval(topLeft.getX(), topLeft.getY(), size.getX(), size.getY());
    }
    
    private void showText(double X, double Y, String text)
    {
    	Point2I position = toCanvasPosition(X, Y);
    	graphics.drawString(text, position.getX(), position.getY());
    }

    private void fillArc(double centerX, double centerY, double radius, int startAngle, int arcAngle) {
        Point2I topLeft = toCanvasPosition(centerX - radius, centerY - radius);
        Point2I size = toCanvasOffset(2.0D * radius, 2.0D * radius);

        graphics.fillArc(topLeft.getX(), topLeft.getY(), size.getX(), size.getY(), startAngle, arcAngle);
    }

    private void drawArc(double centerX, double centerY, double radius, int startAngle, int arcAngle) {
        Point2I topLeft = toCanvasPosition(centerX - radius, centerY - radius);
        Point2I size = toCanvasOffset(2.0D * radius, 2.0D * radius);

        graphics.drawArc(topLeft.getX(), topLeft.getY(), size.getX(), size.getY(), startAngle, arcAngle);
    }

    private void fillRect(double left, double top, double width, double height) {
        Point2I topLeft = toCanvasPosition(left, top);
        Point2I size = toCanvasOffset(width, height);

        graphics.fillRect(topLeft.getX(), topLeft.getY(), size.getX(), size.getY());
    }

    private void drawRect(double left, double top, double width, double height) {
        Point2I topLeft = toCanvasPosition(left, top);
        Point2I size = toCanvasOffset(width, height);

        graphics.drawRect(topLeft.getX(), topLeft.getY(), size.getX(), size.getY());
    }

    private void drawPolygon(Point2D... points) {
        int pointCount = points.length;

        for (int pointIndex = 1; pointIndex < pointCount; ++pointIndex) {
            Point2D pointA = points[pointIndex];
            Point2D pointB = points[pointIndex - 1];
            drawLine(pointA.getX(), pointA.getY(), pointB.getX(), pointB.getY());
        }

        Point2D pointA = points[0];
        Point2D pointB = points[pointCount - 1];
        drawLine(pointA.getX(), pointA.getY(), pointB.getX(), pointB.getY());
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
