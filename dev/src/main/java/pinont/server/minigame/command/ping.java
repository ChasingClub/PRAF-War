package pinont.server.minigame.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import pinont.server.minigame.Minigame;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.bukkit.Bukkit.getServer;

public class ping implements CommandExecutor, Listener {
    Class<?> CPClass;

    String serverName  = getServer().getClass().getPackage().getName(),
            serverVersion = serverName.substring(serverName.lastIndexOf(".") + 1, serverName.length());

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        // Define prefix
        String prefix = Minigame.Plname;


        //  /ping command
        if (cmd.getName().equalsIgnoreCase("ping"))
        {
            // Check for arguments
            if(args.length == 0) {
                // Send command overview
                if (!(sender instanceof Player)){
                    sender.sendMessage(prefix + " " +  "Your ping: " + ChatColor.GRAY + "0 ms" + ChatColor.RESET + " you silly console!");
                    return true;
                } else {
                    // Define player object
                    final Player p = (Player) sender;
                    sender.sendMessage(prefix + " " + "Your ping: " + ChatColor.GRAY + getPing(p) + " ms" + ChatColor.RESET);
                    return true;
                }
            } else if(args.length == 1) {
                if(sender.hasPermission("rank.admin")){
                    Player argplayer = getServer().getPlayer(args[0]);
                    if(argplayer == null) {
                        sender.sendMessage(prefix + " " + "The player " + ChatColor.GRAY + args[0] + ChatColor.RESET +" could not be found");
                        return true;
                    }
                    // Send command overview
                    sender.sendMessage(prefix + " " + getServer().getPlayer(args[0]).getDisplayName().toString() + "'s ping: " + ChatColor.GRAY + getPing(argplayer) + " ms" + ChatColor.RESET);
                    return true;
                } else {
                    sender.sendMessage(prefix + " " +  "You dont have the permission: " + ChatColor.GRAY + "- rank.admin");
                    return true;
                }
            } else {
                // Send command overview
                sender.sendMessage(prefix + ChatColor.YELLOW +" Plugin help:");
                sender.sendMessage("/ping" + ChatColor.GRAY + " - " + ChatColor.GOLD + "Check your ping.");
                sender.sendMessage("/ping <player>" + ChatColor.GRAY + " - " + ChatColor.GOLD + "Check ping of player.");
                return true;
            }

        }
        return false;
    }

    public int getPing(Player p) {
        try {
            CPClass = Class.forName("org.bukkit.craftbukkit." + serverVersion + ".entity.CraftPlayer");
            Object CraftPlayer = CPClass.cast(p);

            Method getHandle = CraftPlayer.getClass().getMethod("getHandle", new Class[0]);
            Object EntityPlayer = getHandle.invoke(CraftPlayer, new Object[0]);

            Field ping = EntityPlayer.getClass().getDeclaredField("ping");

            return ping.getInt(EntityPlayer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
