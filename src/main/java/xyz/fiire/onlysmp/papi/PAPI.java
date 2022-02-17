package xyz.fiire.onlysmp.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.QuestUtils;

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
        if (args.equalsIgnoreCase("coinsAmountPlayer")) {
            return QuestUtils.getCoinsAmountPlayer((Player) player);
        } else if (args.equalsIgnoreCase("test2")) {
            return "test2";
        } // and so on
        return "error";
    }
}
