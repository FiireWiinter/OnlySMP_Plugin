package xyz.fiire.onlysmp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.SQLite;
import xyz.fiire.onlysmp.utils.Utils;

public class CoinsOnBreak implements Listener {

    private OnlySMP plugin;

    public CoinsOnBreak(OnlySMP plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();
        if (Utils.isValidCoinBlock(b.getType())) {
            if (SQLite.getLocExist(b.getLocation().toString())) { // if loc is in db, it's placed by a player
                SQLite.delLoc(b.getLocation().toString()); // not natural
            } else {
                // natural
            }
        }
    }
}
