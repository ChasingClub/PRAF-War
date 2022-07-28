package pinont.server.minigame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import pinont.server.minigame.events.ListenerQuit;
import pinont.server.minigame.command.givePermission;
import pinont.server.minigame.command.kits;
import pinont.server.minigame.command.spawn;
import pinont.server.minigame.commandTabComplete.PermsList;
import pinont.server.minigame.commandTabComplete.kitsTabable;
import pinont.server.minigame.events.*;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;

public class Minigame extends JavaPlugin implements Listener {
    public static String Plname = ChatColor.AQUA + "[" + ChatColor.BLUE + "NET" + ChatColor.LIGHT_PURPLE + "HER" + ChatColor.YELLOW + "IT" + ChatColor.WHITE + "E" + ChatColor.AQUA + "] ";
    public static Logger logger;
    public static String webhookUrl = "https://discord.com/api/webhooks/1001889150129152150/L6a_4y0kUKtP_OJ-JO2wnP--1ZBduqdhge4EcgAkZgmF-8bevBC7hUBxF9JVvLQDalYy"; // fucking DCLeave.java event just needed static method so I can't use config.yml


    public HashMap<UUID, PermissionAttachment> playerPermission = new HashMap<>();
    public static ArrayList<String> antilog = new ArrayList<String>();
    public static HashMap<String, Integer> combatList;
    public static ArrayList<String> ingame = new ArrayList<String>();
    public Minigame plugin;

    @Override
    public void onEnable() {

        // Config
        getConfig().options().copyDefaults(true);
        saveConfig();


        // register Command
        getCommand("spawn").setExecutor(new spawn());
        getCommand("kit").setExecutor(new kits());
        getCommand("perm").setExecutor(new givePermission());

        // register Tab Argrument for Command
        getCommand("kit").setTabCompleter(new kitsTabable());
        getCommand("perm").setTabCompleter(new PermsList());

        // register Event
        getServer().getPluginManager().registerEvents(new dia_to_netherite(), this);
        getServer().getPluginManager().registerEvents(new canceldrops(), this);
        getServer().getPluginManager().registerEvents(new joinEvent(), this);
        getServer().getPluginManager().registerEvents(new Bhopping(), this);

        // start output
        System.out.println(Plname + "Minigames Been Loaded!");

        // Discord Webhook Started
        DiscordWebhook webhook = new DiscordWebhook(webhookUrl);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setAuthor("-------","","")
                .setFooter("-------","")
                .setDescription("Server Started.")
                .setColor(Color.GREEN)
        );
        try {
            webhook.execute();
        }catch (java.io.IOException e){
            getLogger().severe(e.getStackTrace().toString());
        }

