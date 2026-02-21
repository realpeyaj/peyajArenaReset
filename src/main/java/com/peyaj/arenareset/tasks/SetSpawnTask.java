package com.peyaj.arenareset.tasks;

import com.peyaj.arenareset.peyajArenaReset;
import com.peyaj.arenareset.commands.SetSpawnPointCmd;
import com.peyaj.arenareset.data.SpawnPointHandler;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * This process asynchronously executes the 'par_setspawnpoint' commands logic.
 * @author peyaj
 **/
public class SetSpawnTask extends SetSpawnPointCmd {

    private final SpawnPointHandler spawnPointHandler = peyajArenaReset.getPlugin(peyajArenaReset.class).getSpawnPointHandler();
    private final Component setSpawn = messageHandler.getMessageAsComponent("SetSpawnPointMessage");
    private final Player player;
    private final Location location;

    public SetSpawnTask(Player player, Location location) {
        this.player = player;
        this.location = location;
    }

    public RunnableFuture<Boolean> execute() {
        return new FutureTask<>(() -> {
            if(player == null) {
                spawnPointHandler.setSpawnPoint(SpawnPointHandler.SpawnPoint.SPAWNPOINT, null);
                spawnPointHandler.save();
                spawnPointHandler.reload();
                return true;
            }
            spawnPointHandler.setSpawnPoint(SpawnPointHandler.SpawnPoint.SPAWNPOINT, location);
            spawnPointHandler.save();
            spawnPointHandler.reload();
            player.sendMessage(prefix.append(setSpawn));
            return true;
        });
    }

}
