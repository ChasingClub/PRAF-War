package pinont.server.minigame.utils.machanic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pinont.server.minigame.DiscordWebhook;
import pinont.server.minigame.Minigame;

import java.awt.*;
import java.io.IOException;

import static org.bukkit.Bukkit.getLogger;
import static pinont.server.minigame.Minigame.getDate;

public class CombatLog implements Listener {

    public String Webhook = Minigame.plugin.getConfig().getString("Webhook");
    public String webhookURL = Minigame.plugin.getConfig().getString("DiscordWebhookURL");
    public String webhookURLAC = Minigame.plugin.getConfig().getString("AntiCheatHook");
    @EventHandler
    public void ACLogged(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (Minigame.combatList.containsKey(e.getPlayer().getName())) {
            Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " has combat logged.");
            p.damage(21.0D);
            Minigame.combatList.put(p.getName(), -1);
            //////////////////////////////////////////////////////////
            if (Webhook == "true") {
                DiscordWebhook webhookAC = new DiscordWebhook(webhookURLAC);
                webhookAC.addEmbed(new DiscordWebhook.EmbedObject()
                        .setTitle("Combat Logged!")
                        .setThumbnail("https://minotar.net/armor/body/" + p.getName() + "/4096.png")
                        .addField("Name", p.getName(), true)
                        .addField("Time", getDate(), true)
                        .setFooter("------------------------------------------------------------------------------------", "")
                        .setColor(Color.YELLOW)
                );
                try {
                    webhookAC.execute();
                } catch (IOException event) {
                    getLogger().severe(event.getStackTrace().toString());
                }
            }
            //////////////////////////////////////////////////////////
        }
    }
}
