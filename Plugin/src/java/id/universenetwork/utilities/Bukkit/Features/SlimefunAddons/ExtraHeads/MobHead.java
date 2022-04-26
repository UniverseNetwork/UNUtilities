package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.ExtraHeads;

import org.bukkit.inventory.ItemStack;

public class MobHead extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem {
    Runnable runnable;

    public MobHead(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack recipe) {
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