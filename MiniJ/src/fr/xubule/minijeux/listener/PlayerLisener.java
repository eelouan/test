package fr.xubule.minijeux.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.xubule.minijeux.Main;
import fr.xubule.minijeux.Stat;
import fr.xubule.minijeux.task.AutoStart;
import fr.xubule.minijeux.task.Time;

public class PlayerLisener implements Listener {

	private Main Gmain;
	private int nbPlayer = 2;

	public PlayerLisener(Main main) {
		this.Gmain = main;
	}

	@EventHandler

	public void onJoin(PlayerJoinEvent e) {

		Player p = e.getPlayer();

		Location spawnPearl = new Location(Bukkit.getWorld("world"), 123.544, 77, 230);
		p.teleport(spawnPearl);

		ItemStack pearl = new ItemStack(Material.ENDER_PEARL, 1);
		ItemMeta Mpearl = pearl.getItemMeta();
		Mpearl.setDisplayName("§7nom custome");
		Mpearl.addEnchant(Enchantment.KNOCKBACK, 200, true);
		pearl.setItemMeta(Mpearl);

		p.setFoodLevel(20);
		p.setHealth(20);
		p.getInventory().clear();

		if (!Gmain.isStat(Stat.WAITTING)) {
			p.setGameMode(GameMode.SPECTATOR);
			p.sendMessage("Le jeux est déjà lancer");
			e.setJoinMessage(null);
			return;
		}

		if (!Gmain.getPlayer().contains(p)) {
			Gmain.getPlayer().add(p);
			p.setGameMode(GameMode.ADVENTURE);
			e.setJoinMessage("§7[§ePearlWars§7] " + p.getName() + " has join the game !");
		}

		if (Gmain.isStat(Stat.WAITTING) && Gmain.getPlayer().size() == nbPlayer) {
			AutoStart start = new AutoStart(Gmain);
			start.runTaskTimer(Gmain, 0, 20);
			Gmain.setStat(Stat.STARTING);
		}
	}

	@EventHandler

	public void onInteractPearl(PlayerInteractEvent e) {

		Player p = e.getPlayer();
		Action a = e.getAction();
		ItemStack it = e.getItem();
		Time time = new Time(Gmain);
//		ItemStack pearl = new ItemStack(Material.ENDER_PEARL, 1);
//		
//		ItemMeta Mpearl = pearl.getItemMeta();
//		Mpearl.setDisplayName("§7nom custome");
//		Mpearl.addEnchant(Enchantment.KNOCKBACK, 200, true);
//		pearl.setItemMeta(Mpearl);

		if (it.hasItemMeta() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§7nom custome")) {
			if (a != Action.LEFT_CLICK_AIR || a != Action.LEFT_CLICK_BLOCK) {
//			time.runTaskTimer(Gmain, 0, 20);
				p.setGameMode(GameMode.SPECTATOR);
				p.sendMessage("a");
			}
		}

//		if(time.getTime() == 0)
//			p.getInventory().setItem(0, pearl);

	}

	@EventHandler

	public void onQuit(PlayerQuitEvent e) {

	}
}
