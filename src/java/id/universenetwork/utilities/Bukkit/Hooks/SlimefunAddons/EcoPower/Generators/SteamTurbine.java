package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.EcoPower.Generators;

import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import org.bukkit.*;

public class SteamTurbine extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem implements io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider {
    final java.util.Set<Location> validTurbines = java.util.concurrent.ConcurrentHashMap.newKeySet();
    final int generatedPower;

    public SteamTurbine(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, int generatedPower, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe) {
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
        Bukkit.getScheduler().runTask(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, () -> {
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
        org.bukkit.block.Block water = l.getBlock().getRelative(org.bukkit.block.BlockFace.DOWN);
        // A Bubble Column is water above a Magma Block
        if (water.getType() != Material.BUBBLE_COLUMN) return false;
        water.setType(Material.AIR);
        l.getWorld().playSound(l, Sound.BLOCK_FIRE_EXTINGUISH, 0.05F, 1);
        l.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, l.getX() + 0.5, l.getY(), l.getZ() + 0.5, 1, 0, 0.4, 0, 0.01);
        return true;
    }
}