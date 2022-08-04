package pinont.server.minigame.utils.worldgen;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pinont.server.minigame.Minigame;

import javax.swing.plaf.synth.Region;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class worldGen implements Listener {
    @EventHandler
    public void WCStart(PlayerInteractEvent e) {

    }

    public void chunksList(Location x, Location y, Location z) {
        List Arr = new ArrayList<>(Minigame.plugin.getConfig().getStringList("WardenChunks.*"));

    }

}
