package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraHeads;

import org.bukkit.inventory.ItemStack;

class MobHead extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem {
    Runnable runnable;

    public MobHead(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack recipe) {
        super(itemGroup, item, recipeType, new ItemStack[]{null, null, null, null, recipe, null, null, null, null});
    }

    public void register(Runnable runnable) {
        this.runnable = runnable;
        register(id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon);
    }

    @Override
    public void postRegister() {
        super.postRegister();
        runnable.run();
    }
}