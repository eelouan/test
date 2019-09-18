package fr.xubule.minijeux.task;

import org.bukkit.scheduler.BukkitRunnable;

import fr.xubule.minijeux.Main;

public class Time extends BukkitRunnable {

	private int timer = 10;
	private Main gMain;

	public Time(Main gmain) {
		this.gMain = gmain;
	}

	@Override
	public void run() {

		if (timer == 0) {
			cancel();
		}

		timer--;
	}

}
