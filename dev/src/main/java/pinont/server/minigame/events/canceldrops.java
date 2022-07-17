package pinont.server.minigame.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class canceldrops implements Listener {

    @EventHandler
    public void onPlayerDrops(PlayerDropItemEvent event) {
        if((event.getPlayer().getWorld().getName()).equals("Netherite_game")) {
            event.setCancelled(true); // cancel player drop event
        }
    }

}
