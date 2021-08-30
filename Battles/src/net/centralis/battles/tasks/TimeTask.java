package net.centralis.battles.tasks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.centralis.battles.Game;

public class TimeTask {
	private Game game = Game.getInstance();
	private int taskID;
	private int uptimeHours;
	private int uptimeMinutes;
	private int uptimeSeconds;
	private int borderMinutes;

	@SuppressWarnings("deprecation")
	public void runTask() {
		this.taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) game, new BukkitRunnable() {
			public void run() {
				TimeTask.this.uptimeSeconds++;

				if (TimeTask.this.uptimeSeconds == 60) {
					TimeTask.this.uptimeSeconds = 0;
					TimeTask.this.uptimeMinutes = TimeTask.this.uptimeMinutes + 1;
					TimeTask.this.borderMinutes = TimeTask.this.borderMinutes + 1;
					if (TimeTask.this.uptimeMinutes == 60) {
						TimeTask.this.uptimeMinutes = 0;
						TimeTask.this.uptimeHours = TimeTask.this.uptimeHours + 1;
					}
				}
			}
		}, 0L, 20L);
	}

	public void cancelTask() {
		Bukkit.getScheduler().cancelTask(this.taskID);
	}

	public String getFormattedTime() {
		String formattedTime = "";

		if (this.uptimeHours >= 1) {
			if (this.uptimeHours < 10)
				formattedTime = formattedTime + "0";
			formattedTime = formattedTime + this.uptimeHours + ":";
		}

		if (this.uptimeMinutes < 10)
			formattedTime = formattedTime + "0";
		formattedTime = formattedTime + this.uptimeMinutes + ":";

		if (this.uptimeSeconds < 10)
			formattedTime = formattedTime + "0";
		formattedTime = formattedTime + this.uptimeSeconds;

		return formattedTime;
	}
}