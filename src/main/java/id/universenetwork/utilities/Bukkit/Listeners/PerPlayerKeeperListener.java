package id.universenetwork.utilities.Bukkit.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Arrays;

import static id.universenetwork.utilities.Bukkit.Manager.Data.get;

public class PerPlayerKeeperListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        String name = e.getEntity().getName();
        boolean inv = get().getStringList("keepINV").contains(name);
        boolean xp = get().getStringList("keepXP").contains(name);
        if (!inv) {
            Arrays.stream(e.getEntity().getInventory().getContents()).map(i -> e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), i));
            // e.getDrops().stream().map(i -> e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), i));
            e.setKeepInventory(false);
        } else e.setKeepInventory(true);
        if (!xp) {
            e.setDroppedExp(e.getDroppedExp());
            e.setKeepLevel(false);
        } else e.setKeepLevel(true);
    }
}
