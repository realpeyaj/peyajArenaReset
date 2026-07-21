package com.peyaj.arenareset.commands;

import com.peyaj.arenareset.peyajArenaReset;
import com.peyaj.arenareset.tasks.GetSpawnTask;
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
 *  This Class handles the 'par_getSpawnPoint' command.
 *  @author peyaj
 **/
public class GetSpawnPointCmd extends AbstractCommandExecutor implements CommandExecutor {

    protected final MessageHandler messageHandler = peyajArenaReset.getPlugin(peyajArenaReset.class).getMessageHandler();
    protected final Component prefix = messageHandler.getMessageAsComponent("Prefix");
    private final Component noPermission = messageHandler.getMessageAsComponent("NoPermission");
    private final String executedByConsole = messageHandler.getMessageAsString("ExecutedByConsole");

    public GetSpawnPointCmd() {
        super(peyajArenaReset.getPlugin(peyajArenaReset.class));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            super.getPlugin().getLogger().log(Level.SEVERE, executedByConsole);
            return true;
        }
        if(!sender.hasPermission("peyajarenareset.getspawnpoint")) {
            sender.sendMessage(prefix.append(noPermission));
            return true;
        }
        //super.getAsyncExecutor().executeFuture(super.getPlugin().getLogger(), new GetSpawnTask(sender).execute(), 15, TimeUnit.SECONDS);
        super.getPlugin().getServer().getScheduler().runTaskAsynchronously(super.getPlugin(), new GetSpawnTask(sender).execute());
        return true;
    }

}
