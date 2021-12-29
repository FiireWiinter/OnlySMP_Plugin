package xyz.fiire.onlysmp.utils.SQLiteLib.Database;

import xyz.fiire.onlysmp.utils.SQLiteLib.Main;
import xyz.fiire.onlysmp.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class SQLite extends Database {
    private final String dbname;
    private final String createTestTable = "CREATE TABLE IF NOT EXISTS test (`test` varchar(32) NOT NULL,PRIMARY KEY (`test`));";
    private final String customCreateString;
    private final File dataFolder;

    public SQLite(String databaseName, String createStatement, File folder) {
        this.dbname = databaseName;
        this.customCreateString = createStatement;
        this.dataFolder = folder;
    }

    public Connection getSQLConnection() {
        File folder = new File(this.dataFolder, this.dbname + ".db");
        if (!folder.exists()) {
            try {
                folder.createNewFile();
            } catch (IOException e) {
                Utils.severe("File write error: " + this.dbname + ".db\n" + e);
            }
        }

        try {
            if (this.connection != null && !this.connection.isClosed()) {
                return this.connection;
            }

            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + folder);
            return this.connection;
        } catch (SQLException var3) {
            Main.getInstance().getLogger().log(Level.SEVERE, "SQLite exception on initialize", var3);
        } catch (ClassNotFoundException var4) {
            Main.getInstance().getLogger().log(Level.SEVERE, "You need the SQLite JBDC library. Google it. Put it in /lib folder.");
        }

        return null;
    }

    public void load() {
        this.connection = this.getSQLConnection();

        try {
            Statement s = this.connection.createStatement();
            s.executeUpdate(this.createTestTable);
            s.executeUpdate(this.customCreateString);
            s.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        this.initialize();
    }

    public File getDataFolder() {
        return this.dataFolder;
    }
}
