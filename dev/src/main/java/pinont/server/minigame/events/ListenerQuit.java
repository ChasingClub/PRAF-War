package pinont.server.minigame.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pinont.server.minigame.DiscordWebhook;
import pinont.server.minigame.Minigame;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import static pinont.server.minigame.Minigame.webhookUrl;

public class ListenerQuit implements Listener {

    public Logger logger;

    @EventHandler
    public void antiLog(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (Minigame.combatList.containsKey(e.getPlayer().getName())) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            //////////////////////////////////////////////////////////
            DiscordWebhook webhook = new DiscordWebhook(webhookUrl);
            webhook.addEmbed(new DiscordWebhook.EmbedObject()
                    .setAuthor("**Combat Logout**","","")
                    .setDescription("Name : " + p.getName() + "\n" + "Time : " + format)
                    .setFooter("------------------------------","")
                    .setColor(new Color(240, 230, 140))
            );
            try {
                webhook.execute();
            }catch (java.io.IOException event){
                logger.severe(event.getStackTrace().toString());
            }
            //////////////////////////////////////////////////////////
            Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " has combat logged.");
            p.damage(21.0D);
            Minigame.combatList.put(p.getName(), 0);
        }
    }
}
