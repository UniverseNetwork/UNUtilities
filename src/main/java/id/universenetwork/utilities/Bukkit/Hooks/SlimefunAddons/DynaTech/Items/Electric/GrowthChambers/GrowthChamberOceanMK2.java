package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.GrowthChambers;

import org.bukkit.inventory.ItemStack;

import java.util.List;

import static org.bukkit.Material.*;

public class GrowthChamberOceanMK2 extends id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Abstracts.AMachine {
    int[] BORDER = new int[]{};
    int[] BORDER_IN = new int[]{0, 8, 9, 10, 11, 12, 14, 15, 16, 17};
    int[] BORDER_OUT = new int[]{18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 35, 36, 44, 45, 53};

    public GrowthChamberOceanMK2(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(9, new ItemStack(LILY_PAD), new ItemStack(LILY_PAD, 9));
        registerRecipe(9, new ItemStack(SEA_PICKLE), new ItemStack(SEA_PICKLE, 9));
        registerRecipe(12, new ItemStack(SEAGRASS), new ItemStack(SEAGRASS, 12));
        registerRecipe(9, new ItemStack(KELP), new ItemStack(KELP, 9));

        // Coral blocks
        // Brings dead coral blocks back to life!
        registerRecipe(9, new ItemStack(DEAD_TUBE_CORAL_BLOCK), new ItemStack(TUBE_CORAL_BLOCK, 3));
        registerRecipe(9, new ItemStack(DEAD_BRAIN_CORAL_BLOCK), new ItemStack(BRAIN_CORAL_BLOCK, 3));
        registerRecipe(9, new ItemStack(DEAD_BUBBLE_CORAL_BLOCK), new ItemStack(BUBBLE_CORAL_BLOCK, 3));
        registerRecipe(9, new ItemStack(DEAD_FIRE_CORAL_BLOCK), new ItemStack(FIRE_CORAL_BLOCK, 3));
        registerRecipe(9, new ItemStack(DEAD_HORN_CORAL_BLOCK), new ItemStack(HORN_CORAL_BLOCK, 3));

        // Block duplication
        registerRecipe(12, new ItemStack(TUBE_CORAL_BLOCK), new ItemStack(TUBE_CORAL_BLOCK, 6));
        registerRecipe(12, new ItemStack(BRAIN_CORAL_BLOCK), new ItemStack(BRAIN_CORAL_BLOCK, 6));
        registerRecipe(12, new ItemStack(BUBBLE_CORAL_BLOCK), new ItemStack(BUBBLE_CORAL_BLOCK, 6));
        registerRecipe(12, new ItemStack(FIRE_CORAL_BLOCK), new ItemStack(FIRE_CORAL_BLOCK, 6));
        registerRecipe(12, new ItemStack(HORN_CORAL_BLOCK), new ItemStack(HORN_CORAL_BLOCK, 6));

        // Coral
        // Revive for coral
        registerRecipe(9, new ItemStack(DEAD_TUBE_CORAL), new ItemStack(TUBE_CORAL, 3));
        registerRecipe(9, new ItemStack(DEAD_BRAIN_CORAL), new ItemStack(BRAIN_CORAL, 3));
        registerRecipe(9, new ItemStack(DEAD_BUBBLE_CORAL), new ItemStack(BUBBLE_CORAL, 3));
        registerRecipe(9, new ItemStack(DEAD_FIRE_CORAL), new ItemStack(FIRE_CORAL, 3));
        registerRecipe(9, new ItemStack(DEAD_HORN_CORAL), new ItemStack(HORN_CORAL, 3));

        // Coral duplication
        registerRecipe(12, new ItemStack(TUBE_CORAL), new ItemStack(TUBE_CORAL, 6));
        registerRecipe(12, new ItemStack(BRAIN_CORAL), new ItemStack(BRAIN_CORAL, 6));
        registerRecipe(12, new ItemStack(BUBBLE_CORAL), new ItemStack(BUBBLE_CORAL, 6));
        registerRecipe(12, new ItemStack(FIRE_CORAL), new ItemStack(FIRE_CORAL, 6));
        registerRecipe(12, new ItemStack(HORN_CORAL), new ItemStack(HORN_CORAL, 6));

        // Coral fans
        // Medical attention for the fans
        registerRecipe(9, new ItemStack(DEAD_TUBE_CORAL_FAN), new ItemStack(TUBE_CORAL_FAN, 3));
        registerRecipe(9, new ItemStack(DEAD_BRAIN_CORAL_FAN), new ItemStack(BRAIN_CORAL_FAN, 3));
        registerRecipe(9, new ItemStack(DEAD_BUBBLE_CORAL_FAN), new ItemStack(BUBBLE_CORAL_FAN, 3));
        registerRecipe(9, new ItemStack(DEAD_FIRE_CORAL_FAN), new ItemStack(FIRE_CORAL_FAN, 3));
        registerRecipe(9, new ItemStack(DEAD_HORN_CORAL_FAN), new ItemStack(HORN_CORAL_FAN, 3));

        // Fan duplication
        registerRecipe(12, new ItemStack(TUBE_CORAL_FAN), new ItemStack(TUBE_CORAL_FAN, 6));
        registerRecipe(12, new ItemStack(BRAIN_CORAL_FAN), new ItemStack(BRAIN_CORAL_FAN, 6));
        registerRecipe(12, new ItemStack(BUBBLE_CORAL_FAN), new ItemStack(BUBBLE_CORAL_FAN, 6));
        registerRecipe(12, new ItemStack(FIRE_CORAL_FAN), new ItemStack(FIRE_CORAL_FAN, 6));
        registerRecipe(12, new ItemStack(HORN_CORAL_FAN), new ItemStack(HORN_CORAL_FAN, 6));
    }

    @Override
    public boolean isGraphical() {
        return true;
    }

    @Override
    public String getMachineIdentifier() {
        return "GROWTH_CHAMBER_OCEAN_MK2";
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
        return new ItemStack(CONDUIT);
    }

    @Override
    public int getProgressBarSlot() {
        return 13;
    }
}