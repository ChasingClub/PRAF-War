package pinont.server.minigame.utils.machanic;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class particles {
    public static void PlayerParticles(Player p) {
        Location loc = p.getLocation();
        for (int i=0; i<1000;i++) {
            p.playSound(loc , Sound.UI_TOAST_CHALLENGE_COMPLETE, 1000000000, 0);
        }
    }
}
