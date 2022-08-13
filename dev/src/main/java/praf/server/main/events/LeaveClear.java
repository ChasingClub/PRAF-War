package praf.server.main.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import static praf.server.main.PRAF.build;
import static praf.server.main.PRAF.msgconsole;

public class LeaveClear implements Listener {
    @EventHandler
    public void onleavesv(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if (build.contains(p.getName())) {
            build.remove(p.getName());
            msgconsole(p.getName()+"'s build mode has been disabled, Because They left the server.");
        }
        if (p.getLocation().getWorld().getName().endsWith("world") && p.getGameMode() != GameMode.CREATIVE) {
            p.getInventory().clear();
            for (PotionEffect effect : p.getActivePotionEffects())
                p.removePotionEffect(effect.getType());
        }
    }
}
