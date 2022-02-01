package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExoticGarden.Items;

public class MagicalEssence extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem {
    @javax.annotation.ParametersAreNonnullByDefault
    public MagicalEssence(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item) {
        super(itemGroup, item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE, new org.bukkit.inventory.ItemStack[]{item, item, item, item, null, item, item, item, item});
    }

    @Override
    public boolean useVanillaBlockBreaking() {
        return true;
    }
}