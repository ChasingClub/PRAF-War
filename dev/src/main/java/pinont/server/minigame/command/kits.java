package pinont.server.minigame.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class kits implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (sender instanceof Player) {
                ItemStack[] armor = new ItemStack[4];
                armor[0] = new ItemStack(Material.DIAMOND_BOOTS);
                armor[1] = new ItemStack(Material.DIAMOND_LEGGINGS);
                armor[2] = new ItemStack(Material.DIAMOND_CHESTPLATE);
                armor[3] = new ItemStack(Material.DIAMOND_HELMET);
                ((Player) sender).getInventory().setArmorContents(armor);
            }
        }
        return true;
    }
}
