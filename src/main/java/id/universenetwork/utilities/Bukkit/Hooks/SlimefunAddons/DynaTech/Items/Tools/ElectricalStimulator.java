package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Tools;

import org.bukkit.inventory.ItemStack;

public class ElectricalStimulator extends io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock implements io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable {
    public ElectricalStimulator(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public float getMaxItemCharge(ItemStack item) {
        return 1024;
    }

    public float getEnergyComsumption() {
        return 32f;
    }
}