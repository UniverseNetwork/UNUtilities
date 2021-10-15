package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SlimyTreeTaps;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

abstract class RubberFactory extends AContainer implements RecipeDisplayItem {
    public RubberFactory(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @NotNull
    @Override
    public String getInventoryTitle() {
        return "Rubber Factory";
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.FLINT_AND_STEEL);
    }

    @NotNull
    @Override
    public String getMachineIdentifier() {
        return "RUBBER_FACTORY";
    }

    @Override
    public int getCapacity() {
        return 256;
    }
}