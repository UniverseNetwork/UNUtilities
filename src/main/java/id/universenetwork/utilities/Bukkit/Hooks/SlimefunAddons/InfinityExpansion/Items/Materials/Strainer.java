package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Materials;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Groups.Groups;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import org.jetbrains.annotations.Nullable;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public final class Strainer extends SlimefunItem implements NotPlaceable {
    static final NamespacedKey KEY = new NamespacedKey(plugin, "strainer_speed");

    public Strainer(SlimefunItemStack item, ItemStack[] recipe, int speed) {
        super(Groups.BASIC_MACHINES, item, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(KEY, PersistentDataType.INTEGER, speed);
        item.setItemMeta(meta);
    }

    public static int getStrainer(@Nullable ItemStack item) {
        if (item != null && item.hasItemMeta())
            return item.getItemMeta().getPersistentDataContainer().getOrDefault(Strainer.KEY, PersistentDataType.INTEGER, 0);
        return 0;
    }
}