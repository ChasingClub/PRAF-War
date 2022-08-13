<<<<<<<< HEAD:dev/src/main/java/praf/server/main/PRAF.java
package praf.server.main;
========
package praf.server;
>>>>>>>> c292d9304f1200056fc9e0f4bf3cebfcebc5f51b:dev/src/main/java/praf/server/praf.java

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
<<<<<<<< HEAD:dev/src/main/java/praf/server/main/PRAF.java
import praf.server.main.commandTabComplete.kitsTabable;
import praf.server.main.command.*;
import praf.server.main.events.*;


========
import praf.server.command.*;
import praf.server.command.Args.kitsTabable;
import praf.server.events.*;
>>>>>>>> c292d9304f1200056fc9e0f4bf3cebfcebc5f51b:dev/src/main/java/praf/server/praf.java
import java.awt.Color;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
<<<<<<<< HEAD:dev/src/main/java/praf/server/main/PRAF.java

import static org.bukkit.Bukkit.getServer;

public class PRAF extends JavaPlugin implements Listener, CommandExecutor {
    public static HashMap<String, String> anti = new HashMap<String, String>();
    public static ArrayList<String> build = new ArrayList<String>();
    public static HashMap<String,String> duel = new HashMap<String,String>();
    public static HashMap<String, String> games = new HashMap<String, String>();
========
import java.util.*;

public class praf extends JavaPlugin implements Listener, CommandExecutor {
>>>>>>>> c292d9304f1200056fc9e0f4bf3cebfcebc5f51b:dev/src/main/java/praf/server/praf.java
    public static String Plname = (ChatColor.DARK_GRAY + "[" + ChatColor.RED + "P" + ChatColor.AQUA + "R" + ChatColor.GREEN + "A" + ChatColor.BLUE + "F" + ChatColor.DARK_GRAY + "] "+ChatColor.GRAY);
    public static HashMap<String, Integer> combatList;
    public static ArrayList<String> ingame = new ArrayList<String>();
    public FileConfiguration config = this.getConfig();
    public String webhookURL = config.getString("DiscordWebhookURL");
    public String webhookURLAC = config.getString("AntiCheatHook");
    public String Webhook = config.getString("Webhook");
<<<<<<<< HEAD:dev/src/main/java/praf/server/main/PRAF.java
    public PRAF plugin;
========


>>>>>>>> c292d9304f1200056fc9e0f4bf3cebfcebc5f51b:dev/src/main/java/praf/server/praf.java

