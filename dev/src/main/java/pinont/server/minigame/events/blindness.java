package pinont.server.minigame.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class blindness implements Listener {

    @EventHandler
        public void onPlayerMove (PlayerMoveEvent e){
            Location from = e.getFrom();
            Location to = e.getTo();
            Player player = e.getPlayer();
            if (from.getBlockY() < to.getBlockY() && !player.isSwimming() && !player.isFlying() && player.isSprinting()) {
                player.sendMessage("B-hopping...");
                player.setWalkSpeed(0.5f);
            }
        }
}
