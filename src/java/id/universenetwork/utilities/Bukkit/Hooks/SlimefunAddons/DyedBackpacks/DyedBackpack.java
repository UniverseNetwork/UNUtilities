package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DyedBackpacks;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.items.backpacks.SlimefunBackpack;
import org.bukkit.inventory.ItemStack;

/**
 * This is our {@link io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem} implementation for the {@link DyedBackpack}.
 * It extends {@link SlimefunBackpack} but also carries an additional {@link BackpackColor}
 * attribute.
 *
 * @author TheBusyBiscuit
 * @author ARVIN3108 ID
 */
public class DyedBackpack extends SlimefunBackpack {
    final BackpackColor color;

    public DyedBackpack(int size, io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, SlimefunItemStack item, SlimefunItemStack backpack, BackpackColor color) {
        super(size, itemGroup, item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE, createRecipe(backpack, color));
        org.apache.commons.lang.Validate.notNull(color, "Dyed Backpack colors cannot be null!");
        this.color = color;
    }

    /**
     * This returns the {@link BackpackColor} of this {@link DyedBackpack}.
     *
     * @return The {@link BackpackColor}
     */
    public BackpackColor getColor() {
        return color;
    }

    static ItemStack[] createRecipe(SlimefunItemStack backpack, BackpackColor color) {
        ItemStack wool = new ItemStack(color.getWoolMaterial());
        ItemStack leather = new ItemStack(org.bukkit.Material.LEATHER);
        return new ItemStack[]{wool, wool, wool, leather, backpack, leather, wool, wool, wool};
    }
}