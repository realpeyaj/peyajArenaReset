package com.peyaj.arenareset;

import com.peyaj.arenareset.data.AutoResetHandler;
import com.peyaj.arenareset.data.DatabaseHandler;
import com.peyaj.arenareset.data.PositionsHandler;
import com.peyaj.arenareset.data.SpawnPointHandler;
import com.peyaj.arenareset.loaders.CommandLoader;
import com.peyaj.arenareset.loaders.ListenerLoader;
import com.peyaj.arenareset.placeholders.peyajArenaResetExpansion;
import com.peyaj.arenareset.core.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * peyajArenaReset is a Minecraft Plugin enabling you to create, manage and
 * reset selected areas of your world.
 * 
 * @author peyaj
 **/
public final class peyajArenaReset extends JavaPlugin {

    // TODO Before Releasing peyajArenaReset Version 1.4
    // -> Add Enable/Disable Resets feature for Areas.

    private final List<AbstractHandler> handlerList = new ArrayList<>();
    private final List<Boolean> handlerInitList = new ArrayList<>();
    private peyajArenaResetExpansion areaResetterProExpansion;

    @Override
    public void onEnable() {

        MetricsHandler metricsHandler = new MetricsHandler(this, 19274);
        handlerList.add(metricsHandler);
        handlerInitList.add(metricsHandler.initialize());

        UpdateHandler updateHandler = new UpdateHandler(this, 109372);
        handlerList.add(updateHandler);
        handlerInitList.add(updateHandler.initialize());

        PositionsHandler positionsHandler = new PositionsHandler(this, "Positions.yml");
        handlerList.add(positionsHandler);
        handlerInitList.add(positionsHandler.initialize());

        SpawnPointHandler spawnPointHandler = new SpawnPointHandler(this, "SpawnPoint.yml");
        handlerList.add(spawnPointHandler);
        handlerInitList.add(spawnPointHandler.initialize());

        ConfigHandler configHandler = new ConfigHandler(this);
        handlerList.add(configHandler);
        handlerInitList.add(configHandler.initialize());

        MessageHandler messageHandler = new MessageHandler(this);
        handlerList.add(messageHandler);
        handlerInitList.add(messageHandler.initialize());

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        handlerList.add(databaseHandler); // 6
        handlerInitList.add(databaseHandler.initialize());

        AutoResetHandler autoResetHandler = new AutoResetHandler(this);
        handlerList.add(autoResetHandler);
        handlerInitList.add(autoResetHandler.initialize());

        CommandLoader commandLoader = new CommandLoader(this);
        commandLoader.load();

        ListenerLoader listenerLoader = new ListenerLoader(this);
        listenerLoader.load();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            areaResetterProExpansion = new peyajArenaResetExpansion();
            areaResetterProExpansion.register();
            if (((Boolean) configHandler.get("EnableAutoResets")) && autoResetHandler.isInitialized()) {
                peyajArenaReset.getPlugin(peyajArenaReset.class).getpeyajArenaResetExpansion().updateValues();
            }
        }

    }

    @Override
    public void onDisable() {
        for (AbstractHandler handler : this.handlerList) {
            handler.terminate();
        }
    }

    public PositionsHandler getPositionsHandler() {
        return (PositionsHandler) this.handlerList.get(2);
    }

    public SpawnPointHandler getSpawnPointHandler() {
        return (SpawnPointHandler) this.handlerList.get(3);
    }

    public ConfigHandler getConfigHandler() {
        return (ConfigHandler) this.handlerList.get(4);
    }

    public MessageHandler getMessageHandler() {
        return (MessageHandler) this.handlerList.get(5);
    }

    public DatabaseHandler getDatabaseHandler() {
        return (DatabaseHandler) this.handlerList.get(6);
    }

    public AutoResetHandler getAutoResetHandler() {
        return (AutoResetHandler) this.handlerList.get(7);
    }

    public peyajArenaResetExpansion getpeyajArenaResetExpansion() {
        return this.areaResetterProExpansion;
    }

}
