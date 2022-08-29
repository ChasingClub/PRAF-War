package praf.server.main.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;
import static praf.server.main.Core.*;

public class map implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("map")) {
            // Check for arguments
            if (args.length == 0) {
                if (!(sender.hasPermission("rank.admin"))) {
                    sender.sendMessage("/map list" + ChatColor.GRAY + " - " + ChatColor.GOLD + "Check duels map you can join.");
                    return true;
                }else {
                    sender.sendMessage("/map [disable|enable|list] <map>" + ChatColor.GRAY + " - " + ChatColor.GOLD + "Disable/Enable map you want.");
                    return true;
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("list")) {
                    sender.sendMessage(ChatColor.GRAY + "=-----------[ " + ChatColor.GREEN+ChatColor.BOLD+ChatColor.ITALIC+ "MAP" +ChatColor.RESET+ChatColor.GRAY+" ]-----------=");

                    // MAP LIST CHECK
                    String Colosseum = null;
                    String Beach = null;
                    String Abyss = null;
                    if (Boolean.TRUE.equals(maps.get("Colosseum"))){Colosseum = ChatColor.GREEN+"ENABLED";}
                    if (Boolean.FALSE.equals(maps.get("Colosseum"))){Colosseum = ChatColor.RED+"DISABLED";}
                    if (Boolean.TRUE.equals(maps.get("Beach"))){Beach = ChatColor.GREEN+"ENABLED";}
                    if (Boolean.FALSE.equals(maps.get("Beach"))){Beach = ChatColor.RED+"DISABLED";}
                    if (Boolean.TRUE.equals(maps.get("Abyss"))){Abyss = ChatColor.GREEN+"ENABLED";}
                    if (Boolean.FALSE.equals(maps.get("Abyss"))){Abyss = ChatColor.RED+"DISABLED";}

                    // SEND ALL LIST
                    sender.sendMessage(ChatColor.YELLOW+"Colosseum"+ChatColor.GRAY+" - "+Colosseum);
                    sender.sendMessage(ChatColor.YELLOW+"Beach"+ChatColor.GRAY+" - "+Beach);
                    sender.sendMessage(ChatColor.YELLOW+"Abyss"+ChatColor.GRAY+" - "+Abyss);

                    sender.sendMessage(ChatColor.GRAY+"=----------------------------=");
                    return true;
                }
                if (sender.hasPermission("rank.admin")) {
                    if (args[0].equalsIgnoreCase("disable")) {
                        sender.sendMessage("/map "+args[0]+" <map>" + ChatColor.GRAY + " - " + ChatColor.GOLD + "Disable/Enable map you want.");
                        return true;
                    }if (args[0].equalsIgnoreCase("enable")) {
                        sender.sendMessage("/map "+args[0]+" <map>" + ChatColor.GRAY + " - " + ChatColor.GOLD + "Disable/Enable map you want.");
                        return true;
                    }else{
                        sender.sendMessage("/map [disable|enable|list] <map>" + ChatColor.GRAY + " - " + ChatColor.GOLD + "Disable/Enable map you want.");
                        return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.RED+"You Don't have permission to do that!");
                    return true;
                }
            } else if (args.length == 2) {
                if (sender.hasPermission("rank.admin")) {
                    if (maps.get(args[1]) == null){
                        sender.sendMessage(Plname+ChatColor.RED+"This map doesn't exist!");
                        return true;
                    }
                    String m = args[1];
                    // Disable
                    if (args[0].equalsIgnoreCase("disable")) {
                        if (Boolean.TRUE.equals(maps.get(m))){
                            maps.put(m, false);
                            sender.sendMessage(Plname+ChatColor.GREEN+m+ChatColor.GRAY+" has been disabled.");
                            return true;
                        }
                        for (Player p : getServer().getOnlinePlayers()){
                            if (playerinmap.get(p.getName()) != null) {
                                if (playerinmap.get(p.getName()).equals(m)) {
                                    World SessionWorld = Bukkit.getServer().getWorld("world");
                                    Location Spawn = new Location(SessionWorld, 64.5, 180, 26.5);
                                    p.teleport(Spawn);
                                    p.getInventory().clear();
                                    combatList.put(p.getName(), 0);
                                    GetKitSelect(p);
                                    playerinmap.remove(p.getName());
                                    ingame.remove(p.getName());
                                    p.sendMessage(Plname + "Map has been disabled by " + ChatColor.RED + sender.getName());
                                    maps.put(m, false);
                                    sender.sendMessage(Plname + ChatColor.GREEN + m + ChatColor.GRAY + " has been disabled.");
                                }
                            }
                        }
                        if (Boolean.FALSE.equals(maps.get(m))){
                            sender.sendMessage(Plname+ChatColor.YELLOW+m+ChatColor.RED+" already disabled.");
                            return true;
                        }
                        return true;
                    }
                    // Enable
                    if (args[0].equalsIgnoreCase("enable")) {
                        if (Boolean.FALSE.equals(maps.get(m))){
                            maps.put(m, true);
                            sender.sendMessage(Plname+ChatColor.GREEN+m+ChatColor.GRAY+" has been enabled.");
                            return true;
                        }
                        if (Boolean.TRUE.equals(maps.get(m))){
                            sender.sendMessage(Plname+ChatColor.YELLOW+m+ChatColor.RED+" already enabled.");
                            return true;
                        }
                        return true;
                    }else {
                        sender.sendMessage("/map [disable|enable|list] <map>" + ChatColor.GRAY + " - " + ChatColor.GOLD + "Disable/Enable map you want.");
                        return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.RED+"You Don't have permission to do that!");
                    return true;
                }
            } else {
                sender.sendMessage("/map [disable|enable|list] <map>" + ChatColor.GRAY + " - " + ChatColor.GOLD + "Disable/Enable map you want.");
                return true;
            }

        }
        return true;
    }
}
