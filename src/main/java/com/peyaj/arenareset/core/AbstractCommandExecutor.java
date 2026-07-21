package com.peyaj.arenareset.core;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractCommandExecutor implements CommandExecutor {

    private final JavaPlugin plugin;

    public AbstractCommandExecutor(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    @Override
    public abstract boolean onCommand(CommandSender sender, Command command, String label, String[] args);
}
