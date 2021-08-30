package net.centralis.battles.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.EntityPlayer;

public class PingCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("ping")) {
			if (args.length == 0) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYour Ping: " + getPing(player)));
			}
			if (args.length == 1) {
				Player target = Bukkit.getServer().getPlayer(args[0]);
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&a" + target.getDisplayName() + "'s Ping: " + getPing(target)));
			}
			if (args.length > 1) {
				player.sendMessage(ChatColor.RED + "Usage: /ping (player)");
			}
		}
		return false;
	}

	public static int getPing(Player p) {
		CraftPlayer pingcraft = (CraftPlayer) p;
		EntityPlayer pingentity = pingcraft.getHandle();
		return pingentity.ping;
	}
}