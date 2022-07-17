package pinont.server.minigame.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class wardens implements Listener {
    @EventHandler
    public void onPlayerSprint(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(p.getWorld().getName().equals("Wardens")) { // if player event trigger at Wardens game
            if(p.isSprinting()) {
                e.setCancelled(true); // cancel sprint event
            }
        }
    }
}
