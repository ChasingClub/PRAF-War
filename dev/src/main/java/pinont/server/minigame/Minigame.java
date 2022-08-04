package pinont.server.minigame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pinont.server.minigame.command.*;
import pinont.server.minigame.events.*;
import pinont.server.minigame.utils.commandTablist.kitsTabable;
import pinont.server.minigame.utils.machanic.*;

import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

public class Minigame extends JavaPlugin implements Listener, CommandExecutor {
    public static String Plname = ChatColor.AQUA + "[" + ChatColor.RED + "P" + ChatColor.BLUE + "R" + ChatColor.GREEN + "A" + ChatColor.DARK_BLUE + "F" + ChatColor.AQUA + "] "+ChatColor.GRAY;
    public static HashMap<String, Integer> combatList;
    public static ArrayList<String> ingame = new ArrayList<String>();
    public FileConfiguration config = this.getConfig();
    public String webhookURL = config.getString("DiscordWebhookURL");
    public String webhookURLAC = config.getString("AntiCheatHook");
    public String Webhook = config.getString("Webhook");
    public static Minigame plugin;



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
        getCommand("earape").setExecutor(new earape());
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
        getServer().getPluginManager().registerEvents(new CancelCommand(), this);
        getServer().getPluginManager().registerEvents(new CombatLog(), this);
        getServer().getPluginManager().registerEvents(new RecivedDamage(), this);
        getServer().getPluginManager().registerEvents(new combatactionbar(), this);
        getServer().getPluginManager().registerEvents(new PlayerKilled(), this);
//        getServer().getPluginManager().registerEvents(new cancelcombat(), this);
//        this.getServer().getPluginManager().registerEvents(this, this);

        // start output
        msgconsole(Plname + "PRAF Been Loaded!");

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
    }


    public static String getDate() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Bangkok"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(cal.getTime());
    }
    public static String getTime() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Bangkok"));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
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
}