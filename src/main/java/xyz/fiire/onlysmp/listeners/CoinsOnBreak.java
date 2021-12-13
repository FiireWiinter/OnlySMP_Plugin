package xyz.fiire.onlysmp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import xyz.fiire.onlysmp.OnlySMP;

public class CoinsOnBreak implements Listener {

    private OnlySMP plugin;

    public CoinsOnBreak(OnlySMP plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static boolean isValidBlock(Material mat) {
        return  mat == Material.DIAMOND_ORE ||
                mat == Material.DEEPSLATE_DIAMOND_ORE ||
                mat == Material.EMERALD_ORE ||
                mat == Material.DEEPSLATE_EMERALD_ORE ||
                mat == Material.ANCIENT_DEBRIS;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();
        if (isValidBlock(b.getType())) {
            p.sendMessage("detected");
        }
    }
}
