package xyz.fiire.onlysmp.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import xyz.fiire.onlysmp.OnlySMP;

public class PAPI extends PlaceholderExpansion {
    private static OnlySMP plugin;

    public PAPI(OnlySMP plugin) {
        PAPI.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "osmp";
    }

    @Override
    public String getAuthor() {
        return "FiireWiinter";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String args) {
        if (args.equalsIgnoreCase("test")) {
            return "test";
        } else if (args.equalsIgnoreCase("test2")) {
            return "test2";
        } // and so on
        return "error";
    }
}
