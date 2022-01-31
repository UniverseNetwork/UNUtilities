package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;

/**
 * An essential starting crafting ingredient
 * dropped by slimes as a {@link io.github.thebusybiscuit.slimefun4.core.attributes.RandomMobDrop}
 *
 * @author NCBPFluffyBear
 */
public class MagicCream extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<ItemUseHandler> implements io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable, io.github.thebusybiscuit.slimefun4.core.attributes.RandomMobDrop {
    final ItemSetting<Integer> dropChance = new ItemSetting<>(this, "drop-chance", 50);

    public MagicCream(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup category, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
        addItemSetting(dropChance);
    }

    @Override
    public int getMobDropChance() {
        return dropChance.getValue();
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent::cancel;
    }
}