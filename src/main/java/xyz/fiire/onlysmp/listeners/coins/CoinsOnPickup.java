package xyz.fiire.onlysmp.listeners.coins;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.NBTStorage;
import xyz.fiire.onlysmp.utils.SQLite;
import xyz.fiire.onlysmp.utils.Utils;

public class CoinsOnPickup implements Listener {

    private final OnlySMP plugin;

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
                        int newAmount = currentNum + (amount * value);
                        SQLite.setUserValue("amount", Integer.toString(newAmount), playerUUID);

                        Location loc = e.getEntity().getLocation().add(0, 0.5, 0);
                        Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
                        FireworkMeta fwm = fw.getFireworkMeta();
                        fwm.setPower(2);
                        fwm.addEffect(FireworkEffect.builder().withColor(Color.YELLOW).flicker(true).build());
                        fw.setFireworkMeta(fwm);
                        fw.detonate();

                        String valueText = "";
                        if (value != 1) {
                            valueText = String.format(" with a coin value of %s", value);
                        }
                        Utils.actionMessage(player, String.format("&e&lPicked up %s coins%s. You now have %s coins", amount, valueText, newAmount));
                    }
                }
            }
        }
    }
}
