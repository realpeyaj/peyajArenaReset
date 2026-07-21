package com.peyaj.arenareset.core;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractCommandLoader {

    private final JavaPlugin plugin;

    public AbstractCommandLoader(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public abstract void load();
}
