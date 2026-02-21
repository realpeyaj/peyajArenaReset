package com.peyaj.arenareset.core;

import org.bukkit.plugin.java.JavaPlugin;

public class MetricsHandler extends AbstractHandler {

    public MetricsHandler(JavaPlugin plugin, int pluginId) {
        super(plugin);
    }

    @Override
    public boolean initialize() {
        return true;
    }

    @Override
    public boolean terminate() {
        return true;
    }
}
