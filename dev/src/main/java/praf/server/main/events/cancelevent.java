<<<<<<<< HEAD:dev/src/main/java/praf/server/main/events/cancelevent.java
package praf.server.main.events;
========
package praf.server.events;
>>>>>>>> c292d9304f1200056fc9e0f4bf3cebfcebc5f51b:dev/src/main/java/praf/server/events/cancelevent.java

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
