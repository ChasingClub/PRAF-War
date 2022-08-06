package pinont.server.minigame.events;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class Bhopping implements Listener {



    @EventHandler
    public void onPlayerJump(PlayerJumpEvent e) throws InterruptedException {
        Player player = e.getPlayer();
        if (player.getLocation().getWorld().getName().endsWith("world")) {
            if (player.isSneaking() && player.isSprinting() && !(player.isFlying()) && !(player.isInLava()) && !(player.isClimbing()) && !(player.isSwimming())) {
                if (player.getItemInUse().getType().equals(Material.COOKED_BEEF)) {
                    player.sendMessage("Eating");

                }
                if (player.getLocation().getWorld().getName().endsWith("world")) {
                    player.setFoodLevel(player.getFoodLevel() - 3);
                }
                player.setVelocity(player.getLocation().getDirection());
                wait(5000); // wait for 5 sec and add Food level back
                player.setFoodLevel(player.getFoodLevel() + 3);
                player.sendMessage("Refill Feed bar!");
            }
        }
    }
}
