package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.EcoPower.Generators;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class SteamTurbine extends SlimefunItem implements EnergyNetProvider {
    final Set<Location> validTurbines = ConcurrentHashMap.newKeySet();
    final int generatedPower;

    public SteamTurbine(ItemGroup itemGroup, SlimefunItemStack item, int generatedPower, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.generatedPower = generatedPower;
    }

    @NotNull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.GENERATOR;
    }

    @Override
    @ParametersAreNonnullByDefault
    public int getGeneratedOutput(Location l, Config config) {
        int power = validTurbines.remove(l) ? generatedPower : 0;
        Bukkit.getScheduler().runTask(plugin, () -> {
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
        Block water = l.getBlock().getRelative(BlockFace.DOWN);
        // A Bubble Column is water above a Magma Block
        if (water.getType() != Material.BUBBLE_COLUMN) return false;
        water.setType(Material.AIR);
        l.getWorld().playSound(l, Sound.BLOCK_FIRE_EXTINGUISH, 0.05F, 1);
        l.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, l.getX() + 0.5, l.getY(), l.getZ() + 0.5, 1, 0, 0.4, 0, 0.01);
        return true;
    }
}