package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PrivateStorage;

import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SlimefunChest extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem {
    public SlimefunChest(ChestProtectionLevel level, int size, boolean canExplode, io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        int[] slots = getSlotsArray(size);
        new me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset(getId(), item.getItemMeta().getDisplayName()) {
            @Override
            public void init() {
                setSize(size);
                addMenuOpeningHandler(p -> p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 1.8F, 1.6F));
                addMenuCloseHandler(p -> p.playSound(p.getLocation(), Sound.BLOCK_CHEST_CLOSE, 1.8F, 1.6F));
            }

            @Override
            public boolean canOpen(Block b, Player p) {
                if (p.hasPermission("PrivateStorage.bypass")) return true;
                if (level == ChestProtectionLevel.PRIVATE)
                    return BlockStorage.getLocationInfo(b.getLocation(), "owner").equals(p.getUniqueId().toString());
                return io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getProtectionManager().hasPermission(p, b, io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow flow) {
                if (level.equals(ChestProtectionLevel.PUBLIC)) return slots;
                else return new int[0];
            }
        };
        addItemHandler(onBlockPlace(), onBlockBreak(size, canExplode));
    }

    BlockPlaceHandler onBlockPlace() {
        return new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(org.bukkit.event.block.BlockPlaceEvent e) {
                BlockStorage.addBlockInfo(e.getBlock().getLocation(), "owner", e.getPlayer().getUniqueId().toString());
            }
        };
    }

    BlockBreakHandler onBlockBreak(int size, boolean canExplode) {
        return new BlockBreakHandler(false, canExplode) {
            @Override
            public void onPlayerBreak(org.bukkit.event.block.BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                boolean allow = true;
                Player p = e.getPlayer();
                Block b = e.getBlock();
                if (!p.hasPermission("PrivateStorage.bypass"))
                    allow = java.util.Objects.equals(BlockStorage.getLocationInfo(b.getLocation(), "owner"), p.getUniqueId().toString());
                if (allow) {
                    BlockMenu inv = BlockStorage.getInventory(b);
                    for (int slot = 0; slot < size; slot++) {
                        ItemStack stack = inv.getItemInSlot(slot);
                        if (stack != null && !stack.getType().isAir())
                            b.getWorld().dropItemNaturally(b.getLocation(), stack);
                    }
                } else e.setCancelled(true);
            }

            @Override
            public void onExplode(Block b, List<ItemStack> drops) {
                BlockMenu inv = BlockStorage.getInventory(b);
                for (int slot = 0; slot < size; slot++) {
                    ItemStack stack = inv.getItemInSlot(slot);
                    if (stack != null && !stack.getType().isAir()) drops.add(stack);
                }
            }
        };
    }

    int[] getSlotsArray(int size) {
        if (size == 9) return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
        else if (size == 18) return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        else if (size == 27)
            return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        else if (size == 36)
            return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};
        else if (size == 45)
            return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};
        else if (size == 54)
            return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};
        else return new int[0];
    }
}