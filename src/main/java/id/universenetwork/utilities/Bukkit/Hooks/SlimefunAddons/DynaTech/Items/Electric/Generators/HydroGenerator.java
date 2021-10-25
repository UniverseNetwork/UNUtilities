package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Generators;

import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import org.bukkit.Material;
import org.bukkit.block.data.Waterlogged;
import org.jetbrains.annotations.NotNull;

import static java.util.concurrent.TimeUnit.MINUTES;

public class HydroGenerator extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem implements io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider {
    final int energy;
    final int capacity;

    final com.google.common.cache.LoadingCache<BlockPosition, Integer> cachedGeneration = com.google.common.cache.CacheBuilder.newBuilder().refreshAfterWrite(1, MINUTES).expireAfterAccess(3, MINUTES).build(new com.google.common.cache.CacheLoader<>() {
        @Override
        public Integer load(@NotNull BlockPosition key) {
            return fetchOutputForBlock(key);
        }
    });

    public HydroGenerator(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, int energy, int capacity, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.energy = energy;
        this.capacity = capacity;
    }

    @Override
    public int getGeneratedOutput(@NotNull org.bukkit.Location location, @NotNull me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config config) {
        final BlockPosition pos = new BlockPosition(location);
        Integer i = cachedGeneration.getIfPresent(pos);
        if (i != null) return i;
        else {
            int output = fetchOutputForBlock(pos);
            cachedGeneration.put(pos, output);
            return output;
        }
    }

    int fetchOutputForBlock(@NotNull BlockPosition position) {
        final org.bukkit.block.Block b = position.getBlock();
        // Block has been removed, invalidate the cache
        if (b.getType() == Material.COBBLESTONE_WALL || b.getType() == Material.PRISMARINE_WALL) {
            org.bukkit.block.data.BlockData blockData = io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib.getBlockState(b, false).getState().getBlockData();
            if (blockData instanceof Waterlogged) {
                Waterlogged data = (Waterlogged) blockData;
                if (data.isWaterlogged()) return getEnergyProduction();
            }
        } else cachedGeneration.invalidate(position); // Block has been removed, invalidate the cache
        return 0;
    }

    @Override
    public boolean isChargeable() {
        return false;
    }

    public int getEnergyProduction() {
        return energy;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }
}