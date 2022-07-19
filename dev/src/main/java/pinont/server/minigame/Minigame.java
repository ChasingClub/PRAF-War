package pinont.server.minigame;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import pinont.server.minigame.command.kits;
import pinont.server.minigame.command.kitsTabable;
import pinont.server.minigame.command.spawn;
import pinont.server.minigame.events.canceldrops;
import pinont.server.minigame.events.dia_to_netherite;
import pinont.server.minigame.events.joinEvent;
import pinont.server.minigame.events.wardens;

public final class Minigame extends JavaPlugin {
    public static String Plname = ChatColor.AQUA + "[" + ChatColor.BLUE + "NET" + ChatColor.LIGHT_PURPLE + "HER" + ChatColor.YELLOW + "IT" + ChatColor.WHITE + "E" + ChatColor.AQUA + "] ";



    @Override
    public void onEnable() {
        this.getCommand("spawn").setExecutor(new spawn());
        this.getCommand("kit").setExecutor(new kits());
        this.getCommand("kit").setTabCompleter(new kitsTabable());
        getServer().getPluginManager().registerEvents(new dia_to_netherite(), this);
        getServer().getPluginManager().registerEvents(new canceldrops(), this);
        getServer().getPluginManager().registerEvents(new wardens(), this);
        getServer().getPluginManager().registerEvents(new joinEvent(), this);
        System.out.println(Plname + "Minigames Been Loaded!");
    }

    @Override
    public void onDisable() {
        System.out.println(Plname + "Shutdown Minigames");
    }

}
