package praf.server.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;

public class noAbuseGame implements Listener {
    @EventHandler
    public void InventoryChange(InventoryClickEvent e) {
        Player player = (Player) e;
        player.sendMessage("Clicked");

    }
}
