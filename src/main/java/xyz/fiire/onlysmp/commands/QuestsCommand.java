package xyz.fiire.onlysmp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.gui.QuestGUI;
import xyz.fiire.onlysmp.utils.QuestUtils;
import xyz.fiire.onlysmp.utils.Utils;

import java.util.Collections;
import java.util.List;

public class QuestsCommand implements TabExecutor {
    public QuestsCommand(OnlySMP plugin) {
        plugin.getCommand("quests").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can not be ran through Console");
            return true;
        }
        Player p = (Player) sender;
        if (!QuestUtils.isSMPPlayer(p)) {
            p.sendMessage(Utils.chat("&c&lYou don't seem to be a player of the SMP! You do not have access to the quests!"));
            return true;
        }
        p.openInventory(QuestGUI.GUI(p));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            return Collections.emptyList();
        }
        return null;
    }
}
