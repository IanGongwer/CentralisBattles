package net.centralis.battles.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {

	public static Map<String, String> lastReceived = new HashMap<String, String>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("message") && sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0 || args.length == 1) {
				player.sendMessage(ChatColor.RED + "Usage: /msg (player) (message)");
			}
			if (args.length > 1) {
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if (target.isOnline()) {
					String sm = "";
					for (int i = 1; i < args.length; i++) {
						String arg = (args[i] + " ");
						sm = (sm + arg);
					}
					lastReceived.remove(target.getDisplayName(), player.getDisplayName());
					lastReceived.put(player.getDisplayName(), target.getDisplayName());
					target.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&7" + "(From " + player.getDisplayName() + "): " + sm));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&7" + "(To " + target.getDisplayName() + "): " + sm));
				}
				if (!target.isOnline()) {
					lastReceived.remove(target.getDisplayName(), player.getDisplayName());
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThe player is offline."));
				}
			}
		}
		return false;
	}

}