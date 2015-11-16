'''
Python client for visualizer plugin for Russian AI Cup
'''

import socket
import collections

Color = collections.namedtuple('Color', 'r g b')

class DebugClient(object):
    '''
    Main class for controlling the plugin
    '''
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
        self.convert_tile_coords = None

    def pre(self):
        '''
        Method to create a pre-drawing context, that is, to draw things that should be drawn
        *before* the field is drawn by local runner (i.e. they will appear "beneath" the field)
        '''
        assert self.mode == self.MODE_UNKNOWN
        self.mode = self.MODE_PRE
        return self

    def post(self):
        '''
        Method to create a post-drawing context, that is, to draw things that should be drawn
        *after* the field is drawn by local runner (i.e. they will appear "above" the field)
        '''
        assert self.mode == self.MODE_UNKNOWN
        self.mode = self.MODE_POST
        return self

    def __enter__(self):
        self.start()
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        self.stop()

    def start(self):
        '''
        Starts sending messages to specified queue (pre- or post-).
        Note: previous value in the queue will be cleaned up on the server
        '''
        assert self.mode in self.BEGINS
        self.socket.sendall(self.BEGINS[self.mode])

    def stop(self):
        '''
        Stops sendings messages to the queue, so server can draw it
        '''
        assert self.mode in self.ENDS
        self.socket.sendall(self.ENDS[self.mode])
        self.mode = self.MODE_UNKNOWN

    @staticmethod
    def __make_color(color): #pylint: disable=missing-docstring
        if isinstance(color, Color):
            return color
        if isinstance(color, (tuple, list)) and len(color) >= 3:
            return Color(r=color[0], g=color[1], b=color[2])
        return color

    def use_tile_coords(self, game=None):
        '''
        Turns on or off coordinates convertion from tiles to absolute; to turn convertion on
        pass actual in game object, to turn off pass None
        '''
        if game:
            self.convert_tile_coords = game.track_tile_size
        else:
            self.convert_tile_coords = None

    def __convert_coords(self, *coords): #pylint: disable=missing-docstring
        if self.convert_tile_coords is not None:
            return tuple(coord * self.convert_tile_coords for coord in coords)
        return coords

    def __send_command(self, cmd, color, args, pattern=None): #pylint: disable=missing-docstring
        assert self.mode != self.MODE_UNKNOWN
        color = self.__make_color(color)
        if not pattern:
            pattern = '%s' + (' %f' * len(args)) + ' %f %f %f\n'
        self.socket.sendall(pattern % ((cmd,) + args + color))

    def circle(self, x0, y0, r0, color): #pylint: disable=invalid-name
        '''
        Draws a non-filled circle at (x0, y0) with radius "r0" and color "color"
        '''
        self.__send_command('circle', color, args=self.__convert_coords(x0, y0, r0))

    def fill_circle(self, x0, y0, r0, color): #pylint: disable=invalid-name
        '''
        Draws a filled circle at (x0, y0) with radius "r0" and color "color"
        '''
        self.__send_command('fill_circle', color, args=self.__convert_coords(x0, y0, r0))

    def rect(self, x0, y0, x1, y1, color): #pylint: disable=invalid-name, too-many-arguments
        '''
        Draws a non-filled rect with top-left at (x0, y0) and bottom-right at (x1, y1)
        with color "color"
        '''
        self.__send_command('rect', color, args=self.__convert_coords(x0, y0, x1, y1))

    def fill_rect(self, x0, y0, x1, y1, color): #pylint: disable=invalid-name, too-many-arguments
        '''
        Draws a filled rect with top-left at (x0, y0) and bottom-right at (x1, y1)
        with color "color"
        '''
        self.__send_command('fill_rect', color, args=self.__convert_coords(x0, y0, x1, y1))

    def line(self, x0, y0, x1, y1, color): #pylint: disable=invalid-name, too-many-arguments
        '''
        Draws a line from (x0, y0) to (x1, y1) with color "color"
        '''
        self.__send_command('line', color, args=self.__convert_coords(x0, y0, x1, y1))

    def text(self, x0, y0, msg, color): #pylint: disable=invalid-name
        '''
        Shows a text message "msg" at (x0, y0) with color "color"
        '''
        self.__send_command('text', color, args=self.__convert_coords(x0, y0) + (msg,),
                            pattern='%s %f %f %s %f %f %f\n')
