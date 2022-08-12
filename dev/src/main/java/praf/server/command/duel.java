package praf.server.command;

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
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;


import static praf.server.praf.Plname;

public class duel implements CommandExecutor {

    // This is raw code for bungee cord plugin

    /*
    * args[0] = duels options -> { invite, accept, reject }
    * args[1] = player target
    * args[2] = games -> { NetheriteStack, DodgeBall, Paintball, ClassicIron, ClassicDiamond, OP, Crystal }
    * args[3].. error with arguments
    * */

    public String usage = Plname + ChatColor.YELLOW + "/duel <invite/accept/reject> <player>";
    public HashMap<Player, Player> inviteList;
    public static String game = "NetheriteStack, DodgeBall, Paintball, ClassicIron, ClassicDiamond, OP, Crystal";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) { // player

            Player player = (Player) sender;
            String playerName = player.getName();


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
                inviteList = new HashMap<>();

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

                        player.sendMessage(Plname + targetName + " has accept the duel"); // accept
                        // send player to game

                    }
                } else if (args[0].equals("reject")) { // reject

                    if (!(inviteList.containsKey(target))) {

                        player.sendMessage(Plname + targetName + " doesn't invite you to duel!"); // return

                    } else {

                        player.sendMessage(Plname + targetName + " has reject the duels"); // reject

                    }
                }
            } else if (args.length == 3) {

                Player target = Bukkit.getPlayer(args[1]);
                String targetName = target.getName();

                if (args[0].equals("invite")) {

                    if (!(target.isOnline())) {

                        player.sendMessage(Plname + ChatColor.RED + "That player is offline!");

                    } else {

                        // send to player with usage
                        target.sendMessage(Plname + ChatColor.GREEN + " " + playerName + " invites you for duels");
                        target.sendMessage(Plname + ChatColor.GREEN + "Use /duel <accept/reject> " + playerName);

                        // Clickable Chat Message

                        //accept
                        TextComponent accept = new TextComponent(ChatColor.RED + "[Accept]");
                        accept.setBold(true);
                        accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duel accept " + player.getName()));
                        accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                new ComponentBuilder(ChatColor.GRAY + "Accept " + playerName + " Duels").italic(true).create()));

                        //reject
                        TextComponent reject = new TextComponent(ChatColor.RED + "[REJECT]");
                        reject.setBold(true);
                        reject.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duel reject " + player.getName()));
                        reject.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                new ComponentBuilder(ChatColor.GRAY + "REJECT " + playerName + " Duels").italic(true).create()));

                        // send Clickable Message to player
                        target.sendMessage(ChatColor.BLUE + "or" + accept + " " + reject);
                        inviteList.put(player, target); // adds player to duels List

                    }

                } else {

                    player.sendMessage(usage);
                    player.sendMessage(Plname + "Game -> " + game);

                }

            } else {
                player.sendMessage(Plname + "Invalid Arguments");
            }

        } else { // console

        }

        return true;
    }

}
