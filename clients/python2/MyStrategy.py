'''
Main stragegy module
'''

from model.Car import Car #pylint: disable=unused-import
from model.Game import Game #pylint: disable=unused-import
from model.Move import Move #pylint: disable=unused-import
from model.World import World #pylint: disable=unused-import

try:
    from debug_client import Color
except ImportError:
    pass

class MyStrategy: #pylint: disable=old-style-class, too-few-public-methods
    '''
    Main class defining the strategy
    '''
    def __init__(self):
        try:
            from debug_client import DebugClient
        except ImportError: # no debug module, maybe running on the russianaicup.ru server
            self.debug = None
        else:
            self.debug = DebugClient()
            self.green = Color(r=0.0, g=1.0, b=0.0)

    def move(self, me, world, game, move): #pylint: disable=invalid-name
        """
        @type me: Car
        @type world: World
        @type game: Game
        @type move: Move
        """
        if self.debug:
            self.debug.use_tile_coords(game)
            with self.debug.pre() as dbg:
                dbg.fill_circle(100.0, 100.0, 50.0, (1.0, 0.0, 0.0))
                # show the waypoints
                for idx, waypoint in enumerate(world.waypoints, 1):
                    wayX, wayY = waypoint
                    dbg.fill_rect(wayX, wayY, wayX + 1, wayY + 1, (0.9, 0.9, 0.9))
                    dbg.text(wayX + 0.5, wayY + 0.5, idx, (0.2, 0.5, 0.2))

            self.debug.use_tile_coords(False)
            with self.debug.post() as dbg:
                dbg.circle(game.world_width * game.track_tile_size - 100,
                           game.world_height * game.track_tile_size - 100,
                           50.0, self.green)
                dbg.text(me.x, me.y, 'wooo woo', (0.0, 0.0, 1.0))

        move.engine_power = 1.0
        move.throw_projectile = True
        move.spill_oil = True

        if world.tick > game.initial_freeze_duration_ticks:
            move.use_nitro = True
