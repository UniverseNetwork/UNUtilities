package id.universenetwork.utilities.bukkit.features.SlimefunAddons.DyedBackpacks;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.backpacks.SlimefunBackpack;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * This is our {@link SlimefunItem} implementation for the {@link DyedBackpack}.
 * It extends {@link SlimefunBackpack} but also carries an additional {@link BackpackColor}
 * attribute.
 *
 * @author TheBusyBiscuit
 * @author ARVIN3108 ID
 */
public class DyedBackpack extends SlimefunBackpack {
    private final BackpackColor color;

    public DyedBackpack(int size, ItemGroup itemGroup, SlimefunItemStack item, SlimefunItemStack backpack, BackpackColor color) {
        super(size, itemGroup, item, RecipeType.ENHANCED_CRAFTING_TABLE, createRecipe(backpack, color));
        Validate.notNull(color, "Dyed Backpack colors cannot be null!");
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

    private static ItemStack[] createRecipe(SlimefunItemStack backpack, BackpackColor color) {
        ItemStack wool = new ItemStack(color.getWoolMaterial());
        ItemStack leather = new ItemStack(Material.LEATHER);
        return new ItemStack[]{
                wool, wool, wool,
                leather, backpack, leather,
                wool, wool, wool
        };
    }
}