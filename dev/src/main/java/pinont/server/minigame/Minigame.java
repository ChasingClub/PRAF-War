package pinont.server.minigame;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import pinont.server.minigame.command.givePermission;
import pinont.server.minigame.command.kits;
import pinont.server.minigame.command.spawn;
import pinont.server.minigame.commandTabComplete.PermsList;
import pinont.server.minigame.commandTabComplete.kitsTabable;
import pinont.server.minigame.events.blindness;
import pinont.server.minigame.events.canceldrops;
import pinont.server.minigame.events.dia_to_netherite;
import pinont.server.minigame.events.joinEvent;

import java.util.HashMap;
import java.util.UUID;

public class Minigame extends JavaPlugin {
    public static String Plname = ChatColor.AQUA + "[" + ChatColor.BLUE + "NET" + ChatColor.LIGHT_PURPLE + "HER" + ChatColor.YELLOW + "IT" + ChatColor.WHITE + "E" + ChatColor.AQUA + "] ";

    public HashMap<UUID, PermissionAttachment> playerPermission = new HashMap<>();
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
        getServer().getPluginManager().registerEvents(new blindness(), this);

        // start output
        System.out.println(Plname + "Minigames Been Loaded!");

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
    }

}
