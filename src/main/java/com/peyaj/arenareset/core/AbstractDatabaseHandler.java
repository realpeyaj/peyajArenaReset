package com.peyaj.arenareset.core;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDatabaseHandler extends AbstractHandler {

    private final String dbName = "database";
    private Connection connection;

    public AbstractDatabaseHandler(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public boolean initialize() {
        try {
            File dataFolder = new File(getPlugin().getDataFolder(), dbName + ".db");
            if (!dataFolder.exists()) {
                dataFolder.getParentFile().mkdirs();
                dataFolder.createNewFile();
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return true;
        } catch (Exception ex) {
            getPlugin().getLogger().severe("SQLite exception on initialize");
            getPlugin().getLogger().severe(ex.toString());
            return false;
        }
    }

    @Override
    public boolean terminate() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            return true;
        } catch (Exception ex) {
            getPlugin().getLogger().severe("SQLite exception on terminate");
            getPlugin().getLogger().severe(ex.toString());
            return false;
        }
    }

    public Connection getDbCon() {
        return connection;
    }

    public String getDbPath() {
        return new File(getPlugin().getDataFolder(), dbName + ".db").getAbsolutePath();
    }

    protected abstract void createDatabase();

    protected abstract void createTables();

    public void execute(String instruction) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(instruction);
        stmt.close();
    }

    protected void connect() {
        try {
            File dataFolder = new File(getPlugin().getDataFolder(), dbName + ".db");
            if (!dataFolder.exists()) {
                dataFolder.getParentFile().mkdirs();
                dataFolder.createNewFile();
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
        } catch (Exception ex) {
            getPlugin().getLogger().severe("SQLite exception on connect");
            getPlugin().getLogger().severe(ex.toString());
        }
    }
}
