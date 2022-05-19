package id.universenetwork.utilities.Bukkit.Features.BlockBlocker;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events;
import id.universenetwork.utilities.Bukkit.Templates.Feature;
import id.universenetwork.utilities.Bukkit.UNUtilities;
import id.universenetwork.utilities.Bukkit.Utils.Text;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class Main extends Feature implements Listener {
    @Override
    public void Load() {
        Events.registerListeners(this);
    }

    @org.bukkit.event.EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        java.util.List<String> f = new java.util.ArrayList<>();
        for (String s : UNUtilities.cfg.getStringList(configPath + "blocks")) f.add(s.toUpperCase());
        if (UNUtilities.cfg.getBoolean(configPath + "enabled")
                && !e.getPlayer().hasPermission("unutilities.redstone")
                && f.contains(e.getBlock().getType().toString())) {
            e.getPlayer().sendMessage(Text.translateColor(UNUtilities.cfg.getString(configPath + "msg")));
            e.setCancelled(true);
        }
    }
}