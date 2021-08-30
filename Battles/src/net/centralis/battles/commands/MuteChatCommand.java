package net.centralis.battles.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.centralis.battles.Game;

public class MuteChatCommand implements CommandExecutor {
	private Game game = Game.getInstance();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("mutechat") && sender instanceof org.bukkit.entity.Player) {
			if (this.game.isChatMuted()) {
				this.game.setChatMuted(false);
				Bukkit.broadcastMessage(ChatColor.GREEN + "Global Chat has been enabled.");
			} else {
				this.game.setChatMuted(true);
				Bukkit.broadcastMessage(ChatColor.RED + "Global Chat has been disabled.");
			}
		}

		return false;
	}
}