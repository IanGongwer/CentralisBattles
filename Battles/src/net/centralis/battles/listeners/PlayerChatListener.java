package net.centralis.battles.listeners;

import java.util.Arrays;
import java.util.Collection;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.centralis.battles.Game;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public class PlayerChatListener implements Listener {
	private Game game = Game.getInstance();

	LuckPerms api = LuckPermsProvider.get();

	Collection<String> groupCollection = Arrays.asList("OWNER", "SRMOD", "MOD", "TRIALMOD", "TWITCH", "YOUTUBE");

	@EventHandler(priority = EventPriority.MONITOR)
	public void handleASyncPlayerChatEvent(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if (event.getMessage().contains("%")) {
			player.sendMessage(ChatColor.RED + "You can not use % signs in chat.");
			event.setCancelled(true);
		}
		if (!event.getMessage().contains("%")) {
			if (this.game.getPlayersMuted().contains(player.getUniqueId())) {
				event.setCancelled(true);
				player.sendMessage(ChatColor.RED + "You are currently muted.");
			}
			if (!this.game.getPlayersMuted().contains(player.getUniqueId())) {
				event.setCancelled(false);

				if (this.game.isChatMuted() && !player.hasPermission("battles.staff")) {
					event.setCancelled(true);
					player.sendMessage(ChatColor.RED + "Global Chat is currently disabled.");
				}

				if (this.game.isChatMuted() && player.hasPermission("battles.staff")) {
					event.setCancelled(false);
				}

				if (!this.game.isChatMuted()) {
					event.setCancelled(false);
				}
				if (game.getTeam(player).equalsIgnoreCase("red")) {
					event.setFormat(ChatColor.GRAY + "[" + ChatColor.RED + game.getTeam(player) + ChatColor.GRAY + "] "
							+ getPlayerGroup(player, groupCollection) + ChatColor.WHITE
							+ event.getPlayer().getDisplayName() + ": " + event.getMessage());
				}
				if (game.getTeam(player).equalsIgnoreCase("blue")) {
					event.setFormat(ChatColor.GRAY + "[" + ChatColor.BLUE + game.getTeam(player) + ChatColor.GRAY + "] "
							+ getPlayerGroup(player, groupCollection) + ChatColor.WHITE
							+ event.getPlayer().getDisplayName() + ": " + event.getMessage());
				}
				if (game.getTeam(player).equalsIgnoreCase("spec")) {
					event.setFormat(ChatColor.GRAY + "[" + ChatColor.WHITE + game.getTeam(player) + ChatColor.GRAY
							+ "] " + getPlayerGroup(player, groupCollection) + ChatColor.WHITE
							+ event.getPlayer().getDisplayName() + ": " + event.getMessage());
				}
				if (game.getTeam(player).equalsIgnoreCase("none")) {
					event.setFormat(getPlayerGroup(player, groupCollection) + ChatColor.WHITE
							+ event.getPlayer().getDisplayName() + ": " + event.getMessage());
				}
			}
		}
	}

	public static boolean isPlayerInGroup(Player player, String group) {
		return player.hasPermission("group." + group);
	}

	public String getPlayerGroup(Player player, Collection<String> possibleGroups) {
		for (String group : possibleGroups) {
			if (player.hasPermission(group.toUpperCase())) {
				if (group.equalsIgnoreCase("owner")) {
					return ChatColor.RED + "OWNER ";
				}
				if (group.equalsIgnoreCase("srmod")) {
					return ChatColor.DARK_BLUE + "SRMOD ";
				}
				if (group.equalsIgnoreCase("mod")) {
					return ChatColor.BLUE + "MOD ";
				}
				if (group.equalsIgnoreCase("trialmod")) {
					return ChatColor.AQUA + "TRIALMOD ";
				}
				if (group.equalsIgnoreCase("twitch")) {
					return ChatColor.LIGHT_PURPLE + "TWITCH ";
				}
				if (group.equalsIgnoreCase("youtube")) {
					return ChatColor.YELLOW + "YOUTUBE ";
				}
			}
		}
		return "";
	}
}
