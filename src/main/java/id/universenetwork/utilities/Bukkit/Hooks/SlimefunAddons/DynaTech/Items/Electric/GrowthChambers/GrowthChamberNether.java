package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.GrowthChambers;

import org.bukkit.inventory.ItemStack;

import static org.bukkit.Material.*;

public class GrowthChamberNether extends id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Abstracts.AMachine {
    public GrowthChamberNether(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(12, new ItemStack(NETHER_WART), new ItemStack(NETHER_WART, 4));
        registerRecipe(9, new ItemStack(WEEPING_VINES), new ItemStack(WEEPING_VINES, 4));
        registerRecipe(9, new ItemStack(TWISTING_VINES), new ItemStack(TWISTING_VINES, 4));
        registerRecipe(9, new ItemStack(CRIMSON_ROOTS), new ItemStack(CRIMSON_ROOTS, 4));
        registerRecipe(9, new ItemStack(WARPED_ROOTS), new ItemStack(WARPED_ROOTS, 4));
        registerRecipe(9, new ItemStack(NETHER_SPROUTS), new ItemStack(NETHER_SPROUTS, 4));

        registerRecipe(30, new ItemStack[]{new ItemStack(CRIMSON_FUNGUS)}, new ItemStack[]{new ItemStack(CRIMSON_FUNGUS, 2), new ItemStack(CRIMSON_STEM, 6)});
        registerRecipe(30, new ItemStack[]{new ItemStack(WARPED_FUNGUS)}, new ItemStack[]{new ItemStack(WARPED_FUNGUS, 2), new ItemStack(WARPED_STEM, 6)});
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(NETHERRACK);
    }

    @Override
    public String getMachineIdentifier() {
        return "GROWTH_CHAMBER_NETHER";
    }
}