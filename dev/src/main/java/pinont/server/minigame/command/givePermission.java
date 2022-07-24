package pinont.server.minigame.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import pinont.server.minigame.Minigame;

import java.util.HashMap;
import java.util.UUID;

public class givePermission implements CommandExecutor {

    HashMap<UUID, PermissionAttachment> playerPermission = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(!p.getGameMode().equals(GameMode.CREATIVE) || p.hasPermission("rank.admin")) {
            if (args.length > 2) {
                Player target = Bukkit.getPlayer(args[0]);
                PermissionAttachment attachment = sender.addAttachment(new Minigame());
                this.playerPermission.put(p.getUniqueId(), attachment);
                if (args[2].equals("give")) {
                    attachment.setPermission("ranks." + args[3], true);
                } else if (args[2].equals("remove")) {
                    playerPermission.remove("ranks." + args[3]);
                }
//            String permissions : this.getConfig().getStringList("Groups." + groups + ".permissions";
            } else {
                sender.sendMessage(ChatColor.RED + "You need to /perm give [player] [perms]");
            }

        } else {
            if(sender instanceof Player) {
                sender.sendMessage(ChatColor.RED + "You need be Creative before run this command");
            }
        }
        return true;
    }
}
