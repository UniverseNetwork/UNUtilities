package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Misc;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;

public class VexGem extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem implements io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable, io.github.thebusybiscuit.slimefun4.core.attributes.RandomMobDrop {
    private final ItemSetting<Boolean> dropSetting = new ItemSetting<>(this, " drop-from-vexs", true);
    private final ItemSetting<Integer> chance = new io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting(this, "vex-drop-chance", 0, 10, 100);

    public VexGem(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemSetting(dropSetting);
        addItemSetting(chance);
        addItemHandler(getItemHandler());
    }

    @Override
    public int getMobDropChance() {
        return chance.getValue();
    }

    public boolean isDroppedFromVexs() {
        return dropSetting.getValue();
    }

    public io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler getItemHandler() {
        return io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent::cancel;
    }
}