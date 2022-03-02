package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.EcoPower.Generators;

public class LunarGenerator extends io.github.thebusybiscuit.slimefun4.implementation.items.electric.generators.SolarGenerator {
    public LunarGenerator(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe, int energy) {
        super(itemGroup, 0, energy, item, recipeType, recipe);
    }
}