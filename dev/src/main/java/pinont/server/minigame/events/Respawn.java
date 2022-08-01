package pinont.server.minigame.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Respawn implements Listener {
    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (p.getLocation().getWorld().getName().endsWith("world")) {
            World SessionWorld = Bukkit.getServer().getWorld("world");
            Location SessionWorldSpawn = new Location(SessionWorld, 64.5, 180.5, 26.5);
            e.setRespawnLocation(SessionWorldSpawn);
            p.getInventory().clear();
            p.sendMessage(ChatColor.RED + "You Died.");
        }
    }
}