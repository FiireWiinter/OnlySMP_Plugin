package xyz.fiire.onlysmp.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import xyz.fiire.onlysmp.OnlySMP;

public class NBTStorage {
    private static OnlySMP plugin;

    public NBTStorage(OnlySMP plugin) {
        NBTStorage.plugin = plugin;
    }

    // PDC methods
    public static PersistentDataContainer getPlayerPDC(Player p) {
        return p.getPersistentDataContainer();
    }

    public static PersistentDataContainer getItemStackPDC(ItemStack i) { return i.getItemMeta().getPersistentDataContainer(); }

    // Get Functions Player
    public static String getPlayerString(Player p, String key) {
        return getPlayerPDC(p).get(new NamespacedKey(plugin, key), PersistentDataType.STRING);
    }

    public static Integer getPlayerInt(Player p, String key) {
        return getPlayerPDC(p).get(new NamespacedKey(plugin, key), PersistentDataType.INTEGER);
    }

    public static Boolean getPlayerBool(Player p, String key) {
        Integer num = getPlayerPDC(p).get(new NamespacedKey(plugin, key), PersistentDataType.INTEGER);
        if (num == null) {
            setPlayerBool(p, key, false);
            return false;
        }
        return num == 1;
    }

    // Set Functions Player
    public static void setPlayerString(Player p, String key, String val) {
        getPlayerPDC(p).set(new NamespacedKey(plugin, key), PersistentDataType.STRING, val);
    }

    public static void setPlayerInt(Player p, String key, Integer val) {
        getPlayerPDC(p).set(new NamespacedKey(plugin, key), PersistentDataType.INTEGER, val);
    }

    public static void setPlayerBool(Player p, String key, Boolean val) {
        int num;
        if (val) { num = 1; } else { num = 0; }
        getPlayerPDC(p).set(new NamespacedKey(plugin, key), PersistentDataType.INTEGER, num);
    }

    // Get Functions ItemStack
    public static Integer getItemStackInt(ItemStack i, String key) {
        return getItemStackPDC(i).get(new NamespacedKey(plugin, key), PersistentDataType.INTEGER);
    }

    public static Boolean getItemStackBool(ItemStack i, String key) {
        Integer num = getItemStackPDC(i).get(new NamespacedKey(plugin, key), PersistentDataType.INTEGER);
        if (num == null) {
            setItemStackBool(i, key, false);
            return false;
        }
        return num == 1;
    }

    // Set Functions ItemStack
    public static void setItemStackInt(ItemStack i, String key, Integer val) {
        ItemMeta meta = i.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.INTEGER, val);
        i.setItemMeta(meta);
    }

    public static void setItemStackBool(ItemStack i, String key, Boolean val) {
        int num;
        if (val) { num = 1; } else { num = 0; }
        ItemMeta meta = i.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.INTEGER, num);
        i.setItemMeta(meta);
    }


}
