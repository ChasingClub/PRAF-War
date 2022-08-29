package praf.server.main.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;
import static praf.server.main.Core.anti;

public class crash implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("crash")) {
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
                        sender.sendMessage("/crash <player>" + ChatColor.GRAY + " - " + ChatColor.GOLD + "Crash player you want.");
                        return true;
                    }
                }
            } else if (args.length == 1) {
                if (sender.hasPermission("rank.admin")) {
                    Player argplayer = getServer().getPlayer(args[0]);
                    if (argplayer == null) {
                        sender.sendMessage("Player " + ChatColor.GRAY + args[0] + ChatColor.RESET + " could not be found");
                        return true;
                    }else if (anti.get(argplayer.getUniqueId().toString()) != null) {
                        sender.sendMessage(ChatColor.RED+"You can't crash that player.");
                        return true;
                    }
                    // Send command overview
                    getServer().dispatchCommand(getServer().getConsoleSender(), "execute as "+argplayer.getName()+" at @s run particle minecraft:cloud ~ ~ ~ ~ ~ ~ 1 100000000 force @s");
                    sender.sendMessage(ChatColor.GOLD + argplayer.getName() + " has been crashed.");
                    return true;
                } else {
                    sender.sendMessage(ChatColor.RED+"You Don't have permission to do that!");
                    return true;
                }
            } else {
                // Send command overview
                sender.sendMessage("/crash <player>" + ChatColor.GRAY + " - " + ChatColor.GOLD + "Crash player you want.");
                return true;
            }

        }
        return true;
    }
}
