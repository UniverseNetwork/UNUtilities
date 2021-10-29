package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DyedBackpacks;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

class DyedBackpack extends io.github.thebusybiscuit.slimefun4.implementation.items.backpacks.SlimefunBackpack {
    final BackpackColor color;

    public DyedBackpack(int size, io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, SlimefunItemStack item, SlimefunItemStack backpack, BackpackColor color) {
        super(size, itemGroup, item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE, createRecipe(backpack, color));
        org.apache.commons.lang.Validate.notNull(color, "Dyed Backpack colors cannot be null!");
        this.color = color;
    }

    @NotNull
    public BackpackColor getColor() {
        return color;
    }

    @NotNull
    static ItemStack[] createRecipe(SlimefunItemStack backpack, BackpackColor color) {
        ItemStack wool = new ItemStack(color.getWoolMaterial());
        ItemStack leather = new ItemStack(Material.LEATHER);
        return new ItemStack[]{wool, wool, wool, leather, backpack, leather, wool, wool, wool};
    }
}