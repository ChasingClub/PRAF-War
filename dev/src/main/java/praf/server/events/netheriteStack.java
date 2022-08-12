package praf.server.events;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scoreboard.Team;

import java.util.List;
import java.util.Random;

import static org.bukkit.Bukkit.getServer;
import static praf.server.praf.Plname;

public class netheriteStack implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {

        Player target = event.getPlayer();
        Player killer = target.getPlayer();
        Location killerSpawn = killer.getBedSpawnLocation();
        Location targetSpawn = killer.getBedSpawnLocation();
        assert killerSpawn != null;
        assert targetSpawn != null;

        //        if (Minigame.ingame.contains(target.getName())) { // this suck!
        ItemStack armorHead = new ItemStack(Material.NETHERITE_HELMET);
        ItemStack armorBoots = new ItemStack(Material.NETHERITE_BOOTS);
        ItemStack armorLegs = new ItemStack(Material.NETHERITE_LEGGINGS);
        ItemStack armorChest = new ItemStack(Material.NETHERITE_CHESTPLATE);

        if ((target.getWorld().getName()).equals("Netherite_game")) { // if player respawn at Void_World

            if (target.getInventory().getHelmet() != null && (target.getInventory().getHelmet().getType()).equals(Material.DIAMOND_HELMET)) {

                target.getInventory().setHelmet(armorHead); // repalce target helmet after respawn
                event.setRespawnLocation(targetSpawn); // respawn player in specfic spawn point
                killer.teleport(killerSpawn); // prevent spawn camp

            }

            else if (target.getInventory().getBoots() != null && (target.getInventory().getBoots().getType()).equals(Material.DIAMOND_BOOTS)) {

                target.getInventory().setBoots(armorBoots);
                event.setRespawnLocation(targetSpawn);
                killer.teleport(killerSpawn);

            }

            else if (target.getInventory().getLeggings() != null && (target.getInventory().getLeggings().getType()).equals(Material.DIAMOND_LEGGINGS)) {

                target.getInventory().setLeggings(armorLegs);
                event.setRespawnLocation(targetSpawn);
                killer.teleport(killerSpawn);

            }

            else if (target.getInventory().getChestplate() != null && (target.getInventory().getChestplate().getType()).equals(Material.DIAMOND_CHESTPLATE)) {

                target.getInventory().setChestplate(armorChest);
                event.setRespawnLocation(targetSpawn);
                killer.teleport(killerSpawn);

            }

            else if (target.getInventory().contains(Material.DIAMOND_SWORD)) { // if target has diamond sword

                for (int i = 0; i < target.getInventory().getSize(); i++) { // check the target slot
                    ItemStack item = target.getInventory().getItem(i); // get target slot
                    if (item != null && item.getType().equals(Material.DIAMOND_SWORD)) {
                        item.setType(Material.NETHERITE_SWORD); // replace target slot
                    }
                }

                event.setRespawnLocation(targetSpawn);
                killer.teleport(killerSpawn);

            }

            else if (target.getInventory().contains(Material.DIAMOND_AXE)) {

                for (int i = 0; i < target.getInventory().getSize(); i++) {
                    ItemStack item = target.getInventory().getItem(i);
                    if (item != null && item.getType().equals(Material.DIAMOND_AXE)) {
                        item.setType(Material.NETHERITE_AXE);
                    }
                }

                event.setRespawnLocation(targetSpawn);
                killer.teleport(killerSpawn);

            } else if (target.getInventory().contains(Material.NETHERITE_AXE)) {

                target.setGameMode(GameMode.SPECTATOR);
                target.sendMessage(Plname + "Nice Try! you has been defeated by " + killer.getName());
                killer.setGameMode(GameMode.SPECTATOR);
                killer.sendMessage(Plname + "GG! you have defeated " + target.getName());
                target.getInventory().clear();
                killer.getInventory().clear();
                World SessionWorld = getServer().getWorld("world");
                Location SessionWorldSpawn = new Location(SessionWorld, 64.5, 180, 26.5);
                event.setRespawnLocation(SessionWorldSpawn);
                killer.teleport(SessionWorldSpawn);

            }
        }
    }
}