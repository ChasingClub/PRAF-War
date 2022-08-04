package pinont.server.minigame.command;


import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import pinont.server.minigame.Minigame;

import static pinont.server.minigame.Minigame.Plname;

public class spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (sender instanceof Player) {
                if (p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
<<<<<<< HEAD
                    if (p.getLocation().getWorld().getName().endsWith("mapgen")) {
=======
                    if (p.getLocation().getWorld().getName().endsWith("world")) {
>>>>>>> 8fa805bcde8725f5a1ba696ac001f35c9ef6f392
                        p.getInventory().clear();
                        for (PotionEffect effect : p.getActivePotionEffects())
                            p.removePotionEffect(effect.getType());
                    }
                }
                World SessionWorld = Bukkit.getServer().getWorld("mapgen");
                Location SessionWorldSpawn = new Location(SessionWorld, 64.5, 180, 26.5);
                p.teleport(SessionWorldSpawn);
                if (p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
                    p.sendMessage(Plname + "You have been teleported to spawn.");
                }else if (p.getGameMode() == GameMode.CREATIVE) {
                    p.sendMessage(Plname + "You have been teleported to spawn and your item will not clear.");
                }else if (p.getGameMode() == GameMode.SPECTATOR) {
                    p.sendMessage(Plname + "You have been teleported to spawn and your item will not clear.");
                }
            }
        } else {
            System.out.println("You need to be a player to send the command");
        }
        return true;
    }
}
