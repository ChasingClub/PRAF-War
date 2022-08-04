package pinont.server.minigame.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinMessage implements Listener {
    @EventHandler
    public void Joinmsg(PlayerJoinEvent event)
    {
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        Bukkit.broadcastMessage(ChatColor.GREEN + "[+] " + ChatColor.GRAY + player.getName());
    }

    @EventHandler
    public void Leavemsg(PlayerQuitEvent event)
    {
        event.setQuitMessage(null);
        Player player = event.getPlayer();
        Bukkit.broadcastMessage(ChatColor.RED + "[-] "+ChatColor.GRAY + player.getName());
    }
}
