package praf.server.events;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Bukkit.getServer;
import static praf.server.praf.Plname;

public class dia_to_netherite implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player target = event.getPlayer();
        Player killer = target.getPlayer();
//        if (Minigame.ingame.contains(target.getName())) { // this suck!
            ItemStack armorHead = new ItemStack(Material.NETHERITE_HELMET);
            ItemStack armorBoots = new ItemStack(Material.NETHERITE_BOOTS);
            ItemStack armorLegs = new ItemStack(Material.NETHERITE_LEGGINGS);
            ItemStack armorChest = new ItemStack(Material.NETHERITE_CHESTPLATE);
            if ((target.getWorld().getName()).equals("Netherite_game")) { // if player respawn at Void_World
                    if ((target.getInventory().getHelmet().getType()).equals(Material.DIAMOND_HELMET)) {
                        target.getInventory().setHelmet(armorHead); // repalce target helmet after respawn
                    } else if ((target.getInventory().getBoots().getType()).equals(Material.DIAMOND_BOOTS)) {
                        target.getInventory().setBoots(armorBoots);
                    } else if ((target.getInventory().getLeggings().getType()).equals(Material.DIAMOND_LEGGINGS)) {
                        target.getInventory().setLeggings(armorLegs);
                    } else if ((target.getInventory().getChestplate().getType()).equals(Material.DIAMOND_CHESTPLATE)) {
                        target.getInventory().setChestplate(armorChest);
                    } else if (target.getInventory().contains(Material.DIAMOND_SWORD)) { // if target has diamond sword
                        for (int i = 0; i < target.getInventory().getSize(); i++) { // check the target slot
                            ItemStack item = target.getInventory().getItem(i); // get target slot
                            if (item != null && item.getType().equals(Material.DIAMOND_SWORD)) {
                                item.setType(Material.NETHERITE_SWORD); // replace target slot
                            }
                        }
                    } else if (target.getInventory().contains(Material.DIAMOND_AXE)) {
                        for (int i = 0; i < target.getInventory().getSize(); i++) {
                            ItemStack item = target.getInventory().getItem(i);
                            if (item != null && item.getType().equals(Material.DIAMOND_AXE)) {
                                item.setType(Material.NETHERITE_AXE);
                            }
                        }
                    } else if (target.getInventory().contains(Material.NETHERITE_AXE) && target.getInventory().contains(Material.SHIELD)) {
                        target.setGameMode(GameMode.SPECTATOR);
                        target.sendMessage(Plname + "Nice Try! you has been defeated by " + killer.getName());
                        killer.setGameMode(GameMode.SPECTATOR);
                        target.sendMessage(Plname + "GG! you have defeated " + target.getName());
                        target.getInventory().clear();
                        killer.getInventory().clear();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        World SessionWorld = getServer().getWorld("world");
                        Location SessionWorldSpawn = new Location(SessionWorld, 64.5, 180, 26.5);
                        target.teleport(SessionWorldSpawn);
                        target.sendMessage("Send Back to spawn");

                    }
                }


    }
}
