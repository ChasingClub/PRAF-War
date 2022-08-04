package pinont.server.minigame.utils.machanic;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import pinont.server.minigame.DiscordWebhook;
import pinont.server.minigame.Minigame;

import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class RecivedDamage implements Listener {

    public String Webhook = Minigame.plugin.getConfig().getString("Webhook");
    public String webhookURL = Minigame.plugin.getConfig().getString("DiscordWebhookURL");
    public String webhookURLAC = Minigame.plugin.getConfig().getString("AntiCheatHook");

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event)
    {
        Minigame.combatList = new HashMap<>();
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player){
            Player target = (Player) event.getEntity();

            getServer().getPluginManager().registerEvents(this, Minigame.plugin);
            new BukkitRunnable()
            {
                @Override
                public void run(){
                    onDelay();
                    onactionbar();
//                tablist();
                }
            }.runTaskTimer(Minigame.plugin, 0, 20);

            Player damager = (Player) event.getDamager();
            if((damager.getHealth() < 20)) {
                Minigame.combatList.put(target.getName(), 11);
                Minigame.combatList.put(damager.getName(), 11);
            }
        }
    }

    private void onactionbar() {
        for (String players : Minigame.combatList.keySet()) {
            for (Integer time : Minigame.combatList.values()) {
                Player p = Bukkit.getPlayer(players);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GRAY + "You are no longer combat"));
                if (time > 0){
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "You are in combat"+ChatColor.WHITE+" | "+ChatColor.YELLOW+time+"s"));
                }
            }
        }
    }

    private void onDelay() {
        HashMap<String, Integer> temp = new HashMap<>();
        for (String id : Minigame.combatList.keySet())
        {
            int timer = Minigame.combatList.get(id) - 1;
            if (timer > -1)
            {
                temp.put(id, timer);
            }
        }
        Minigame.combatList = temp;
    }
}
