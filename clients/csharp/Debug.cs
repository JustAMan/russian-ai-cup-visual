using System.Globalization;

namespace Com.CodeGame.CodeWizards2016.DevKit.CSharpCgdk
{
    public static class Debug
    {
#if local
        private static System.Net.Sockets.TcpClient client;
        private static System.IO.StreamWriter writer;

        static Debug()
        {
            Connect("localhost", 13579);
        }
#endif

        public static void Connect(string host, int port) {
#if local
            client = new System.Net.Sockets.TcpClient(host, port);
#endif
        }

        public static void Disconnect() {
#if local
            client.Close();
#endif
        }

        private static void SendCommand(string command)
        {
#if local
            if (client != null) {
                if (writer == null) {
                    writer = new System.IO.StreamWriter(client.GetStream(), System.Text.Encoding.ASCII);
                }
                writer.WriteLine(command);
                writer.Flush();
            }
            System.Console.WriteLine(command);
#endif
        }


        public static void BeginPre() {
            SendCommand("begin pre");
        }

        public static void BeginPost() {
            SendCommand("begin post");
        }

        public static void EndPre() {
            SendCommand("end pre");
        }

        public static void EndPost() {
            SendCommand("end post");
        }

        private static string EncodeColor(int color) {
            int red = (color & 0xFF0000) >> 16;
            int green = (color & 0x00FF00) >> 8;
            int blue = color & 0x0000FF;

            return $"{Print((double) red / 256.0)} {Print((double) green / 256.0)} {Print((double) blue / 256.0)}";
        }

        private static string Print(this double val) => val.ToString(CultureInfo.InvariantCulture);

        public static void Circle(double x, double y, double radius, int color = 0x000000) {
            SendCommand($"circle {x.Print()} {y.Print()} {radius.Print()} {EncodeColor(color)}");
        }

        public static void FillCircle(double x, double y, double radius, int color = 0x000000) {
            SendCommand($"fill_circle {x.Print()} {y.Print()} {radius.Print()} {EncodeColor(color)}");
        }

        public static void Rect(double x1, double y1, double x2, double y2, int color = 0x000000) {
            SendCommand($"rect {x1.Print()} {y1.Print()} {x2.Print()} {y2.Print()} {EncodeColor(color)}");
        }

        public static void FillRect(double x1, double y1, double x2, double y2, int color = 0x000000) {
            SendCommand($"fill_rect {x1.Print()} {y1.Print()} {x2.Print()} {y2.Print()} {EncodeColor(color)}");
        }

        public static void Line(double x1, double y1, double x2, double y2, int color = 0x000000) {
            SendCommand($"line {x1.Print()} {y1.Print()} {x2.Print()} {y2.Print()} {EncodeColor(color)}");
        }

        public static void Print(double x, double y, string msg, int color = 0x000000) {
            SendCommand($"text {x.Print()} {y.Print()} {msg} {EncodeColor(color)}");
        }
    }
}
