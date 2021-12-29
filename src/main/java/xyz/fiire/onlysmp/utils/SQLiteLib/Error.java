package xyz.fiire.onlysmp.utils.SQLiteLib;

import xyz.fiire.onlysmp.OnlySMP;

import java.util.logging.Level;

public class Error {
    public static void execute(OnlySMP instance, Exception ex) {
        instance.getLogger().log(Level.SEVERE, "Couldn't execute MySQL statement: ", ex);
    }

    public static void close(OnlySMP instance, Exception ex) {
        instance.getLogger().log(Level.SEVERE, "Failed to close MySQL connection: ", ex);
    }
}
