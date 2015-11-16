import socket

class DebugClient:
    DEFAULT_HOST = '127.0.0.1'
    DEFAULT_PORT = 13579
    def __init__(self, host=None, port=None):
        self.socket = socket.socket()
        self.socket.setsockopt(socket.IPPROTO_TCP, socket.TCP_NODELAY, True)
        self.socket.connect((host or self.DEFAULT_HOST, port or self.DEFAULT_PORT))

    def try_me(self, world, game):
        self.socket.sendall('begin pre\n')
        self.socket.sendall('circle 100.0 100.0 50.0 1.0 0.0 0.0\n')
        self.socket.sendall('end pre\n')

        self.socket.sendall('begin post\n')
        self.socket.sendall('circle %f %f 50.0 0.0 1.0 0.0\n' % \
                (game.world_width * game.track_tile_size - 100,
                 game.world_height * game.track_tile_size - 100))
        self.socket.sendall('end post\n')
