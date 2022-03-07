package xyz.fiire.onlysmp.listeners.coins;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.SQLite;
import xyz.fiire.onlysmp.utils.Utils;

public class CoinsOnPlace implements Listener {

    private final OnlySMP plugin;

    public CoinsOnPlace(OnlySMP plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();
        if (Utils.isValidCoinBlock(b.getType())) {
            SQLite.setLoc(b.getLocation().toString()); // add to db to detect placed block
        } else if (p.getInventory().getItemInMainHand().getType() == Material.SUNFLOWER) {
            if (!p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) return;
            if (p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 10) {
                e.setCancelled(true);
            }
        } else if (p.getInventory().getItemInOffHand().getType() == Material.SUNFLOWER) {
            if (!p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()) return;
            if (p.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 10) {
                e.setCancelled(true);
            }
        }
    }
}
