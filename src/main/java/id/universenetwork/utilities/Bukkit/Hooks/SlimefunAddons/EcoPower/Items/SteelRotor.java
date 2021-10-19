package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.EcoPower.Items;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SteelRotor extends SimpleSlimefunItem<ItemUseHandler> implements NotPlaceable {
    public SteelRotor(ItemGroup itemGroup, SlimefunItemStack item, ItemStack[] recipe, ItemStack recipeOutput) {
        super(itemGroup, item, RecipeType.ENHANCED_CRAFTING_TABLE, recipe, recipeOutput);
    }

    @NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return PlayerRightClickEvent::cancel;
    }
}