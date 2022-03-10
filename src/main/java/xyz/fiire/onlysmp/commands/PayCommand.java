package xyz.fiire.onlysmp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.QuestUtils;
import xyz.fiire.onlysmp.utils.SQLite;
import xyz.fiire.onlysmp.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PayCommand implements TabExecutor {
    public PayCommand(OnlySMP plugin) {
        plugin.getCommand("pay").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can not be ran through Console");
            return true;
        }
        if (args.length == 2) {
            Player sendingPlayer = (Player) sender;
            Player receivingPlayer = Bukkit.getPlayerExact(args[0]);
            if (receivingPlayer == null) {
                sendingPlayer.sendMessage(Utils.chat("&c&lPlayer not found! Are they online?"));
                return true;
            }
            if (sendingPlayer.getPlayer() == receivingPlayer.getPlayer()) {
                sendingPlayer.sendMessage(Utils.chat("&c&lYou can't send money to yourself!"));
            }
            Integer amount = Integer.parseInt(args[1]);
            Integer sendingPlayerCurrent = (Integer) SQLite.getUserValue("amount", sendingPlayer.getUniqueId().toString());
            Integer receivingPlayerCurrent = (Integer) SQLite.getUserValue("amount", receivingPlayer.getUniqueId().toString());
            if (amount <= 0) {
                sendingPlayer.sendMessage(Utils.chat("&c&lPlease specify a amount over 0!"));
                return true;
            }
            if (sendingPlayerCurrent < amount) {
                sendingPlayer.sendMessage(Utils.chat("&c&lYou don't have that kind of money!"));
                return true;
            }

            // all checks have passed
            sendingPlayerCurrent -= amount;
            receivingPlayerCurrent += amount;
            SQLite.setUserValue("amount", sendingPlayerCurrent.toString(), sendingPlayer.getUniqueId().toString());
            SQLite.setUserValue("amount", receivingPlayerCurrent.toString(), receivingPlayer.getUniqueId().toString());
            sendingPlayer.sendMessage(Utils.chat(String.format("&a&lYou just sent %s coins to %s", amount, receivingPlayer.getDisplayName())));
            receivingPlayer.sendMessage(Utils.chat(String.format("&e&lYou just received %s coins from %s", amount, sendingPlayer.getDisplayName())));

            // Complete /pay quest
            if (!QuestUtils.isPlayerQuestCompleted(sendingPlayer, "command-pay")) QuestUtils.finishQuest(sendingPlayer, "command-pay");
            return true;
        } else {
            return false;
        }
    }

    // Tab Completion
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1) {
                List<String> result = new ArrayList<>();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    result.add(player.getName());
                }
                return Utils.filterByStart(result, args[0]);
            }
            return Collections.singletonList("");
        }
        return null;
    }
}
