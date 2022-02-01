package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExoticGarden.Items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;

/**
 * This class has an {@link ItemSetting} to disable bonemeal usage on this {@link SlimefunItem}.
 *
 * @author Walshy
 */
public class BonemealableItem extends SlimefunItem {
    final ItemSetting<Boolean> disableBoneMeal = new ItemSetting<>(this, "disable-bonemeal", false);

    @javax.annotation.ParametersAreNonnullByDefault
    public BonemealableItem(ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemSetting(disableBoneMeal);
    }

    public boolean isBonemealDisabled() {
        return disableBoneMeal.getValue();
    }
}