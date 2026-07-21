package com.peyaj.arenareset.core;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public abstract class AbstractInventory {

    private final JavaPlugin plugin;
    private final Inventory inventory;

    public AbstractInventory(JavaPlugin plugin, int size, Component title) {
        this.plugin = plugin;
        this.inventory = Bukkit.createInventory(null, size, title);
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public Inventory getInventory() {
        return inventory;
    }

    protected abstract boolean initialize();

    protected ItemStack constructItem(Material material, Component name, List<Component> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.displayName(name);
            if (lore != null && !lore.isEmpty()) {
                meta.lore(lore);
            }
            item.setItemMeta(meta);
        }
        return item;
    }
}
