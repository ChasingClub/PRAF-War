<<<<<<<< HEAD:dev/src/main/java/praf/server/main/events/Bhopping.java
package praf.server.main.events;
========
package praf.server.events;
>>>>>>>> c292d9304f1200056fc9e0f4bf3cebfcebc5f51b:dev/src/main/java/praf/server/events/Bhopping.java

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Bhopping implements Listener {

    @EventHandler
    public void onPlayerJump(PlayerJumpEvent e) {
        Player player = e.getPlayer();
<<<<<<<< HEAD:dev/src/main/java/praf/server/main/events/Bhopping.java
        if (player.getLocation().getWorld().getName().endsWith("world")) {
========
        if (player.getLocation().getWorld().getName().endsWith("world") || player.getLocation().getWorld().getName().endsWith("Lobby")) {

            if (player.getLocation().getWorld().getName().endsWith("Lobby") && player.hasPermission("lobby.bhop") && player.isSneaking() && player.isSprinting() && !(player.isFlying()) && !(player.isInLava()) && !(player.isClimbing()) && !(player.isSwimming())) {
                player.setVelocity(player.getLocation().getDirection());
            }
//
>>>>>>>> c292d9304f1200056fc9e0f4bf3cebfcebc5f51b:dev/src/main/java/praf/server/events/Bhopping.java
            if (player.isSneaking() && player.isSprinting() && !(player.isFlying()) && !(player.isInLava()) && !(player.isClimbing()) && !(player.isSwimming()) && player.getLocation().getBlockY() <= 80) {
                if (player.getLocation().getWorld().getName().endsWith("world")) {
                    player.setFoodLevel(player.getFoodLevel() - 3);
                }
                player.setVelocity(player.getLocation().getDirection());
            }
        }
//        if (player.getLocation().getWorld().getName().endsWith("world_nether")) {
//            if (player.isSneaking() && player.isSprinting() && !(player.isFlying()) && !(player.isInLava()) && !(player.isClimbing()) && !(player.isSwimming())) {
//                if (player.getLocation().getWorld().getName().endsWith("world")) {
//                    player.setFoodLevel(player.getFoodLevel() - 3);
//                }
//                player.setVelocity(player.getLocation().getDirection());
//            }
//        }
    }
}