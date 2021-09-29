package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Items.Materials;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Groups.Groups.INFINITY_MATERIALS;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.InfinityExpansion.getConfig;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Items.Machines.SingularityConstructor.TYPE;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getLogger;

public final class Singularity extends UnplaceableBlock {
    static double COST_MULTIPLIER() {
        double v = getConfig().getDouble("balance-options.singularity-cost-multiplier", 1.0);
        if (v < 0.1 || v > 100) {
            getLogger().warning(prefix + " §6singularity-cost-multiplier in balance-options on §dInfinityExpansion §6Addon Settings is less than 1 or greater than 100!");
            return 1.0;
        }
        return v;
    }

    public Singularity(SlimefunItemStack item, SlimefunItemStack recipe, int amount) {
        super(INFINITY_MATERIALS, item, TYPE, makeRecipe(recipe, (int) (amount * COST_MULTIPLIER())));
    }

    public Singularity(SlimefunItemStack item, Material recipe, int amount) {
        super(INFINITY_MATERIALS, item, TYPE, makeRecipe(new ItemStack(recipe), (int) (amount * COST_MULTIPLIER())));
    }

    @Nonnull
    private static ItemStack[] makeRecipe(ItemStack item, int amount) {
        List<ItemStack> recipe = new ArrayList<>();
        int stacks = (int) Math.floor(amount / 64D);
        int extra = amount % 64;
        for (int i = 0; i < stacks; i++) recipe.add(new CustomItemStack(item, 64));
        recipe.add(new CustomItemStack(item, extra));
        while (recipe.size() < 9) recipe.add(null);
        return recipe.toArray(new ItemStack[9]);
    }
}