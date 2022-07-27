package pinont.server.minigame.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class combat_log implements Listener {

    public ArrayList<String> antilog = new ArrayList<String>();

    @EventHandler
    public void antiLog(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if(this.antilog.contains(p.getName())) {
            Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " has combat logged and a villager was spawned");
            p.damage(21.0D);
            Bukkit.getServer().getWorld("world").spawnEntity(p.getLocation(), EntityType.VILLAGER);
        }
    }

    @EventHandler
    public void antiLogDMG(EntityDamageByEntityEvent e) {
        if ((e.getDamager() instanceof Player) && ((e.getEntity() instanceof Player))) {
            final Player player = (Player) e.getEntity();
            final Player target = (Player) e.getDamager();

            if ((!this.antilog.contains(player.getName())) && (!this.antilog.contains(target.getName()))) {
                this.antilog.add(player.getName());
                this.antilog.add(target.getName());
                player.sendMessage(ChatColor.GRAY + "You are now in combat with " + target.getName());
                target.sendMessage(ChatColor.GRAY + "You are now in combat with " + player.getName());
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this, new Runnable() {
                    public void run() {
                        if((combat_log.this.antilog.contains(player.getName()) && (combat_log.this.antilog.contains(target.getName())))) {
                            combat_log.this.antilog.remove(player.getName());
                            combat_log.this.antilog.remove(target.getName());
                            player.sendMessage(ChatColor.GRAY + "You are no longer in combat");
                            target.sendMessage(ChatColor.GRAY + "You are no longer in combat");
                        }
                    }
                }, 1000L);
            }
        }
    }

}
