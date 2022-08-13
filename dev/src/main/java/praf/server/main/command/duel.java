package praf.server.main.command;

import static org.bukkit.Bukkit.getServer;
import static praf.server.main.PRAF.*;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class duel implements CommandExecutor {
    /*
     * args[0] = duels options -> { invite, accept, reject }
     * args[1] = player target
     * args[2] = games -> { NetheriteStack, DodgeBall, Paintball, ClassicIron, ClassicDiamond, OP, Crystal }
     * args[3].. error with arguments
     * */

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("duel")) {
            // Check for arguments
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Can't use with console.");
                    return true;
                }else{
                    if (sender instanceof Player) {
                        sender.sendMessage(ChatColor.YELLOW+" Plugin help:");
                        sender.sendMessage(ChatColor.RED+"/duel <invite/accept/reject> <player> <game>");
                        return true;
                    }
                }
            } else if (args.length == 1) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Can't use with console.");
                    return true;
                }else{
                    if (duel.get(args[0].toLowerCase()) == null) {
                        sender.sendMessage(ChatColor.YELLOW+" Plugin help:");
                        sender.sendMessage(ChatColor.RED+"/duel <invite/accept/reject> <player> <game>");
                        return true;
                    }
                    sender.sendMessage(ChatColor.YELLOW+" Plugin help:");
                    sender.sendMessage(ChatColor.RED+"/duel "+args[0].toLowerCase()+" <player> <game>");
                    return true;
                }
            } else if (args.length == 2) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Can't use with console.");
                    return true;
                }
                if (duel.get(args[0].toLowerCase()) == null) {
                    sender.sendMessage(ChatColor.YELLOW+" Plugin help:");
                    sender.sendMessage(ChatColor.RED+"/duel <invite/accept/reject> <player> <game>");
                    return true;
                }
                Player argplayer = getServer().getPlayer(args[1]);
                if (argplayer == null) {
                    sender.sendMessage("Player " + ChatColor.GRAY + args[1] + ChatColor.RESET + " could not be found");
                    return true;
                }
                sender.sendMessage(ChatColor.YELLOW+" Plugin help:");
                sender.sendMessage(ChatColor.RED+"/duel "+args[0].toLowerCase()+" "+argplayer.getName()+" <game>");
                return true;
            } else if (args.length == 3) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Can't use with console.");
                    return true;
                }
                if (duel.get(args[0].toLowerCase()) == null) {
                    sender.sendMessage(ChatColor.YELLOW+" Plugin help:");
                    sender.sendMessage(ChatColor.RED+"/duel <invite/accept/reject> <player> <game>");
                    return true;
                }
                Player argplayer = getServer().getPlayer(args[1]);
                if (argplayer == null) {
                    sender.sendMessage("Player " + ChatColor.GRAY + args[1].toLowerCase() + ChatColor.RESET + " could not be found");
                    return true;
                }
                if (games.get(args[2].toLowerCase()) == null) {
                    sender.sendMessage("Player " + ChatColor.GRAY + args[1].toLowerCase() + ChatColor.RESET + " could not be found");
                    return true;
                }
                // when send invite done

                return true;
            } else {
                sender.sendMessage(ChatColor.YELLOW+" Plugin help:");
                sender.sendMessage(ChatColor.RED+"/duel <invite/accept/reject> <player> <game>");
                return true;
            }

        }
        return true;
    }
}