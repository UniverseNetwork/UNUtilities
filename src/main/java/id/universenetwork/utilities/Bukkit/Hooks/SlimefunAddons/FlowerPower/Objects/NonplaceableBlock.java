package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Objects;

import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;

/**
 * A SlimefunItem that can not be disenchanted or placed
 *
 * @author NCBPFluffyBear
 */
public class NonplaceableBlock extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<ItemUseHandler> implements io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable {
    public NonplaceableBlock(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent::cancel;
    }

    @Override
    public boolean isDisenchantable() {
        return false;
    }
}