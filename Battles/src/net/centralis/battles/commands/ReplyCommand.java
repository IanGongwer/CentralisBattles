package net.centralis.battles.commands;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplyCommand implements CommandExecutor
/*    */ {

	/*    */ public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		/* 21 */ if (cmd.getName().equalsIgnoreCase("reply") &&
		/* 22 */ sender instanceof Player) {
			/* 20 */ Player player = (Player) sender;
			if (args.length == 0) {
				player.sendMessage(ChatColor.RED + "Usage: /r (message)");
			}
			if (args.length > 0) {
				String sm = "";
				for (int i = 0; i < args.length; i++) {
					String arg = (args[i] + " ");
					sm = (sm + arg);
				}
				if (!MessageCommand.lastReceived.containsValue(player.getDisplayName())) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + "No one has messaged you."));
				}
				if (MessageCommand.lastReceived.containsValue(player.getDisplayName())) {
					String targettext = getKeyByValue(MessageCommand.lastReceived, player.getDisplayName());
					Player target = Bukkit.getServer().getPlayer(targettext);
					target.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&7" + "(From " + player.getDisplayName() + "): " + sm));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&7" + "(To " + target.getDisplayName() + "): " + sm));
					MessageCommand.lastReceived.remove(target.getDisplayName(), player.getDisplayName());
					MessageCommand.lastReceived.put(player.getDisplayName(), target.getDisplayName());
				}
			}
		}
		return false;
	}

	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
		for (Entry<T, E> entry : map.entrySet()) {
			if (Objects.equals(value, entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

}