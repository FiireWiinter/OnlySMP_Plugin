package xyz.fiire.onlysmp.listeners.quests;

import org.bukkit.Bukkit;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.QuestUtils;
import xyz.fiire.onlysmp.utils.Utils;

public class QuestAdvancements implements Listener {

    private final OnlySMP plugin;


    public QuestAdvancements(OnlySMP plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onAdvancement(PlayerAdvancementDoneEvent e) {
        Advancement advancement = e.getAdvancement();
        Player p = e.getPlayer();
        String key = advancement.getKey().toString();
        Utils.debug(advancement.getKey().toString());
        switch (key) {
            case "minecraft:adventure/hero_of_the_village":
                QuestUtils.finishQuest(p, 20, "complete-raid");
                break;
            case "minecraft:story/enchant_item":
                QuestUtils.finishQuest(p, 10, "enchant-item");
                break;
            default:
                break;
        }
    }
}
