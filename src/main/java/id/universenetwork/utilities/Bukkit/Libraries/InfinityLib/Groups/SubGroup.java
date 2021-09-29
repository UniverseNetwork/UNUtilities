package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Groups;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

@ParametersAreNonnullByDefault
public final class SubGroup extends ItemGroup {
    public SubGroup(String key, ItemStack item) {
        this(key, item, 3);
    }

    public SubGroup(String key, ItemStack item, int tier) {
        super(new NamespacedKey(plugin, key), item, tier);
    }

    @Override
    public boolean isHidden(Player p) {
        return true;
    }
}