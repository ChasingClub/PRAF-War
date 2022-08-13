<<<<<<<< HEAD:dev/src/main/java/praf/server/main/events/JoinMessage.java
package praf.server.main.events;
========
package praf.server.events;
>>>>>>>> c292d9304f1200056fc9e0f4bf3cebfcebc5f51b:dev/src/main/java/praf/server/events/JoinMessage.java

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
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
