package xyz.fiire.onlysmp.utils.SQLiteLib;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.SQLiteLib.Database.Database;
import xyz.fiire.onlysmp.utils.SQLiteLib.Database.SQLite;

import java.util.HashMap;
import java.util.Map;

public class Main {
    private static OnlySMP INSTANCE;
    private final Map<String, Database> databases = new HashMap();

    public Main(OnlySMP instance) {
        INSTANCE = instance;
    }

    public static OnlySMP getInstance() {
        return INSTANCE;
    }

    public static Main hookSQLiteLib(Plugin hostPlugin) {
        Main plugin = (Main) Bukkit.getPluginManager().getPlugin("SQLiteLib");
        if (plugin == null) {
            Bukkit.getLogger().severe("SQLiteLib is not yet ready! You have called hookSQLiteLib() too early.");
            return null;
        } else {
            return plugin;
        }
    }

    public void initializeDatabase(Plugin plugin, String databaseName, String createStatement) {
        Database db = new SQLite(databaseName, createStatement, plugin.getDataFolder());
        db.load();
        this.databases.put(databaseName, db);
    }

    public Map<String, Database> getDatabases() {
        return this.databases;
    }

    public Database getDatabase(String databaseName) {
        return this.getDatabases().get(databaseName);
    }
}
