package com.yourplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PotatoTransfer extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("PotatoTransfer enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("PotatoTransfer disabled!");
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            Player victim = (Player) event.getEntity();

            if (attacker.getInventory().contains(Material.POTATO)) {
                attacker.getInventory().removeItem(new ItemStack(Material.POTATO, 1));
                victim.getInventory().addItem(new ItemStack(Material.POTATO, 1));
                attacker.sendMessage("You have transferred a potato to " + victim.getName());
                victim.sendMessage("You have received a potato from " + attacker.getName());
            }
        }
    }
}
