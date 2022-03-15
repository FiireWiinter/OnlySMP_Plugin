package xyz.fiire.onlysmp.listeners.quests;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.QuestUtils;

public class QuestOnMine implements Listener {

    private final OnlySMP plugin;

    public QuestOnMine(OnlySMP plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onMine(BlockBreakEvent e) {
        Player p = e.getPlayer();
        int stone = p.getStatistic(Statistic.MINE_BLOCK, Material.STONE);
        int deepslate = p.getStatistic(Statistic.MINE_BLOCK, Material.DEEPSLATE);
        int amount = stone + deepslate;

        if (amount >= 10000) QuestUtils.finishQuest(p, "stone-10k");
    }
}
