package com.peyaj.arenareset.core;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractListenerLoader {

    private final JavaPlugin plugin;

    public AbstractListenerLoader(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public abstract void load();
}
