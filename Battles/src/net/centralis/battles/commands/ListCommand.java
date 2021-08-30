package net.centralis.battles.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.centralis.battles.Game;
import net.md_5.bungee.api.ChatColor;

public class ListCommand implements CommandExecutor {
	private Game game = Game.getInstance();

	public boolean onCommand(CommandSender player, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("list")) {
			List<String> moderators = new ArrayList<>();

			for (Player allSpectators : Bukkit.getOnlinePlayers()) {
				if (allSpectators.hasPermission("battles.staff")) {
					moderators.add(allSpectators.getName());
				}
			}

			String[] stringArray = moderators.<String>toArray(new String[moderators.size()]);

			player.sendMessage("§8§m---------------------------");
			player.sendMessage("Online Players: " + Bukkit.getOnlinePlayers().size());
			player.sendMessage("Alive Players: " + this.game.getPlayers().size());
			player.sendMessage("");
			player.sendMessage("Spectators: " + this.game.getSpectators().size());
			player.sendMessage("Moderators: " + ChatColor.GREEN
					+ Arrays.toString((Object[]) stringArray).replace("[", "").replace("]", ""));
			player.sendMessage("§8§m---------------------------");
		}
		return false;
	}
}