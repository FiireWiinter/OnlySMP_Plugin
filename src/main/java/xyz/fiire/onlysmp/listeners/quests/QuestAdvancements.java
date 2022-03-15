package xyz.fiire.onlysmp.listeners.quests;

import org.bukkit.Bukkit;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.QuestUtils;
import xyz.fiire.onlysmp.utils.SQLite;
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
        switch (key) {
            case "minecraft:adventure/hero_of_the_village":
                QuestUtils.finishQuest(p, "complete-raid");
                break;
            case "minecraft:story/enchant_item":
                QuestUtils.finishQuest(p, "enchant-item");
                break;
            case "minecraft:adventure/adventuring_time":
                QuestUtils.finishQuest(p, "adventuring-time");
                break;
            case "minecraft:nether/explore_nether":
                QuestUtils.finishQuest(p, "hot-tourist");
                break;
            case "minecraft:story/mine_diamond":
                if (SQLite.getValKeyExist("first-diamond")) break;
                Utils.debug("setting sql");
                SQLite.setVal("first-diamond", p.getName());
                Utils.debug(SQLite.getVal("first-diamond").toString());
                QuestUtils.finishQuest(p, "first-diamond");
                break;
            default:
                break;
        }
    }
}
