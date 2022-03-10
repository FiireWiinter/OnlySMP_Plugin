package xyz.fiire.onlysmp.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import xyz.fiire.onlysmp.OnlySMP;

public class GUIHandler implements Listener {

    private final OnlySMP plugin;

    public GUIHandler(OnlySMP plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        String title = e.getView().getTitle();
        // Check if the opened inventory is a GUI
        if (title.equals(QuestGUI.inventory_name)) {
            // Cancel the event, resulting the item not being removed
            e.setCancelled(true);
            if (e.getCurrentItem() == null) return;
            QuestGUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
        }
    }
}
