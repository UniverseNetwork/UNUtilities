package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Misc;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;

public class MobDropItem extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem implements io.github.thebusybiscuit.slimefun4.core.attributes.RandomMobDrop {
    final ItemSetting<Boolean> dropSetting = new ItemSetting<>(this, "drop-from-mob", true);
    int dropChance = 0;

    public MobDropItem(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe, int dropChance) {
        super(itemGroup, item, recipeType, recipe);
        this.dropChance = dropChance;
        addItemSetting(dropSetting);
    }

    public boolean isDroppedFromMob() {
        return dropSetting.getValue();
    }

    @Override
    public int getMobDropChance() {
        return dropChance;
    }
}