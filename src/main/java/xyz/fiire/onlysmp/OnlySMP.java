package xyz.fiire.onlysmp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.fiire.onlysmp.commands.*;
import xyz.fiire.onlysmp.gui.GUIHandler;
import xyz.fiire.onlysmp.gui.QuestGUI;
import xyz.fiire.onlysmp.listeners.PlayerJoin;
import xyz.fiire.onlysmp.listeners.coins.CoinsOnBreak;
import xyz.fiire.onlysmp.listeners.coins.CoinsOnPickup;
import xyz.fiire.onlysmp.listeners.coins.CoinsOnPlace;
import xyz.fiire.onlysmp.listeners.quests.*;
import xyz.fiire.onlysmp.papi.PAPI;
import xyz.fiire.onlysmp.utils.*;

public final class OnlySMP extends JavaPlugin {

    @Override
    public void onEnable() {
        // Init Utils
        new NBTStorage(this);
        new Utils(this);
        new Config(this);
        new SQLite(this);
        new QuestUtils(this);

        // Init Commands
        new TestCommand(this);
        new PayCommand(this);
        new DepositCommand(this);
        new WithdrawCommand(this);
        new QuestsCommand(this);

        // Fuck you Tea
        new TeaCommand(this);

        // Init GUIs
        new QuestGUI(this);

        // Init Listeners
        new GUIHandler(this);
        new PlayerJoin(this);
        // Coin Listeners
        new CoinsOnBreak(this);
        new CoinsOnPlace(this);
        new CoinsOnPickup(this);
        // Quest Listeners
        new QuestAdvancements(this);
        new QuestOnDeath(this);
        new QuestOnKill(this);
        new QuestOnInteract(this);
        new QuestOnMine(this);
        new QuestOnCraft(this);

        // Init PAPI
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Utils.info("Loading PlaceholderAPI related Content...");
            // TBI https://github.com/PlaceholderAPI/PlaceholderAPI/wiki/PlaceholderExpansion
            new PAPI(this).register();
        } else {
            Utils.severe("PlaceholderAPI is required for this plugin to work! Unloading...");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
