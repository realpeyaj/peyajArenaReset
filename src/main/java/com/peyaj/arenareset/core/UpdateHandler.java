package com.peyaj.arenareset.core;

import org.bukkit.plugin.java.JavaPlugin;

public class UpdateHandler extends AbstractHandler {

    public UpdateHandler(JavaPlugin plugin, int resourceId) {
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
