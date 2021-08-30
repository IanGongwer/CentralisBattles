package net.centralis.battles.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener implements Listener {

	@EventHandler
	public void onPlayerItemDrop(PlayerDropItemEvent event) {
		event.setCancelled(true);
	}

}