package xyz.fiire.onlysmp.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.Utils;

public class QuestGUI {
    public static Inventory inv;
    public static String inventory_name;
    public static int inventory_rows;
    private static OnlySMP plugin;

    public QuestGUI(OnlySMP plugin) {
        QuestGUI.plugin = plugin;
        inventory_name = Utils.chat("&c&lQuest GUI");
        inventory_rows = 6;
    }

    public static Inventory GUI(Player p) {
        inv = Bukkit.createInventory(null, inventory_rows * 9, inventory_name);
        Utils.createItem(inv, Utils.getPlayerHead("FiireWiinter"), 49, "FiireWiinter", "guten tag :)");
        return inv;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
        switch (slot) {
            case 49: p.sendMessage("I created the plugin :) Also, fuck you tea"); break;
        }
    }
}
