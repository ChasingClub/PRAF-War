package praf.server.main.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import static praf.server.main.PRAF.build;

public class cancelevent implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if (!(build.contains(p.getName())))
            {e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if (!(build.contains(p.getName()))) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block blk = e.getClickedBlock();
        if(blk == null) {
            return;
        }else if (blk.getType().name().startsWith("POTTED_") || blk.getType() == Material.FLOWER_POT) {
            if (!(build.contains(p.getName()))) {
                e.setCancelled(true);
                return;
            }
        }
    }
}
