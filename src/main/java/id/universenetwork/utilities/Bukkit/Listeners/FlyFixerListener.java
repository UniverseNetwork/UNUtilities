package id.universenetwork.utilities.Bukkit.Listeners;

import id.universenetwork.utilities.Bukkit.Manager.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.List;

import static id.universenetwork.utilities.Bukkit.Manager.Data.get;
import static id.universenetwork.utilities.Bukkit.Manager.Data.set;

public class FlyFixerListener implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandSendEvent e) {
        List<String> fp = get().getStringList("FlyingPlayer");
        if (!fp.contains(e.getPlayer().getName()) && e.getPlayer().getAllowFlight()) {
            fp.add(e.getPlayer().getName());
            set("FlyingPlayer", fp);
        }
        if (fp.contains(e.getPlayer().getName()) && !e.getPlayer().getAllowFlight()) {
            fp.remove(e.getPlayer().getName());
            set("FlyingPlayer", fp);
        }
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        if (get().getStringList("FlyingPlayer").contains(e.getPlayer().getName()) && Config.FFEnabled()) {
            e.getPlayer().setAllowFlight(true);
            e.getPlayer().setFlying(true);
        }
    }
}