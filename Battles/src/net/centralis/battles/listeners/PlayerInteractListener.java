package net.centralis.battles.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.centralis.battles.Game;

public class PlayerInteractListener implements Listener {

	private Game game = Game.getInstance();

	ItemStack redwool = new ItemStack(Material.WOOL, 1, (short) 14);

	ItemStack bluewool = new ItemStack(Material.WOOL, 1, (short) 11);

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getMaterial().equals(Material.WOOL)) {
			if (event.getItem().getDurability() == 14) {
				if (game.getRedTeam().contains(player)) {
					player.sendMessage(ChatColor.RED + "You are already on the red team.");
				}
				if (!game.getRedTeam().contains(player)) {
					player.sendMessage(ChatColor.GREEN + "You have joined the red team.");
					for (Player team : game.getRedTeam()) {
						team.sendMessage(ChatColor.GREEN + player.getDisplayName() + " has joined your team.");
					}
					game.getRedTeam().add(player);
					game.getBlueTeam().remove(player);
				}
			}
			if (event.getItem().getDurability() == 11) {
				if (game.getBlueTeam().contains(player)) {
					player.sendMessage(ChatColor.RED + "You are already on the blue team.");
				}
				if (!game.getBlueTeam().contains(player)) {
					player.sendMessage(ChatColor.GREEN + "You have joined the blue team.");
					for (Player team : game.getBlueTeam()) {
						team.sendMessage(ChatColor.GREEN + player.getDisplayName() + " has joined your team.");
					}
					game.getBlueTeam().add(player);
					game.getRedTeam().remove(player);
				}
			}
		}
	}

}