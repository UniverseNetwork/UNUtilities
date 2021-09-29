package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Items.Gear;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.attributes.Soulbound;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Groups.Groups.INFINITY_CHEAT;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Items.Blocks.InfinityWorkbench.TYPE;

public final class InfinityTool extends SlimefunItem implements Soulbound, NotPlaceable {
    public InfinityTool(SlimefunItemStack stack, ItemStack[] recipe) {
        super(INFINITY_CHEAT, stack, TYPE, recipe);
    }
}