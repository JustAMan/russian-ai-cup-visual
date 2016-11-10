from model.ActionType import ActionType
from model.Game import Game
from model.Move import Move
from model.Wizard import Wizard
from model.World import World

try:
    from debug_client import DebugClient
except:
    debug = None
else:
    debug = DebugClient()

class MyStrategy:
    def move(self, me, world, game, move):
        """
        @type me: Wizard
        @type world: World
        @type game: Game
        @type move: Move
        """
        move.speed = game.wizard_forward_speed
        move.strafe_speed = game.wizard_strafe_speed
        move.turn = game.wizard_max_turn_angle
        move.action = ActionType.MAGIC_MISSILE

        if debug:
            with debug.post() as dbg:
                for w in world.wizards:
                    dbg.text(w.x, w.y, '%s (%s)' % ([player.name for player in world.players if player.id == w.owner_player_id][0], w.level), (0, 1, 0))
