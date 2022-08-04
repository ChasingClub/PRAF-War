package pinont.server.minigame.utils.machanic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import pinont.server.minigame.Minigame;

import java.util.List;
import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public class PlayerKilled implements Listener {
    @EventHandler
    public void onKill(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Player killer = p.getKiller();
        Random r = new Random();
        List<String> sl = Minigame.plugin.getConfig().getStringList("deadmsg");
        String s = sl.get(r.nextInt(sl.size()));
        if (killer instanceof Player && p instanceof Player) {
            if (killer.getName() == p.getName()) {
                World SessionWorld = getServer().getWorld("world");
                Location SessionWorldSpawn = new Location(SessionWorld, 64.5, 180.5, 26.5);
                p.teleport(SessionWorldSpawn);
                p.getInventory().clear();
                for (PotionEffect effect : p.getActivePotionEffects())
                    p.removePotionEffect(effect.getType());
                Bukkit.broadcastMessage(ChatColor.RED + p.getName() + ChatColor.YELLOW + " has killed themself by accident.");
                return;
            }
            Bukkit.broadcastMessage(ChatColor.RED + p.getName() + ChatColor.YELLOW + s + ChatColor.DARK_AQUA + killer.getName());
        }
    }
}
