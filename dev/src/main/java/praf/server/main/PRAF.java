package praf.server.main;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import praf.server.main.commandTabComplete.kitsTabable;
import praf.server.main.command.*;
import praf.server.main.events.*;


import java.awt.Color;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class PRAF extends JavaPlugin implements Listener, CommandExecutor {
    public static HashMap<String, String> anti = new HashMap<String, String>();
    public static ArrayList<String> build = new ArrayList<String>();
    public static HashMap<Player, String> kits = new HashMap<Player, String>();
    public static HashMap<String, Integer> playerkits = new HashMap<String, Integer>();
    public static HashMap<String,String> duel = new HashMap<String,String>();
    public static HashMap<String, String> games = new HashMap<String, String>();
    public static String Plname = (ChatColor.DARK_GRAY + "[" + ChatColor.RED + "P" + ChatColor.AQUA + "R" + ChatColor.GREEN + "A" + ChatColor.BLUE + "F" + ChatColor.DARK_GRAY + "] "+ChatColor.GRAY);
    public static HashMap<String, Integer> combatList;
    public static ArrayList<String> ingame = new ArrayList<String>();
    public FileConfiguration config = this.getConfig();
    public String webhookURL = config.getString("DiscordWebhookURL");
    public String webhookURLAC = config.getString("AntiCheatHook");
    public String Webhook = config.getString("Webhook");
    public PRAF plugin;

    public static void msgconsole(String message){
        Bukkit.getConsoleSender().sendMessage(message);
    }
    // ENABLED SERVER
    @Override
    public void onEnable() {
        // Add String Arraylist/HashMap
        for (Player p : getServer().getOnlinePlayers()){
            kits.put(p,"Default");
        }
        anti.put("ItDragClick", "1");anti.put("NotAGodBridger", "2");
        playerkits.put("Default", 1);playerkits.put("trident", 2);playerkits.put("blueaxeblackhead", 3);playerkits.put("bow", 4);playerkits.put("admin", 5);
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
        getCommand("fling").setExecutor(new fling());
        getCommand("crash").setExecutor(new crash());
        getCommand("earape").setExecutor(new earape());
        getCommand("build").setExecutor(new build());
        getCommand("duel").setExecutor(new duel());
        getCommand("givekit").setExecutor(new givekit());
        getCommand("setkit").setExecutor(new setkit());
//        getCommand("perm").setExecutor(new givePermission());

        // register Tab Argrument for Command
        getCommand("getkit").setTabCompleter(new kitsTabable());
        getCommand("feed").setTabCompleter(new kitsTabable());
//        getCommand("perm").setTabCompleter(new PermsList());

        // register Event
        getServer().getPluginManager().registerEvents(new dia_to_netherite(), this);
        getServer().getPluginManager().registerEvents(new canceldrops(), this);
        getServer().getPluginManager().registerEvents(new joinEvent(), this);
        getServer().getPluginManager().registerEvents(new Bhopping(), this);
        getServer().getPluginManager().registerEvents(new Respawn(), this);
        getServer().getPluginManager().registerEvents(new heal(), this);
        getServer().getPluginManager().registerEvents(new LeaveClear(), this);
        getServer().getPluginManager().registerEvents(new JoinMessage(), this);
        getServer().getPluginManager().registerEvents(new cancelevent(), this);
        getServer().getPluginManager().registerEvents(new BADWords(), this);
//        getServer().getPluginManager().registerEvents(new cancelcombat(), this);
//        this.getServer().getPluginManager().registerEvents(this, this);

        // start output
        msgconsole(Plname + "CustomPlugin Been Loaded!");

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
        getServer().getPluginManager().registerEvents(this, this);
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
            if(event.isCancelled() != true) {
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
        if (PRAF.combatList.containsKey(e.getPlayer().getName())) {
            Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " has combat logged.");
            p.damage(21.0D);
            PRAF.combatList.put(p.getName(), -1);
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
    public void CancelCmd(PlayerCommandPreprocessEvent event){
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
}