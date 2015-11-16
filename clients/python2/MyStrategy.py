from model.Car import Car
from model.Game import Game
from model.Move import Move
from model.World import World

try:
    from debug_client import Color
except ImportError:
    pass

class MyStrategy:
    def __init__(self):
        try:
            from debug_client import DebugClient
        except ImportError: # no debug module, maybe running on the russianaicup.ru server
            self.debug = None
        else:
            self.debug = DebugClient()
            self.GREEN = Color(r=0.0, g=1.0, b=0.0)

    def move(self, me, world, game, move):
        """
        @type me: Car
        @type world: World
        @type game: Game
        @type move: Move
        """
        if self.debug:
            with self.debug.pre() as dbg:
                dbg.circle(100.0, 100.0, 50.0, (1.0, 0.0, 0.0))
            with self.debug.post() as dbg:
                dbg.circle(game.world_width * game.track_tile_size - 100,
                           game.world_height * game.track_tile_size - 100,
                           50.0, self.GREEN)

        move.engine_power = 1.0
        move.throw_projectile = True
        move.spill_oil = True

        if world.tick > game.initial_freeze_duration_ticks:
            move.use_nitro = True
