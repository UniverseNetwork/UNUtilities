package id.universenetwork.utilities.Bukkit.Features.BlockBlocker;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events;
import id.universenetwork.utilities.Bukkit.Templates.Feature;
import id.universenetwork.utilities.Bukkit.UNUtilities;
import id.universenetwork.utilities.Bukkit.Utils.Text;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.List;

public class Main extends Feature implements Listener {
    @Override
    public void Load() {
        Events.registerListeners(this);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        List<String> f = new ArrayList<>();
        for (String s : UNUtilities.cfg.getStringList(configPath + "blocks")) f.add(s.toUpperCase());
        if (UNUtilities.cfg.getBoolean(configPath + "enabled")
                && !e.getPlayer().hasPermission("unutilities.redstone")
                && f.contains(e.getBlock().getType().toString())) {
            e.getPlayer().sendMessage(Text.translateColor(UNUtilities.cfg.getString(configPath + "msg")));
            e.setCancelled(true);
        }
    }
}