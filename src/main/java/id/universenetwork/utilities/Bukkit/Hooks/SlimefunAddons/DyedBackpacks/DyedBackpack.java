package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DyedBackpacks;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.backpacks.SlimefunBackpack;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

class DyedBackpack extends SlimefunBackpack {
    final BackpackColor color;

    @ParametersAreNonnullByDefault
    public DyedBackpack(int size, ItemGroup itemGroup, SlimefunItemStack item, SlimefunItemStack backpack, BackpackColor color) {
        super(size, itemGroup, item, RecipeType.ENHANCED_CRAFTING_TABLE, createRecipe(backpack, color));
        Validate.notNull(color, "Dyed Backpack colors cannot be null!");
        this.color = color;
    }

    @NotNull
    public BackpackColor getColor() {
        return color;
    }

    @NotNull
    @ParametersAreNonnullByDefault
    static ItemStack[] createRecipe(SlimefunItemStack backpack, BackpackColor color) {
        ItemStack wool = new ItemStack(color.getWoolMaterial());
        ItemStack leather = new ItemStack(Material.LEATHER);
        return new ItemStack[]{wool, wool, wool, leather, backpack, leather, wool, wool, wool};
    }
}
