package praf.server.main.command;

import static org.bukkit.Bukkit.getServer;
import static praf.server.main.PRAF.*;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class duel implements CommandExecutor {
    /*
     * args[0] = duels options -> { invite, accept, reject }
     * args[1] = player target
     * args[2] = games -> { NetheriteStack, DodgeBall, Paintball, ClassicIron, ClassicDiamond, OP, Crystal }
     * args[3].. error with arguments
<<<<<<< Updated upstream
     * */

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("duel")) {
            // Check for arguments
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Can't use with console.");
                    return true;
                }else{
                    if (sender instanceof Player) {
                        sender.sendMessage(ChatColor.YELLOW+" Plugin help:");
                        sender.sendMessage(ChatColor.RED+"/duel <invite/accept/reject> <player> <game>");
                        return true;
                    }
                }
            } else if (args.length == 1) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Can't use with console.");
                    return true;
                }else{
                    if (duel.get(args[0].toLowerCase()) == null) {
                        sender.sendMessage(ChatColor.YELLOW+" Plugin help:");
                        sender.sendMessage(ChatColor.RED+"/duel <invite/accept/reject> <player> <game>");
                        return true;
=======
     */

    public String usage = Plname + ChatColor.YELLOW + "/duel <invite/accept/reject> <player>";
    public HashMap<Player, String> inviteList;
    public static String game = "NetheriteStack, DodgeBall, Paintball, ClassicIron, ClassicDiamond, OP, Crystal";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) { // player

            Player player = (Player) sender;
            String playerName = player.getName();
            World NetheriteStack = Bukkit.getServer().getWorld("netherite_game");
            Location NethStack = new Location(NetheriteStack, 0, 77, 0);
            Location TeamA = new Location(NetheriteStack, 0.5, 77, 17.5);
            Location TeamB = new Location(NetheriteStack, 0.5, 77, -16.5);

            ItemStack[] armor = new ItemStack[4];
            armor[0] = new ItemStack(Material.DIAMOND_BOOTS);
            armor[1] = new ItemStack(Material.DIAMOND_LEGGINGS);
            armor[2] = new ItemStack(Material.DIAMOND_CHESTPLATE);
            armor[3] = new ItemStack(Material.DIAMOND_HELMET);

            ItemStack[] weapon = new ItemStack[6];
            weapon[0] = new ItemStack(Material.NETHERITE_SWORD);
            weapon[1] = new ItemStack(Material.NETHERITE_AXE);
            weapon[2] = new ItemStack(Material.BOW);
            weapon[3] = new ItemStack(Material.CROSSBOW);
            weapon[4] = new ItemStack(Material.ARROW, 32);
            weapon[5] = new ItemStack(Material.COOKED_BEEF, 64);

            ItemStack shield = new ItemStack(Material.SHIELD);


            if (args.length == 0) {

                player.sendMessage(Plname + ChatColor.RED + "You need to provide an options to duels");
                player.sendMessage(usage);

            } else if (args.length == 1) { // if se
                // nd only options
                if (args[0].equals("invite")) {

                    player.sendMessage(Plname + ChatColor.RED + "You need to specify a player to duels!");
                    player.sendMessage(usage);

                }


            } else if (args.length == 2) {// if send until player

                Player target = Bukkit.getPlayer(args[1]);
                String targetName = target.getName();
                inviteList = new HashMap<Player, String>();

                if (args[0].equals("invite")) {

                    if (!(target.isOnline())) {

                        player.sendMessage(Plname + ChatColor.RED + "That player is offline!");

                    } else { // not specify game

                        player.sendMessage(Plname + "You need to specify the game!");
                        player.sendMessage(Plname + game);

                    }
                } else if (args[0].equals("accept")) { // accept

                    if (!(inviteList.containsKey(target))) { // if target doest invite the player

                        player.sendMessage(Plname + targetName + " doesn't invite you to duel!"); // return

                    } else {

                        if (args[2].equalsIgnoreCase("netheriteStack")) {

                            if(inviteList.containsValue("netheritestack"))

                                player.sendMessage(Plname + targetName + Color.GREEN + " has accept the duel"); // accept
                                player.teleport(NethStack); // send player to game
                                target.teleport(NethStack);
                                player.setBedSpawnLocation(TeamA, true);
                                target.setBedSpawnLocation(TeamB, true);
                                player.getInventory().setArmorContents(armor);
                                target.getInventory().setArmorContents(armor);
                                player.getInventory().setStorageContents(weapon);
                                target.getInventory().setStorageContents(weapon);
                                player.getInventory().setItemInOffHand(shield);
                                target.getInventory().setItemInOffHand(shield);

                        } else {
                            player.sendMessage(Plname + ChatColor.RED + "Game: " + game);
                        }

                        inviteList.remove(player, target);

                    }
                } else if (args[0].equals("reject")) { // reject

                    if (!(inviteList.containsKey(player))) {

                        player.sendMessage(Plname + targetName + " doesn't invite you to duel!"); // return

                    } else {

                        player.sendMessage(Plname + targetName + " has reject the duels"); // reject
                        inviteList.remove(player, target);
                        player.sendMessage(inviteList.get(player).toString());

>>>>>>> Stashed changes
                    }
                    sender.sendMessage(ChatColor.YELLOW+" Plugin help:");
                    sender.sendMessage(ChatColor.RED+"/duel "+args[0].toLowerCase()+" <player> <game>");
                    return true;
                }
            } else if (args.length == 2) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Can't use with console.");
                    return true;
                }
                if (duel.get(args[0].toLowerCase()) == null) {
                    sender.sendMessage(ChatColor.YELLOW+" Plugin help:");
                    sender.sendMessage(ChatColor.RED+"/duel <invite/accept/reject> <player> <game>");
                    return true;
                }
                Player argplayer = getServer().getPlayer(args[1]);
                if (argplayer == null) {
                    sender.sendMessage("Player " + ChatColor.GRAY + args[1] + ChatColor.RESET + " could not be found");
                    return true;
                }
                sender.sendMessage(ChatColor.YELLOW+" Plugin help:");
                sender.sendMessage(ChatColor.RED+"/duel "+args[0].toLowerCase()+" "+argplayer.getName()+" <game>");
                return true;
            } else if (args.length == 3) {
<<<<<<< Updated upstream
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Can't use with console.");
                    return true;
                }
                if (duel.get(args[0].toLowerCase()) == null) {
                    sender.sendMessage(ChatColor.YELLOW+" Plugin help:");
                    sender.sendMessage(ChatColor.RED+"/duel <invite/accept/reject> <player> <game>");
                    return true;
                }
                Player argplayer = getServer().getPlayer(args[1]);
                if (argplayer == null) {
                    sender.sendMessage("Player " + ChatColor.GRAY + args[1].toLowerCase() + ChatColor.RESET + " could not be found");
                    return true;
                }
                if (games.get(args[2].toLowerCase()) == null) {
                    sender.sendMessage("Game " + ChatColor.GRAY + args[2].toLowerCase() + ChatColor.RESET + " could not be found");
                    return true;
=======

                Player target = Bukkit.getPlayer(args[1]);

                if (args[0].equals("invite")) {

                    if (!(target.isOnline())) {

                        player.sendMessage(Plname + ChatColor.RED + "That player is offline!");

                    } else {

                        // send to player with usage
                        target.sendMessage(Plname + ChatColor.GREEN + " " + playerName + " invites you for duels");
                        target.sendMessage(Plname + ChatColor.GREEN + "Use /duel <accept/reject> " + playerName);

                        // send Clickable Message to player
                        inviteList.put(target, args[2].toLowerCase()); // adds player to duels List

                    }

                } else {

                    player.sendMessage(usage);
                    player.sendMessage(Plname + "Game -> " + game);

>>>>>>> Stashed changes
                }
                // when send invite done

                return true;
            } else {
                sender.sendMessage(ChatColor.YELLOW+" Plugin help:");
                sender.sendMessage(ChatColor.RED+"/duel <invite/accept/reject> <player> <game>");
                return true;
            }

        }
        return true;
    }
}