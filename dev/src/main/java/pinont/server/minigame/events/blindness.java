package pinont.server.minigame.events;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class blindness implements Listener {

    @EventHandler
    public void onPlayerJump(PlayerJumpEvent e) {
        Player player = e.getPlayer();
        player.sendMessage("Jump!");
        if (player.isSprinting() && player.isJumping()) {
            player.setWalkSpeed(0.5F);
            player.sendMessage("Bhop");
            if(!player.isSprinting()) {
                player.sendMessage("run!");
                player.setWalkSpeed(0.25F);
            }
        }

//        player.sendMessage("you just jumped");
    }
}
