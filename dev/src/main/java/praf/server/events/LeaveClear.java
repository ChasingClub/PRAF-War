package praf.server.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;

public class LeaveClear implements Listener {
    @EventHandler
    public void onleavesv(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if (p.getLocation().getWorld().getName().endsWith("world") && p.getGameMode() != GameMode.CREATIVE) {
            p.getInventory().clear();
            for (PotionEffect effect : p.getActivePotionEffects())
                p.removePotionEffect(effect.getType());
        }
    }
}
