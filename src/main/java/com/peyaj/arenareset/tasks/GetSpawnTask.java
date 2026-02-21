package com.peyaj.arenareset.tasks;


import com.peyaj.arenareset.peyajArenaReset;
import com.peyaj.arenareset.commands.GetSpawnPointCmd;
import com.peyaj.arenareset.data.SpawnPointHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;

import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * This process asynchronously executes the 'par_getspawnpoint' commands logic.
 * @author peyaj
 **/
public class GetSpawnTask extends GetSpawnPointCmd {

    private final SpawnPointHandler spawnPointHandler = peyajArenaReset.getPlugin(peyajArenaReset.class).getSpawnPointHandler();
    private final Component noSpawn = messageHandler.getMessageAsComponent("SpawnPointNotSet");
    private final CommandSender sender;

    public GetSpawnTask(CommandSender sender) {
        this.sender = sender;
    }

    public RunnableFuture<Boolean> execute() {
        return new FutureTask<>(() -> {
            if(spawnPointHandler.getSpawnPoint(SpawnPointHandler.SpawnPoint.SPAWNPOINT) == null) {
                sender.sendMessage(prefix.append(noSpawn));
                return false;
            }
            Component spawn = createSpawnMessage();
            sender.sendMessage(prefix.append(spawn));
            return true;
        });
    }

    private Component createSpawnMessage() {
        return MiniMessage.miniMessage().deserialize("<blue> Spawnpoint:</blue> \n" +
                "<light_purple>x: " + spawnPointHandler.getSpawnPoint(SpawnPointHandler.SpawnPoint.SPAWNPOINT).getX() + "\n" +
                "y: " + spawnPointHandler.getSpawnPoint(SpawnPointHandler.SpawnPoint.SPAWNPOINT).getY() + "\n" +
                "z: " + spawnPointHandler.getSpawnPoint(SpawnPointHandler.SpawnPoint.SPAWNPOINT).getZ() + "</light_purple>");
    }

}
