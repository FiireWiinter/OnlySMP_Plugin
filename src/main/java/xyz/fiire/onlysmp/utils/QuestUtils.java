package xyz.fiire.onlysmp.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import xyz.fiire.onlysmp.OnlySMP;

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

    public static void finishQuest(Player p, Integer coinAmount, String questKey) {
        if (isPlayerQuestCompleted(p, questKey)) return;
        Integer current = (Integer) SQLite.getUserValue("amount", p.getUniqueId().toString());
        int newAmount = current + coinAmount;
        SQLite.setUserValue("amount", Integer.toString(newAmount), p.getUniqueId().toString());
        p.sendMessage(Utils.chat(String.format("&e&lYou just got %s coins for completing a quest!", coinAmount)));
    }

    private static List<String> getPlayerQuests(Player p) {
        String list = NBTStorage.getPlayerPDC(p).get(new NamespacedKey(plugin, "osmp_quests"), PersistentDataType.STRING);
        return Arrays.asList(list.split("##"));
    }

    private static void savePlayerQuests(Player p, List<String> questList) {
        String finalList = String.join("##", questList);
        NBTStorage.setPlayerString(p, "osmp_quests", finalList);
    }

    private static void addPlayerQuest(Player p, String questKey) {
        List<String> list = getPlayerQuests(p);
        list.add(questKey);
        savePlayerQuests(p, list);
    }

    public static boolean isPlayerQuestCompleted(Player p, String questKey) {
        List<String> list = getPlayerQuests(p);
        return list.contains(questKey);
    }
}
