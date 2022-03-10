package xyz.fiire.onlysmp.listeners.quests;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.QuestUtils;
import xyz.fiire.onlysmp.utils.Utils;

public class QuestOnDeath implements Listener {

    private final OnlySMP plugin;

    public QuestOnDeath(OnlySMP plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        String text = e.getDeathMessage();
        assert text != null;
        if (text.contains("fell from a high place")) {
            QuestUtils.finishQuest(p, "death-height");
        }
    }
}
