package id.universenetwork.utilities.Bukkit.Listeners;

import id.universenetwork.utilities.Bukkit.Enums.Features.AntiRedstone;
import id.universenetwork.utilities.Bukkit.Manager.Config;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class AntiRedstoneListener implements Listener {
    @EventHandler
    public void onRedstoneComponentsPlace(BlockPlaceEvent e) {
        Material m = e.getBlock().getType();
        if (!e.getPlayer().hasPermission("unutilities.redstone")) {
            if (Config.ARBoolean(AntiRedstone.ENABLED)) {
                if (m == Material.REDSTONE_WIRE) {
                    if (Config.ARBoolean(AntiRedstone.REDSTONE)) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(Config.ARMessage(AntiRedstone.NOPERMISSION));
                    }
                } else if (m == Material.REPEATER) {
                    if (Config.ARBoolean(AntiRedstone.REPEATER)) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(Config.ARMessage(AntiRedstone.NOPERMISSION));
                    }
                } else if (m == Material.COMPARATOR) {
                    if (Config.ARBoolean(AntiRedstone.COMPARATOR)) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(Config.ARMessage(AntiRedstone.NOPERMISSION));
                    }
                } else if (m == Material.REDSTONE_TORCH) {
                    if (Config.ARBoolean(AntiRedstone.REDSTONE_TORCH)) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(Config.ARMessage(AntiRedstone.NOPERMISSION));
                    }
                } else if (m == Material.REDSTONE_WALL_TORCH) {
                    if (Config.ARBoolean(AntiRedstone.WALL_REDSTONE_TORCH)) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(Config.ARMessage(AntiRedstone.NOPERMISSION));
                    }
                } else if (m == Material.OBSERVER) {
                    if (Config.ARBoolean(AntiRedstone.OBSERVER)) {
                        if (Config.ARBoolean(AntiRedstone.OBSERVER)) {
                            e.setCancelled(true);
                            e.getPlayer().sendMessage(Config.ARMessage(AntiRedstone.NOPERMISSION));
                        }
                    }
                } else if (m == Material.REDSTONE_BLOCK) {
                    if (Config.ARBoolean(AntiRedstone.REDSTONE_BLOCK)) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(Config.ARMessage(AntiRedstone.NOPERMISSION));
                    }
                } else if (m == Material.REDSTONE_LAMP) {
                    if (Config.ARBoolean(AntiRedstone.REDSTONE_LAMP)) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(Config.ARMessage(AntiRedstone.NOPERMISSION));
                    }
                }
            }
        }
    }
}