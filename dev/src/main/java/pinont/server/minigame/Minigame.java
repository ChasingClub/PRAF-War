package pinont.server.minigame;

<<<<<<< HEAD
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
=======
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
>>>>>>> 8fa805bcde8725f5a1ba696ac001f35c9ef6f392
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pinont.server.minigame.command.*;
<<<<<<< HEAD
=======
import pinont.server.minigame.commandTabComplete.kitsTabable;
>>>>>>> 8fa805bcde8725f5a1ba696ac001f35c9ef6f392
import pinont.server.minigame.events.*;
import pinont.server.minigame.utils.commandTablist.kitsTabable;
import pinont.server.minigame.utils.machanic.*;


import java.awt.*;
import java.awt.Color;
import java.io.File;
<<<<<<< HEAD
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

public class Minigame extends JavaPlugin implements Listener, CommandExecutor {
    public static String Plname = ChatColor.AQUA + "[" + ChatColor.RED + "P" + ChatColor.BLUE + "R" + ChatColor.GREEN + "A" + ChatColor.DARK_BLUE + "F" + ChatColor.AQUA + "] "+ChatColor.GRAY;
=======
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.getServer;

public class Minigame extends JavaPlugin implements Listener, CommandExecutor {
    public static String Plname = ChatColor.AQUA + "[" + ChatColor.BLUE + "NET" + ChatColor.LIGHT_PURPLE + "HER" + ChatColor.YELLOW + "IT" + ChatColor.WHITE + "E" + ChatColor.AQUA + "] "+ChatColor.GRAY;
>>>>>>> 8fa805bcde8725f5a1ba696ac001f35c9ef6f392
    public static HashMap<String, Integer> combatList;
    public static ArrayList<String> ingame = new ArrayList<String>();
    public FileConfiguration config = this.getConfig();
    public String webhookURL = config.getString("DiscordWebhookURL");
    public String webhookURLAC = config.getString("AntiCheatHook");
    public String Webhook = config.getString("Webhook");
    public static Minigame plugin;

<<<<<<< HEAD


=======
>>>>>>> 8fa805bcde8725f5a1ba696ac001f35c9ef6f392
    public void msgconsole(String message){
        Bukkit.getConsoleSender().sendMessage(message);
    }
    @Override
    public void onEnable() {
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
        getCommand("getkit").setExecutor(new getkits());
        getCommand("feed").setExecutor(new feed());
        getCommand("ping").setExecutor(new ping());
        getCommand("heal").setExecutor(new health());
<<<<<<< HEAD
        getCommand("earape").setExecutor(new earape());
=======
>>>>>>> 8fa805bcde8725f5a1ba696ac001f35c9ef6f392
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
        getServer().getPluginManager().registerEvents(new JoinMessage(), this);
<<<<<<< HEAD
        getServer().getPluginManager().registerEvents(new CancelCommand(), this);
        getServer().getPluginManager().registerEvents(new CombatLog(), this);
        getServer().getPluginManager().registerEvents(new RecivedDamage(), this);
        getServer().getPluginManager().registerEvents(new combatactionbar(), this);
        getServer().getPluginManager().registerEvents(new PlayerKilled(), this);
=======
>>>>>>> 8fa805bcde8725f5a1ba696ac001f35c9ef6f392
//        getServer().getPluginManager().registerEvents(new cancelcombat(), this);
//        this.getServer().getPluginManager().registerEvents(this, this);

        // start output
<<<<<<< HEAD
        msgconsole(Plname + "PRAF Been Loaded!");
=======
        msgconsole(Plname + "CustomPlugin Been Loaded!");
>>>>>>> 8fa805bcde8725f5a1ba696ac001f35c9ef6f392

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
<<<<<<< HEAD
=======

        // combat thing
        this.combatList = new HashMap<>();
        getServer().getPluginManager().registerEvents(this, this);
        new BukkitRunnable()
        {
            @Override
            public void run(){
                onDelay();
                onactionbar();
            }
        }.runTaskTimer(this, 0, 20);
>>>>>>> 8fa805bcde8725f5a1ba696ac001f35c9ef6f392
    }
    public void particles(Player p) {
        Location loc = p.getLocation();
        for (int i=0; i<1000;i++) {
            p.playSound(loc , Sound.UI_TOAST_CHALLENGE_COMPLETE, 1000000000, 0);
        }
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("earape")) {
            // Check for arguments
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Player Not Found");
                    return true;
                }else{
                    if (!(sender.hasPermission("rank.admin"))) {
                        sender.sendMessage(ChatColor.RED+"You Don't have permission to do that!");
                        return true;
                    }
                    if (sender instanceof Player) {
                        sender.sendMessage("/earape <player>" + ChatColor.GRAY + " - " + ChatColor.GOLD + "Earape player you want.");
                        return true;
                    }
                }
            } else if (args.length == 1) {
                if (sender.hasPermission("rank.admin")) {
                    Player argplayer = getServer().getPlayer(args[0]);
                    if (argplayer == null) {
                        sender.sendMessage("Player " + ChatColor.GRAY + args[0] + ChatColor.RESET + " could not be found");
                        return true;
                    }
                    // Send command overview
                    particles(argplayer);
                    sender.sendMessage(ChatColor.GOLD + argplayer.getName() + " has been earaped.");
                    return true;
                } else {
                    sender.sendMessage(ChatColor.RED+"You Don't have permission to do that!");
                    return true;
                }
            } else {
                // Send command overview
                sender.sendMessage("/earape <player>" + ChatColor.GRAY + " - " + ChatColor.GOLD + "Earape player you want.");
                return true;
            }

        }
        return true;
    }

<<<<<<< HEAD

    public static String getDate() {
=======
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
>>>>>>> 8fa805bcde8725f5a1ba696ac001f35c9ef6f392
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Bangkok"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(cal.getTime());
    }
<<<<<<< HEAD
    public static String getTime() {
=======
    public String getTime(){
>>>>>>> 8fa805bcde8725f5a1ba696ac001f35c9ef6f392
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Bangkok"));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }
<<<<<<< HEAD
=======
    @EventHandler
    //JOIN SERVER
    public void DCjoin(PlayerJoinEvent event) {

        Player p = event.getPlayer();
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
            }catch (java.io.IOException e){
                getLogger().severe(e.getStackTrace().toString());
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
                } catch (java.io.IOException event) {
                    getLogger().severe(event.getStackTrace().toString());
                }
            }
            //////////////////////////////////////////////////////////
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


>>>>>>> 8fa805bcde8725f5a1ba696ac001f35c9ef6f392

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
        msgconsole(Plname + "Shutdown CustomPlugin");
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
<<<<<<< HEAD
=======
    Class<?> CPClass;

    String serverName  = getServer().getClass().getPackage().getName(),
            serverVersion = serverName.substring(serverName.lastIndexOf(".") + 1, serverName.length());
    public int getPing(Player p) {
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
                int tmpPing = getPing(tmpPlayer);
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
>>>>>>> 8fa805bcde8725f5a1ba696ac001f35c9ef6f392
}