package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;

import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListener;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class ProjectileListener implements org.bukkit.event.Listener {
    public ProjectileListener() {
        registerListener(this);
    }

    @EventHandler
    public void onTippedArrowHit(ProjectileHitEvent e) {
        if (e.getEntity() instanceof Arrow && e.getEntity().hasMetadata("hotbarpets_projectile")) {
            e.getEntity().removeMetadata("hotbarpets_projectile", plugin);
            e.getEntity().remove();
        }
    }
}