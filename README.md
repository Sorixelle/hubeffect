HubEffect
===
Bukkit plugin that applies potion effects to players in a world or by using items.

### Installation
Get the VoidCore plugin from [here](http://dev.bukkit.org/bukkit-plugins/voidcore/), then put both in your server's `plugins` directory.

### What is it?
HubEffect gives players status effects when they are in a certain world or then they use an item that they can receive on join. The plugin is completely configurable, and there are virtually no limits to the amount of effect sets one can have on a server.

### Configuration
Here's an example of a configuration file (actually, it's the default configuration):

```yaml
items:
  item1:
    offItem:
      type: "sulphur"
      name: "&3&lHub Effects: &c&l&n&oOFF"
    onItem:
      type: "sugar"
      name: "&3&lHub Effects: &a&l&n&oON"
    invincible: true
    doublejump: true
    effects:
      - speed|5
      - jump|5
    enablemsg: "&4&l[&2&lHub&a&lSpeed&4&l] &a&lEnabled effects!"
    disablemsg: "&4&l[&2&lHub&a&lSpeed&4&l] &c&lDisabled effects!"
    world: world_nether
```

`items`: The root value. Don't delete this, or the plugin wont work.
`item1`: The container for one item. This can be named anything you want, and there can be as many of these as you want.
`offItem`: Information for an item when the effect set is in the "off" state.
`onItem`: Information for an item when the effect set is in the "on" state.
`type`: The type of item. Case-insensitive. A list of possible items can be found [here](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html).
`name`: The name of the item. Colour codes can be used. A list of possible colour codes can be found [here](http://ess.khhq.net/mc/).
`invincible`: `true` if you want the effect set to grant invincibility; `false` otherwise.
`doublejump`: `true` if you want the effect set to grant double jumping powers, `false` otherwise.
`effects`: A list of effects for the set to apply in the format `- <effect_name>|<strength>`. A list of valid effect names can be found [here](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/potion/PotionEffectType.html).
`enablemsg`: A message to display to the player when they enable the effects.
`disablemsg`: A message to display to the player when they disable the effects.
`world`: A world for this effect set to always be applied in.

### Commands and Permissions
###### Commands
`/hubeffectgive <item_name>` OR `/hegive <item_name>`: Gives a item from the config to the player who used it. Requires the `hubeffect.admin` permission.

###### Permissions
`hubeffect.admin`: Access to all admin commands.
`hubeffect.<item_name>`: Gives the player with the permission the corresponding item on join.

### Contributing
If you have a contribution to make to the plugin, just fork it and make a pull request. I'll take a look at it and merge it if I like it. Be aware, however, that you are agreeing to the Apache 2.0 Licence when you contribute!
If you have a bug to report, put it in the GitHub issue tracker (not the BukkitDev page comments) and I'll get back to you on it as soon as possible.

### License
This plugin is licensed under the Apache 2.0 Licence, which can be found [here](http://www.apache.org/licenses/LICENSE-2.0.html).

### Credits
* stinkysquidkid for requesting this plugin on the Bukkit Forums (post can be found [here](https://bukkit.org/threads/hubspeed.415086/))
