package com.peyaj.arenareset.commands;

import com.peyaj.arenareset.peyajArenaReset;
import com.peyaj.arenareset.ui.AreaMenu;
import com.peyaj.arenareset.core.AbstractCommandExecutor;
import com.peyaj.arenareset.core.MessageHandler;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

/**
 * This Class handles the 'par_menu' command.
 * @author peyaj
 **/
public class MenuCmd extends AbstractCommandExecutor implements CommandExecutor {

    private final MessageHandler messageHandler = peyajArenaReset.getPlugin(peyajArenaReset.class).getMessageHandler();
    private final Component prefix = messageHandler.getMessageAsComponent("Prefix");
    private final Component noPermission = messageHandler.getMessageAsComponent("NoPermission");
    private final String executedByConsole = messageHandler.getMessageAsString("ExecutedByConsole");

    public MenuCmd() {
        super(peyajArenaReset.getPlugin(peyajArenaReset.class));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            super.getPlugin().getLogger().log(Level.SEVERE, executedByConsole);
            return true;
        }
        if(!sender.hasPermission("peyajarenareset.menu")) {
            sender.sendMessage(prefix.append(noPermission));
            return true;
        }
        ((Player) sender).openInventory(new AreaMenu().getInventory());
        return true;
    }

}
