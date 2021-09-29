package id.universenetwork.utilities.Bukkit.Listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;

public class FlyFixerListener implements Listener {
    public void onPortalCreate(PortalCreateEvent e) {
        e.getBlocks().stream().map(b -> {
            System.out.println(b.getType().name());
            return null;
        });
    }
}