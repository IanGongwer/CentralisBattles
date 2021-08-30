package net.centralis.battles;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.centralis.battles.enums.GameState;
import net.centralis.battles.listeners.BlockBreakAndPlaceListener;
import net.centralis.battles.listeners.InventoryItemDragListener;
import net.centralis.battles.listeners.PlayerChatListener;
import net.centralis.battles.listeners.PlayerDropItemListener;
import net.centralis.battles.listeners.PlayerInteractListener;
import net.centralis.battles.listeners.PlayerJoinListener;
import net.centralis.battles.tasks.TimeTask;

public class Game extends JavaPlugin {
	private static Game instance;

	private boolean whitelistedbool;
	private boolean chatMuted;

	private TimeTask timeTask;

	private ArrayList<UUID> players;
	private ArrayList<UUID> spectators;
	private ArrayList<UUID> whitelisted;
	private ArrayList<UUID> mutedplayers;
	private ArrayList<Player> redteam;
	private ArrayList<Player> blueteam;

	@SuppressWarnings("deprecation")
	public void onEnable() {
		GameState.setState(GameState.Lobby);
		instance = this;
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new net.centralis.battles.board.ActionBar(), 0L,
				20L);
		Bukkit.getPluginManager().registerEvents((Listener) new BlockBreakAndPlaceListener(), (Plugin) this);
		Bukkit.getPluginManager().registerEvents((Listener) new PlayerChatListener(), (Plugin) this);
		Bukkit.getPluginManager().registerEvents((Listener) new PlayerJoinListener(), (Plugin) this);
		Bukkit.getPluginManager().registerEvents((Listener) new PlayerInteractListener(), (Plugin) this);
		Bukkit.getPluginManager().registerEvents((Listener) new PlayerDropItemListener(), (Plugin) this);
		Bukkit.getPluginManager().registerEvents((Listener) new InventoryItemDragListener(), (Plugin) this);
		getCommand("whitelist").setExecutor((CommandExecutor) new net.centralis.battles.commands.WhitelistCommand());
		getCommand("broadcast").setExecutor((CommandExecutor) new net.centralis.battles.commands.BroadcastCommand());
		getCommand("list").setExecutor((CommandExecutor) new net.centralis.battles.commands.ListCommand());
		getCommand("message").setExecutor((CommandExecutor) new net.centralis.battles.commands.MessageCommand());
		getCommand("mutechat").setExecutor((CommandExecutor) new net.centralis.battles.commands.MuteChatCommand());
		getCommand("mute").setExecutor((CommandExecutor) new net.centralis.battles.commands.MuteCommand());
		getCommand("ping").setExecutor((CommandExecutor) new net.centralis.battles.commands.PingCommand());
		getCommand("reply").setExecutor((CommandExecutor) new net.centralis.battles.commands.ReplyCommand());
		getCommand("staffchat").setExecutor((CommandExecutor) new net.centralis.battles.commands.StaffChatCommand());
		this.timeTask = new TimeTask();
		this.players = new ArrayList<>();
		this.spectators = new ArrayList<>();
		this.whitelisted = new ArrayList<>();
		this.mutedplayers = new ArrayList<>();
		this.redteam = new ArrayList<>();
		this.blueteam = new ArrayList<>();
		this.chatMuted = false;
	}

	public void onDisable() {
		instance = null;
		this.players.clear();
		this.spectators.clear();
		this.whitelisted.clear();
		this.mutedplayers.clear();
		this.redteam.clear();
		this.blueteam.clear();
	}

	public static Game getInstance() {
		return instance;
	}

	public TimeTask getTimeTask() {
		return this.timeTask;
	}

	public ArrayList<UUID> getSpectators() {
		return this.spectators;
	}

	public ArrayList<UUID> getPlayers() {
		return this.players;
	}

	public boolean whitelisted() {
		return whitelistedbool;
	}

	public void setWhitelisted(boolean bool) {
		whitelistedbool = bool;
	}

	public ArrayList<UUID> getWhitelisted() {
		return this.whitelisted;
	}

	public void setChatMuted(boolean chatMuted) {
		this.chatMuted = chatMuted;
	}

	public boolean isChatMuted() {
		return this.chatMuted;
	}

	public ArrayList<UUID> getPlayersMuted() {
		return this.mutedplayers;
	}

	public ArrayList<Player> getRedTeam() {
		return this.redteam;
	}

	public ArrayList<Player> getBlueTeam() {
		return this.blueteam;
	}

	public String getTeam(Player player) {
		if (this.redteam.contains(player)) {
			return "Red";
		}
		if (this.blueteam.contains(player)) {
			return "Blue";
		}
		if (!this.redteam.contains(player) && !this.blueteam.contains(player)) {
			return "None";
		}
		if (this.spectators.contains(player.getUniqueId())) {
			return "Spec";
		}
		return "";
	}

}