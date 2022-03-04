package xyz.fiire.onlysmp.commands;

import org.bukkit.Material;
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

public class DepositCommand implements TabExecutor {
    public DepositCommand(OnlySMP plugin) {
        plugin.getCommand("deposit").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can not be ran through Console");
            return true;
        }
        Player p = (Player) sender;
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item.getType() == Material.SUNFLOWER) {
            if (item.getItemMeta().hasCustomModelData()) {
                if (item.getItemMeta().getCustomModelData() == 10) {
                    // Coin detected
                    Integer value = NBTStorage.getItemStackInt(item, "osmp_coin_value");
                    int curAmount = Integer.parseInt(QuestUtils.getCoinsAmountPlayer(p));
                    int newAmount = curAmount + (value*item.getAmount());
                    SQLite.setUserValue("amount", Integer.toString(newAmount), p.getUniqueId().toString());

                    p.sendMessage(Utils.chat(String.format("&a&lDeposited %s coins to your account.", value*item.getAmount())));
                    item.setAmount(0);
                }
            }
        }
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
