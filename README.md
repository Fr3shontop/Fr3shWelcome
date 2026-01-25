# Fr3sh-Welcome

A modern, fully customizable welcome message plugin. Greet your players with beautiful, dynamic messages using titles, action bars, boss bars, chat messages, and even sounds!

---

## ✨ Features

- **Server Compatibility**: Works flawlessly on Paper and its forks (like Purpur).
- **Highly Customizable Messages**: Control every aspect of the join and quit messages.
- **Multiple Message Types**:
  - **Chat**: Standard broadcast messages.
  - **Title**: Big text on the player's screen.
  - **Action Bar**: Text above the hotbar.
  - **Boss Bar**: A configurable boss bar at the top of the screen.
- **Custom Sounds**: Play a configurable sound to all players when someone joins or leaves.
- **Advanced Formatting**: Robust support for both modern **MiniMessage** and legacy **&** color codes.
  - HEX colors (`<#ff0000>` or `&#RRGGBB`)
  - Gradients (`<gradient:red:blue>`)
  - Click and hover events.
- **Easy Configuration**: A clean and well-documented `config.yml` file.
- **Reload Command**: Reload the configuration without restarting the server.
- **Optimized**: Lightweight and built on the modern Paper API.

---

## 🔧 Configuration

The `config.yml` is designed to be intuitive. You can enable or disable each message type and customize its content and appearance.

```yaml
# Fr3sh-Welcome Configuration
# You can use MiniMessage format (<red>, <gradient:red:blue>, <#ff0000>)
# AND legacy codes (&c, &l). The plugin will handle both.

messages:
  reload: "<green>Configuration reloaded!"
  no-permission: "<red>You don't have permission!"

join:
  chat:
    enabled: true
    message: "<gradient:#5e4fa2:#f79459>Welcome to the server <yellow>%player%</yellow>!</gradient>"
  sound:
    enabled: true
    # List of all sounds: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html
    name: "ENTITY_PLAYER_LEVELUP"
    volume: 1.0
    pitch: 1.2
  # ... and so on
```

---

## 🎮 Commands & Permissions

| Command      | Description              | Permission       |
|--------------|--------------------------|------------------|
| `/fw reload` | Reloads the config file. | `fr3sh.reload`   |

The `fr3sh.reload` permission is granted to operators by default.

---

## 🚀 Installation

1.  Download the latest version from the [Releases](https://github.com/Fr3shontop/Fr3shWelcome/releases/tag/Release) page.
2.  Place the `.jar` file in your server's `plugins` folder.
3.  Restart or reload your server.
4.  Customize the `config.yml` file in the `plugins/Fr3sh-Welcome` folder to your liking.
5.  Use `/fw reload` to apply the changes.

