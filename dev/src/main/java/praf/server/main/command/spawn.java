package praf.server.main.command;


import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import praf.server.main.Core;

import static org.bukkit.Bukkit.getServer;
import static praf.server.main.Core.*;

public class spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                if (ingame.get(p.getName()) != null) {
                    p.sendMessage(Plname + ChatColor.RED + "You are currently in a game!");
                    p.sendMessage(Plname + ChatColor.YELLOW + "You can use this command to leave the game " + ChatColor.GRAY + "- " + ChatColor.RED + "/duel leave");
                    return true;
                }
                if (p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
                    if (p.getLocation().getWorld().getName().endsWith("world")) {
                        p.getInventory().clear();
                        for (PotionEffect effect : p.getActivePotionEffects())
                            p.removePotionEffect(effect.getType());
                    }
                    GetKitSelect(p);
                }
                World SessionWorld = getServer().getWorld("world");
                Location SessionWorldSpawn = new Location(SessionWorld, 64.5, 180, 26.5);
                p.teleport(SessionWorldSpawn);
                if (p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
                    p.sendMessage(Core.Plname + "You have been teleported to spawn.");
                } else if (p.getGameMode() == GameMode.CREATIVE) {
                    p.sendMessage(Core.Plname + "You have been teleported to spawn and your item will not clear.");
                } else if (p.getGameMode() == GameMode.SPECTATOR) {
                    p.sendMessage(Core.Plname + "You have been teleported to spawn and your item will not clear.");
                }
                return true;
            }else if (args.length == 1) {
                if (!(p.hasPermission("rank.admin"))){
                    if (ingame.get(p.getName()) != null) {
                        p.sendMessage(Plname + ChatColor.RED + "You are currently in a game!");
                        p.sendMessage(Plname + ChatColor.YELLOW + "You can use this command to leave the game " + ChatColor.GRAY + "- " + ChatColor.RED + "/duel leave");
                        return true;
                    }
                    if (p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
                        if (p.getLocation().getWorld().getName().endsWith("world")) {
                            p.getInventory().clear();
                            for (PotionEffect effect : p.getActivePotionEffects())
                                p.removePotionEffect(effect.getType());
                        }
                        GetKitSelect(p);
                    }
                    World SessionWorld = getServer().getWorld("world");
                    Location SessionWorldSpawn = new Location(SessionWorld, 64.5, 180, 26.5);
                    p.teleport(SessionWorldSpawn);
                    if (p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
                        p.sendMessage(Core.Plname + "You have been teleported to spawn.");
                    } else if (p.getGameMode() == GameMode.CREATIVE) {
                        p.sendMessage(Core.Plname + "You have been teleported to spawn and your item will not clear.");
                    } else if (p.getGameMode() == GameMode.SPECTATOR) {
                        p.sendMessage(Core.Plname + "You have been teleported to spawn and your item will not clear.");
                    }
                    return true;
                } else {
                    // HAVE RANK
                    Player target = getServer().getPlayer(args[0]);
                    if (target == null){
                        p.sendMessage(Plname+ChatColor.RED+"That player is offline.");
                        return true;
                    }
                    if (ingame.get(target.getName()) != null) {
                        p.sendMessage(Plname + ChatColor.RED + "That player is currently in a game!");
                        p.sendMessage(Plname + ChatColor.YELLOW + "You can use this command to kick them from the game " + ChatColor.GRAY + "- " + ChatColor.RED + "/duel kick");
                        p.sendMessage(Plname + ChatColor.DARK_RED + "(ComingSoon..)");
                        return true;
                    }
                    if (target.getGameMode() != GameMode.CREATIVE && target.getGameMode() != GameMode.SPECTATOR) {
                        target.getInventory().clear();
                        for (PotionEffect effect : target.getActivePotionEffects()) {
                            target.removePotionEffect(effect.getType());
                        }
                        GetKitSelect(p);
                    }
                    World SessionWorld = getServer().getWorld("world");
                    Location SessionWorldSpawn = new Location(SessionWorld, 64.5, 180, 26.5);
                    target.teleport(SessionWorldSpawn);
                    if (target.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
                        target.sendMessage(Core.Plname + "You have been teleported to spawn.");
                    } else if (target.getGameMode() == GameMode.CREATIVE) {
                        target.sendMessage(Core.Plname + "You have been teleported to spawn and your item will not clear.");
                    } else if (target.getGameMode() == GameMode.SPECTATOR) {
                        target.sendMessage(Core.Plname + "You have been teleported to spawn and your item will not clear.");
                    }
                    p.sendMessage(Plname+"Sent "+target.getName()+" to spawn.");
                    return true;
                }
            } else {
                if (!(p.hasPermission("rank.admin"))){
                    if (ingame.get(p.getName()) != null) {
                        p.sendMessage(Plname + ChatColor.RED + "You are currently in a game!");
                        p.sendMessage(Plname + ChatColor.YELLOW + "You can use this command to leave the game " + ChatColor.GRAY + "- " + ChatColor.RED + "/duel leave");
                        return true;
                    }
                    if (p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
                        if (p.getLocation().getWorld().getName().endsWith("world")) {
                            p.getInventory().clear();
                            for (PotionEffect effect : p.getActivePotionEffects())
                                p.removePotionEffect(effect.getType());
                        }
                        GetKitSelect(p);
                    }
                    World SessionWorld = getServer().getWorld("world");
                    Location SessionWorldSpawn = new Location(SessionWorld, 64.5, 180, 26.5);
                    p.teleport(SessionWorldSpawn);
                    if (p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
                        p.sendMessage(Core.Plname + "You have been teleported to spawn.");
                    } else if (p.getGameMode() == GameMode.CREATIVE) {
                        p.sendMessage(Core.Plname + "You have been teleported to spawn and your item will not clear.");
                    } else if (p.getGameMode() == GameMode.SPECTATOR) {
                        p.sendMessage(Core.Plname + "You have been teleported to spawn and your item will not clear.");
                    }
                    return true;
                }else{
                    // HAVE RANK
                    sender.sendMessage("/spawn <player>" + ChatColor.GRAY + " - " + ChatColor.YELLOW + "Spawn player you want.");
                    return true;
                }
            }
        } else {
            System.out.println("You need to be a player to send the command");
        }
        return true;
    }
}
