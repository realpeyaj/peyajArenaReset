package com.peyaj.arenareset.core;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public abstract class AbstractFileHandler extends AbstractHandler {

    private final String fileName;
    private File file;
    private YamlConfiguration config;

    public AbstractFileHandler(JavaPlugin plugin, String fileName) {
        super(plugin);
        this.fileName = fileName;
    }

    @Override
    public boolean initialize() {
        if (!getPlugin().getDataFolder().exists()) {
            getPlugin().getDataFolder().mkdirs();
        }
        this.file = new File(getPlugin().getDataFolder(), fileName);
        if (!file.exists()) {
            if (getPlugin().getResource(fileName) != null) {
                getPlugin().saveResource(fileName, false);
            } else {
                try {
                    file.createNewFile();
                } catch (Exception e) {
                    getPlugin().getLogger().severe("Failed to create " + fileName);
                    return false;
                }
            }
        }
        this.config = YamlConfiguration.loadConfiguration(file);
        return true;
    }

    @Override
    public boolean terminate() {
        return true;
    }

    public Object get(String path) {
        return config.get(path);
    }

    public String getString(String path) {
        return config.getString(path);
    }

    public int getInt(String path) {
        return config.getInt(path);
    }

    public boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    public void set(String path, Object arg) {
        config.set(path, arg);
        save();
    }

    public void save() {
        try {
            config.save(file);
        } catch (Exception e) {
            getPlugin().getLogger().severe("Failed to save " + fileName);
        }
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public YamlConfiguration getFileConfig() {
        return config;
    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(file);
    }
}
