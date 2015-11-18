using System.IO;
using System.Net.Sockets;
using System.Text;

namespace Com.CodeGame.CodeRacing2015.DevKit.CSharpCgdk
{
    public sealed class VisualClient
    {
        private const int BUFFER_SIZE_BYTES = 1 << 20;

        private readonly TcpClient _client;
        private readonly BinaryWriter _writer;

        public VisualClient(string host, int port)
        {
            _client = new TcpClient(host, port)
            {
                SendBufferSize = BUFFER_SIZE_BYTES,
                ReceiveBufferSize = BUFFER_SIZE_BYTES,
                NoDelay = true
            };

            _writer = new BinaryWriter(_client.GetStream());
        }

        //---------------------------------------------------------------------
        // Public
        //---------------------------------------------------------------------

        /// <summary>
        /// Start queueing commands to be displayed either before main drawing
        /// </summary>
        public void BeginPre()
        {
            SendCommand("begin pre");
        }

        /// <summary>
        /// Start queueing commands to be displayed either after main drawing
        /// </summary>
        public void BeginPost()
        {
            SendCommand("begin post");
        }

        /// <summary>
        /// Mark either "pre" queue of commands as ready to be displayed
        /// </summary>
        public void EndPre()
        {
            SendCommand("end pre");
        }

        /// <summary>
        /// Mark either "post" queue of commands as ready to be displayed
        /// </summary>
        public void EndPost()
        {
            SendCommand("end post");
        }

        /// <summary>
        /// Draw a circle at (x, y) with radius r and color color
        /// </summary>
        public void Circle(double x, double y, float radius, float r = 0f, float g = 0f, float b = 0f)
        {
            SendCommand($"circle {x} {y} {radius} {r} {g} {b}");
        }

        /// <summary>
        /// Draw a filled circle at (x, y) with radius r and color color
        /// </summary>
        public void FillCircle(double x, double y, float radius, float r = 0f, float g = 0f, float b = 0f)
        {
            SendCommand($"fill_circle {x} {y} {radius} {r} {g} {b}");
        }

        /// <summary>
        /// Draw a rect with corners at (x, y) to (x, y) with color color
        /// </summary>
        public void Rect(double x1, double y1, double x2, double y2, float r = 0f, float g = 0f, float b = 0f)
        {
            SendCommand($"rect {x1} {y1} {x2} {y2} {r} {g} {b}");
        }

        /// <summary>
        /// Draw a filled rect with corners at (x1, y1) to (x2, y2) with color color
        /// </summary>
        public void FillRect(double x1, double y1, double x2, double y2, float r = 0f, float g = 0f, float b = 0f)
        {
            SendCommand($"fill_rect {x1} {y1} {x2} {y2} {r} {g} {b}");
        }

        /// <summary>
        /// Draw a line from (x1, y1) to (x2, y2) with color color
        /// </summary>
        public void Line(double x1, double y1, double x2, double y2, float r = 0f, float g = 0f, float b = 0f)
        {
            SendCommand($"line {x1} {y1} {x2} {y2} {r} {g} {b}");
        }

        /// <summary>
        /// Show msg at coordinates (x, y) with color color
        /// </summary>
        public void Text(double x, double y, string msg, float r = 0f, float g = 0f, float b = 0f)
        {
            SendCommand($"text {x} {y} {msg} {r} {g} {b}");
        }

        //---------------------------------------------------------------------
        // Helpers
        //---------------------------------------------------------------------

        private void SendCommand(string command)
        {
            WriteString(command + "\n");
            _writer.Flush();
        }

        private void WriteString(string value)
        {
            if (value == null)
            {
                WriteInt(-1);
                return;
            }

            var bytes = Encoding.UTF8.GetBytes(value);
            _writer.Write(bytes, 0, bytes.Length);
        }

        private void WriteInt(int value)
        {
            _writer.Write(value);
        }

        public void Close()
        {
            _client.Close();
        }
    }
}