package id.universenetwork.utilities.bukkit.features.SlimefunAddons.ExtraHeads;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

public class MobHead extends SlimefunItem {
    Runnable runnable;

    public MobHead(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack recipe) {
        super(itemGroup, item, recipeType, new ItemStack[]{null, null, null, null, recipe, null, null, null, null});
    }

    public void register(ExtraHeads addon, Runnable runnable) {
        this.runnable = runnable;
        register(addon);
    }

    @Override
    public void postRegister() {
        super.postRegister();
        if (!isDisabled()) runnable.run();
    }
}