        // combat thing
        this.combatList = new HashMap<>();
        getServer().getPluginManager().registerEvents(this, this);
        new BukkitRunnable()
        {
            @Override
            public void run(){
                onDelay();
            }
        }.runTaskTimer(this, 0, 20);
    }
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event)
    {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player){
            Player target = (Player) event.getEntity();
            Player damager = (Player) event.getDamager();
            combatList.put(target.getName(), 8);
            combatList.put(damager.getName(), 8);
        }
    }
    public String getDate(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(cal.getTime());
    }
    public String getTime(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }
    @EventHandler
    //JOIN SERVER
    public void DCjoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        Date date = new Date(System.currentTimeMillis());
        DiscordWebhook webhook = new DiscordWebhook(webhookUrl);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setColor(Color.GREEN)
                .setDescription("+ **" + p.getName() + "**")
                .addField("Joined", getTime(), true)
                .setThumbnail("https://minotar.net/armor/bust/"+ p.getName() +"/4096.png")
        );
        try {
            webhook.execute();
        }
        catch(java.io.IOException event) {
            getLogger().severe(event.getStackTrace().toString());
        }
    }
    @EventHandler
    //LEAVE SERVER
    public void DCleave(PlayerQuitEvent e) {

        Player p = e.getPlayer();
        DiscordWebhook webhook = new DiscordWebhook(webhookUrl);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setColor(Color.RED)
                .setDescription("- **" + p.getName() + "**")
                .addField("Disconnected", getTime(), true)
                .setThumbnail("https://minotar.net/armor/bust/"+ p.getName() +"/4096.png")
        );
        try {
            webhook.execute();
        }
        catch(java.io.IOException event) {
            getLogger().severe(event.getStackTrace().toString());
        }
    }
    @EventHandler
    public void antiLog(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (Minigame.combatList.containsKey(e.getPlayer().getName())) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            //////////////////////////////////////////////////////////
            DiscordWebhook webhook = new DiscordWebhook(webhookUrl);
            webhook.addEmbed(new DiscordWebhook.EmbedObject()
                    .setTitle("Combat Logged!")
                    .setThumbnail("https://minotar.net/armor/body/"+ p.getName() +"/4096.png")
                    .addField("Name", p.getName(), true)
                    .addField("Time", getDate(), true)
                    .setFooter("------------------------------------------------------------------------------------","")
                    .setColor(Color.YELLOW)
            );
            try {
                webhook.execute();
            }
            catch(java.io.IOException event) {
                getLogger().severe(event.getStackTrace().toString());
            }
            //////////////////////////////////////////////////////////
            Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " has combat logged.");
            p.damage(21.0D);
            Minigame.combatList.put(p.getName(), 0);
        }
    }
    public void onDelay()
    {
        HashMap<String, Integer> temp = new HashMap<>();
        for (String id : combatList.keySet())
        {
            int timer = combatList.get(id) - 1;
            if (timer >= 0)
            {
                temp.put(id, timer);
            }
        }
            combatList = temp;
    }
    @EventHandler
    public void onKill(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Player killer = p.getKiller();
        if (killer instanceof Player && p instanceof Player) {
            Bukkit.broadcastMessage(ChatColor.RED + p.getName() + ChatColor.YELLOW + " was slain by " + ChatColor.DARK_AQUA + killer.getName());
        }
    }
    @EventHandler
    public void CancelCmd(PlayerCommandPreprocessEvent event)
    {
        if (combatList.containsKey(event.getPlayer().getName()))
        {
            List<String> commands = Arrays.asList("/spawn");

            String[] parts = event.getMessage().split(" ");

            String cmd = parts[0].toLowerCase();

            if (commands.contains(cmd))
            {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "You are still in combat!");
            }
        }
    }


//    public void setupPermission(Player p) {
//        PermissionAttachment attachment = p.addAttachment(this);
//        this.playerPermission.put(p.getUniqueId(), attachment);
//        permissionSetter(p.getUniqueId());
//    }
//
//    private void permissionSetter(UUID uuid) {
//        PermissionAttachment attachment = this.playerPermission.get(this);
//        for (String groups : this.getConfig().getConfigurationSection("Groups").getKeys(false)) {
//            for (String permissions : this.getConfig().getStringList("Groups." + groups + ".permissions")) {
//                attachment.setPermission(permissions, true);
//            }
//        }
//    }


//    public enum PermissionLevel {
//        ADMIN("rank.admin"),
//        MOD("rank.mod"),
//        JR_MOD("rank.jrmod");
//
//        private String perm;
//
//
//        PermissionLevel(String perm) {
//            this.perm = perm;
//        }
//
//        PermissionLevel() {}
//
//        public static Boolean has(Player p, PermissionLevel level) {
//            PermissionLevel[] values = values();
//            int ord = level.ordinal();
//            for (int i = ord; i >= 0; i--) {
//                PermissionLevel lvl = values[i];
//                if (lvl.perm == null) {
//                    return true;
//                }
//                if (p.hasPermission(lvl.perm)) {
//                    return true;
//                }
//
//            }
//            return false;
//        }
//
//
//    }


    @Override
    public void onDisable() {
        System.out.println(Plname + "Shutdown Minigames");
        // Discord Webhook Started
        DiscordWebhook webhook = new DiscordWebhook(webhookUrl);
        webhook.addEmbed(new DiscordWebhook.EmbedObject().setDescription("Server Stopped."));
        try {
            webhook.execute();
        }catch (java.io.IOException e){
            getLogger().severe(e.getStackTrace().toString());
        }
    }

}