package xyz.fiire.onlysmp.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import xyz.fiire.onlysmp.OnlySMP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestUtils {

    private static OnlySMP plugin;

    public QuestUtils(OnlySMP plugin) {
        QuestUtils.plugin = plugin;
    }

    public static String getCoinsAmountPlayer(Player p) {
        return SQLite.getUserValue("amount", p.getUniqueId().toString()).toString();
    }

    /*public static void finishQuest(Player p, Integer coinAmount, String questKey) {
        if (isPlayerQuestCompleted(p, questKey)) return;
        Integer current = (Integer) SQLite.getUserValue("amount", p.getUniqueId().toString());
        int newAmount = current + coinAmount;
        SQLite.setUserValue("amount", Integer.toString(newAmount), p.getUniqueId().toString());
        addPlayerQuest(p, questKey);
        p.sendMessage(Utils.chat(String.format("&e&lYou just got %s coins for completing a quest!", coinAmount)));
    }*/

    public static void finishQuest(Player p, String questKey) {
        if (isPlayerQuestCompleted(p, questKey)) return;
        List<String> res = getQuestDetails(questKey);
        Integer current = (Integer) SQLite.getUserValue("amount", p.getUniqueId().toString());
        int newAmount = current + Integer.parseInt(res.get(3));
        SQLite.setUserValue("amount", Integer.toString(newAmount), p.getUniqueId().toString());
        addPlayerQuest(p, questKey);
        p.sendMessage(Utils.chat(String.format("&f&l[&b&lQuests&f&l] &eYou got %s coins for completing &f[&a%s&f]", res.get(3), res.get(0))));
    }

    public static List<String> getQuestDetails(String questKey) {
        questKey = String.format("quests.%s.", questKey);
        List<String> res = new ArrayList<>();
        res.add(Config.get_str(questKey + "title"));
        res.add(Config.get_str(questKey + "description"));
        res.add(Config.get_str(questKey + "item"));
        res.add(Config.get_str(questKey + "amount"));

        return res;
    }

    public static List<String> getPlayerQuests(Player p) {
        String list = NBTStorage.getPlayerPDC(p).get(new NamespacedKey(plugin, "osmp_quests"), PersistentDataType.STRING);
        if (list == null) { list = ""; }
        return Arrays.asList(list.split("##"));
    }

    private static void savePlayerQuests(Player p, List<String> questList) {
        String finalList = String.join("##", questList);
        NBTStorage.setPlayerString(p, "osmp_quests", finalList);
    }

    private static void addPlayerQuest(Player p, String questKey) {
        List<String> list = getPlayerQuests(p);
        List<String> newList = new ArrayList<>(list);
        newList.add(questKey);
        savePlayerQuests(p, newList);
    }

    public static boolean isPlayerQuestCompleted(Player p, String questKey) {
        List<String> list = getPlayerQuests(p);
        return list.contains(questKey);
    }
}
