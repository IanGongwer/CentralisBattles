package net.centralis.battles.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCommand implements CommandExecutor {

	/*    */ public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		/* 22 */ if (cmd.getName().equalsIgnoreCase("staffchat") &&
		/* 22 */ sender instanceof Player) {
			/* 20 */ Player player = (Player) sender;
			if (args.length == 0) {
				player.sendMessage(ChatColor.RED + "Usage: /sc (message)");
			}
			/* 24 */ if (args.length > 0) {
				String sm = "";
				for (int i = 0; i < args.length; i++) {
					String arg = (args[i] + " ");
					sm = (sm + arg);
				}
				for (Player player2 : Bukkit.getOnlinePlayers()) {
					if (player2.hasPermission("uhc.host")) {
						player2.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&7[&aStaff Chat&7]&a " + player.getDisplayName() + ": &7" + sm));
					}
				}
				/*    */ }
			/*    */ }
		return false;
	}
}