package pinont.server.minigame.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pinont.server.minigame.Minigame;

import java.util.HashMap;

public class joinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (Minigame.combatList.containsKey(e.getPlayer().getName())) {
            Minigame.combatList.put(p.getName(), 0);
        }
            p.setGameMode(GameMode.ADVENTURE);
            World SessionWorld = Bukkit.getServer().getWorld("world");
            Location SessionWorldSpawn = new Location(SessionWorld, 64.5, 180, 26.5);
            p.teleport(SessionWorldSpawn);
            p.setWalkSpeed(0.2F); // default walk speed is 2F
    }

}
