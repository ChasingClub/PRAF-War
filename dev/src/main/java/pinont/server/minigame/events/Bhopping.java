package pinont.server.minigame.events;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Bhopping implements Listener {

    @EventHandler
    public void onPlayerJump(PlayerJumpEvent e) {
        Player player = e.getPlayer();
        if (player.isSneaking() && player.isSprinting() && !(player.isFlying()) && !(player.isInLava()) && !(player.isClimbing()) && !(player.isSwimming())){
            if (player.getLocation().getWorld().getName().endsWith("world")) {
                player.setFoodLevel(player.getFoodLevel() - 3);
            }
            player.setVelocity(player.getLocation().getDirection());
        }
    }
}
