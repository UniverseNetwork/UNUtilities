package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Machines;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.AbstractMachineBlock;
import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.RandomizedSet;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import lombok.Setter;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.slimefunTickCount;

@ParametersAreNonnullByDefault
public final class GeoQuarry extends AbstractMachineBlock implements RecipeDisplayItem {
    static final int STATUS = 4;
    static final int[] OUTPUT_SLOTS = {29, 30, 31, 32, 33, 38, 39, 40, 41, 42};
    final Map<Pair<Biome, World.Environment>, RandomizedSet<ItemStack>> recipes = new HashMap<>();
    @Setter
    int ticksPerOutput;

    public GeoQuarry(ItemGroup category, SlimefunItemStack item, RecipeType type, ItemStack[] recipe) {
        super(category, item, type, recipe);
    }

    @Override
    protected void setup(@NotNull BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 26, 27, 35, 36, 44, 45, 53});
        blockMenuPreset.drawBackground(OUTPUT_BORDER, new int[]{19, 20, 21, 22, 23, 24, 25, 28, 34, 37, 43, 46, 47, 48, 49, 50, 51, 52});
    }

    @Override
    protected int[] getInputSlots() {
        return new int[0];
    }

    @Override
    protected int[] getOutputSlots() {
        return OUTPUT_SLOTS;
    }

    @Override
    protected boolean process(Block b, BlockMenu inv) {
        if (slimefunTickCount % this.ticksPerOutput != 0) {
            if (inv.hasViewer())
                inv.replaceExistingItem(STATUS, new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, "&aDrilling..."));
            return true;
        }
        ItemStack output = this.recipes.computeIfAbsent(new Pair<>(b.getBiome(), b.getWorld().getEnvironment()), k -> {
            RandomizedSet<ItemStack> set = new RandomizedSet<>();
            for (GEOResource resource : Slimefun.getRegistry().getGEOResources().values())
                if (resource.isObtainableFromGEOMiner()) {
                    int supply = resource.getDefaultSupply(b.getWorld().getEnvironment(), b.getBiome());
                    if (supply > 0) set.add(resource.getItem(), supply);
                }
            return set;
        }).getRandom();
        if (!inv.fits(output, OUTPUT_SLOTS)) {
            if (inv.hasViewer()) inv.replaceExistingItem(STATUS, NO_ROOM_ITEM);
            return false;
        }
        inv.pushItem(output.clone(), OUTPUT_SLOTS);
        if (inv.hasViewer())
            inv.replaceExistingItem(STATUS, new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, "&aFound!"));
        return true;
    }

    @Override
    protected int getStatusSlot() {
        return STATUS;
    }

    @NotNull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes = new LinkedList<>();
        for (GEOResource resource : Slimefun.getRegistry().getGEOResources().values())
            if (resource.isObtainableFromGEOMiner()) displayRecipes.add(resource.getItem());
        return displayRecipes;
    }
}