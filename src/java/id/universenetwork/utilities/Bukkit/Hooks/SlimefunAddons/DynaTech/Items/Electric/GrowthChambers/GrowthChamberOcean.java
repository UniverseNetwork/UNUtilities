package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.GrowthChambers;

import org.bukkit.inventory.ItemStack;

import static org.bukkit.Material.*;

public class GrowthChamberOcean extends id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Abstracts.AMachine {
    public GrowthChamberOcean(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(9, new ItemStack(LILY_PAD), new ItemStack(LILY_PAD, 3));
        registerRecipe(9, new ItemStack(SEA_PICKLE), new ItemStack(SEA_PICKLE, 3));
        registerRecipe(12, new ItemStack(SEAGRASS), new ItemStack(SEAGRASS, 4));
        registerRecipe(9, new ItemStack(KELP), new ItemStack(KELP, 3));

        // Coral blocks
        // Brings dead coral blocks back to life!
        registerRecipe(9, new ItemStack(DEAD_TUBE_CORAL_BLOCK), new ItemStack(TUBE_CORAL_BLOCK, 1));
        registerRecipe(9, new ItemStack(DEAD_BRAIN_CORAL_BLOCK), new ItemStack(BRAIN_CORAL_BLOCK, 1));
        registerRecipe(9, new ItemStack(DEAD_BUBBLE_CORAL_BLOCK), new ItemStack(BUBBLE_CORAL_BLOCK, 1));
        registerRecipe(9, new ItemStack(DEAD_FIRE_CORAL_BLOCK), new ItemStack(FIRE_CORAL_BLOCK, 1));
        registerRecipe(9, new ItemStack(DEAD_HORN_CORAL_BLOCK), new ItemStack(HORN_CORAL_BLOCK, 1));

        // Block duplication
        registerRecipe(12, new ItemStack(TUBE_CORAL_BLOCK), new ItemStack(TUBE_CORAL_BLOCK, 2));
        registerRecipe(12, new ItemStack(BRAIN_CORAL_BLOCK), new ItemStack(BRAIN_CORAL_BLOCK, 2));
        registerRecipe(12, new ItemStack(BUBBLE_CORAL_BLOCK), new ItemStack(BUBBLE_CORAL_BLOCK, 2));
        registerRecipe(12, new ItemStack(FIRE_CORAL_BLOCK), new ItemStack(FIRE_CORAL_BLOCK, 2));
        registerRecipe(12, new ItemStack(HORN_CORAL_BLOCK), new ItemStack(HORN_CORAL_BLOCK, 2));

        // Coral
        // Revive for coral
        registerRecipe(9, new ItemStack(DEAD_TUBE_CORAL), new ItemStack(TUBE_CORAL, 1));
        registerRecipe(9, new ItemStack(DEAD_BRAIN_CORAL), new ItemStack(BRAIN_CORAL, 1));
        registerRecipe(9, new ItemStack(DEAD_BUBBLE_CORAL), new ItemStack(BUBBLE_CORAL, 1));
        registerRecipe(9, new ItemStack(DEAD_FIRE_CORAL), new ItemStack(FIRE_CORAL, 1));
        registerRecipe(9, new ItemStack(DEAD_HORN_CORAL), new ItemStack(HORN_CORAL, 1));

        // Coral duplication
        registerRecipe(12, new ItemStack(TUBE_CORAL), new ItemStack(TUBE_CORAL, 2));
        registerRecipe(12, new ItemStack(BRAIN_CORAL), new ItemStack(BRAIN_CORAL, 2));
        registerRecipe(12, new ItemStack(BUBBLE_CORAL), new ItemStack(BUBBLE_CORAL, 2));
        registerRecipe(12, new ItemStack(FIRE_CORAL), new ItemStack(FIRE_CORAL, 2));
        registerRecipe(12, new ItemStack(HORN_CORAL), new ItemStack(HORN_CORAL, 2));

        // Coral fans
        // Medical attention for the fans
        registerRecipe(9, new ItemStack(DEAD_TUBE_CORAL_FAN), new ItemStack(TUBE_CORAL_FAN, 1));
        registerRecipe(9, new ItemStack(DEAD_BRAIN_CORAL_FAN), new ItemStack(BRAIN_CORAL_FAN, 1));
        registerRecipe(9, new ItemStack(DEAD_BUBBLE_CORAL_FAN), new ItemStack(BUBBLE_CORAL_FAN, 1));
        registerRecipe(9, new ItemStack(DEAD_FIRE_CORAL_FAN), new ItemStack(FIRE_CORAL_FAN, 1));
        registerRecipe(9, new ItemStack(DEAD_HORN_CORAL_FAN), new ItemStack(HORN_CORAL_FAN, 1));

        // Fan duplication
        registerRecipe(12, new ItemStack(TUBE_CORAL_FAN), new ItemStack(TUBE_CORAL_FAN, 2));
        registerRecipe(12, new ItemStack(BRAIN_CORAL_FAN), new ItemStack(BRAIN_CORAL_FAN, 2));
        registerRecipe(12, new ItemStack(BUBBLE_CORAL_FAN), new ItemStack(BUBBLE_CORAL_FAN, 2));
        registerRecipe(12, new ItemStack(FIRE_CORAL_FAN), new ItemStack(FIRE_CORAL_FAN, 2));
        registerRecipe(12, new ItemStack(HORN_CORAL_FAN), new ItemStack(HORN_CORAL_FAN, 2));
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(CONDUIT);
    }

    @Override
    public String getMachineIdentifier() {
        return "GROWTH_CHAMBER_OCEAN";
    }
}