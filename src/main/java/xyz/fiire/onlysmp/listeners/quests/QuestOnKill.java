package xyz.fiire.onlysmp.listeners.quests;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.QuestUtils;
import xyz.fiire.onlysmp.utils.SQLite;
import xyz.fiire.onlysmp.utils.Utils;

public class QuestOnKill implements Listener {

    private final OnlySMP plugin;

    public QuestOnKill(OnlySMP plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onKill(EntityDeathEvent e) {
        Entity entity = e.getEntity();
        Player killer = e.getEntity().getKiller();
        assert killer != null;
        if (!QuestUtils.isSMPPlayer(killer)) return;
        Utils.debug("Death of " + entity.getName() + " by " + killer.getName());
        Utils.debug("Entity Name is " + entity.getName());
        switch (entity.getName()) {
            case "Wither": {
                QuestUtils.finishQuest(killer, "kill-wither");
                break;
            }
        }

        // Random killing Quest
        if (entity.getType() == EntityType.PLAYER) {
            String player = SQLite.getVal(killer.getName()).toString();
            if (player.equals(entity.getName())) QuestUtils.finishQuest(killer, "random-kill");
        }
    }
}
