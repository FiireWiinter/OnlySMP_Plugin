package xyz.fiire.onlysmp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.Config;
import xyz.fiire.onlysmp.utils.NBTStorage;
import xyz.fiire.onlysmp.utils.QuestUtils;
import xyz.fiire.onlysmp.utils.Utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestCommand implements TabExecutor {
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
                case "temp": {
                    p.sendMessage(Utils.chat("&c&k&lI shall summon the holy lemon"));
                    break;
                }
                case "giveCoin": {
                    int value = Integer.parseInt(args[1]);
                    int amount = Integer.parseInt(args[2]);
                    ItemStack coins = Utils.createCoin(value);
                    coins.setAmount(amount);
                    p.getInventory().addItem(coins);
                    break;
                }
                case "checkQuest": {
                    p.sendMessage(QuestUtils.getPlayerQuests(p).toString());
                    break;
                }
                case "resetQuest": {
                    NBTStorage.setPlayerString(p, "osmp_quests", "");
                    p.sendMessage("reseted :)");
                    break;
                }
            }
        }
        return true;
    }

    // Tab Completion
    private static final List<String> tabSelection = Arrays.asList("config", "debug", "temp", "giveCoin", "checkQuest", "resetQuest");

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            switch (args.length) {
                case 1:
                    return Utils.filterByStart(tabSelection, args[0]);
                case 2:
                    if (args[0].equals("giveCoin")) return Collections.singletonList("value");
                    return Collections.emptyList();
                case 3:
                    if (args[0].equals("giveCoin")) return Collections.singletonList("amount");
                    return Collections.emptyList();
            }
            return Collections.emptyList();
        }
        return null;
    }
}
