package pinont.server.minigame.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import pinont.server.minigame.DiscordWebhook;
import pinont.server.minigame.Minigame;

import java.awt.*;
import java.io.IOException;

import static org.bukkit.Bukkit.getLogger;
import static pinont.server.minigame.Minigame.getTime;

public class LeaveClear implements Listener {

    public String Webhook = Minigame.plugin.getConfig().getString("Webhook");
    public String webhookURL = Minigame.plugin.getConfig().getString("DiscordWebhookURL");

    @EventHandler
    public void onleavesv(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if (Webhook == "true") {
            DiscordWebhook webhook = new DiscordWebhook(webhookURL);
            webhook.addEmbed(new DiscordWebhook.EmbedObject()
                    .setColor(Color.RED)
                    .setDescription("- **" + p.getName() + "**")
                    .addField("Disconnected", getTime(), true)
                    .setThumbnail("https://minotar.net/armor/bust/" + p.getName() + "/4096.png")
            );
            try {
                webhook.execute();
            } catch (IOException event) {
                getLogger().severe(event.getStackTrace().toString());
            }
        }
        if (p.getLocation().getWorld().getName().endsWith("mapgen") && p.getGameMode() != GameMode.CREATIVE) {
            p.getInventory().clear();
            for (PotionEffect effect : p.getActivePotionEffects())
                p.removePotionEffect(effect.getType());
        }
    }

    public void DCleave(PlayerQuitEvent e) {

        Player p = e.getPlayer();

    }
}
