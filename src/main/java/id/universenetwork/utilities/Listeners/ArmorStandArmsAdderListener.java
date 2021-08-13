package id.universenetwork.utilities.Listeners;

import id.universenetwork.utilities.Enums.Features.ArmorStandArmsAdder;
import id.universenetwork.utilities.Enums.Settings;
import id.universenetwork.utilities.Manager.Color;
import id.universenetwork.utilities.Manager.Config;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPlaceEvent;

public class ArmorStandArmsAdderListener implements Listener {
    @EventHandler
    public void onArmorStandPlace(EntityPlaceEvent e) {
        Location l = e.getEntity().getLocation();
        if(Config.ArmorStandArmsAdderSettings(ArmorStandArmsAdder.ENABLED)) {
            if (e.getEntityType() != EntityType.ARMOR_STAND) return;
            ((ArmorStand) e.getEntity()).setArms(true);
            if (Config.ArmorStandArmsAdderSettings(ArmorStandArmsAdder.LOG))
                System.out.println(Config.Settings(Settings.PREFIX) + Color.Translator("&bBerhasil menambahkan tangan ke Armor Stand dikoordinat ") + ChatColor.GREEN + l.getX() + " " + l.getY() + " " + l.getZ());
        }
    }
}
