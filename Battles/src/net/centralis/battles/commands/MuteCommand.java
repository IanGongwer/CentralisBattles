package net.centralis.battles.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.centralis.battles.Game;
import net.md_5.bungee.api.ChatColor;

public class MuteCommand implements CommandExecutor {
	private Game game = Game.getInstance();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("mute") && sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				player.sendMessage(ChatColor.RED + "Usage: /mute (player)");
			}
			if (args.length == 1) {
				if (Bukkit.getServer().getPlayer(args[0]).isOnline()) {
					if (this.game.getPlayersMuted().contains(Bukkit.getServer().getPlayer(args[0]).getUniqueId())) {
						this.game.getPlayersMuted().remove(Bukkit.getServer().getPlayer(args[0]).getUniqueId());
						player.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&c" + args[0] + " has been unmuted."));
						Bukkit.getServer().getPlayer(args[0])
								.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have been unmuted."));
					} else {
						this.game.getPlayersMuted().add(Bukkit.getServer().getPlayer(args[0]).getUniqueId());
						player.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&c" + args[0] + " has been muted."));
						Bukkit.getServer().getPlayer(args[0])
								.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have been muted."));
					}
				}
				if (!Bukkit.getServer().getPlayer(args[0]).isOnline()) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThe player is offline."));
				}
			}
		}

		return false;
	}
}