package com.peyaj.arenareset.commands;

import com.peyaj.arenareset.peyajArenaReset;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CommandManager implements CommandExecutor {

    private final Map<String, CommandExecutor> subCommands = new HashMap<>();

    public CommandManager() {
        subCommands.put("help", new HelpCmd());
        subCommands.put("reload", new ReloadCmd());
        subCommands.put("tool", new ToolCmd());
        subCommands.put("getpos", new GetPosCmd());
        subCommands.put("setspawnpoint", new SetSpawnPointCmd());
        subCommands.put("getspawnpoint", new GetSpawnPointCmd());
        subCommands.put("create", new CreateCmd());
        subCommands.put("remove", new RemoveCmd());
        subCommands.put("reset", new ResetCmd());
        subCommands.put("menu", new MenuCmd());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
            @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage(
                    peyajArenaReset.getPlugin(peyajArenaReset.class).getMessageHandler().getMessageAsString("Prefix")
                            + "Use /par help for a list of commands.");
            return true;
        }

        String subCommandName = args[0].toLowerCase();
        CommandExecutor executor = subCommands.get(subCommandName);

        if (executor != null) {
            // Pass the remaining arguments to the subcommand
            String[] subArgs = new String[args.length - 1];
            System.arraycopy(args, 1, subArgs, 0, args.length - 1);
            return executor.onCommand(sender, command, label, subArgs);
        } else {
            sender.sendMessage(
                    peyajArenaReset.getPlugin(peyajArenaReset.class).getMessageHandler().getMessageAsString("Prefix")
                            + "Unknown subcommand. Use /par help for an overview.");
            return true;
        }
    }
}
