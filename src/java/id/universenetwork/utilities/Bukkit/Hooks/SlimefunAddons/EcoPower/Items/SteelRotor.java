package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.EcoPower.Items;

import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.inventory.ItemStack;

public class SteelRotor extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<ItemUseHandler> implements io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable {
    public SteelRotor(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, ItemStack[] recipe, ItemStack recipeOutput) {
        super(itemGroup, item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE, recipe, recipeOutput);
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent::cancel;
    }
}