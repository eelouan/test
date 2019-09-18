package fr.xubule.minijeux.task;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.xubule.minijeux.Main;
import fr.xubule.minijeux.Stat;

public class AutoStart extends BukkitRunnable {

	private int timer = 10;
	private Main gMain;

	public AutoStart(Main gmain) {
		this.gMain = gmain;
	}

	@Override
	public void run() {

		for (Player p : gMain.getPlayer()) {
			p.setLevel(timer);
		}

		if (timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1)
			Bukkit.broadcastMessage("§4Lancement du jeux dans : " + ChatColor.YELLOW + timer);

		if (timer == 0) {
			Bukkit.broadcastMessage("Lancement du jeu");
			gMain.setStat(Stat.PLAYING);
			cancel();
		}

		timer--;
	}

}
