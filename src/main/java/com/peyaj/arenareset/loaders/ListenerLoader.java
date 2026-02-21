package com.peyaj.arenareset.loaders;

import com.peyaj.arenareset.listeners.SetPosToolListener;
import com.peyaj.arenareset.core.AbstractListenerLoader;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This Class used to register Plugin listeners during server startup and manage them thereafter.
 * @author peyaj
 **/
public class ListenerLoader extends AbstractListenerLoader {

    private final SetPosToolListener setPosToolListener = new SetPosToolListener();

    public ListenerLoader(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void load() {
        super.getPlugin().getServer().getPluginManager().registerEvents(setPosToolListener, super.getPlugin());
    }

}
