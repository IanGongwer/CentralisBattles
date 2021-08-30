package net.centralis.battles.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.centralis.battles.enums.GameState;

public class InventoryItemDragListener implements Listener {

	@EventHandler
	public void onItemDrag(InventoryClickEvent event) {
		if (GameState.isState(GameState.Lobby)) {
			event.setCancelled(true);
		}
	}

}