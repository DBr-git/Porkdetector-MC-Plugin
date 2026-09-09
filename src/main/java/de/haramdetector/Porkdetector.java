package de.porkdetector;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Porkdetector extends JavaPlugin implements Listener {
    public void onEnable() {
        System.out.println("Haram Detection enabled");
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerEat(PlayerItemConsumeEvent event) {
        Player p;
        Location loc;
        if (event.getItem().getType() == Material.COOKED_PORKCHOP || event.getItem().getType() == Material.PORKCHOP) {
            p = event.getPlayer();
            loc = p.getLocation();

            // Check if the world is not null
            if (loc.getWorld() != null) {
                loc.getWorld().strikeLightning(loc);
                p.getWorld().setStorm(true);
                p.sendMessage(ChatColor.RED + "Pork is Haram!");
            } else {
                System.out.println("Cannot Spawn Lightning, world is null");;
            }
        }
    }
}
