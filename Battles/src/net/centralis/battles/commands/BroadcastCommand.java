package net.centralis.battles.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class BroadcastCommand implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("broadcast") && sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("uhc.host")) {
				if (args.length == 0) {
					player.sendMessage(ChatColor.RED + "Usage: /broadcast (message)");
				}
				if (args.length > 0) {
					String message = "";
					for (String part : args) {
						if (message != "")
							message += " ";
						message += part;
					}
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&a" + message + "."));
				}
			}
		}
		return false;
	}
}