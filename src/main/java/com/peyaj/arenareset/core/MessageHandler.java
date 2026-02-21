package com.peyaj.arenareset.core;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MessageHandler extends AbstractFileHandler {

    public MessageHandler(JavaPlugin plugin) {
        super(plugin, "messages.yml");
    }

    public String getMessageAsString(String path) {
        return getString(path);
    }

    public void sendMessage(CommandSender sender, String path) {
        String msg = getString(path);
        if (msg != null && !msg.isEmpty()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
        }
    }

    public void sendMessage(CommandSender sender, String path, String[] placeholders, String[] replacements) {
        String msg = getString(path);
        if (msg != null && !msg.isEmpty()) {
            for (int i = 0; i < placeholders.length; i++) {
                msg = msg.replace(placeholders[i], replacements[i]);
            }
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
        }
    }

    public Component getMessageAsComponent(String path) {
        String msg = getString(path);
        if (msg == null)
            return Component.empty();
        return MiniMessage.miniMessage().deserialize(msg);
    }

    public List<Component> getMessagesAsComponentList(String path) {
        List<String> messages = getConfig().getStringList(path);
        if (messages == null || messages.isEmpty()) {
            String single = getString(path);
            if (single != null) {
                return Collections.singletonList(MiniMessage.miniMessage().deserialize(single));
            }
            return new ArrayList<>();
        }
        List<Component> components = new ArrayList<>();
        for (String msg : messages) {
            components.add(MiniMessage.miniMessage().deserialize(msg));
        }
        return components;
    }
}
