package pinont.server.minigame.command;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static pinont.server.minigame.Minigame.Plname;

public class spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (sender instanceof Player) {
                World SessionWorld = Bukkit.getServer().getWorld("world");
                Location SessionWorldSpawn = new Location(SessionWorld, 64.5, 180, 26.5);
                p.sendMessage(Plname + "Woosh! you've been back to Server hub");
                p.teleport(SessionWorldSpawn);
            }
        } else {
            System.out.println("You need to be a player to send the command");
        }
        return true;
    }
}
