package xyz.fiire.onlysmp.gui;

import org.bukkit.Bukkit;
import org.bukkit.configuration.MemorySection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.Config;
import xyz.fiire.onlysmp.utils.QuestUtils;
import xyz.fiire.onlysmp.utils.SQLite;
import xyz.fiire.onlysmp.utils.Utils;

import java.util.List;
import java.util.Set;

public class QuestGUI {
    public static Inventory inv;
    public static String inventory_name;
    public static int inventory_rows;
    private static OnlySMP plugin;

    public QuestGUI(OnlySMP plugin) {
        QuestGUI.plugin = plugin;
        inventory_name = Utils.chat("&c&lQuest GUI");
        inventory_rows = 2;
    }

    public static Inventory GUI(Player p) {
        inv = Bukkit.createInventory(null, inventory_rows * 9, inventory_name);

        MemorySection mem = (MemorySection) Config.getConfig().get("quests");
        assert mem != null;
        Utils.debug(mem.getKeys(false).toString());
        Set<String> keys = mem.getKeys(false);

        int slot = 0;
        for (String key : keys ) {
            // Get all the values from the config
            String title = Config.get_str(String.format("quests.%s.title", key));
            String description = Config.get_str(String.format("quests.%s.description", key));
            String material = Config.get_str(String.format("quests.%s.item", key));
            String amount = Config.get_str(String.format("quests.%s.amount", key));
            String sql = Config.get_str(String.format("quests.%s.sql", key));

            // In case we have to show a coin
            if (material.equals("COIN")) {
                Utils.createItem(inv, Utils.createCoin(1), 1, slot, "&e&l" + title, "&b" + description, String.format("&eReward: &6%s coins", amount));
            } else {
                Utils.createItem(inv, material, 1, slot, "&e&l" + title, "&b" + description, String.format("&eReward: &6%s coins", amount));
            }

            // Add a glare, if a quest is completed
            ItemStack item = inv.getItem(slot);
            if (QuestUtils.isPlayerQuestCompleted(p, key)) {

                ItemMeta meta = item.getItemMeta();

                List<String> lore = meta.getLore();
                lore.add(Utils.chat("&a&l[COMPLETED]"));
                meta.setLore(lore);
                meta.addEnchant(Enchantment.ARROW_INFINITE, 69, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                item.setItemMeta(meta);
                inv.setItem(slot, item);
            }

            // Set Lore on one time quest
            ItemMeta meta = item.getItemMeta();
            List<String> lore = meta.getLore();
            if (sql != null) {
                Utils.debug(SQLite.getVal("first-diamond").toString());
                if (SQLite.getValKeyExist("first-diamond")) {
                    lore.add(Utils.chat(String.format("&eClaimed by %s", SQLite.getVal("first-diamond"))));
                }
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(slot, item);
            slot++;
        }

        return inv;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
        switch (slot) {
            default:
                break;
        }
    }
}
