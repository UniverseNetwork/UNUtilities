package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Machines;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Objects.AutoCrafter;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class AutoMagicWorkbench extends AutoCrafter {
    public AutoMagicWorkbench(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe, "&6Auto Magic Workbench", Material.BOOKSHELF, "&6Magic Workbench", RecipeType.MAGIC_WORKBENCH);
    }
}