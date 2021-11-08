package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements;

import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript.TypeClassInfo;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript.EnumClassInfo.create;

public class SimpleTypes extends id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.AbstractTask {
    @Override
    public void run() {
        create(org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason.class, "regainreason").register();
        create(org.bukkit.entity.Villager.Profession.class, "profession").register();
        create(org.bukkit.event.inventory.InventoryType.class, "inventorytype").register();
        create(org.bukkit.scoreboard.DisplaySlot.class, "displayslot").register();
        create(id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Minecraft.MoonPhase.class, "moonphase").register();
        create(org.bukkit.Particle.class, "particle").register();
        create(org.bukkit.Sound.class, "sound").register();
        create(org.bukkit.Art.class, "art").register();
        TypeClassInfo.create(org.bukkit.WorldBorder.class, "worldborder").register();
        TypeClassInfo.create(java.sql.ResultSet.class, "queryresult").register();
    }
}