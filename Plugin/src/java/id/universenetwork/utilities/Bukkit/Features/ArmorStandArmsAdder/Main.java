package id.universenetwork.utilities.Bukkit.Features.ArmorStandArmsAdder;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events;
import id.universenetwork.utilities.Bukkit.Templates.Feature;
import id.universenetwork.utilities.Bukkit.UNUtilities;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPlaceEvent;

public class Main extends Feature implements Listener {
    @Override
    public void Load() {
        Events.registerListeners(this);
    }

    @org.bukkit.event.EventHandler
    public void onEntityPlace(EntityPlaceEvent e) {
        if (e.getEntityType() == EntityType.ARMOR_STAND && UNUtilities.cfg.getBoolean(configPath + "enabled"))
            ((org.bukkit.entity.ArmorStand) e.getEntity()).setArms(true);
    }
}