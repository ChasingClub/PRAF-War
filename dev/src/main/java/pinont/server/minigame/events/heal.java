package pinont.server.minigame.events;

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
