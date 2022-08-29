package praf.server.main.command;

import static org.bukkit.Bukkit.getServer;
import static praf.server.main.Core.*;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class duel implements CommandExecutor {
    /*
     * args[0] = duels options -> { invite, accept, reject }
     * args[1] = player target
     * args[2] = games -> { NetheriteStack, DodgeBall, Paintball, ClassicIron, ClassicDiamond, OP, Crystal }
     * args[3].. error with arguments
     * */

    public String usage = Plname + ChatColor.YELLOW + "/duel <invite/accept/reject> <player>";
    public static String game = "NetheriteStack, DodgeBall, Paintball, ClassicIron, ClassicDiamond, OP, Crystal";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) { // player

            Player player = (Player) sender;
            String playerName = player.getName();
            World NetheriteStack = Bukkit.getServer().getWorld("netherite_game");

            ItemStack[] armor = new ItemStack[4];
            armor[0] = new ItemStack(Material.DIAMOND_BOOTS);
            armor[1] = new ItemStack(Material.DIAMOND_LEGGINGS);
            armor[2] = new ItemStack(Material.DIAMOND_CHESTPLATE);
            armor[3] = new ItemStack(Material.DIAMOND_HELMET);
            ItemMeta imh = armor[0].getItemMeta();
            ItemMeta imc = armor[1].getItemMeta();
            ItemMeta iml = armor[2].getItemMeta();
            ItemMeta imb = armor[3].getItemMeta();
            imh.setUnbreakable(true);
            imc.setUnbreakable(true);
            iml.setUnbreakable(true);
            imb.setUnbreakable(true);
            armor[0].setItemMeta(imh);
            armor[1].setItemMeta(imc);
            armor[2].setItemMeta(iml);
            armor[3].setItemMeta(imb);

            ItemStack arrow = new ItemStack(Material.ARROW, 32);

            ItemStack[] weapon = new ItemStack[5];
            weapon[0] = new ItemStack(Material.DIAMOND_SWORD);
            weapon[1] = new ItemStack(Material.DIAMOND_AXE);
            weapon[2] = new ItemStack(Material.BOW);
            weapon[3] = new ItemStack(Material.CROSSBOW);
            weapon[4] = new ItemStack(Material.COOKED_BEEF, 64);

            ItemStack shield = new ItemStack(Material.SHIELD);


            if (args.length == 0) {

                player.sendMessage(Plname + ChatColor.RED + "You need to provide an options to duels");
                player.sendMessage(usage);

            } else if (args.length == 1) { // if se
                // nd only options
                if (args[0].equalsIgnoreCase("leave")){
                    if (ingame.get(player.getName()) == null) {
                        player.sendMessage(Plname+ChatColor.RED+"You are not in the game!");
                        return true;
                    }
                    World SessionWorld = Bukkit.getServer().getWorld("world");
                    Location Spawn = new Location(SessionWorld, 64.5, 180, 26.5);
                    Player target = getServer().getPlayer(ingame.get(player.getName()));
                    // run duel leave
                    target.getInventory().clear();
                    player.getInventory().clear();
                    combatList.put(player.getName(), 0);
                    combatList.put(target.getName(), 0);
                    target.teleport(Spawn);
                    target.sendMessage(Plname + "GG! you have defeated " + player.getName());
                    player.teleport(Spawn);
                    player.sendMessage(Plname + "You lost because you left the game!");
                    GetKitSelect(target);
                    GetKitSelect(player);
                    if (playerinmap.get(player.getName()).equals("Colosseum")){
                        maps.put("Colosseum", true);
                    }if (playerinmap.get(player.getName()).equals("Beach")){
                        maps.put("Beach", true);
                    }if (playerinmap.get(player.getName()).equals("Abyss")){
                        maps.put("Abyss", true);
                    }
                    playerinmap.remove(player.getName());
                    playerinmap.remove(target.getName());
                    ingame.remove(target.getName());
                    ingame.remove(player.getName());
                    return true;
                }
                if (duel.get(args[0]) != null) {

                    player.sendMessage(Plname + ChatColor.RED + "You need to specify a player to duels!");
                    player.sendMessage(Plname + ChatColor.YELLOW + "/duel "+args[0]+" <player>");

                }else{
                    player.sendMessage(Plname + ChatColor.RED + "You need to provide an options to duels");
                    player.sendMessage(usage);
                }
            } else if (args.length == 2) {// if send until player

                Player target = getServer().getPlayer(args[1]);
                if (duel.get(args[0]) == null) {

                    player.sendMessage(Plname + ChatColor.RED + "You need to provide an options to duels");
                    player.sendMessage(usage);

                }
                if (args[0].equalsIgnoreCase("invite")) {

                    if (target == null) {

                        player.sendMessage(Plname + ChatColor.RED + "That player is offline!");
                        return true;

                    } else { // not specify game

                        player.sendMessage(Plname + "You need to specify the game!");
                        player.sendMessage(Plname + game);

                    }
                } else if (args[0].equalsIgnoreCase("accept")) { // accept
                    if (target == null) {

                        player.sendMessage(Plname + ChatColor.RED + "That player is offline!");
                        return true;

                    }
                    if (inviteList.get(player.getName()) == null) { // if target doest invite the player

                        player.sendMessage(Plname + target.getName() + " doesn't invite you to duel!");
                        return true;

                    }
                    dueltimer.remove(player.getName());
                    if (inviteList.get(player.getName()) != null) {

                        if (inviteList.get(player.getName()).containsValue("netheritestack")) {

                            if (Boolean.TRUE.equals(maps.get("Colosseum"))) {
                                target.sendMessage(Plname + player.getName() + ChatColor.GREEN + " has accept the duel"); // accept
                                Location TeamA = new Location(NetheriteStack, 0.5, 66, 17.5, 180f, 0f);
                                Location TeamB = new Location(NetheriteStack, 0.5, 66, -16.5, 0f, 0f);
                                player.teleport(TeamA);
                                target.teleport(TeamB);
                                player.setBedSpawnLocation(TeamA, true);
                                target.setBedSpawnLocation(TeamB, true);
                                player.getInventory().setArmorContents(armor);
                                target.getInventory().setArmorContents(armor);
                                player.getInventory().setStorageContents(weapon);
                                target.getInventory().setStorageContents(weapon);
                                player.getInventory().setItemInOffHand(shield);
                                target.getInventory().setItemInOffHand(shield);
                                player.getInventory().setItem(9, arrow);
                                target.getInventory().setItem(9, arrow);
                                player.setGameMode(GameMode.ADVENTURE);
                                target.setGameMode(GameMode.ADVENTURE);
                                player.sendMessage(Plname+"You are currently playing on "+ChatColor.GREEN+"\"Colosseum\"");
                                target.sendMessage(Plname+"You are currently playing on "+ChatColor.GREEN+"\"Colosseum\"");
                                ingame.put(player.getName(), target.getName());
                                ingame.put(target.getName(), player.getName());
                                playerinmap.put(player.getName(), "Colosseum");
                                playerinmap.put(target.getName(), "Colosseum");
                                maps.put("Colosseum", false);
                                inviteList.remove(player.getName());
                                return true;
                            }else if (Boolean.TRUE.equals(maps.get("Beach"))) {
                                target.sendMessage(Plname + player.getName() + ChatColor.GREEN + " has accept the duel"); // accept
                                Location TeamA = new Location(NetheriteStack, -99.5, 66, -80.5, 180f, 0f);
                                Location TeamB = new Location(NetheriteStack, -99.5, 66, -118.5, 0f, 0f);
                                player.teleport(TeamA);
                                target.teleport(TeamB);
                                player.setBedSpawnLocation(TeamA, true);
                                target.setBedSpawnLocation(TeamB, true);
                                player.getInventory().setArmorContents(armor);
                                target.getInventory().setArmorContents(armor);
                                player.getInventory().setStorageContents(weapon);
                                target.getInventory().setStorageContents(weapon);
                                player.getInventory().setItemInOffHand(shield);
                                target.getInventory().setItemInOffHand(shield);
                                player.getInventory().setItem(9, arrow);
                                target.getInventory().setItem(9, arrow);
                                player.setGameMode(GameMode.ADVENTURE);
                                target.setGameMode(GameMode.ADVENTURE);
                                player.sendMessage(Plname+"You are currently playing on "+ChatColor.GREEN+"\"Beach\"");
                                target.sendMessage(Plname+"You are currently playing on "+ChatColor.GREEN+"\"Beach\"");
                                ingame.put(player.getName(), target.getName());
                                ingame.put(target.getName(), player.getName());
                                playerinmap.put(player.getName(), "Beach");
                                playerinmap.put(target.getName(), "Beach");
                                maps.put("Beach", false);
                                inviteList.remove(player.getName());
                                return true;
                            }else if (Boolean.TRUE.equals(maps.get("Abyss"))) {
                                target.sendMessage(Plname + player.getName() + ChatColor.GREEN + " has accept the duel"); // accept
                                Location TeamA = new Location(NetheriteStack, -99.5, 65, 107.5, 180f, 0f);
                                Location TeamB = new Location(NetheriteStack, -99.5, 65, 93.5, 0f, 0f);
                                player.teleport(TeamA);
                                target.teleport(TeamB);
                                player.setBedSpawnLocation(TeamA, true);
                                target.setBedSpawnLocation(TeamB, true);
                                player.getInventory().setArmorContents(armor);
                                target.getInventory().setArmorContents(armor);
                                player.getInventory().setStorageContents(weapon);
                                target.getInventory().setStorageContents(weapon);
                                player.getInventory().setItemInOffHand(shield);
                                target.getInventory().setItemInOffHand(shield);
                                player.getInventory().setItem(9, arrow);
                                target.getInventory().setItem(9, arrow);
                                player.setGameMode(GameMode.ADVENTURE);
                                target.setGameMode(GameMode.ADVENTURE);
                                player.sendMessage(Plname+"You are currently playing on "+ChatColor.GREEN+"\"Abyss\"");
                                target.sendMessage(Plname+"You are currently playing on "+ChatColor.GREEN+"\"Abyss\"");
                                ingame.put(player.getName(), target.getName());
                                ingame.put(target.getName(), player.getName());
                                playerinmap.put(player.getName(), "Abyss");
                                playerinmap.put(target.getName(), "Abyss");
                                maps.put("Abyss", false);
                                inviteList.remove(player.getName());
                                return true;
                            }else{
                                player.sendMessage(Plname+ChatColor.RED+"No area available.");
                                inviteList.remove(player.getName());
                                return true;
                            }
                        } else {
                            player.sendMessage(Plname + ChatColor.RED + "Game: " + game);
                        }
                    }
                } else if (args[0].equalsIgnoreCase("reject")) { // reject
                    if (target == null) {
                        player.sendMessage(Plname + ChatColor.RED + "That player is offline!");
                        return true;
                    }
                    if (!(inviteList.containsKey(player.getName()))) {

                        player.sendMessage(Plname + target.getName() + " doesn't invite you to duel!");
                        return true;

                    } else if (inviteList.containsKey(player.getName())) {

                        player.sendMessage(Plname + target.getName() +ChatColor.GREEN+ " has reject the duels"); // reject
                        inviteList.remove(player.getName());
                        return true;
                    }
                    sender.sendMessage(ChatColor.YELLOW + " Plugin help:");
                    sender.sendMessage(ChatColor.RED + "/duel " + args[0].toLowerCase() + " <player> <game>");
                    return true;
                }
            } else if (args.length == 2) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Can't use with console.");
                    return true;
                }
                if (duel.get(args[0].toLowerCase()) == null) {
                    sender.sendMessage(ChatColor.YELLOW + " Plugin help:");
                    sender.sendMessage(ChatColor.RED + "/duel <invite/accept/reject> <player> <game>");
                    return true;
                }
                Player argplayer = getServer().getPlayer(args[1]);
                if (argplayer == null) {
                    sender.sendMessage("Player " + ChatColor.GRAY + args[1] + ChatColor.RESET + " could not be found");
                    return true;
                }
                sender.sendMessage(ChatColor.YELLOW + " Plugin help:");
                sender.sendMessage(ChatColor.RED + "/duel " + args[0].toLowerCase() + " " + argplayer.getName() + " <game>");
                return true;
            } else if (args.length == 3) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Can't use with console.");
                    return true;
                }
                if (duel.get(args[0].toLowerCase()) == null) {
                    sender.sendMessage(ChatColor.YELLOW + " Plugin help:");
                    sender.sendMessage(ChatColor.RED + "/duel <invite/accept/reject> <player> <game>");
                    return true;
                }
                Player argplayer = getServer().getPlayer(args[1]);
                if (argplayer == null) {
                    sender.sendMessage("Player " + ChatColor.GRAY + args[1].toLowerCase() + ChatColor.RESET + " could not be found");
                    return true;
                }
                if (games.get(args[2].toLowerCase()) == null) {
                    player.sendMessage(Plname + "You need to specify the game!");
                    player.sendMessage(Plname + game);
                    return true;
                }


                    Player target = Bukkit.getPlayer(args[1]);
                    if (args[0].equalsIgnoreCase("invite")) {

                        if (target == null) {

                            player.sendMessage(Plname + ChatColor.RED + "That player is offline!");
                            return true;

                        }if (ingame.get(target.getName()) != null){

                            player.sendMessage(Plname + ChatColor.RED + "That player is currently in a game!");
                            return true;

                        }if (inviteList.get(target.getName()) != null){

                            player.sendMessage(Plname + ChatColor.RED + "You already invite that player!");
                            return true;

                        } else {

                            // send to player with usage
                            target.sendMessage(Plname + ChatColor.GREEN + " " + playerName + " invites you for duels");
                            target.sendMessage(Plname + ChatColor.GREEN + " invite will be remove in 20s");
                            target.sendMessage(Plname + ChatColor.GREEN + "Use /duel <accept/reject> " + playerName);

                            // send Clickable Message to player
                            dueltimer.put(target.getName(), 20);
                            HashMap<String, String> values = new HashMap<>();
                            values.put(player.getName(), args[2].toLowerCase());
                            inviteList.put(target.getName(), values); // adds player to duels List
                            player.sendMessage(Plname+"Duel have been sent to "+target.getName());

                        }

                    } else {

                        player.sendMessage(usage);
                        player.sendMessage(Plname + "Game -> " + game);
                    }
                    // when send invite done

                    return true;
                } else {
                    sender.sendMessage(ChatColor.YELLOW + " Plugin help:");
                    sender.sendMessage(ChatColor.RED + "/duel <invite/accept/reject> <player> <game>");
                    return true;
                }
            }
        return true;
    }
}