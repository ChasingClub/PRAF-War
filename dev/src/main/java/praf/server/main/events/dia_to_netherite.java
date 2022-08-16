package praf.server.main.events;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Objects;

import static org.bukkit.Bukkit.getServer;
import static praf.server.main.PRAF.Plname;

public class dia_to_netherite implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player target = event.getPlayer();
        Player killer = target.getPlayer();
//        if (Minigame.ingame.contains(target.getName())) { // Newwwww check world to check Arraylist <-----------------------------
            ItemStack armorHead = new ItemStack(Material.NETHERITE_HELMET);
            ItemStack armorBoots = new ItemStack(Material.NETHERITE_BOOTS);
            ItemStack armorLegs = new ItemStack(Material.NETHERITE_LEGGINGS);
            ItemStack armorChest = new ItemStack(Material.NETHERITE_CHESTPLATE);
            ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
            if (target.getInventory().getHelmet() == null){return;}
            ItemMeta itemMeta = Objects.requireNonNull(target.getInventory().getHelmet()).getItemMeta();
            if ((target.getWorld().getName()).equals("Netherite_game")) { // if player respawn at Void_World
                if (target.getInventory().getHelmet() != null && (target.getInventory().getHelmet().getType()).equals(Material.DIAMOND_HELMET)) {
                    target.getInventory().setHelmet(armorHead); // repalce target helmet after respawn
                } else if (target.getInventory().getHelmet() != null && (target.getInventory().getBoots().getType()).equals(Material.DIAMOND_BOOTS) && target.getInventory().getBoots() != null) {
                    target.getInventory().setBoots(armorBoots);
                } else if (target.getInventory().getHelmet() != null && (target.getInventory().getLeggings().getType()).equals(Material.DIAMOND_LEGGINGS) && target.getInventory().getLeggings() != null) {
                    target.getInventory().setLeggings(armorLegs);
                } else if (target.getInventory().getHelmet() != null && (target.getInventory().getChestplate().getType()).equals(Material.DIAMOND_CHESTPLATE) && target.getInventory().getChestplate() != null) {
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
                    BukkitScheduler scheduler = getServer().getScheduler();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    World SessionWorld = getServer().getWorld("world");
                    Location SessionWorldSpawn = new Location(SessionWorld, 64.5, 180, 26.5);
                    target.teleport(SessionWorldSpawn);
                    target.sendMessage("Send Back to spawn");

                } else {
                    return;
                }
            }
    }
}
