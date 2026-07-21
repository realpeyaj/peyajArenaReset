package com.peyaj.arenareset.core;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class MetricsHandler extends AbstractHandler {

    private final int pluginId;
    private Metrics metrics;

    public MetricsHandler(JavaPlugin plugin, int pluginId) {
        super(plugin);
        this.pluginId = pluginId;
    }

    @Override
    public boolean initialize() {
        try {
            this.metrics = new Metrics(super.getPlugin(), this.pluginId);
            return true;
        } catch (Exception e) {
            super.getPlugin().getLogger().warning("Failed to initialize bStats Metrics: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean terminate() {
        this.metrics = null;
        return true;
    }
}
