package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.EcoPower.Generators;

import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import static org.bukkit.Tag.WOODEN_FENCES;

public class WindTurbine extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem implements io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider {
    static final BlockFace[] airFaces = {BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};
    final java.util.Set<Location> validTurbines = java.util.concurrent.ConcurrentHashMap.newKeySet();
    final int generatedPower;

    public WindTurbine(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, int generatedPower, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.generatedPower = generatedPower;
    }

    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.GENERATOR;
    }

    @Override
    public int getGeneratedOutput(Location l, me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config config) {
        int power = validTurbines.remove(l) ? generatedPower : 0;
        org.bukkit.Bukkit.getScheduler().runTask(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, () -> {
            // Mark the turbine as valid (if valid)
            if (validateLocation(l)) validTurbines.add(l);
        });
        return power;
    }

    @Override
    public int getCapacity() {
        return 0;
    }

    boolean validateLocation(Location l) {
        Block b = l.getBlock();
        Block fence1 = b.getRelative(BlockFace.DOWN);
        Block fence2 = b.getRelative(BlockFace.DOWN, 2);
        if (!WOODEN_FENCES.isTagged(fence1.getType()) || !WOODEN_FENCES.isTagged(fence2.getType()))
            return false;
        for (BlockFace face : airFaces) if (!b.getRelative(face).isEmpty()) return false;
        l.getWorld().spawnParticle(org.bukkit.Particle.SPELL, l.getX() + 0.5, l.getY(), l.getZ() + 0.5, 4, 0, 0.4, 0, 0.01);
        return true;
    }
}