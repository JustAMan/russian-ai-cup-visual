'''
Python client for visualizer plugin for Russian AI Cup
'''

import socket
import collections
import time
import errno

try:
    xrange
except NameError:
    # python 3-to-2 compatibility
    xrange = range #pylint: disable=redefined-builtin, invalid-name

Color = collections.namedtuple('Color', 'r g b')

class DebugClient(object):
    '''
    Main class for controlling the plugin
    '''
    DEFAULT_HOST = '127.0.0.1'
    DEFAULT_PORT = 13579

    MODE_PRE, MODE_POST, MODE_ABS, MODE_UNKNOWN = 'pre post abs unknown'.split()
    BEGINS = {MODE_PRE: 'begin pre\n', MODE_POST: 'begin post\n', MODE_ABS: 'begin abs\n'}
    ENDS = {MODE_PRE: 'end pre\n', MODE_POST: 'end post\n', MODE_ABS: 'end abs\n'}
    def __init__(self, host=None, port=None):
        self.socket = socket.socket()
        self.socket.setsockopt(socket.IPPROTO_TCP, socket.TCP_NODELAY, True)
        for _ in xrange(20):
            try:
                self.socket.connect((host or self.DEFAULT_HOST, port or self.DEFAULT_PORT))
            except socket.error as ex:
                if ex.errno == errno.WSAECONNREFUSED:
                    time.sleep(0.1)
                    continue
                raise
            else:
                break

        self.mode = self.MODE_UNKNOWN
        self.last_sync_tick = None
        self.reader = self.__buffered_reader()

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

    def abs(self):
        '''
        Method to create an absolutely positioned drawing context, that is, to draw things that
        should be drawn *after* the field is drawn by local runner (i.e. they will appear
        "above" the field) and coordinates used are based on the view window, not the field
        '''
        assert self.mode == self.MODE_UNKNOWN
        self.mode = self.MODE_ABS
        return self

    def __enter__(self):
        self.start()
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        self.stop()

    def start(self):
        '''
        Starts sending messages to specified queue.
        Note: previous value in the queue will be cleaned up on the server
        '''
        assert self.mode in self.BEGINS
        self.socket.sendall((self.BEGINS[self.mode]).encode(encoding='UTF-8'))

    def stop(self):
        '''
        Stops sendings messages to the queue, so server can draw it
        '''
        assert self.mode in self.ENDS
        self.socket.sendall((self.ENDS[self.mode]).encode(encoding='UTF-8'))
        self.mode = self.MODE_UNKNOWN

    @staticmethod
    def __make_color(color): #pylint: disable=missing-docstring
        if isinstance(color, Color):
            return color
        if isinstance(color, (tuple, list)) and len(color) >= 3:
            return Color(r=color[0], g=color[1], b=color[2])
        return color

    def __send_command(self, cmd, color, args, pattern=None): #pylint: disable=missing-docstring
        assert self.mode != self.MODE_UNKNOWN
        color = self.__make_color(color)
        if not pattern:
            pattern = '%s' + (' %f' * len(args)) + ' %f %f %f\n'
        self.socket.sendall((pattern % ((cmd, ) + args + color)).encode(encoding='UTF-8'))

    def circle(self, x0, y0, r0, color): #pylint: disable=invalid-name
        '''
        Draws a non-filled circle at (x0, y0) with radius "r0" and color "color"
        '''
        self.__send_command('circle', color, args=(x0, y0, r0))

    def fill_circle(self, x0, y0, r0, color): #pylint: disable=invalid-name
        '''
        Draws a filled circle at (x0, y0) with radius "r0" and color "color"
        '''
        self.__send_command('fill_circle', color, args=(x0, y0, r0))

    def arc(self, x0, y0, r0, start_angle, arc_angle, color): #pylint: disable=invalid-name, too-many-arguments
        '''
        Draws an arc at (x0, y0) with radius "r0" and color "color", starting at "start_angle"
        and extending for "arc_angle" radians
        '''
        self.__send_command('arc', color, args=(x0, y0, r0, start_angle, arc_angle))

    def fill_arc(self, x0, y0, r0, start_angle, arc_angle, color): #pylint: disable=invalid-name, too-many-arguments
        '''
        Draws a filled arc (sector) at (x0, y0) with radius "r0" and color "color", starting at
        "start_angle" and extending for "arc_angle" radians
        '''
        self.__send_command('fill_arc', color, args=(x0, y0, r0, start_angle, arc_angle))

    def rect(self, x0, y0, x1, y1, color): #pylint: disable=invalid-name, too-many-arguments
        '''
        Draws a non-filled rect with top-left at (x0, y0) and bottom-right at (x1, y1)
        with color "color"
        '''
        self.__send_command('rect', color, args=(x0, y0, x1, y1))

    def fill_rect(self, x0, y0, x1, y1, color): #pylint: disable=invalid-name, too-many-arguments
        '''
        Draws a filled rect with top-left at (x0, y0) and bottom-right at (x1, y1)
        with color "color"
        '''
        self.__send_command('fill_rect', color, args=(x0, y0, x1, y1))

    def line(self, x0, y0, x1, y1, color): #pylint: disable=invalid-name, too-many-arguments
        '''
        Draws a line from (x0, y0) to (x1, y1) with color "color"
        '''
        self.__send_command('line', color, args=(x0, y0, x1, y1))

    def text(self, x0, y0, msg, color): #pylint: disable=invalid-name
        '''
        Shows a text message "msg" at (x0, y0) with color "color"
        '''
        self.__send_command('text', color, args=(x0, y0, msg),
                            pattern='%s %f %f %s %f %f %f\n')

    def is_replay(self, world):
        '''
        Method to check if given world corresponds to a replay from russianaicup.ru
        '''
        try:
            return self.__is_replay
        except AttributeError:
            result = True
            for player in world.players:
                if player.name.startswith('MyStrategy'):
                    result = False
                    break
            self.__is_replay = result #pylint: disable=attribute-defined-outside-init
            return result

    def __buffered_reader(self):
        '''
        Internal generator that implements buffered reads from the socket
        '''
        buf = ''
        while True:
            if '\n' in buf:
                line, buf = buf.split('\n', 1)
                yield line
            else:
                try:
                    buf += self.socket.recv(4096)
                except socket.error:
                    return

    def syncronize(self, world):
        '''
        Method to syncronize with the debug server if playing from a replay;
        waits for "sync event" and sends acknowledgement
        '''
        if not self.is_replay(world):
            return
        if self.last_sync_tick is None or world.tick_index > self.last_sync_tick:
            if self.last_sync_tick is not None:
                # server waits for an acknowledgement from us
                self.socket.sendall('ack\n')

            # get a new sync tick
            line = next(self.reader)
            if line.startswith('sync '):
                self.last_sync_tick = int(line.split()[1].strip())
