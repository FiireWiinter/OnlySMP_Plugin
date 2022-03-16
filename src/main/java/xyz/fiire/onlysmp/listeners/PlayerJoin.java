package xyz.fiire.onlysmp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.Config;
import xyz.fiire.onlysmp.utils.SQLite;
import xyz.fiire.onlysmp.utils.Utils;

import java.util.ArrayList;
import java.util.Random;

public class PlayerJoin implements Listener {

    private final OnlySMP plugin;

    public PlayerJoin(OnlySMP plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        // Add Player to the Coin Table
        SQLite.addPlayer(e.getPlayer());

        // Randomize player
        if (SQLite.getValKeyExist(e.getPlayer().getName())) return;
        @SuppressWarnings("unchecked")
        ArrayList<String> players = (ArrayList<String>) Config.getConfig().get("list_of_player");
        if (!players.contains(e.getPlayer().getName())) {
            e.getPlayer().sendMessage(Utils.chat("&c&lYou don't seem to be a player of the SMP! You will not have access to the quests!"));
            return;
        }
        players.remove(e.getPlayer().getName());
        String randomPlayer = players.get(new Random().nextInt(players.size()));
        SQLite.setVal(e.getPlayer().getName(), randomPlayer);
        Utils.debug(String.format("%s gonna kill %s", e.getPlayer().getName(), randomPlayer));

    }
}
