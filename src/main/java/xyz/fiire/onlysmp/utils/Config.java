package xyz.fiire.onlysmp.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.fiire.onlysmp.OnlySMP;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class Config {
    private static OnlySMP plugin;
    private static FileConfiguration dataConfig = null;
    private static File configFile = null;

    public Config(OnlySMP plugin) {
        Config.plugin = plugin;
        saveDefaultConfig();
    }

    // Reload the config, in case something shit broke
    public static void reloadConfig() {
        if (configFile == null) {
            configFile = new File(plugin.getDataFolder(), "config.yml");
        }

        dataConfig = YamlConfiguration.loadConfiguration(configFile);
        InputStream defaultStream = plugin.getResource("config.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            dataConfig.setDefaults(defaultConfig);
        }
    }

    // Retreive the config
    public static FileConfiguration getConfig() {
        if (dataConfig == null) {
            reloadConfig();
        }
        return dataConfig;
    }

    // Save the config to a file
    private static void saveConfig() {
        if (dataConfig == null || configFile == null) {
            return;
        }
        try {
            getConfig().save(configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, e);
        }
    }

    // If the default config doesn't exist, create it
    private void saveDefaultConfig() {
        if (configFile == null) {
            configFile = new File(plugin.getDataFolder(), "config.yml");
        }

        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }
    }

    // Fetch a string from the config
    public static String get_str(String key) {
        String str = getConfig().getString(key);
        Utils.debug("&l&dGET STRING KEY &l&b" + key + "&l&d VALUE &l&b" + str);
        return str;
    }

    // Fetch a boolean from the config
    public static boolean get_bool(String key) {
        boolean bool = getConfig().getBoolean(key);
        Utils.debug("&l&dGET BOOLEAN KEY &l&b" + key + "&l&d VALUE &l&b" + bool);
        return bool;
    }

    public static Integer get_int(String key) {
        Integer integer = getConfig().getInt(key);
        Utils.debug("&l&dGET INTEGER KEY &l&b" + key + "&l&d VALUE &l&b" + integer);
        return integer;
    }
}
