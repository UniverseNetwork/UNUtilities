package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ChestTerminal.Items;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public class ExportBus extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem {
    static final int[] border = {0, 1, 3, 4, 5, 6, 9, 10, 11, 12, 13, 14, 15, 18, 22, 24, 27, 31, 33, 34, 35, 36, 40, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 32, 23, 41};

    public ExportBus(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        new BlockMenuPreset(getId(), "&3CT Export Bus") {
            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public boolean canOpen(Block b, org.bukkit.entity.Player p) {
                String owner = BlockStorage.getLocationInfo(b.getLocation(), "owner");
                return (owner != null && owner.equals(p.getUniqueId().toString())) || p.hasPermission("slimefun.cargo.bypass");
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow flow) {
                return new int[0];
            }
        };
        addItemHandler(new CTBlockBreakHandler(getInputSlots()));
        addItemHandler(new io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(@NotNull org.bukkit.event.block.BlockPlaceEvent e) {
                Block b = e.getBlock();
                BlockStorage.addBlockInfo(b, "owner", e.getPlayer().getUniqueId().toString());
                BlockStorage.addBlockInfo(b, "index", "0");
                BlockStorage.addBlockInfo(b, "filter-type", "whitelist");
                BlockStorage.addBlockInfo(b, "filter-lore", "true");
                BlockStorage.addBlockInfo(b, "filter-durability", "true");
            }
        });
    }

    protected void constructMenu(BlockMenuPreset preset) {
        me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuClickHandler click = (p, slot, item, action) -> false;
        for (int i : border) preset.addItem(i, new CustomItemStack(Material.CYAN_STAINED_GLASS_PANE, " "), click);
        preset.addItem(7, new CustomItemStack(Material.BLUE_STAINED_GLASS_PANE, " "), click);
        preset.addItem(8, new CustomItemStack(Material.BLUE_STAINED_GLASS_PANE, " "), click);
        preset.addItem(16, new CustomItemStack(Material.BLUE_STAINED_GLASS_PANE, " "), click);
        preset.addItem(25, new CustomItemStack(Material.BLUE_STAINED_GLASS_PANE, " "), click);
        preset.addItem(26, new CustomItemStack(Material.BLUE_STAINED_GLASS_PANE, " "), click);
        preset.addItem(2, new CustomItemStack(Material.PAPER, "&3Items", "", "&bPut in all Items you want to", "&bwhitelist"), click);
    }

    public int[] getInputSlots() {
        return new int[]{19, 20, 21, 28, 29, 30, 37, 38, 39};
    }
}