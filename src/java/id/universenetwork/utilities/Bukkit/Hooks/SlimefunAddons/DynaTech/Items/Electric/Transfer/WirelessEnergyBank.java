package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Transfer;

import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;

public class WirelessEnergyBank extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem implements io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent {
    final int capacity;

    @javax.annotation.ParametersAreNonnullByDefault
    public WirelessEnergyBank(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, int capacity, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.capacity = capacity;
    }

    @Override
    @org.jetbrains.annotations.NotNull
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CAPACITOR;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }
}