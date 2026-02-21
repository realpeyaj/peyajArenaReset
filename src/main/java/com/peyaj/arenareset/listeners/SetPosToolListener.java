package com.peyaj.arenareset.listeners;

import com.peyaj.arenareset.commands.ToolCmd;
import com.peyaj.arenareset.data.PositionsHandler;
import com.peyaj.arenareset.tasks.SetPosTask;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Listener for events from SetPosTool.
 * @author peyaj
 **/
public class SetPosToolListener extends ToolCmd implements Listener {

    private final SetPosTool tool = new SetPosTool();

    @EventHandler
    public void onToolClick(PlayerInteractEvent event) {
        if(event.hasItem() && event.getItem().getItemMeta().getPersistentDataContainer().has(tool.getPosToolKey())) {
            Player player = event.getPlayer();
            if(!player.hasPermission("peyajarenareset.tool")) {
                player.sendMessage(prefix.append(noPermission));
                event.setCancelled(true);
                return;
            }
            if(event.getClickedBlock() != null) {
                Location location = event.getClickedBlock().getLocation();
                if(event.getAction().isLeftClick()) {
                    execute(player, PositionsHandler.Position.POS1, location);
                    event.setCancelled(true);
                }
                if(event.getAction().isRightClick()) {
                    execute(player, PositionsHandler.Position.POS2, location);
                    event.setCancelled(true);
                }
            }
        }
    }

    private void execute(Player player, PositionsHandler.Position position, Location location) {
        //super.getAsyncExecutor().executeFuture(super.getPlugin().getLogger(), new SetPosTask(player, position, location).execute(), 15, TimeUnit.SECONDS);
        super.getPlugin().getServer().getScheduler().runTaskAsynchronously(super.getPlugin(), new SetPosTask(player, position, location).execute());
    }

}
