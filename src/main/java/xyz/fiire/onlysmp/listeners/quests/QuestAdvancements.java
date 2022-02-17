package xyz.fiire.onlysmp.listeners.quests;

import org.bukkit.Bukkit;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.SQLite;
import xyz.fiire.onlysmp.utils.Utils;

public class QuestAdvancements implements Listener {

    private final OnlySMP plugin;


    public QuestAdvancements(OnlySMP plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private static void addCoins(Player p, Integer amount) {
        Integer current = (Integer) SQLite.getUserValue("amount", p.getUniqueId().toString());
        int newAmount = current + amount;
        SQLite.setUserValue("amount", Integer.toString(newAmount), p.getUniqueId().toString());
        p.sendMessage(Utils.chat(String.format("&e&lYou just got %s coins for completing a quest!", amount)));
    }

    @EventHandler
    public void onAdvancement(PlayerAdvancementDoneEvent e) {
        Advancement advancement = e.getAdvancement();
        Player p = e.getPlayer();
        String key = advancement.getKey().toString();
        Utils.debug(advancement.getKey().toString());
        switch (key) {
            case "minecraft:adventure/hero_of_the_village":
                addCoins(p, 20);
                break;
            case "minecraft:story/enchant_item":
                addCoins(p, 10);
                break;
            default:
                break;
        }
    }
}
