package pinont.server.minigame.events;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class Bhopping implements Listener {

    @EventHandler
    public void onPlayerJump(PlayerMoveEvent e) {
        Location from = e.getFrom();
        Location to = e.getTo();
        Player player = e.getPlayer();
        player.setWalkSpeed(0.2F);
        if (!(player.isJumping()) && !(player.isSprinting())) {
            player.setWalkSpeed(0.2F);
            player.sendMessage("not jump and not sprint");
        } else if (from.getBlockY() == to.getBlockY() && player.isSprinting() && player.isJumping()){
            player.setWalkSpeed(0.3F);
            player.sendMessage("jump and sprint");
        }
    }
}
