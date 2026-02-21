package com.peyaj.arenareset.loaders;

import com.peyaj.arenareset.commands.*;
import com.peyaj.arenareset.core.AbstractCommandLoader;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This Class is used to register Plugin commands during server startup.
 * 
 * @author peyaj
 **/
public class CommandLoader extends AbstractCommandLoader {

    public CommandLoader(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void load() {

        super.getPlugin().getCommand("par").setExecutor(new CommandManager());

    }

}
