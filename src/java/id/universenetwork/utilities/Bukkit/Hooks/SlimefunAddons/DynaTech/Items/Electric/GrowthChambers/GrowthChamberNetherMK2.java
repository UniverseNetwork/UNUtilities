package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.GrowthChambers;

import org.bukkit.inventory.ItemStack;

import java.util.List;

import static org.bukkit.Material.*;

public class GrowthChamberNetherMK2 extends id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Abstracts.AMachine {
    int[] BORDER = new int[]{};
    int[] BORDER_IN = new int[]{0, 8, 9, 10, 11, 12, 14, 15, 16, 17};
    int[] BORDER_OUT = new int[]{18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 35, 36, 44, 45, 53};

    public GrowthChamberNetherMK2(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(12, new ItemStack(NETHER_WART), new ItemStack(NETHER_WART, 12));
        registerRecipe(9, new ItemStack(WEEPING_VINES), new ItemStack(WEEPING_VINES, 12));
        registerRecipe(9, new ItemStack(TWISTING_VINES), new ItemStack(TWISTING_VINES, 12));
        registerRecipe(9, new ItemStack(CRIMSON_ROOTS), new ItemStack(CRIMSON_ROOTS, 12));
        registerRecipe(9, new ItemStack(WARPED_ROOTS), new ItemStack(WARPED_ROOTS, 12));
        registerRecipe(9, new ItemStack(NETHER_SPROUTS), new ItemStack(NETHER_SPROUTS, 12));

        registerRecipe(30, new ItemStack[]{new ItemStack(CRIMSON_FUNGUS)}, new ItemStack[]{new ItemStack(CRIMSON_FUNGUS, 6), new ItemStack(CRIMSON_STEM, 18), new ItemStack(SHROOMLIGHT, 6), new ItemStack(NETHER_WART_BLOCK, 12)});
        registerRecipe(30, new ItemStack[]{new ItemStack(WARPED_FUNGUS)}, new ItemStack[]{new ItemStack(WARPED_FUNGUS, 6), new ItemStack(WARPED_STEM, 18), new ItemStack(SHROOMLIGHT, 6), new ItemStack(WARPED_WART_BLOCK, 12)});
    }

    @Override
    public boolean isGraphical() {
        return true;
    }

    @Override
    public String getMachineIdentifier() {
        return "GROWTH_CHAMBER_NETHER_MK2";
    }

    @Override
    public List<int[]> getBorders() {
        List<int[]> borders = new java.util.ArrayList<>();
        borders.add(BORDER);
        borders.add(BORDER_IN);
        borders.add(BORDER_OUT);
        return borders;
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{1, 2, 3, 4, 5, 6, 7};
    }

    @Override
    public int[] getOutputSlots() {
        return new int[]{28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43, 46, 47, 48, 49, 50, 51, 52};
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(NETHERRACK);
    }

    @Override
    public int getProgressBarSlot() {
        return 13;
    }
}