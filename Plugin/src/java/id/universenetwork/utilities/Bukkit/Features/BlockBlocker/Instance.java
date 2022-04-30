package id.universenetwork.utilities.Bukkit.Features.BlockBlocker;

import id.universenetwork.utilities.Bukkit.UNUtilities;

import java.util.ArrayList;
import java.util.List;

public class Instance extends id.universenetwork.utilities.Bukkit.ClassInstance.Feature implements org.bukkit.event.Listener {
    @Override
    public void Load() {
        id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListeners(this);
    }

    @org.bukkit.event.EventHandler
    public void onBlockPlace(org.bukkit.event.block.BlockPlaceEvent e) {
        List<String> f = new ArrayList<>();
        for (String s : UNUtilities.cfg.getStringList(configPath + "blocks")) f.add(s.toUpperCase());
        if (UNUtilities.cfg.getBoolean(configPath + "enabled") && !e.getPlayer().hasPermission("unutilities.redstone") && f.contains(e.getBlock().getType().toString())) {
            e.getPlayer().sendMessage(UNUtilities.translateColor(UNUtilities.cfg.getString(configPath + "msg")));
            e.setCancelled(true);
        }
    }
}