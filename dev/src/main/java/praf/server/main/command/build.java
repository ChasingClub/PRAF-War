package praf.server.main.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static praf.server.main.PRAF.Plname;
import static praf.server.main.PRAF.build;

public class build implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("build")) {
            // Check for arguments
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.RED+"Can't use this command with console.");
                    return true;
                }else{
                    if (!(sender.hasPermission("rank.admin"))) {
                        sender.sendMessage(ChatColor.RED+"You Don't have permission to do that!");
                        return true;
                    }else {
                        Player p = (Player) sender;
                        if (!(build.contains(p.getName()))) {
                            build.add(p.getName());
                            p.sendMessage(Plname+"Build has been "+ChatColor.GREEN+"enabled"+ChatColor.GRAY+".");
                            return true;
                        } else if (build.contains(p.getName())) {
                            build.remove(p.getName());
                            p.sendMessage(Plname+"Build has been "+ChatColor.RED+"disabled"+ChatColor.GRAY+".");
                            return true;
                        }
                    }
                }
            } else {
                // Send command overview
                sender.sendMessage("/build" + ChatColor.GRAY + " - " + ChatColor.GREEN + "Enable/Disable build mode.");
                return true;
            }

        }
        return true;
    }
}
