package net.centralis.battles.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.centralis.battles.enums.GameState;

public class PlayerJoinListener implements Listener {

	ItemStack redwool = new ItemStack(Material.WOOL, 1, (short) 14);
	ItemMeta redwoolim = redwool.getItemMeta();

	ItemStack bluewool = new ItemStack(Material.WOOL, 1, (short) 11);
	ItemMeta bluewoolim = bluewool.getItemMeta();

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage("");
		if (GameState.isState(GameState.Lobby)) {
			player.getInventory().clear();
			redwoolim.setDisplayName(ChatColor.WHITE + "Red Team");
			;
			redwool.setItemMeta(redwoolim);
			player.getInventory().addItem(redwool);
			bluewoolim.setDisplayName(ChatColor.WHITE + "Blue Team");
			;
			bluewool.setItemMeta(bluewoolim);
			player.getInventory().addItem(bluewool);
		}
		if (GameState.isState(GameState.Ingame)) {

		}
		if (GameState.isState(GameState.End)) {

		}
	}

}