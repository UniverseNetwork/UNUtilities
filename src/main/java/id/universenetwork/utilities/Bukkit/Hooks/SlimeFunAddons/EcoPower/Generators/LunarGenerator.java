package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.EcoPower.Generators;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.generators.SolarGenerator;
import org.bukkit.inventory.ItemStack;

public class LunarGenerator extends SolarGenerator {
    public LunarGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int energy) {
        super(itemGroup, 0, energy, item, recipeType, recipe);
    }
}