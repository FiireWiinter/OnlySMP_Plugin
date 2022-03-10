package xyz.fiire.onlysmp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.NBTStorage;
import xyz.fiire.onlysmp.utils.QuestUtils;
import xyz.fiire.onlysmp.utils.SQLite;
import xyz.fiire.onlysmp.utils.Utils;

import java.util.Collections;
import java.util.List;

public class WithdrawCommand implements TabExecutor {
    public WithdrawCommand(OnlySMP plugin) {
        plugin.getCommand("withdraw").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can not be ran through Console");
            return true;
        }
        if (args.length != 1) return false;

        Player p = (Player) sender;
        int amount;
        try {
            amount = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            p.sendMessage(Utils.chat("&c&lPlease specify a valid number!"));
            return true;
        }
        int coins = Integer.parseInt(QuestUtils.getCoinsAmountPlayer(p));
        if (coins - amount < 0) {
            p.sendMessage(Utils.chat("&c&lYou don't have that kind of money!"));
            return true;
        }
        SQLite.setUserValue("amount", Integer.toString(coins-amount), p.getUniqueId().toString());

        ItemStack item = Utils.createCoin(1);
        item.setAmount(amount);
        NBTStorage.setItemStackBool(item, "osmp_coin_pickup", false);
        p.getWorld().dropItemNaturally(p.getLocation(), item);

        // Complete /withdraw 50 quest
        if (!QuestUtils.isPlayerQuestCompleted(p, "command-withdraw") && amount >= 50) QuestUtils.finishQuest(p, "command-withdraw");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (sender instanceof Player) {
            return Collections.emptyList();
        }
        return null;
    }
}
