import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Formatter;
import java.util.Locale;

public class VisualClient {


    Socket          socket;
    OutputStream    outputStream;
    InputStream     inputStream;
    BufferedReader  reader;
    final String    DEFAULT_HOST = "127.0.0.1";
    final int       DEFAULT_PORT = 13579;//13579

    public VisualClient() {
        Locale.setDefault(new Locale("en", "US"));
        try {
            socket = new Socket(DEFAULT_HOST, DEFAULT_PORT);
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public VisualClient(String host, int port) {
        Locale.setDefault(new Locale("en", "US"));
        try {
            socket = new Socket(host, port);
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendCommand(String command) {
        try {
            outputStream.write((command + System.lineSeparator()).getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * start queueing commands to be displayed either before main drawing
     */
    public void beginPre() {
        sendCommand("begin pre");
    }

    /**
     * start queueing commands to be displayed either after main drawing
     */
    public void beginPost() {
        sendCommand("begin post");
    }

    /**
     * start queueing commands to be displayed on the absolute coordinates
     */
    public void beginAbs() {
        sendCommand("begin abs");
    }

    /**
     * mark either "pre" queue of commands as ready to be displayed
     */
    public void endPre() {
        sendCommand("end pre");
    }

    /**
     * mark either "post" queue of commands as ready to be displayed
     */
    public void endPost() {
        sendCommand("end post");
    }

    /**
     * mark either "abs" queue of commands as ready to be displayed
     */
    public void endAbs() {
        sendCommand("end abs");
    }

    /**
     * draw a circle at (x, y) with radius r and color color
     */
    public void circle(double x, double y, double r, Color color) {
        Formatter f = new Formatter();
        sendCommand(f.format("circle %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f", x, y, r, (float) color.getRed()/255, (float) color.getGreen()/255, (float) color.getBlue()/255).toString());
    }

    /**
     * draw a filled circle at (x, y) with radius r and color color
     */
    public void fillCircle(double x, double y, double r, Color color) {
        Formatter f = new Formatter();
        sendCommand(f.format("fill_circle %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f", x, y, r, (float) color.getRed()/255, (float) color.getGreen()/255, (float) color.getBlue()/255).toString());
    }

    /**
     * draw a rect with corners at (x, y) to (x, y) with color color
     */
    public void rect(double x1, double y1, double x2, double y2, Color color) {
        Formatter f = new Formatter();
        sendCommand(f.format("rect %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f", x1, y1, x2, y2, (float) color.getRed()/255, (float) color.getGreen()/255, (float) color.getBlue()/255).toString());
    }

    /**
     * draw a filled rect with corners at (x1, y1) to (x2, y2) with color color
     */
    public void fillRect(double x1, double y1, double x2, double y2, Color color) {
        Formatter f = new Formatter();
        sendCommand(f.format("fill_rect %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f", x1, y1, x2, y2, (float) color.getRed()/255, (float) color.getGreen()/255, (float) color.getBlue()/255).toString());
    }

    /**
     * draw a line from (x1, y1) to (x2, y2) with color color
     */
    public void line(double x1, double y1, double x2, double y2, Color color) {
        Formatter f = new Formatter();
        sendCommand(f.format("line %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f", x1, y1, x2, y2, (float) color.getRed()/255, (float) color.getGreen()/255, (float) color.getBlue()/255).toString());
    }

    /**
     * show msg at coordinates (x, y) with color color
     */
    public void text(double x, double y, String msg, Color color) {
        Formatter f = new Formatter();
        sendCommand(f.format("text %1.1f %1.1f %s %1.1f %1.1f %1.1f", x, y, msg, (float) color.getRed()/255, (float) color.getGreen()/255, (float) color.getBlue()/255).toString());
    }

    public void stop() {
        try {
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * synchronizes local-runner with the render commands from bot, call AFTER you have sent
     * render commands for tick=tickIndex
     */
    public void sync(int tickIndex) {
        try {
            while(true) {
                String line = reader.readLine();
                if (line.startsWith("sync ")) {
                    int tick = Integer.parseInt(line.substring(5).trim());
                    sendCommand("ack");
                    if (tick >= tickIndex) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
