package net.centralis.battles.board;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import net.centralis.battles.Game;
import net.centralis.battles.enums.GameState;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class ActionBar extends BukkitRunnable {

	private Game game = Game.getInstance();

	@SuppressWarnings("rawtypes")
	public static void sendActionBar(Player player, String message) {
		IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a((String) ("{\"text\": \"" + message + "\"}"));
		PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte) 2);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket((Packet) ppoc);
	}

	@Override
	public void run() {
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			if (player.getScoreboard().getObjectives().stream()
					.noneMatch(objective -> objective.getName().equals("list"))) {
				Objective healthList = player.getScoreboard().registerNewObjective("list", "health");
				healthList.setDisplaySlot(DisplaySlot.PLAYER_LIST);
			}
			if (player.getScoreboard().getObjectives().stream()
					.noneMatch(objective -> objective.getName().equals("name"))) {
				Objective healthName = player.getScoreboard().registerNewObjective("name", "health");
				healthName.setDisplaySlot(DisplaySlot.BELOW_NAME);
				healthName.setDisplayName(ChatColor.DARK_RED + "♥");
			}
			if (GameState.isState(GameState.Lobby)) {
				sendActionBar(player, ChatColor.translateAlternateColorCodes('&',
						"&aWaiting on players &7| &fClick a wool to choose your team"));
			}
			if (GameState.isState(GameState.Ingame)) {
				sendActionBar(player, ChatColor.translateAlternateColorCodes('&',
						"Time: &a" + game.getTimeTask().getFormattedTime()));
			}
			if (GameState.isState(GameState.End)) {
				sendActionBar(player,
						ChatColor.translateAlternateColorCodes('&', "&aThe game is over &7| &fDiscord Link in Chat"));
			}
		}
	}
}