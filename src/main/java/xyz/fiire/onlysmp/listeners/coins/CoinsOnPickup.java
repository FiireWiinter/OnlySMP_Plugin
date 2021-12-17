package xyz.fiire.onlysmp.listeners.coins;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.NBTStorage;
import xyz.fiire.onlysmp.utils.SQLite;
import xyz.fiire.onlysmp.utils.Utils;

public class CoinsOnPickup implements Listener {

    private OnlySMP plugin;

    public CoinsOnPickup(OnlySMP plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        LivingEntity entity = e.getEntity();
        Item item = e.getItem();
        ItemStack itemStack = item.getItemStack();
        if (entity.getType() == EntityType.PLAYER) {
            if (itemStack.getType() == Material.SUNFLOWER) {
                if (!itemStack.getItemMeta().hasCustomModelData()) return;
                if (itemStack.getItemMeta().getCustomModelData() == 10) {
                    if (NBTStorage.getItemStackBool(itemStack, "osmp_coin_pickup")) {
                        e.setCancelled(true);
                        item.remove();
                        int amount = itemStack.getAmount();
                        Integer value = NBTStorage.getItemStackInt(itemStack, "osmp_coin_value");
                        Player player = (Player) entity;
                        String playerUUID = player.getUniqueId().toString();
                        Integer currentNum = (Integer) SQLite.getUserValue("amount", playerUUID);
                        int newAmount = currentNum+(amount*value);
                        SQLite.setUserValue("amount", Integer.toString(newAmount), playerUUID);
                        String debugCurrentAmount = SQLite.getUserValue("amount", playerUUID).toString();
                        Utils.debug(String.format("%s coins | value: %s | old total: %s | new total: %s", amount, amount*value, currentNum, debugCurrentAmount));
                    }
                }
            }
        }
    }
}
