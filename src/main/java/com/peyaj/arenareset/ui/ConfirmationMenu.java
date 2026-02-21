package com.peyaj.arenareset.ui;

import com.peyaj.arenareset.peyajArenaReset;
import com.peyaj.arenareset.tasks.RemoveTask;
import com.peyaj.arenareset.core.MessageHandler;
import com.peyaj.arenareset.core.AbstractInventory;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * This Class represents a part of the plugins GUI.
 * 
 * @author peyaj
 **/
public class ConfirmationMenu extends AbstractInventory {

    private final MessageHandler messageHandler = peyajArenaReset.getPlugin(peyajArenaReset.class).getMessageHandler();
    private ItemStack confirmationItem, cancelItem;
    private final String areaName;

    public ConfirmationMenu(String areaName) {
        super(peyajArenaReset.getPlugin(peyajArenaReset.class), 9,
                MiniMessage.miniMessage().deserialize("<aqua>peyajArenaReset</aqua>"));
        this.areaName = areaName;
        initialize();
        super.getPlugin().getServer().getPluginManager().registerEvents(new ConfirmationMenuListener(),
                super.getPlugin());
    }

    @Override
    protected boolean initialize() {
        confirmationItem = constructItem(Material.LIME_STAINED_GLASS_PANE,
                messageHandler.getMessageAsComponent("ConfirmationItemName"),
                messageHandler.getMessagesAsComponentList("ConfirmationItemLore"));
        cancelItem = constructItem(Material.RED_STAINED_GLASS_PANE,
                messageHandler.getMessageAsComponent("CancelItemName"),
                messageHandler.getMessagesAsComponentList("CancelItemLore"));
        fillContents();
        return true;
    }

    private void fillContents() {
        super.getInventory().clear();
        super.getInventory().setItem(0, confirmationItem);
        super.getInventory().setItem(super.getInventory().getSize() - 1, cancelItem);
    }

    /**
     * This Class listens for and executes events from the ConfirmationMenu.
     * 
     * @author peyaj
     **/
    @Override
    public Inventory getInventory() {
        return super.getInventory();
    }

    private class ConfirmationMenuListener implements Listener {
        @EventHandler
        public void onConfirmationMenuClickEvent(InventoryClickEvent event) {
            Player player = (Player) event.getWhoClicked();
            if (event.getClickedInventory() == ConfirmationMenu.this.getInventory()) {
                if (confirmationItem.equals(event.getCurrentItem())) {
                    ConfirmationMenu.this.getPlugin().getServer().getScheduler().runTaskAsynchronously(
                            ConfirmationMenu.this.getPlugin(), new RemoveTask(player, areaName).execute());
                    player.openInventory(new AreaMenu().getInventory());
                }
                if (cancelItem.equals(event.getCurrentItem())) {
                    player.openInventory(new AreaMenu().getInventory());
                }
                event.setCancelled(true);
            }
        }
    }

}
