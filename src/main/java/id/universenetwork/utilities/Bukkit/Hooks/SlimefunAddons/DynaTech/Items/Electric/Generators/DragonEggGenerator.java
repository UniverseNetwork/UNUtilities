package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Generators;

import org.jetbrains.annotations.NotNull;

public class DragonEggGenerator extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem implements io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider {
    public DragonEggGenerator(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public int getGeneratedOutput(@NotNull org.bukkit.Location l, @NotNull me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config c) {
        if (l.getBlock().getRelative(org.bukkit.block.BlockFace.UP).getType() == org.bukkit.Material.DRAGON_EGG)
            return 32;
        return 0;
    }

    @Override
    public boolean isChargeable() {
        return false;
    }

    @Override
    public int getCapacity() {
        return 512;
    }
}