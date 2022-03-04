package xyz.fiire.onlysmp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import xyz.fiire.onlysmp.OnlySMP;
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
        sender.sendMessage(Utils.chat("&c&lTBI"));
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
