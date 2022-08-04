package pinont.server.minigame.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pinont.server.minigame.DiscordWebhook;
import pinont.server.minigame.Minigame;

import javax.sql.rowset.spi.SyncFactoryException;
import java.awt.*;
import java.io.IOException;

import static org.bukkit.Bukkit.getLogger;
import static pinont.server.minigame.Minigame.getTime;


public class joinEvent implements Listener {

    public String Webhook = Minigame.plugin.getConfig().getString("Webhook");
    public String webhookURL = Minigame.plugin.getConfig().getString("DiscordWebhookURL");
    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws SyncFactoryException {
        Player p = e.getPlayer();
        if (Minigame.combatList.containsKey(e.getPlayer().getName())) {
            Minigame.combatList.put(p.getName(), 0);
        }
        if (p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR){
            p.setGameMode(GameMode.ADVENTURE);
            World SessionWorld = Bukkit.getServer().getWorld("mapgen");
            Location SessionWorldSpawn = new Location(SessionWorld, 64.5, 180, 26.5);
            p.teleport(SessionWorldSpawn);
            p.setWalkSpeed(0.2F); // default walk speed is 2F
        }

        if (Webhook == "true") {
            DiscordWebhook webhook = new DiscordWebhook(webhookURL);
            webhook.addEmbed(new DiscordWebhook.EmbedObject()
                    .setColor(Color.GREEN)
                    .setDescription("+ **" + p.getName() + "**")
                    .addField("Joined", getTime(), true)
                    .setThumbnail("https://minotar.net/armor/bust/" + p.getName() + "/4096.png")
            );
            try {
                webhook.execute();
            }catch (IOException error){
                getLogger().severe(error.getStackTrace().toString());
            }
        }
    }

}
