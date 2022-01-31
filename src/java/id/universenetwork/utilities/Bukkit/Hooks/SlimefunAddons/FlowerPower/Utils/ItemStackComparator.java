package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Utils;

import org.bukkit.inventory.ItemStack;

/**
 * A comparator that is used to sort {@link id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.MultiBlocks.MagicBasin}
 * recipes to compare recipes
 *
 * @author NCBPFluffyBear
 */
public class ItemStackComparator implements java.util.Comparator<ItemStack> {
    @Override
    public int compare(ItemStack item1, ItemStack item2) {
        return item1.toString().compareTo(item2.toString());
    }
}