
<h1 align="center">
  <p>peyajArenaReset</p>
  <img width=20% src="https://i.imgur.com/vVXzVAR.png" alt="peyajArenaReset Banner">
</h1>

<h4 align="center">A light-weight arena resetter Minecraft Plugin for PaperMC.</h4>
<hr>

<p align="center">
  <a href="#about">About</a>
  •
  <a href="#features">Features</a>
  •
  <a href="#dependencies">Dependencies</a>
  •
  <a href="#installation-guide">Installation Guide</a>
  •
  <a href="#permissions">Permissions</a>
  •
  <a href="#commands">Commands</a>
  •
  <a href="#support">Support</a>
  •
  <a href="#contributions">Contributions</a>
  •
  <a href="#license">License</a>
</p>

---

> [!Note]
>
> #### ***peyajArenaReset requires FastAsyncWorldEdit to operate!***

## About

peyajArenaReset is a versatile Minecraft plugin (originally a fork of AreaResetterPro by lgndluke, now thoroughly updated and maintained by peyaj) which enables you to create, manage and reset predefined areas of your Minecraft server. It's built to support multiple worlds and offers a in-game GUI for easy administration.

## Features

- **Efficient Area Resets**: Reseting an Area is done asynchronously, so your servers main-thread can focus on handling other important tasks!
- **Simplicity**: The Plugin is designed to work straight out of the box.
- **Scheduled Resets**: peyajArenaReset allows for automated resets. This feature can be disabled in the 'config.yml' file.
- **Multi-World Support**: Operating across different worlds of your server is no problem.
- **Customization**: The Plugin allows for customization of nearly every message you will ever receive.

## Dependencies

- `FastAsyncWorldEdit`: Make sure to have the latest version of FastAsyncWorldEdit installed on your Minecraft server. Otherwise peyajArenaReset will not be able to function!
- `PlaceholderAPI`: Not required! Allows to use placeholders to show how much time until an area is reset the next time. Usage: %peyajarenareset_[areaName]%

## Installation Guide

1. Download peyajArenaReset from [SpigotMC](https://www.spigotmc.org/resources/peyajarenareset.109372/).
2. Place the downloaded file in your server's `plugins` folder.
3. Restart your server to load the plugin.

## Permissions

- `peyajarenareset.reload`: Allows players to reload the plugins configuration files.
- `peyajarenareset.tool`: Allows players to obtain and use the peyajArenaReset Tool.
- `peyajarenareset.getpos`: Allows players to receive the currently set positions.
- `peyajarenareset.setspawnpoint`: Allows players to set the spawn-point.
- `peyajarenareset.getspawnpoint`: Allows players to receive the currently set spawn-point.
- `peyajarenareset.create`: Allows players to create an new area object.
- `peyajarenareset.remove`: Allows players to remove an area object.
- `peyajarenareset.reset`: Allows players to reset an area.
- `peyajarenareset.menu`: Allows players to use the plugins menu.
- `peyajarenareset.help`: Allows players to used the help command.

## Commands

- `/par reload`: Reloads the configuration files.
- `/par tool`: Provides the caller with the peyajArenaReset Tool.
- `/par getpos`: Provides the player with the currently set positions.
- `/par setspawnpoint`: Will set the spawn-point to the players location.
- `/par getspawnpoint`: Provides the player with the currently set spawn-point.
- `/par create [AreaName]`: Will create a new area object with the corresponding name.
- `/par remove [AreaName]`: Will delete the corresponding area object, if it exists.
- `/par reset [AreaName]`: Will reset the corresponding area object, if it exists.
- `/par menu`: Opens the plugins GUI.
- `/par help`: Provides players with a simple help text.

## Support

Join my [Discord Server](https://discord.gg/QNz9MdnmGK) for assistance, suggestions, or discussions regarding peyajArenaReset.

## Contributions

### How to Contribute
1. Fork and star the repository.
2. Create a branch for your changes.
3. Commit and push your changes.
4. Submit a pull request with a clear description of your improvements.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

<div align="center"> 
<a href='https://www.paypal.com/paypalme/peyaj' target='_blank'><img height='50' src='https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/PayPal.svg/2560px-PayPal.svg.png' alt='Support Me via PayPal'/></a>
</div>
