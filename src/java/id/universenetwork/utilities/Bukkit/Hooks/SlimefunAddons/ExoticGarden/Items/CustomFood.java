package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExoticGarden.Items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExoticGarden.ExoticGardenRecipeTypes.KITCHEN;

public class CustomFood extends ExoticGardenFruit {
    final int food;

    @ParametersAreNonnullByDefault
    public CustomFood(ItemGroup itemGroup, SlimefunItemStack item, ItemStack[] recipe, int food) {
        super(itemGroup, item, KITCHEN, true, recipe);
        this.food = food;
    }

    @ParametersAreNonnullByDefault
    public CustomFood(ItemGroup itemGroup, SlimefunItemStack item, int amount, ItemStack[] recipe, int food) {
        super(itemGroup, item, KITCHEN, true, recipe, new SlimefunItemStack(item, amount));
        this.food = food;
    }

    @Override
    public int getFoodValue() {
        return food;
    }
}