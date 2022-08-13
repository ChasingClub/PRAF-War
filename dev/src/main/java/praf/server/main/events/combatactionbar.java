<<<<<<<< HEAD:dev/src/main/java/praf/server/main/events/combatactionbar.java
package praf.server.main.events;
========
package praf.server.events;
>>>>>>>> c292d9304f1200056fc9e0f4bf3cebfcebc5f51b:dev/src/main/java/praf/server/events/combatactionbar.java

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class combatactionbar implements Listener {
    @EventHandler
    public void incombat(PlayerMoveEvent e){
        Player p = e.getPlayer();
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED+"You are in combat."));
    }
}
