package xyz.fiire.onlysmp.listeners.quests;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.QuestUtils;
import xyz.fiire.onlysmp.utils.SQLite;
import xyz.fiire.onlysmp.utils.Utils;

public class QuestOnCraft implements Listener {

	private final OnlySMP plugin;

	public QuestOnCraft(OnlySMP plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onDeath(CraftItemEvent e) {
		Player p = (Player) e.getWhoClicked();
		ItemStack item = e.getInventory().getResult();
		if (item.getType() == Material.NETHERITE_INGOT) {
			if (SQLite.getValKeyExist("first-netherite")) return;
			Utils.debug("setting sql");
			SQLite.setVal("first-netherite", p.getName());
			Utils.debug(SQLite.getVal("first-netherite").toString());
			QuestUtils.finishQuest(p, "first-netherite");
		}
	}
}
