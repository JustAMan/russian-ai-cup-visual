import socket
import collections

Color = collections.namedtuple('Color', 'r g b')

class DebugClient:
    DEFAULT_HOST = '127.0.0.1'
    DEFAULT_PORT = 13579

    MODE_PRE, MODE_POST, MODE_UNKNOWN = 'pre post unknown'.split()
    BEGINS = {MODE_PRE: 'begin pre\n', MODE_POST: 'begin post\n'}
    ENDS = {MODE_PRE: 'end pre\n', MODE_POST: 'end post\n'}
    def __init__(self, host=None, port=None):
        self.socket = socket.socket()
        self.socket.setsockopt(socket.IPPROTO_TCP, socket.TCP_NODELAY, True)
        self.socket.connect((host or self.DEFAULT_HOST, port or self.DEFAULT_PORT))
        self.mode = self.MODE_UNKNOWN

    def pre(self):
        assert self.mode == self.MODE_UNKNOWN
        self.mode = self.MODE_PRE
        return self

    def post(self):
        assert self.mode == self.MODE_UNKNOWN
        self.mode = self.MODE_POST
        return self

    def __enter__(self):
        assert self.mode in self.BEGINS
        self.socket.sendall(self.BEGINS[self.mode])
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        assert self.mode in self.ENDS
        self.socket.sendall(self.ENDS[self.mode])
        self.mode = self.MODE_UNKNOWN

    @staticmethod
    def __make_color(color):
        if isinstance(color, Color):
            return color
        if isinstance(color, (tuple, list)) and len(color) >= 3:
            return Color(r=color[0], g=color[1], b=color[2])
        return color

    def circle(self, x0, y0, r0, color):
        assert self.mode != self.MODE_UNKNOWN
        color = self.__make_color(color)
        self.socket.sendall('circle %f %f %f %f %f %f\n' % (x0, y0, r0,
                                                            color.r, color.g, color.b))
