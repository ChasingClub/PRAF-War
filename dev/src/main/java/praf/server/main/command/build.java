package praf.server.main.command;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;
import static praf.server.main.PRAF.*;

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
                        sender.sendMessage(org.bukkit.ChatColor.RED+"You Don't have permission to do that!");
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
            }else if (args.length == 1) {
                if (!(sender.hasPermission("rank.admin"))) {
                    sender.sendMessage(org.bukkit.ChatColor.RED+"You Don't have permission to do that!");
                    return true;
                }else {
                    Player target = getServer().getPlayer(args[0]);
                    if (target == null){
                        sender.sendMessage("Player offline");
                        return true;
                    }
                    if (!(build.contains(target.getName()))) {
                        build.add(target.getName());
                        target.sendMessage(Plname+"Build has been "+ChatColor.GREEN+"enabled"+ChatColor.GRAY+" by "+ChatColor.YELLOW+sender.getName()+ChatColor.GRAY+".");
                        sender.sendMessage(Plname+ChatColor.GREEN+"Enabled build for "+ChatColor.YELLOW+target.getName());
                        return true;
                    } else if (build.contains(target.getName())) {
                        build.remove(target.getName());
                        target.sendMessage(Plname+"Build has been "+ChatColor.RED+"disabled"+ChatColor.GRAY+" by "+ChatColor.YELLOW+sender.getName()+ChatColor.GRAY+".");
                        sender.sendMessage(Plname+ChatColor.RED+"Disabled build for "+ChatColor.YELLOW+target.getName());
                        return true;
                    }
                }
            } else {
                // Send command overview
                sender.sendMessage("/build" + org.bukkit.ChatColor.GRAY + " - " + ChatColor.GREEN + "Enable/Disable build mode.");
                sender.sendMessage("/build <player>" + org.bukkit.ChatColor.GRAY + " - " + ChatColor.GREEN + "Enable/Disable build mode player you want.");
                return true;
            }

        }
        return true;
    }
}
