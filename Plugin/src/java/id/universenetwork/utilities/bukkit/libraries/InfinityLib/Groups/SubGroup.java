package id.universenetwork.utilities.bukkit.libraries.InfinityLib.Groups;

import id.universenetwork.utilities.bukkit.UNUtilities;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import org.bukkit.inventory.ItemStack;

/**
 * A category that is hidden from the main guide page
 *
 * @author Mooy1
 * @author ARVIN3108 ID
 */
public final class SubGroup extends ItemGroup {
    public SubGroup(String key, ItemStack item) {
        this(key, item, 3);
    }

    public SubGroup(String key, ItemStack item, int tier) {
        super(UNUtilities.createKey(key), item, tier);
    }

    @Override
    public boolean isHidden(org.bukkit.entity.Player p) {
        return true;
    }
}