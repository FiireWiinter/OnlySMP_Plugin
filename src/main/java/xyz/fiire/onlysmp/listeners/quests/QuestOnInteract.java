package xyz.fiire.onlysmp.listeners.quests;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import xyz.fiire.onlysmp.OnlySMP;
import xyz.fiire.onlysmp.utils.QuestUtils;

public class QuestOnInteract implements Listener {

    private final OnlySMP plugin;

    public QuestOnInteract(OnlySMP plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private boolean isDisc(Material mat) {
        return  mat == Material.MUSIC_DISC_13        ||
                mat == Material.MUSIC_DISC_CAT       ||
                mat == Material.MUSIC_DISC_BLOCKS    ||
                mat == Material.MUSIC_DISC_CHIRP     ||
                mat == Material.MUSIC_DISC_FAR       ||
                mat == Material.MUSIC_DISC_MALL      ||
                mat == Material.MUSIC_DISC_MELLOHI   ||
                mat == Material.MUSIC_DISC_STAL      ||
                mat == Material.MUSIC_DISC_STRAD     ||
                mat == Material.MUSIC_DISC_WARD      ||
                mat == Material.MUSIC_DISC_11        ||
                mat == Material.MUSIC_DISC_WAIT      ||
                mat == Material.MUSIC_DISC_OTHERSIDE ||
                mat == Material.MUSIC_DISC_PIGSTEP;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Action action = e.getAction();
        if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
            Block block = e.getClickedBlock();
            if (block.getType() == Material.JUKEBOX) {
                if (isDisc(e.getItem().getType())) {
                    QuestUtils.finishQuest(e.getPlayer(), "play-disc");
                }
            }
        }
    }
}
