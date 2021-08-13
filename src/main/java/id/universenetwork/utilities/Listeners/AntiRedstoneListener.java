package id.universenetwork.utilities.Listeners;

import id.universenetwork.utilities.Enums.Features.AntiRedstone;
import id.universenetwork.utilities.Manager.Config;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class AntiRedstoneListener implements Listener {
    @EventHandler
    public void onRedstoneComponentsPlace(BlockPlaceEvent e) {
        Material m = e.getBlock().getType();
        if (!e.getPlayer().hasPermission("unutilities.redstone")) {
            if (Config.ARSettings(AntiRedstone.ENABLED)) {
                if (m == Material.REDSTONE_WIRE) {
                    e.setCancelled(Config.ARSettings(AntiRedstone.REDSTONE));
                } else if (m == Material.REPEATER) {
                    e.setCancelled(Config.ARSettings(AntiRedstone.REPEATER));
                } else if (m == Material.COMPARATOR) {
                    e.setCancelled(Config.ARSettings(AntiRedstone.COMPARATOR));
                } else if (m == Material.REDSTONE_TORCH) {
                    e.setCancelled(Config.ARSettings(AntiRedstone.REDSTONE_TORCH));
                } else if (m == Material.REDSTONE_WALL_TORCH) {
                    e.setCancelled(Config.ARSettings(AntiRedstone.WALL_REDSTONE_TORCH));
                } else if (m == Material.OBSERVER) {
                    e.setCancelled(Config.ARSettings(AntiRedstone.OBSERVER));
                } else if (m == Material.REDSTONE_BLOCK) {
                    e.setCancelled(Config.ARSettings(AntiRedstone.REDSTONE_BLOCK));
                } else if (m == Material.REDSTONE_LAMP) {
                    e.setCancelled(Config.ARSettings(AntiRedstone.REDSTONE_LAMP));
                }
            }
            if (e.isCancelled()) e.getPlayer().sendMessage(Config.ARMessage(AntiRedstone.NOPERMISSION));
        }
    }
}
