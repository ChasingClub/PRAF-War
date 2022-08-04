package pinont.server.minigame.utils.machanic;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import pinont.server.minigame.Minigame;

import java.util.Arrays;
import java.util.List;

public class CancelCommand implements Listener {
    public void CancelCmd(PlayerCommandPreprocessEvent e)
    {
        if (Minigame.combatList.containsKey(e.getPlayer().getName()))
        {
            List<String> commands = Arrays.asList("/spawn");

            String[] parts = e.getMessage().split(" ");

            String cmd = parts[0].toLowerCase();

            if (commands.contains(cmd))
            {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED + "You are still in combat!");
            }
        }
    }
}
