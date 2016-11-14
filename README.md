# What it is
This is a plugin for Russian AI Cup local runner that can be controlled by the strategy a player is developing.
Plugin is based on the source that was provided by AI Cup committee.

# How to control
Plugin is controlled by the property file named `visualizer-plugin.properties` placed in the same directory where `.properties` file which is used by local runner is stored.
Properties are:
* `plugin-port-number` - port which plugin listens for incoming connections. Default value is `13579`.
* `plugin-do-tick-sync` - whether to do a sync between local runner and debug client, see "re-playing games" for more.

# How to use
Plugin starts a server thread that accepts **only one** connection to its port number.
Then it starts communicating with other party using line-level text protocol.

Currently known commands are:
* `begin pre` / `begin post` - start queueing commands to be displayed either before or after main drawing
* `end pre` / `end post` - mark either "pre" or "post" queue of commands as ready to be displayed
* `circle x0 y0 r0 <color>` - draw a circle at (x0, y0) with radius r0 and color `color`
* `fill_circle x0 y0 r0 <color>` - draw a filled circle at (x0, y0) with radius r0 and color `color`
* `rect x1 y1 x2 y2 <color>` - draw a rect with corners at (x1, y1) to (x2, y2) with color `color`
* `fill_rect x1 y1 x2 y2 <color>` - draw a filled rect with corners at (x1, y1) to (x2, y2) with color `color`
* `line x1 y1 x2 y2 <color>` - draw a line from (x1, y1) to (x2, y2) with color `color`
* `text x0 y0 msg <color>` - show `msg` at coordinates (x0, y0) with color `color`
* `arc x y r startAngle arcAngle <color>` - draw an arc with center at (x, y) with radius r, begins at startAngle and extends for arcAngle. All angles are in radians
* `fill_arc x y r startAngle arcAngle <color>` - draw a sector with center at (x, y) with radius r, begins at startAngle and extends for arcAngle. All angles are in radians

Color `<color>` is actually an `r g b` triple of floats where `0.0 0.0 0.0` will be black and `1.0 1.0 1.0` will be white.

# Re-playing games from russianaicup.ru with visual debug
## NOTE: currently it is untested if it works with replays from AI cup 2016

To support that your debug client has to support syncing model.
It is currently done as follows:
1. Each tick plugin sends to the client `SYNC <TICK_NUMBER>` line and waits for `ACK` from client
2. Debug client should respond with `ACK` as soon as the strategy using this client has finished computing `<TICK_NUMBER>` tick

This mode has to be enabled in `visualizer-plugin.properties` with setting `plugin-do-tick-sync` to either `true` or to `auto`.
Auto mode will detect replay mode by checking names of players and assuming that if there is **NO** `MyStrategy` then it is a replay and it requires sync mode.

# How strategy can use it
Well, this is actually up to the user... currently there is very simple debug client implemented in Python provided.
