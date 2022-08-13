<<<<<<<< HEAD:dev/src/main/java/praf/server/main/events/canceldrops.java
package praf.server.main.events;
========
package praf.server.events;
>>>>>>>> c292d9304f1200056fc9e0f4bf3cebfcebc5f51b:dev/src/main/java/praf/server/events/canceldrops.java

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

<<<<<<<< HEAD:dev/src/main/java/praf/server/main/events/canceldrops.java
import static praf.server.main.PRAF.Plname;
========
import static praf.server.praf.Plname;
>>>>>>>> c292d9304f1200056fc9e0f4bf3cebfcebc5f51b:dev/src/main/java/praf/server/events/canceldrops.java

public class canceldrops implements Listener {

    @EventHandler
    public void onPlayerDrops(PlayerDropItemEvent event) {
        Player p = event.getPlayer();
        if (p.getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
            p.sendMessage(Plname + ChatColor.RED + "You can't drop item.");
        }
    }
}