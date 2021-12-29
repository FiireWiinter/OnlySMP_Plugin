package xyz.fiire.onlysmp.listeners.coins;

import org.bukkit.Bukkit;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import xyz.fiire.onlysmp.OnlySMP;

public class CoinsAdvancements implements Listener {
    private final OnlySMP plugin;

    public CoinsAdvancements(OnlySMP plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onAdvancement(PlayerAdvancementDoneEvent e) {
        Advancement advancement = e.getAdvancement();
        Player p = e.getPlayer();
        String key = advancement.getKey().toString();

    }
}
