package com.peyaj.arenareset.tasks;

import com.fastasyncworldedit.core.FaweAPI;
import com.peyaj.arenareset.peyajArenaReset;
import com.peyaj.arenareset.commands.CreateCmd;
import com.peyaj.arenareset.data.AutoResetHandler;
import com.peyaj.arenareset.data.DatabaseHandler;
import com.peyaj.arenareset.data.PositionsHandler;
import com.peyaj.arenareset.data.SpawnPointHandler;
import com.peyaj.arenareset.core.ConfigHandler;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.BuiltInClipboardFormat;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.logging.Level;

import static com.peyaj.arenareset.data.PositionsHandler.Position.POS1;
import static com.peyaj.arenareset.data.PositionsHandler.Position.POS2;

public class CreateTask extends CreateCmd {

    private final PositionsHandler positionsHandler = peyajArenaReset.getPlugin(peyajArenaReset.class).getPositionsHandler();
    private final SpawnPointHandler spawnPointHandler = peyajArenaReset.getPlugin(peyajArenaReset.class).getSpawnPointHandler();
    private final ConfigHandler configHandler = peyajArenaReset.getPlugin(peyajArenaReset.class).getConfigHandler();
    private final DatabaseHandler databaseHandler = peyajArenaReset.getPlugin(peyajArenaReset.class).getDatabaseHandler();
    private final AutoResetHandler autoResetHandler = peyajArenaReset.getPlugin(peyajArenaReset.class).getAutoResetHandler();
    private final Component success = messageHandler.getMessageAsComponent("SuccessfullySavedArea");
    private final Component failed = messageHandler.getMessageAsComponent("CreationFailedMessage");
    private final Component noPos1 = messageHandler.getMessageAsComponent("Pos1NotSet");
    private final Component noPos2 = messageHandler.getMessageAsComponent("Pos2NotSet");
    private final Component areaAlreadyExists = messageHandler.getMessageAsComponent("AreaAlreadyExists");
    private final Component creationStarted = messageHandler.getMessageAsComponent("AreaCreationStartedMessage");
    private final Component creationFinished = messageHandler.getMessageAsComponent("CreationSucceededMessage");
    private final Player player;
    private final String areaName;

    public CreateTask(Player player, String areaName) {
        this.player = player;
        this.areaName = areaName;
    }

    public RunnableFuture<Boolean> execute() {
        return new FutureTask<>(() -> {
            try {
                Location[] positions = getLocations();
                Location spawnPoint = getSpawnPoint();

                if(positions == null || !validAreaName()) {
                    return false;
                }

                player.sendMessage(prefix.append(creationStarted));
                createAreaDataFolder();
                UUID uuid = UUID.randomUUID();

                if(spawnPoint != null) {
                    databaseHandler.insertAreaData(uuid, areaName, positions[0].getWorld().getName(), positions[0], positions[1], spawnPoint);
                } else {
                    databaseHandler.insertAreaData(uuid, areaName, positions[0].getWorld().getName(), positions[0], positions[1], positions[0]);
                }

                saveAreaDataSchematics(uuid, positions);
                databaseHandler.insertAreaStats(uuid, ((long) (Math.abs(positions[0].getBlockX() - positions[1].getBlockX()) + 1) *
                        (Math.abs(positions[0].getBlockY() - positions[1].getBlockY()) + 1) *
                        (Math.abs(positions[0].getBlockZ() - positions[1].getBlockZ()) + 1)));

                int configTimerValue = (int) configHandler.get("DefaultTimerValue");
                databaseHandler.insertAreaTimer(uuid, configTimerValue);
                autoResetHandler.addNewAutoResetter(areaName, configTimerValue);

                if(((Boolean) configHandler.get("EnableAutoResets")) && autoResetHandler.isInitialized()) {
                    peyajArenaReset.getPlugin(peyajArenaReset.class).getpeyajArenaResetExpansion().updateValues();
                }

                player.sendMessage(prefix.append(success));
                super.getPlugin().getServer().getScheduler().runTaskAsynchronously(super.getPlugin(), new SetPosTask(null, null, null).execute());
                super.getPlugin().getServer().getScheduler().runTaskAsynchronously(super.getPlugin(), new SetSpawnTask(null, null).execute());
                player.sendMessage(prefix.append(creationFinished));
            } catch (IOException io) {
                player.sendMessage(prefix.append(failed));
                super.getPlugin().getLogger().log(Level.SEVERE, "An I/O error occurred during the creation process!", io);
            }
            return true;
        });
    }

    private Location[] getLocations() {
        Location[] positions = new Location[] { positionsHandler.getPosition(POS1), positionsHandler.getPosition(POS2) };
        if(positions[0] == null) {
            player.sendMessage(prefix.append(noPos1));
            return null;
        }
        if(positions[1] == null) {
            player.sendMessage(prefix.append(noPos2));
            return null;
        }
        return positions;
    }

    private Location getSpawnPoint() {
        return spawnPointHandler.getSpawnPoint(SpawnPointHandler.SpawnPoint.SPAWNPOINT);
    }

    private boolean validAreaName() {
        try {
            ResultSet areaData = databaseHandler.getAreaData();
            while(areaData.next()) {
                if(Objects.equals(areaData.getString("areaName"), areaName)) {
                    player.sendMessage(prefix.append(areaAlreadyExists));
                    return false;
                }
            }
            areaData.close();
            return true;
        } catch (SQLException se) {
            player.sendMessage(prefix.append(this.failed));
            super.getPlugin().getLogger().log(Level.SEVERE, "An SQL error occurred during the creation process!", se);
            return false;
        }
    }

    private void createAreaDataFolder() {
        File dataFolder = new File(super.getPlugin().getDataFolder().getAbsolutePath(), "AreaData");
        if(!dataFolder.exists()) {
            boolean folderCreated = dataFolder.mkdir();
            if(folderCreated) {
                super.getPlugin().getLogger().log(Level.INFO, "Successfully created 'AreaData' folder inside the 'peyajArenaReset' plugin folder.");
            }
        }
    }

    private void saveAreaDataSchematics(UUID uuid, Location[] positions) throws IOException {
        File dataFolder = new File(super.getPlugin().getDataFolder().getAbsolutePath(), "AreaData");
        File dataFile = new File(dataFolder.getAbsolutePath(), uuid + ".schem");

        Region region = new CuboidRegion(BlockVector3.at(positions[0].getBlockX(), positions[0].getBlockY(), positions[0].getBlockZ()),
                                         BlockVector3.at(positions[1].getBlockX(), positions[1].getBlockY(), positions[1].getBlockZ()));
        region.setWorld(FaweAPI.getWorld(positions[0].getWorld().getName()));

        Clipboard clip = new BlockArrayClipboard(region);
        EditSession edit = WorldEdit.getInstance().newEditSession(FaweAPI.getWorld(positions[0].getWorld().getName()));
        ForwardExtentCopy copy = new ForwardExtentCopy(edit, region, clip, region.getMinimumPoint());
        copy.setCopyingEntities((boolean) configHandler.get("SaveEntities"));
        Operations.complete(copy);
        edit.close();
        clip.save(dataFile, BuiltInClipboardFormat.FAST);
        clip.close();
    }

}
