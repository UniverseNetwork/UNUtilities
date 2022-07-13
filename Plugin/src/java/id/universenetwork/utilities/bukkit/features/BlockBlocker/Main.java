package id.universenetwork.utilities.bukkit.features.BlockBlocker;

import id.universenetwork.utilities.bukkit.templates.Feature;
import id.universenetwork.utilities.bukkit.utils.Text;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.List;

public class Main extends Feature {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        List<String> f = new ArrayList<>();
        for (String s : cfgSection.getStringList("blocks")) f.add(s.toUpperCase());
        if (cfgSection.getBoolean("enabled")
                && !e.getPlayer().hasPermission("unutilities.blockblocker")
                && f.contains(e.getBlock().getType().toString())) {
            e.getPlayer().sendMessage(Text.translateColor(cfgSection.getString("msg")));
            e.setCancelled(true);
        }
    }
}