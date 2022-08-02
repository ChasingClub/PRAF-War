package pinont.server.minigame;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import pinont.server.minigame.command.*;
import pinont.server.minigame.commandTabComplete.PermsList;
import pinont.server.minigame.commandTabComplete.kitsTabable;
import pinont.server.minigame.events.*;

import java.awt.*;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.getServer;

public class Minigame extends JavaPlugin implements Listener, CommandExecutor {
    public static String Plname = ChatColor.AQUA + "[" + ChatColor.BLUE + "NET" + ChatColor.LIGHT_PURPLE + "HER" + ChatColor.YELLOW + "IT" + ChatColor.WHITE + "E" + ChatColor.AQUA + "] ";
    public static Logger logger;

    public static ArrayList<String> antilog = new ArrayList<String>();
    public static HashMap<String, Integer> combatList;
    public static HashMap<UUID, PermissionAttachment> playerPermission = new HashMap<>();
    public static ArrayList<String> ingame = new ArrayList<String>();
    public FileConfiguration config = this.getConfig();
    public String webhookURL = config.getString("DiscordWebhookLogURL");
    public String webhookURLAC = config.getString("DiscordWebhookURL");
    public String Webhook = config.getString("Webhook");
    public Minigame plugin;

    @Override
    public void onEnable() {
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
        getCommand("ping").setExecutor(new ping());
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
        getServer().getPluginManager().registerEvents(new feed(), this);
        getServer().getPluginManager().registerEvents(new ping(), this);
        this.getServer().getPluginManager().registerEvents(this, this);

        // start output
        Bukkit.getLogger().info(Plname + "Minigames Been Loaded!");

        // Discord Webhook Started
        if (Webhook == "true") {
            DiscordWebhook webhook = new DiscordWebhook(webhookURL);
            webhook.addEmbed(new DiscordWebhook.EmbedObject()
                    .setDescription("Server Started.")
                    .addField("Time", getDate(), true)
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
                onactionbar();
                tablist();
            }
        }.runTaskTimer(this, 0, 20);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event)
    {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player){
            Player target = (Player) event.getEntity();
            Player damager = (Player) event.getDamager();
            combatList.put(target.getName(), 11);
            combatList.put(damager.getName(), 11);
        }
    }
    public String getDate(){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+7"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(cal.getTime());
    }
    public String getTime(){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+7"));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }
    @EventHandler
    //JOIN SERVER
    public void DCjoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        Date date = new Date(System.currentTimeMillis());
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
            } catch (java.io.IOException event) {
                getLogger().severe(event.getStackTrace().toString());
            }
        }
    }
    @EventHandler
    //LEAVE SERVER
    public void DCleave(PlayerQuitEvent e) {

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
            } catch (java.io.IOException event) {
                getLogger().severe(event.getStackTrace().toString());
            }
        }
    }
    @EventHandler
    public void antiLog(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (Minigame.combatList.containsKey(e.getPlayer().getName())) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            //////////////////////////////////////////////////////////
            if (Webhook == "true") {
                DiscordWebhook webhook = new DiscordWebhook(webhookURLAC);
                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setTitle("Combat Logged!")
                        .setThumbnail("https://minotar.net/armor/body/" + p.getName() + "/4096.png")
                        .addField("Name", p.getName(), true)
                        .addField("Time", getDate(), true)
                        .setFooter("------------------------------------------------------------------------------------", "")
                        .setColor(Color.YELLOW)
                );
                try {
                    webhook.execute();
                } catch (java.io.IOException event) {
                    getLogger().severe(event.getStackTrace().toString());
                }
            }
            //////////////////////////////////////////////////////////
            Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " has combat logged.");
            p.damage(21.0D);
            Minigame.combatList.put(p.getName(), 0);
        }
    }
    public void onactionbar(){
        for (String players : combatList.keySet()) {
            for (Integer time : combatList.values()) {
                Player p = Bukkit.getPlayer(players);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GRAY + "You are no longer combat"));
                if (time > 0){
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "You are in combat"+ChatColor.WHITE+" | "+ChatColor.YELLOW+time+"s"));
                }
            }
        }
    }
    public void onDelay(){
        HashMap<String, Integer> temp = new HashMap<>();
        for (String id : combatList.keySet())
        {
            int timer = combatList.get(id) - 1;
            if (timer > -1)
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
        Bukkit.getLogger().info(Plname + "Shutdown Minigames");
        // Discord Webhook Started
        DiscordWebhook webhook = new DiscordWebhook(webhookURL);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setDescription("Server Stopped.")
                .addField("Time", getDate(), true)
                .setColor(Color.GREEN)
        );
        try {
            webhook.execute();
        }catch (java.io.IOException e){
            getLogger().severe(e.getStackTrace().toString());
        }
    }
    Class<?> CPClass;

    String serverName  = getServer().getClass().getPackage().getName(),
            serverVersion = serverName.substring(serverName.lastIndexOf(".") + 1, serverName.length());
    public int getthePing(Player p) {
        try {
            CPClass = Class.forName("org.bukkit.craftbukkit." + serverVersion + ".entity.CraftPlayer");
            Object CraftPlayer = CPClass.cast(p);

            Method getHandle = CraftPlayer.getClass().getMethod("getHandle", new Class[0]);
            Object EntityPlayer = getHandle.invoke(CraftPlayer, new Object[0]);

            Field ping = EntityPlayer.getClass().getDeclaredField("ping");

            return ping.getInt(EntityPlayer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String format(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }
    public void tablist(){
        if (Bukkit.getOnlinePlayers().size() == 0) {
            return;
        }

        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        Iterator<? extends Player> itPlayers = players.iterator();
        while (itPlayers.hasNext()) {
            Player player = itPlayers.next();

            if (player.getScoreboard().getObjective("PingTab") == null) {
                player.getScoreboard().registerNewObjective("PingTab", "dummy");
                player.getScoreboard().getObjective("PingTab")
                        .setDisplaySlot(DisplaySlot.PLAYER_LIST);
                player.getScoreboard().getObjective("PingTab").setDisplayName("ms");
            }


            Collection<? extends Player> tmpPlayers = Bukkit.getOnlinePlayers();
            Iterator<? extends Player> itTmpPlayers = tmpPlayers.iterator();
            while (itTmpPlayers.hasNext()) {
                Player tmpPlayer = itTmpPlayers.next();
                int tmpPing = getthePing(tmpPlayer);
                if (!tmpPlayer.getPlayerListName().equals(tmpPlayer.getName())) {
								/*
								player.getScoreboard()
										.getObjective("PingTab")
										.getScore(
												Bukkit.getOfflinePlayer(tmpPlayer
														.getPlayerListName())).setScore(tmpPing);
								*/
                    player.getScoreboard()
                            .getObjective("PingTab")
                            .getScore(tmpPlayer.getPlayerListName()).setScore(tmpPing);
                } else {
                    player.getScoreboard().getObjective("PingTab").getScore(tmpPlayer.getName())
                            .setScore(tmpPing);
                }

            }
        }
    }
}