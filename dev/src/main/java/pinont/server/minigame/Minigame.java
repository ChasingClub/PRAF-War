package pinont.server.minigame;

import org.bukkit.plugin.java.JavaPlugin;
import pinont.server.minigame.command.spawn;
import pinont.server.minigame.events.*;

public final class Minigame extends JavaPlugin {


    @Override
    public void onEnable() {
        this.getCommand("spawn").setExecutor(new spawn());
        getServer().getPluginManager().registerEvents(new dia_to_netherite(), this);
        getServer().getPluginManager().registerEvents(new canceldrops(), this);
        getServer().getPluginManager().registerEvents(new wardens(), this);
        getServer().getPluginManager().registerEvents(new joinEvent(), this);
        System.out.println("Minigames Been Loaded!");
    }

    @Override
    public void onDisable() {
        System.out.println("Shutdown Minigames");
    }

}
