package pinont.server.minigame;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import pinont.server.minigame.command.kits;
import pinont.server.minigame.command.spawn;
import pinont.server.minigame.commandTabComplete.kitsTabable;
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

        // register Tab Argrument for Command
        getCommand("kit").setTabCompleter(new kitsTabable());

        // register Event
        getServer().getPluginManager().registerEvents(new dia_to_netherite(), this);
        getServer().getPluginManager().registerEvents(new canceldrops(), this);
        getServer().getPluginManager().registerEvents(new joinEvent(), this);

        // start output
        System.out.println(Plname + "Minigames Been Loaded!");

    }




    public void setupPermission(Player p) {
        PermissionAttachment attachment = p.addAttachment(this);
        this.playerPermission.put(p.getUniqueId(), attachment);
        permissionSetter(p.getUniqueId());
    }

    private void permissionSetter(UUID uuid) {
        PermissionAttachment attachment = this.playerPermission.get(this);
        for (String groups : this.getConfig().getConfigurationSection("Groups").getKeys(false)) {
            for (String permissions : this.getConfig().getStringList("Groups." + groups + ".permissions")) {
                attachment.setPermission(permissions, true);
            }
        }
    }



    @Override
    public void onDisable() {
        System.out.println(Plname + "Shutdown Minigames");
    }

}
