# What it is
This is a plugin for Russian AI Cup local runner that can be controlled by the strategy a player is developing.
Plugin is based on the source that was provided by AI Cup committee.

# How to control
Plugin is controlled by the property file named `visualizer-plugin.properties` placed in the same directory where `.properties` file which is used by local runner is stored.

Currently there is only one value that plugin will honor, `plugin-port-number`, which is the port which plugin listens for incoming connections. Default value is `13579`.

# How to use
Plugin starts a server thread that accepts **only one** connection to its port number.
Then it starts communicating with other party using line-level text protocol.

Currently known commands are:
* `begin pre` / `begin post` - start queueing commands to be displayed either before or after main drawing
* `end pre` / `end post` - mark either "pre" or "post" queue of commands as ready to be displayed
* `circle x0 y0 r0 <color>` - draw a circle at (x0, y0) with radius r0 and color `color`
* `rect x1 y1 x2 y2 <color>` - draw a rect with corners at (x1, y1) to (x2, y2) with color `color`
* `line x1 y1 x2 y2 <color>` - draw a line from (x1, y1) to (x2, y2) with color `color`
* `text x0 y0 msg <color>` - show `msg` at coordinates (x0, y0) with color `color`

Color `<color>` is actually an `r g b` triple of floats where `0.0 0.0 0.0` will be black and `1.0 1.0 1.0` will be white.

# How strategy can use it
Well, this is actually up to the user... currently there is very simple debug client implemented in Python provided.
