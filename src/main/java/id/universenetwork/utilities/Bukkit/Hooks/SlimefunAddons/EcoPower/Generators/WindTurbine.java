package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.EcoPower.Generators;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static org.bukkit.Tag.WOODEN_FENCES;
import static org.bukkit.block.BlockFace.*;

public class WindTurbine extends SlimefunItem implements EnergyNetProvider {
    static final BlockFace[] airFaces = {NORTH, EAST, SOUTH, WEST};
    final Set<Location> validTurbines = ConcurrentHashMap.newKeySet();
    final int generatedPower;

    public WindTurbine(ItemGroup itemGroup, SlimefunItemStack item, int generatedPower, RecipeType recipeType, ItemStack[] recipe) {
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
        Block b = l.getBlock();
        Block fence1 = b.getRelative(BlockFace.DOWN);
        Block fence2 = b.getRelative(BlockFace.DOWN, 2);
        if (!WOODEN_FENCES.isTagged(fence1.getType()) || !WOODEN_FENCES.isTagged(fence2.getType()))
            return false;
        for (BlockFace face : airFaces) if (!b.getRelative(face).isEmpty()) return false;
        l.getWorld().spawnParticle(Particle.SPELL, l.getX() + 0.5, l.getY(), l.getZ() + 0.5, 4, 0, 0.4, 0, 0.01);
        return true;
    }
}