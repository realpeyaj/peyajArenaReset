package com.peyaj.arenareset.core;

import org.bukkit.plugin.java.JavaPlugin;

public class ConfigHandler extends AbstractFileHandler {

    public ConfigHandler(JavaPlugin plugin) {
        super(plugin, "config.yml");
    }
}
