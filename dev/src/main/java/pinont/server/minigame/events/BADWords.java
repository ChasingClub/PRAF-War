package pinont.server.minigame.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class BADWords implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        char Marks = '\u0022';
        Player p = e.getPlayer();
        if (e.getMessage().toLowerCase().contains("fuck")) {
            e.setMessage("I'm so stupid.");
            return;
        }else if (e.getMessage().toLowerCase().contains("pussy")) {
            e.setMessage("I'm so stupid.");
            return;
        }else if (e.getMessage().toLowerCase().contains("bitch")) {
            e.setMessage("I'm so stupid.");
            return;
        }else if (e.getMessage().toLowerCase().contains("dick")) {
            e.setMessage("I'm so stupid.");
            return;
        }else if (e.getMessage().toLowerCase().contains("shit")) {
            e.setMessage("I'm so stupid.");
            return;
        }else if (e.getMessage().toLowerCase().contains("nigga")) {
            e.setMessage("I'm nigga.");
            return;
        }
    }
}
