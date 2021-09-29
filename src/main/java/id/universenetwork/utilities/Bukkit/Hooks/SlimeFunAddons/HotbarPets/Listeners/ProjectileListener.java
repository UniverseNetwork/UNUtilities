package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static org.bukkit.Bukkit.getPluginManager;

public class ProjectileListener implements Listener {
    public ProjectileListener() {
        getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onTippedArrowHit(ProjectileHitEvent e) {
        if (e.getEntity() instanceof Arrow && e.getEntity().hasMetadata("hotbarpets_projectile")) {
            e.getEntity().removeMetadata("hotbarpets_projectile", plugin);
            e.getEntity().remove();
        }
    }
}