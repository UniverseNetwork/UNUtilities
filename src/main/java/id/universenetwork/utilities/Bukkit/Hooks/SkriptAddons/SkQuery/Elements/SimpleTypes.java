package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements;

import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.AbstractTask;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript.EnumClassInfo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript.TypeClassInfo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Minecraft.MoonPhase;
import org.bukkit.Art;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.scoreboard.DisplaySlot;

import java.sql.ResultSet;

public class SimpleTypes extends AbstractTask {
    @Override
    public void run() {
        EnumClassInfo.create(EntityRegainHealthEvent.RegainReason.class, "regainreason").register();
        EnumClassInfo.create(Villager.Profession.class, "profession").register();
        EnumClassInfo.create(InventoryType.class, "inventorytype").register();
        EnumClassInfo.create(DisplaySlot.class, "displayslot").register();
        EnumClassInfo.create(MoonPhase.class, "moonphase").register();
        EnumClassInfo.create(Particle.class, "particle").register();
        EnumClassInfo.create(Sound.class, "sound").register();
        EnumClassInfo.create(Art.class, "art").register();
        TypeClassInfo.create(WorldBorder.class, "worldborder").register();
        TypeClassInfo.create(ResultSet.class, "queryresult").register();
    }
}