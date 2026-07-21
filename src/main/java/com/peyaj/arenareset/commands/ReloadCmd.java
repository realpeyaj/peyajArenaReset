package com.peyaj.arenareset.commands;

import com.peyaj.arenareset.peyajArenaReset;
import com.peyaj.arenareset.data.AutoResetHandler;
import com.peyaj.arenareset.data.PositionsHandler;
import com.peyaj.arenareset.data.SpawnPointHandler;
import com.peyaj.arenareset.core.AbstractCommandExecutor;
import com.peyaj.arenareset.core.ConfigHandler;
import com.peyaj.arenareset.core.MessageHandler;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

/**
 * This Class handles the 'par_reload' command.
 * 
 * @author peyaj
 **/
public class ReloadCmd extends AbstractCommandExecutor implements CommandExecutor {

    private final PositionsHandler positionsHandler = peyajArenaReset.getPlugin(peyajArenaReset.class)
            .getPositionsHandler();
    private final SpawnPointHandler spawnPointHandler = peyajArenaReset.getPlugin(peyajArenaReset.class)
            .getSpawnPointHandler();
    private final ConfigHandler configHandler = peyajArenaReset.getPlugin(peyajArenaReset.class).getConfigHandler();
    private final MessageHandler messageHandler = peyajArenaReset.getPlugin(peyajArenaReset.class).getMessageHandler();
    private final AutoResetHandler autoResetHandler = peyajArenaReset.getPlugin(peyajArenaReset.class)
            .getAutoResetHandler();
    private final Component prefix = messageHandler.getMessageAsComponent("Prefix");
    private final Component reloadMsg = messageHandler.getMessageAsComponent("ReloadConfigsMessage");
    private final Component noPermission = messageHandler.getMessageAsComponent("NoPermission");

    public ReloadCmd() {
        super(peyajArenaReset.getPlugin(peyajArenaReset.class));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
            @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            reloadByConsole();
            return true;
        }
        if (!sender.hasPermission("peyajarenareset.reload")) {
            sender.sendMessage(prefix.append(noPermission));
            return true;
        }
        reloadByPlayer(sender);
        return true;
    }

    private void reloadByPlayer(CommandSender sender) {
        configHandler.reload();
        messageHandler.reload();
        positionsHandler.reload();
        spawnPointHandler.reload();
        if (((Boolean) configHandler.get("EnableAutoResets")) && autoResetHandler.isInitialized()) {
            peyajArenaReset.getPlugin(peyajArenaReset.class).getpeyajArenaResetExpansion().updateValues();
        }
        sender.sendMessage(prefix.append(reloadMsg));
    }

    private void reloadByConsole() {
        configHandler.reload();
        messageHandler.reload();
        positionsHandler.reload();
        spawnPointHandler.reload();
        if (((Boolean) configHandler.get("EnableAutoResets")) && autoResetHandler.isInitialized()) {
            peyajArenaReset.getPlugin(peyajArenaReset.class).getpeyajArenaResetExpansion().updateValues();
        }
        super.getPlugin().getLogger().log(Level.INFO, messageHandler.getMessageAsString("ReloadConfigsMessage"));
    }

}
