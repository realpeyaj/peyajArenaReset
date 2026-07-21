<div align="center">

# peyajArenaReset
**The Ultimate Area Management & Resetting Tool for PaperMC**

[![Paper Supported](https://img.shields.io/badge/Paper-1.21.x%20--%2026.x-blue.svg?style=for-the-badge&logo=paper)](https://papermc.io/)
[![Version](https://img.shields.io/badge/Version-2.1-success.svg?style=for-the-badge)]()

*A modern, asynchronous, and fully-featured fork of AreaResetterPro, now maintained and updated by **peyaj**.*

</div>

---

## About This Fork

**peyajArenaReset** breathes new life into the original concept of AreaResetterPro. It has been completely refactored to seamlessly support **Java 21 to Java 25+**, **Paper 1.21.x to 26.x**, and the modern **FastAsyncWorldEdit** API. 

Designed for performance and simplicity, this plugin allows server administrators to create, manage, and schedule automated resets for specific regions in any world—all without stalling the main server thread.

## Features

-  **Asynchronous Operations:** All area resets are handled off the main thread, ensuring zero lag spikes during scheduled regenerations.
-  **Automated Scheduling:** Set specific time intervals for domains to auto-reset. Perfect for minigames, resource worlds, or PvP arenas.
-  **Fully Customizable:** Every single message, menu title, and item lore is translatable and stylized with modern MiniMessage (Kyori) formatting.
-  **Multi-World Architecture:** Create and manage distinct areas across any dimension or custom world seamlessly.
-  **In-Game GUI:** An intuitive, click-to-manage menu system for handling area settings, timers, and spawn points.

---

## Requirements

To run **peyajArenaReset**, your server must meet the following requirements:

| Requirement | Version | Note |
| :--- | :--- | :--- |
| **Server Software** | PaperMC 1.21+ / 26.1.1+ | Required for asynchronous scheduler API. |
| **Java** | Java 21+ | Required for modern runtime compatibility. |
| **FastAsyncWorldEdit** | Latest | **CRITICAL:** The plugin will not function without FAWE installed. |
| **PlaceholderAPI** | Latest | *Optional.* Enables `%peyajarenareset_[areaName]%` for reset countdowns. |

---

## Installation

1. Ensure **FastAsyncWorldEdit** is installed in your `plugins/` directory.
2. Download `peyajArenaReset-2.1.jar`.
3. Drop the `.jar` into your server's `plugins/` folder.
4. Restart your server.
5. Configure the generated `config.yml` and `messages.yml` files to your liking!

---

## Commands & Permissions

All commands are routed cleanly through the `/par` root command. 

| Command | Permission | Description |
| :--- | :--- | :--- |
| `/par help` | `peyajarenareset.help` | Displays the help menu in chat. |
| `/par menu` | `peyajarenareset.menu` | Opens the interactive management GUI. |
| `/par create <name>` | `peyajarenareset.create` | Creates a new area object with the given name. |
| `/par remove <name>` | `peyajarenareset.remove` | Deletes the specified area object. |
| `/par reset <name>` | `peyajarenareset.reset` | Force-resets the specified area instantly. |
| `/par tool` | `peyajarenareset.tool` | Gives the admin wand for selecting region corners. |
| `/par getpos` | `peyajarenareset.getpos` | Retrieves the currently set selection coordinates. |
| `/par setspawnpoint` | `peyajarenareset.setspawnpoint` | Sets the area's spawn to your current location. |
| `/par getspawnpoint` | `peyajarenareset.getspawnpoint` | Retrieves the area's configured spawn coordinates. |
| `/par reload` | `peyajarenareset.reload` | Hot-reloads all plugin configurations and messages. |

---

<div align="center">
  <b>Developed by <a href="https://github.com/realpeyaj">peyaj</a></b>
</div>
