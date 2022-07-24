package pinont.server.minigame.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pinont.server.minigame.Minigame;

import static org.bukkit.Bukkit.getScheduler;

public class speed implements Listener {
        @EventHandler
        public void onPlayerMove (PlayerMoveEvent e){
            Location from = e.getFrom();
            Location to = e.getTo();
            Player player = e.getPlayer();
            player.sendMessage("Checked Move!");
//             while (from.getBlockY() == to.getBlockY()) {
//                 player.setWalkSpeed(0.1f);
//                 player.sendMessage("Stopped B-hop..");
//             }.runTaskLater(new Minigame(), 20*4);
            getScheduler().scheduleSyncDelayedTask(new Minigame(), new Runnable() {
                    @Override
                    public void run() {
                        if (from.getBlockY() == to.getBlockY()) {
                            player.sendMessage("run");
                        }
                    }

            }, 20L);
//             new BukkitRunnable(){
//                 @Override
//                 public void run() {
//                     player.sendMessage("stopping B-hop...");
//                     if (from.getBlockY() == to.getBlockY()) {
//                         player.setWalkSpeed(0.2f);
//                     }
//                 }
//
//             }.runTaskLater(new Minigame(), 20*4);
        }
    }
