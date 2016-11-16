import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Formatter;
import java.util.Locale;

public class VisualClient {

    enum TargetQueue {PRE, POST, ABS}

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
     * Сигнализирует о конце текущего цикла отрисовки
     */
    public void drawEnd() { sendCommand("draw_end"); }

    /**
     * draw a circle at (x, y) with radius r and color color
     */
    public void circle(double x, double y, double r, Color color, TargetQueue targetQueue) {
        Formatter f = new Formatter();
        sendCommand(f.format("%s circle %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f", targetQueue.name().toLowerCase(), x, y, r, (float) color.getRed()/255, (float) color.getGreen()/255, (float) color.getBlue()/255).toString());
    }

    /**
     * draw a filled circle at (x, y) with radius r and color color
     */
    public void fillCircle(double x, double y, double r, Color color, TargetQueue targetQueue) {
        Formatter f = new Formatter();
        sendCommand(f.format("%s fill_circle %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f", targetQueue.name().toLowerCase(), x, y, r, (float) color.getRed()/255, (float) color.getGreen()/255, (float) color.getBlue()/255).toString());
    }

    /**
     * draw a rect with corners at (x, y) to (x, y) with color color
     */
    public void rect(double x1, double y1, double x2, double y2, Color color, TargetQueue targetQueue) {
        Formatter f = new Formatter();
        sendCommand(f.format("%s rect %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f", targetQueue.name().toLowerCase(), x1, y1, x2, y2, (float) color.getRed()/255, (float) color.getGreen()/255, (float) color.getBlue()/255).toString());
    }

    /**
     * draw a filled rect with corners at (x1, y1) to (x2, y2) with color color
     */
    public void fillRect(double x1, double y1, double x2, double y2, Color color, TargetQueue targetQueue) {
        Formatter f = new Formatter();
        sendCommand(f.format("%s fill_rect %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f", targetQueue.name().toLowerCase(), x1, y1, x2, y2, (float) color.getRed()/255, (float) color.getGreen()/255, (float) color.getBlue()/255).toString());
    }

    /**
     * draw a arc with center at (centerX, centerY), radius radius and angle arcAngle, started from startAngle with color color, angles in radians
     */
    public void arc(double centerX, double centerY, double radius, double startAngle, double arcAngle, Color color, TargetQueue targetQueue) {
        Formatter f = new Formatter();
        sendCommand(f.format("%s arc %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f", targetQueue.name().toLowerCase(), centerX, centerY, radius, startAngle, arcAngle, (float) color.getRed()/255, (float) color.getGreen()/255, (float) color.getBlue()/255).toString());
    }

    /**
     * draw a filled arc with center at (centerX, centerY), radius radius and angle arcAngle, started from startAngle with color color, angles in radians
     */
    public void fillArc(double centerX, double centerY, double radius, double startAngle, double arcAngle, Color color, TargetQueue targetQueue) {
        Formatter f = new Formatter();
        sendCommand(f.format("%s fill_arc %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f", targetQueue.name().toLowerCase(), centerX, centerY, radius, startAngle, arcAngle, (float) color.getRed()/255, (float) color.getGreen()/255, (float) color.getBlue()/255).toString());
    }

    /**
     * draw a line from (x1, y1) to (x2, y2) with color color
     */
    public void line(double x1, double y1, double x2, double y2, Color color, TargetQueue targetQueue) {
        Formatter f = new Formatter();
        sendCommand(f.format("%s line %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f %1.1f", targetQueue.name().toLowerCase(), x1, y1, x2, y2, (float) color.getRed()/255, (float) color.getGreen()/255, (float) color.getBlue()/255).toString());
    }

    /**
     * show msg at coordinates (x, y) with color color
     */
    public void text(double x, double y, String msg, Color color, TargetQueue targetQueue) {
        Formatter f = new Formatter();
        sendCommand(f.format("%s text %1.1f %1.1f %s %1.1f %1.1f %1.1f", targetQueue.name().toLowerCase(), x, y, msg, (float) color.getRed()/255, (float) color.getGreen()/255, (float) color.getBlue()/255).toString());
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
