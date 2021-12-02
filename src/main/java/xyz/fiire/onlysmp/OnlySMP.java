package xyz.fiire.onlysmp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.fiire.onlysmp.papi.PAPI;
import xyz.fiire.onlysmp.utils.NBTStorage;
import xyz.fiire.onlysmp.utils.Utils;

public final class OnlySMP extends JavaPlugin {

    @Override
    public void onEnable() {
        // Init Utils
        new NBTStorage(this);
        new Utils(this);

        // Init Commands
        // tbi

        // Init GUIs
        // tbi

        // Init Listeners
        // tbi

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
