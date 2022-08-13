<<<<<<<< HEAD:dev/src/main/java/praf/server/main/events/heal.java
package praf.server.main.events;
========
package praf.server.events;
>>>>>>>> c292d9304f1200056fc9e0f4bf3cebfcebc5f51b:dev/src/main/java/praf/server/events/heal.java

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class heal implements Listener {
    @EventHandler
    public void onKillGetHeal(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Player killer = p.getKiller();
        if (killer instanceof Player && p instanceof Player && killer.getName() != p.getName()) {
            double rounded = Math.round(killer.getHealth() * 10) / 10;
            p.sendMessage(killer.getName()+" have "+ChatColor.YELLOW+rounded+ChatColor.RED+"♥"+ChatColor.RESET+" left.");
            killer.setHealth(killer.getMaxHealth());
            killer.setFoodLevel(20);
            killer.setSaturation(20f);
        }
    }
}
