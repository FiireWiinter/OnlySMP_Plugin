package xyz.fiire.onlysmp.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.Utils;

public class TeaCommand implements CommandExecutor {
    public TeaCommand(OnlySMP plugin) {
        plugin.getCommand("tea").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can not be ran through Console");
            return true;
        }
        Player p = (Player) sender;
        TextComponent message = new TextComponent(Utils.chat("&c&lFuck you MrTetleyTea"));
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.youtube.com/watch?v=iik25wqIuFo"));
        p.spigot().sendMessage(message);
        return true;
    }
}
