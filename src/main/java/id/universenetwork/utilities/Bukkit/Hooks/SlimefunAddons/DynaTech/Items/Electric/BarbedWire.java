package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric;

import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.entity.EntityType.*;
import static org.bukkit.util.NumberConversions.isFinite;

public class BarbedWire extends id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Abstracts.AMachine {
    static final int MAX_DIRECTION_VEL = 10000;

    public BarbedWire(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void tick(org.bukkit.block.Block b) {
        if (getCharge(b.getLocation()) < getEnergyConsumption()) return;
        id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTech.runSync(() -> sendEntitiesFlying(b.getLocation(), b.getWorld()));
        removeCharge(b.getLocation(), getEnergyConsumption());
    }

    public void sendEntitiesFlying(@NotNull org.bukkit.Location loc, @NotNull org.bukkit.World w) {
        java.util.List<Entity> shotEntities = new java.util.ArrayList<>();
        int waitTime = 0;
        for (Entity e : w.getNearbyEntities(loc, 9, 9, 9)) {
            Vector tempV = e.getVelocity();
            if (e.getType() != PLAYER && e.getType() != ARMOR_STAND && e.getType() != DROPPED_ITEM && !shotEntities.contains(e)) {
                Vector tempV2 = tempV.multiply(-1).multiply(1.2).add(new Vector(1, 0.7, 1));
                if (tempV2.getX() >= MAX_DIRECTION_VEL || tempV2.getY() >= MAX_DIRECTION_VEL || tempV2.getZ() >= MAX_DIRECTION_VEL)
                    tempV2 = new Vector(0, 0, 0);
                if (isFinite(tempV2.getX()) && isFinite(tempV2.getY()) && isFinite(tempV2.getZ())) {
                    e.setVelocity(tempV2);
                    shotEntities.add(e);
                } else if (isFinite(tempV.getX()) && isFinite(tempV.getY()) && isFinite(tempV.getZ()))
                    e.setVelocity(tempV);
                else e.setVelocity(new Vector(0, 0, 0));
            }
            if (shotEntities.contains(e) && waitTime > 8) e.setVelocity(tempV);
            waitTime++;
        }
    }

    @Override
    public String getMachineIdentifier() {
        return "BARBED_WIRE";
    }


    @Override
    public boolean isGraphical() {
        return false;
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(org.bukkit.Material.IRON_BARS);
    }
}