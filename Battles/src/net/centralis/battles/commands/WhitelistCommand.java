package net.centralis.battles.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.centralis.battles.Game;

public class WhitelistCommand implements CommandExecutor {
	private Game game = Game.getInstance();

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender player, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("whitelist")) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("on")) {
					if (!this.game.whitelisted()) {
						this.game.setWhitelisted(true);
						Bukkit.broadcastMessage(ChatColor.GREEN + "The game has been whitelisted.");
					} else {
						player.sendMessage(ChatColor.RED + "The game is already whitelisted.");
					}
				} else if (args[0].equalsIgnoreCase("off")) {
					if (this.game.whitelisted()) {
						this.game.setWhitelisted(false);
						Bukkit.broadcastMessage(ChatColor.GREEN + "The game has been un-whitelisted.");
					} else {
						player.sendMessage(ChatColor.RED + "The server is not whitelisted.");
					}
				}
			} else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("add")) {
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
					if (!this.game.getWhitelisted().contains(target.getUniqueId())) {
						this.game.getWhitelisted().add(target.getUniqueId());
						player.sendMessage(ChatColor.GREEN + target.getName() + " has been whitelisted.");
					} else {
						player.sendMessage(ChatColor.RED + target.getName() + " is already whitelisted.");
					}
				} else if (args[0].equalsIgnoreCase("remove")) {
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
					if (this.game.getWhitelisted().contains(target.getUniqueId())) {
						this.game.getWhitelisted().remove(target.getUniqueId());
						player.sendMessage(ChatColor.GREEN + target.getName() + " has been un-whitelisted.");
					} else {
						player.sendMessage(ChatColor.RED + target.getName() + " is not whitelisted.");
					}
				}
			} else {
				player.sendMessage(ChatColor.RED + "Usage: /whitelist (on/off)");
				player.sendMessage(ChatColor.RED + "Usage: /whitelist (add/remove) (player)");
			}
		}
		return false;
	}
}
