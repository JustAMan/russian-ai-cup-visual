import java.net.Socket
import java.util.Locale

object VisualClient {
  object Color {
    val black = Color(0, 0, 0)
    val red = Color(255, 0, 0)
    val green = Color(0, 255, 0)
    val blue = Color(0, 0, 255)
    def apply(r: Int, g: Int, b: Int) = new Color(r / 255, g / 255, b / 255)
  }

  case class Color(r: Float, g: Float, b: Float)
}

class VisualClient(host: String, port: Int) {

  import VisualClient._

  Locale.setDefault(new Locale("en", "US"))
  private val s = new Socket(host, port)
  private val os = s.getOutputStream

  private def sendCommand(command: String): Unit = {
    os.write((command + System.lineSeparator()).getBytes)
    os.flush()
  }

  /**
    * start queueing commands to be displayed either before main drawing
    */
  def beginPre() = sendCommand("begin pre")

  /**
    * start queueing commands to be displayed either after main drawing
    */
  def beginPost() = sendCommand("begin post")

  /**
    * mark either "pre" queue of commands as ready to be displayed
    */
  def endPre() = sendCommand("end pre")

  /**
    * mark either "post" queue of commands as ready to be displayed
    */
  def endPost() = sendCommand("end post")

  /**
    * draw a circle at (x, y) with radius r and color color
    */
  def circle(x: Float, y: Float, r: Float, color: Color = Color.black) = sendCommand(f"circle $x%1.1f $y%1.1f $r%1.1f ${color.r}%1.1f ${color.g}%1.1f ${color.b}%1.1f")

  /**
    * draw a filled circle at (x, y) with radius r and color color
    */
  def fillCircle(x: Float, y: Float, r: Float, color: Color = Color.black) = sendCommand(f"fill_circle $x%1.1f $y%1.1f $r%1.1f ${color.r}%1.1f ${color.g}%1.1f ${color.b}%1.1f")

  /**
    * draw a rect with corners at (x, y) to (x, y) with color color
    */
  def rect(x1: Float, y1: Float, x2: Float, y2: Float, color: Color = Color.black) = sendCommand(f"rect $x1%1.1f $y1%1.1f $x2%1.1f $y2%1.1f ${color.r}%1.1f ${color.g}%1.1f ${color.b}%1.1f")

  /**
    * draw a filled rect with corners at (x1, y1) to (x2, y2) with color color
    */
  def fillRect(x1: Float, y1: Float, x2: Float, y2: Float, color: Color = Color.black) = sendCommand(f"fill_rect $x1%1.1f $y1%1.1f $x2%1.1f $y2%1.1f ${color.r}%1.1f ${color.g}%1.1f ${color.b}%1.1f")

  /**
    * draw a line from (x1, y1) to (x2, y2) with color color
    */
  def line(x1: Float, y1: Float, x2: Float, y2: Float, color: Color = Color.black) = sendCommand(f"line $x1%1.1f $y1%1.1f $x2%1.1f $y2%1.1f ${color.r}%1.1f ${color.g}%1.1f ${color.b}%1.1f")

  /**
    * show msg at coordinates (x, y) with color color
    */
  def text(x: Float, y: Float, msg: String, color: Color = Color.black) = sendCommand(f"text $x%1.1f $y%1.1f $msg ${color.r}%1.1f ${color.g}%1.1f ${color.b}%1.1f")

  def stop() = {
    os.close()
  }
}
