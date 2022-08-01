package pinont.server.minigame.events;

import org.bukkit.Bukkit;
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
        if (killer instanceof Player && p instanceof Player) {
            killer.setHealth(killer.getMaxHealth());
            killer.setFoodLevel(21);
        }
    }
}
