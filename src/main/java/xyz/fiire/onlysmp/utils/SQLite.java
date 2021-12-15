package xyz.fiire.onlysmp.utils;

import xyz.fiire.onlysmp.utils.SQLiteLib.Database.Database;
import xyz.fiire.onlysmp.utils.SQLiteLib.Main;
import xyz.fiire.onlysmp.OnlySMP;
import org.bukkit.entity.Player;

import java.util.List;

public class SQLite {
    private static OnlySMP plugin;
    public static Main sqlLib;
    public static Database db;

    public SQLite(OnlySMP plugin) {
        SQLite.plugin = plugin;
        sqlLib = new Main(plugin);
        sqlLib.initializeDatabase(plugin, "db", "CREATE TABLE IF NOT EXISTS users (`uuid` string NOT NULL, `name` string NOT NULL, `amount` string NOT NULL, PRIMARY KEY (`uuid`));");
        db = sqlLib.getDatabase("db");
        db.executeStatement("CREATE TABLE IF NOT EXISTS blocks (`loc` string NOT NULL, PRIMARY KEY (`loc`));");
        // db.executeStatement("INSERT OR IGNORE INTO kenny (uuid, name, event) VALUES ('0', 'stopped', '-');");
    }

    /*
    public static Object getValue(String column, String uuid) {
        return db.queryValue(String.format("SELECT * FROM kenny WHERE uuid='%s'", uuid), column);
    }

    public static void setValue(String column, String value, String uuid) {
        db.executeStatement(String.format("UPDATE kenny SET `%s`='%s' WHERE uuid='%s'", column, value, uuid));
    }
    */

    // custom
    // this will be used later
    public static void addPlayer(Player player) {
        String uuid = player.getUniqueId().toString();
        String name = player.getName();
        db.executeStatement(String.format("INSERT OR IGNORE INTO users (uuid, name, amount) VALUES ('%s', '%s', '0');", uuid, name));
    }

    public static List<Object> getAllValueByOrder(String order, String value) {
        return db.queryRow(String.format("SELECT * FROM users ORDER BY %s DESC", order), value);
    }

    public static void setLoc(String loc) {
        db.executeStatement(String.format("INSERT OR IGNORE INTO blocks (loc) VALUES ('%s');", loc));
    }

    public static void delLoc(String loc) {
        db.executeStatement(String.format("DELETE FROM blocks WHERE loc='%s';", loc));
    }

    public static boolean getLocExist(String loc) {
        Object val = db.queryValue(String.format("SELECT * FROM blocks WHERE loc='%s'", loc), "loc");
        return val != null;
    }

}
