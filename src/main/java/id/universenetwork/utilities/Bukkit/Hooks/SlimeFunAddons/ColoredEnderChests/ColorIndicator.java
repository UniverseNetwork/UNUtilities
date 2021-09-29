package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.ColoredEnderChests;

import io.github.thebusybiscuit.slimefun4.utils.ColoredMaterial;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import java.util.Objects;

final class ColorIndicator {
    static double angle = Math.toRadians(345);
    static double offset = -0.08;

    ColorIndicator() {
    }

    static void updateIndicator(Block b, int c1, int c2, int c3, int yaw) {
        removeIndicator(b);
        EulerAngle euler = new EulerAngle(angle, 0F, 0F);
        Location l = b.getLocation().add(0.5D, 0.5D + offset, 0.5D);
        createArmorStand(l, new ItemStack(getWool(c1)), euler, (float) yaw);
        createArmorStand(translocate(l, yaw), new ItemStack(getWool(c2)), euler, (float) yaw);
        createArmorStand(translocate(l, yaw), new ItemStack(getWool(c3)), euler, (float) yaw);
    }

    static void removeIndicator(Block b) {
        for (Entity n : b.getChunk().getEntities())
            if (n instanceof ArmorStand && b.getLocation().add(0.5D, 0.5D, 0.5D).distanceSquared(n.getLocation()) < 0.75D && n.getCustomName() == null)
                n.remove();
    }

    public static void createArmorStand(Location l, ItemStack item, EulerAngle arm, float yaw) {
        l.setYaw(yaw);
        ArmorStand armorStand = (ArmorStand) l.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
        Objects.requireNonNull(armorStand.getEquipment()).setItemInMainHand(item);
        armorStand.setVisible(false);
        armorStand.setSilent(true);
        armorStand.setMarker(true);
        armorStand.setGravity(false);
        armorStand.setSmall(true);
        armorStand.setArms(true);
        armorStand.setRightArmPose(arm);
        armorStand.setBasePlate(false);
        armorStand.setRemoveWhenFarAway(false);
    }

    static Material getWool(int index) {
        return ColoredMaterial.WOOL.get(index);
    }

    static Location translocate(Location l, int yaw) {
        if (yaw == 45) { // 0
            return l.add(0.275 * 1, 0, 0);
        } else if (yaw == 225) { // 180
            return l.add(-0.275 * 1, 0, 0);
        } else if (yaw == -45) { // -90
            return l.add(0, 0, -0.275 * 1);
        } else return l.add(0, 0, 0.275 * 1); // 90
    }
}