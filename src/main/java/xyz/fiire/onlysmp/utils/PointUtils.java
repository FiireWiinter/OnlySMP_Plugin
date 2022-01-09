package xyz.fiire.onlysmp.utils;

import org.bukkit.entity.Player;
import xyz.fiire.onlysmp.OnlySMP;

public class PointUtils {

    private final OnlySMP plugin;

    public PointUtils(OnlySMP plugin) {
        this.plugin = plugin;
    }

    public static String getCoinsAmountPlayer(Player p) {
        return SQLite.getUserValue("amount", p.getUniqueId().toString()).toString();
    }
}
