package xyz.fiire.onlysmp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.Config;
import xyz.fiire.onlysmp.utils.NBTStorage;

public class TestCommand implements CommandExecutor {
    public TestCommand(OnlySMP plugin) {
        plugin.getCommand("test").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (p.getUniqueId().toString().equals("a9085607-f473-45f1-90ec-7ad71d697853")) {
            switch (args[0]) {
                case "config": {
                    Config.reloadConfig();
                    p.sendMessage("reloaded");
                    break;
                }
                case "debug": {
                    Boolean debug = NBTStorage.getPlayerBool(p, "osmp_debug");
                    NBTStorage.setPlayerBool(p, "osmp_debug", !debug);
                    p.sendMessage("set debug to " + !debug);
                    break;
                }
            }
        }
        return true;
    }
}
