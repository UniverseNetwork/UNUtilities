package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Generators;

import org.bukkit.inventory.ItemStack;

public class StardustReactor extends id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Abstracts.AMachineGenerator {
    public StardustReactor(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultFuelTypes() {
        registerFuel(new me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel(32, id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTechItems.STAR_DUST));
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(org.bukkit.Material.IRON_CHESTPLATE);
    }

    @Override
    public String getMachineIdentifier() {
        return "STARDUST_REACTOR";
    }
}