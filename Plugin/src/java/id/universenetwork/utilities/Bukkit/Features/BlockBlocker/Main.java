package id.universenetwork.utilities.Bukkit.Features.BlockBlocker;

import static id.universenetwork.utilities.Bukkit.UNUtilities.cfg;

public class Main extends id.universenetwork.utilities.Bukkit.Templates.Feature implements org.bukkit.event.Listener {
    @Override
    public void Load() {
        id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListeners(this);
    }

    @org.bukkit.event.EventHandler
    public void onBlockPlace(org.bukkit.event.block.BlockPlaceEvent e) {
        java.util.List<String> f = new java.util.ArrayList<>();
        for (String s : cfg.getStringList(configPath + "blocks")) f.add(s.toUpperCase());
        if (cfg.getBoolean(configPath + "enabled")
                && !e.getPlayer().hasPermission("unutilities.redstone")
                && f.contains(e.getBlock().getType().toString())) {
            e.getPlayer().sendMessage(id.universenetwork.utilities.Bukkit.Utils.Text.translateColor(cfg.getString(configPath + "msg")));
            e.setCancelled(true);
        }
    }
}