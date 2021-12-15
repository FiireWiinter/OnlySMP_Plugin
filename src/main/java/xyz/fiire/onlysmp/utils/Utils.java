package xyz.fiire.onlysmp.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.fiire.onlysmp.OnlySMP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class Utils {
    private static OnlySMP plugin;

    public Utils(OnlySMP plugin) {
        Utils.plugin = plugin;
    }

    // Format color strings
    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    // Tab Completion
    public static List<String> filterByStart(List<String> list, String startingWith) {
        if (list == null || startingWith == null) {
            return Collections.emptyList();
        }
        return list.stream().filter(name -> name.toLowerCase().startsWith(startingWith.toLowerCase())).collect(Collectors.toList());
    }

    // Logging
    public static void info(String msg) {
        plugin.getLogger().log(Level.INFO, msg);
    }

    public static void severe(String msg) {
        plugin.getLogger().log(Level.SEVERE, msg);
    }

    public static void warning(String msg) {
        plugin.getLogger().log(Level.WARNING, msg);
    }

    public static void debug(String msg) {
        Player p = Bukkit.getPlayer(UUID.fromString("a9085607-f473-45f1-90ec-7ad71d697853")); // FiireWiinter
        if (p != null) {
            if (NBTStorage.getPlayerBool(p, "osmp_debug")) p.sendMessage(Utils.chat(msg));
        }
    }

    // Creates an item for GUIs
    public static ItemStack createItem(Inventory inv, String material, int amount, int invSlot, String displayName, String... loreString) {
        List<String> lore = new ArrayList();
        ItemStack item = new ItemStack(Material.matchMaterial(material), amount);
        return addItemStackLore(inv, invSlot, displayName, lore, item, loreString);
    }

    // Creates an item for GUIs with special bytes
    public static ItemStack createItemByte(Inventory inv, String material, int byteId, int amount, int invSlot, String displayName, String... loreString) {
        List<String> lore = new ArrayList();
        ItemStack item = new ItemStack(Material.matchMaterial(material), amount, (short) byteId);
        return addItemStackLore(inv, invSlot, displayName, lore, item, loreString);
    }

    // Add meta and add to inventory
    private static ItemStack addItemStackLore(Inventory inv, int invSlot, String displayName, List<String> lore, ItemStack item, String[] loreString) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(chat(displayName));

        for (String s : loreString) {
            lore.add(chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(invSlot, item);
        return item;
    }

    // Send every player on the server a title
    public static void titleEveryone(String title, String subtitle, int fadeInTicks, int stayTicks, int fadeOutTicks) {
        for (Player player : Bukkit.getOnlinePlayers())
            player.sendTitle(chat(title), chat(subtitle), fadeInTicks, stayTicks, fadeOutTicks);
    }

    // Send a title to only a player
    public static void titlePlayer(Player player, String title, String subtitle, int fadeInSecs, int staySecs, int fadeOutSecs) {
        player.sendTitle(chat(title), chat(subtitle), fadeInSecs * 20, staySecs * 20, fadeOutSecs * 20);
    }

    // custom
    public static boolean isValidCoinBlock(Material mat) {
        return  mat == Material.DIAMOND_ORE ||
                mat == Material.DEEPSLATE_DIAMOND_ORE ||
                mat == Material.EMERALD_ORE ||
                mat == Material.DEEPSLATE_EMERALD_ORE ||
                mat == Material.ANCIENT_DEBRIS;
    }

    public static ItemStack createCoin(Integer value) {
        ItemStack item = new ItemStack(Material.SUNFLOWER);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(10);
        meta.setDisplayName(chat("&e&lCoin"));
        item.setItemMeta(meta);
        NBTStorage.setItemStackInt(item, "osmp_coin_value", value);
        NBTStorage.setItemStackBool(item, "osmp_coin_pickup", true);
        return item;
    }
}
