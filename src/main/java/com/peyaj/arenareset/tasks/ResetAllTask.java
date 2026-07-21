package com.peyaj.arenareset.tasks;


import com.peyaj.arenareset.peyajArenaReset;
import com.peyaj.arenareset.commands.ResetCmd;
import com.peyaj.arenareset.data.DatabaseHandler;
import org.bukkit.command.CommandSender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.logging.Level;

/**
 * This process asynchronously executes the 'Reset all item`s' logic.
 * @author peyaj
 **/
public class ResetAllTask extends ResetCmd {

    private final DatabaseHandler databaseHandler = peyajArenaReset.getPlugin(peyajArenaReset.class).getDatabaseHandler();
    private final CommandSender sender;

    public ResetAllTask(CommandSender sender) {
        this.sender = sender;
    }

    public RunnableFuture<Boolean> execute() {
        return new FutureTask<>(() -> {
            try {
                ResultSet results = databaseHandler.getAreaData();
                while (results.next()) {
                    //super.getPlugin().getServer().getScheduler().runTaskAsynchronously(super.getPlugin(), new ResetTask(sender, results.getString("areaName")).execute());
                    super.getPlugin().getServer().getScheduler().runTaskAsynchronously(super.getPlugin(), new ResetTask(sender, results.getString("areaName")).execute());
                }
                results.close();
                return true;
            } catch (SQLException se) {
                super.getPlugin().getLogger().log(Level.SEVERE, "Couldn't fetch AreaData!", se);
                return false;
            }
        });
    }

}