    public static void msgconsole(String message){
        Bukkit.getConsoleSender().sendMessage(message);
    }
    // ENABLED SERVER
    @Override
    public void onEnable() {
        // Add String Arraylist/HashMap
        anti.put("ItDragClick", "1");anti.put("NotAGodBridger", "2");
        duel.put("invite", "1");duel.put("accept", "2");duel.put("reject", "3");
        games.put("netheritestack", "1");
        // Set Normal TimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Bangkok"));
        // Config
        File file = new File(getDataFolder() + File.separator + "config.yml"); //This will get the config file

        if (!file.exists()){ //This will check if the file exist
            getConfig().options().copyDefaults(true); //function to check the important settings
            saveConfig(); //saves the config
            reloadConfig(); //reloads the config
        }

        // register Command
        getCommand("spawn").setExecutor(new spawn());
        getCommand("getkit").setExecutor(new kits());
        getCommand("feed").setExecutor(new feed());
        getCommand("heal").setExecutor(new health());
        getCommand("gmc").setExecutor(new gmc());
        getCommand("gma").setExecutor(new gma());
        getCommand("gms").setExecutor(new gms());
        getCommand("gmsp").setExecutor(new gmsp());
<<<<<<<< HEAD:dev/src/main/java/praf/server/main/PRAF.java
        getCommand("fling").setExecutor(new fling());
        getCommand("crash").setExecutor(new crash());
        getCommand("earape").setExecutor(new earape());
        getCommand("build").setExecutor(new build());
        getCommand("duel").setExecutor(new duel());
========
//        getCommand("duel").setExecutor(new duel()); // this is for bungee
>>>>>>>> c292d9304f1200056fc9e0f4bf3cebfcebc5f51b:dev/src/main/java/praf/server/praf.java
//        getCommand("perm").setExecutor(new givePermission());

        // register Tab Argrument for Command
        getCommand("getkit").setTabCompleter(new kitsTabable());
        getCommand("feed").setTabCompleter(new kitsTabable());
//        getCommand("perm").setTabCompleter(new PermsList());

        // register Event
        getServer().getPluginManager().registerEvents(new netheriteStack(), this);
        getServer().getPluginManager().registerEvents(new canceldrops(), this);
        getServer().getPluginManager().registerEvents(new joinEvent(), this);
        getServer().getPluginManager().registerEvents(new Bhopping(), this);
        getServer().getPluginManager().registerEvents(new Respawn(), this);
        getServer().getPluginManager().registerEvents(new heal(), this);
        getServer().getPluginManager().registerEvents(new LeaveClear(), this);
        getServer().getPluginManager().registerEvents(new JoinMessage(), this);
        getServer().getPluginManager().registerEvents(new cancelevent(), this);
        getServer().getPluginManager().registerEvents(new BADWords(), this);
//        getServer().getPluginManager().registerEvents(new noAbuseGame(), this);
        this.getServer().getPluginManager().registerEvents(this, this);

        // start output
        msgconsole(Plname + "has Been Loaded!");

        // Discord Webhook Started
        if (Webhook == "true") {
            DiscordWebhook webhook = new DiscordWebhook(webhookURL);
            webhook.addEmbed(new DiscordWebhook.EmbedObject()
                    .setDescription("Server Started.")
                    .addField("Time", getDate(), false)
                    .addField("Server", "Main", false)
                    .setColor(Color.GREEN)
            );
            try {
                webhook.execute();
            }catch (java.io.IOException e){
                getLogger().severe(e.getStackTrace().toString());
            }
        }

        // combat thing
        this.combatList = new HashMap<>();
        new BukkitRunnable()
        {
            @Override
            public void run(){
                onDelay();
            }
        }.runTaskTimer(this, 0, 20);
        new BukkitRunnable()
        {
            @Override
            public void run(){
                nightvision();
            }
        }.runTaskTimer(this, 0, 100);
    }
    // DISABLED SERVER
    @Override
    public void onDisable() {
        msgconsole(Plname + "Shutdown CustomPlugin");
        // Discord Webhook Started
        DiscordWebhook webhook = new DiscordWebhook(webhookURL);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setDescription("Server Stopped.")
                .addField("Time", getDate(), false)
                .addField("Server", "Main", false)
                .setColor(Color.GRAY)
        );
        try {
            webhook.execute();
        }catch (java.io.IOException e){
            getLogger().severe(e.getStackTrace().toString());
        }
    }
    public void nightvision(){
        for (Player p : getServer().getOnlinePlayers()){
            if (p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR && p.getLocation().getWorld().getName().endsWith("world")) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 1, false, false));
            }
        }
    }
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event)
    {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player){
            Player target = (Player) event.getEntity();
            Player damager = (Player) event.getDamager();
            if((damager.getHealth() < 20)) {
                combatList.put(target.getName(), 11);
                combatList.put(damager.getName(), 11);
            }
        }
    }
    public String getDate(){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Bangkok"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(cal.getTime());
    }
    @EventHandler
    public void ACLogged(PlayerQuitEvent e) {
        Player p = e.getPlayer();
<<<<<<<< HEAD:dev/src/main/java/praf/server/main/PRAF.java
        if (PRAF.combatList.containsKey(e.getPlayer().getName())) {
            Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " has combat logged.");
            p.damage(21.0D);
            PRAF.combatList.put(p.getName(), -1);
========
        if (praf.combatList.containsKey(e.getPlayer().getName())) {
            Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " has combat logged.");
            p.damage(21.0D);
            praf.combatList.put(p.getName(), -1);
>>>>>>>> c292d9304f1200056fc9e0f4bf3cebfcebc5f51b:dev/src/main/java/praf/server/praf.java
            //////////////////////////////////////////////////////////
            if (Webhook == "true") {
                DiscordWebhook webhookAC = new DiscordWebhook(webhookURLAC);
                webhookAC.addEmbed(new DiscordWebhook.EmbedObject()
                        .setTitle("Combat Logged!")
                        .setThumbnail("https://minotar.net/armor/body/" + p.getName() + "/4096.png")
                        .addField("Name", p.getName()+" / "+p.getAddress().getAddress(), false)
                        .addField("Time", getDate(), false)
                        .addField("Server", "main", false)
                        .setFooter("------------------------------------------------------------------------------------", "")
                        .setColor(Color.YELLOW)
                );
                try {
                    webhookAC.execute();
                } catch (java.io.IOException event) {
                    getLogger().severe(event.getStackTrace().toString());
                }
            }
            //////////////////////////////////////////////////////////
        }
    }
    public void onDelay(){
        HashMap<String, Integer> temp = new HashMap<>();
        for (String id : combatList.keySet())
        {
            Player p = Bukkit.getPlayer(id);
            int timer = combatList.get(id) - 1;
            System.out.println(combatList);
            if (timer == 0){
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GRAY + "You are no longer combat"));
            }
            if (timer > 0){
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "You are in combat"));
                temp.put(id, timer);
            }
        }
            combatList = temp;
    }
    @EventHandler
    public void onKill(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Player killer = p.getKiller();
        Random r = new Random();
        List<String> sl = config.getStringList("deadmsg");
        String s = sl.get(r.nextInt(sl.size()));
        if (killer instanceof Player && p instanceof Player) {
            if (killer.getName() == p.getName()) {
                World SessionWorld = Bukkit.getServer().getWorld("world");
                Location SessionWorldSpawn = new Location(SessionWorld, 64.5, 180.5, 26.5);
                p.teleport(SessionWorldSpawn);
                p.getInventory().clear();
                for (PotionEffect effect : p.getActivePotionEffects())
                    p.removePotionEffect(effect.getType());
                Bukkit.broadcastMessage(ChatColor.RED + p.getName() + ChatColor.YELLOW + " has killed themself by accident.");
                return;
            }
            Bukkit.broadcastMessage(ChatColor.RED + p.getName() + ChatColor.YELLOW + s + ChatColor.DARK_AQUA + killer.getName());
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
<<<<<<<< HEAD:dev/src/main/java/praf/server/main/PRAF.java
========



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
        msgconsole(Plname + "Shutdown");
        // Discord Webhook Started
        DiscordWebhook webhook = new DiscordWebhook(webhookURL);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setDescription("Server Stopped.")
                .addField("Time", getDate(), false)
                .addField("Server", "Main", false)
                .setColor(Color.GRAY)
        );
        try {
            webhook.execute();
        }catch (java.io.IOException e){
            getLogger().severe(e.getStackTrace().toString());
        }
    }
>>>>>>>> c292d9304f1200056fc9e0f4bf3cebfcebc5f51b:dev/src/main/java/praf/server/praf.java
}