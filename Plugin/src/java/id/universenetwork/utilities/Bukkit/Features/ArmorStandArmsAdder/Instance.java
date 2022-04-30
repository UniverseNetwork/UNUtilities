package id.universenetwork.utilities.Bukkit.Features.ArmorStandArmsAdder;

public class Instance extends id.universenetwork.utilities.Bukkit.ClassInstance.Feature implements org.bukkit.event.Listener {
    @Override
    public void Load() {
        id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListeners(this);
    }

    @org.bukkit.event.EventHandler
    public void onEntityPlace(org.bukkit.event.entity.EntityPlaceEvent e) {
        if (e.getEntityType() == org.bukkit.entity.EntityType.ARMOR_STAND && id.universenetwork.utilities.Bukkit.UNUtilities.cfg.getBoolean(configPath + "enabled"))
            ((org.bukkit.entity.ArmorStand) e.getEntity()).setArms(true);
    }
}