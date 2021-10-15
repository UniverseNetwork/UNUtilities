package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Machines;

import dev.j3fftw.extrautils.objects.NonHopperableBlock;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.HologramOwner;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class WarpPad extends NonHopperableBlock implements HologramOwner {
    public WarpPad(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(onPlace(), onUse(), onBreak());
    }

    BlockBreakHandler onBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(@NotNull BlockBreakEvent e, @NotNull ItemStack item, @NotNull List<ItemStack> drops) {
                Block b = e.getBlock();
                removeHologram(b);
            }
        };
    }

    BlockPlaceHandler onPlace() {
        return new BlockPlaceHandler(true) {
            @Override
            public void onPlayerPlace(@NotNull BlockPlaceEvent e) {
                BlockStorage.addBlockInfo(e.getBlockPlaced(), "type", "none");
                updateHologram(e.getBlockPlaced(), "&4&lX");
            }
        };
    }

    ItemHandler onUse() {
        return (BlockUseHandler) PlayerRightClickEvent::cancel;
    }
}