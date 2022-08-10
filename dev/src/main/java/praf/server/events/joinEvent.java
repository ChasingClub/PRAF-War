package praf.server.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import praf.server.praf;

public class joinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (praf.combatList.containsKey(e.getPlayer().getName())) {
            praf.combatList.put(p.getName(), 0);
        }
        if (p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR){
            p.setGameMode(GameMode.ADVENTURE);
            World SessionWorld = Bukkit.getServer().getWorld("world");
            Location SessionWorldSpawn = new Location(SessionWorld, 64.5, 180, 26.5);
            p.teleport(SessionWorldSpawn);
            p.getInventory().clear();
            for (PotionEffect effect : p.getActivePotionEffects())
                p.removePotionEffect(effect.getType());
            p.setWalkSpeed(0.2F); // default walk speed is 2F
        }
    }

}
