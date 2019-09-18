package fr.xubule.minijeux;

import java.util.List;
import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.xubule.minijeux.listener.PlayerLisener;

public class Main extends JavaPlugin {

	private List<Player> players = new ArrayList<>();
	private Stat stat;

	@Override
	public void onEnable() {
		setStat(Stat.WAITTING);

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerLisener(this), this);
	}

	public void setStat(Stat stat) {
		this.stat = stat;
	}

	public boolean isStat(Stat stat) {
		return this.stat == stat;
	}

	public List<Player> getPlayer() {
		return players;
	}
}
