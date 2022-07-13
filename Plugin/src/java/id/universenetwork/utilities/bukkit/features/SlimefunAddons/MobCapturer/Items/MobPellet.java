package id.universenetwork.utilities.bukkit.features.SlimefunAddons.MobCapturer.Items;

import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;

public class MobPellet extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<ItemUseHandler> implements io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable {
    public MobPellet(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe, new io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack(item, 2));
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent::cancel;
    }
}