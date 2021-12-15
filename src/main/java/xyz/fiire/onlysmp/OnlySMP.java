package xyz.fiire.onlysmp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.fiire.onlysmp.commands.TestCommand;
import xyz.fiire.onlysmp.listeners.CoinsOnBreak;
import xyz.fiire.onlysmp.listeners.CoinsOnPlace;
import xyz.fiire.onlysmp.papi.PAPI;
import xyz.fiire.onlysmp.utils.Config;
import xyz.fiire.onlysmp.utils.NBTStorage;
import xyz.fiire.onlysmp.utils.SQLite;
import xyz.fiire.onlysmp.utils.Utils;

public final class OnlySMP extends JavaPlugin {

    @Override
    public void onEnable() {
        // Init Utils
        new NBTStorage(this);
        new Utils(this);
        new Config(this);
        new SQLite(this);

        // Init Commands
        new TestCommand(this);

        // Init GUIs
        // tbi

        // Init Listeners
        new CoinsOnBreak(this);
        new CoinsOnPlace(this);

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
