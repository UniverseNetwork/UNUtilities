package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ChestTerminal.Items;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuClickHandler;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.getCustomHead;
import static org.bukkit.Material.*;

public class AccessTerminal extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<BlockTicker> {
    final int[] terminalSlots = {0, 1, 2, 3, 4, 5, 6, 9, 10, 11, 12, 13, 14, 15, 18, 19, 20, 21, 22, 23, 24, 27, 28, 29, 30, 31, 32, 33, 36, 37, 38, 39, 40, 41, 42};

    public AccessTerminal(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        new BlockMenuPreset(getId(), "&3CT Access Terminal") {
            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public void newInstance(BlockMenu menu, @NotNull Block b) {
                menu.replaceExistingItem(46, new CustomItemStack(getCustomHead("f2599bd986659b8ce2c4988525c94e19ddd39fad08a38284a197f1b70675acc"), "&7\u21E6 Previous Page", "", "&c(This may take up to a Second to update)"));
                menu.addMenuClickHandler(46, (p, slot, item, action) -> {
                    int page = getPage(b) - 1;
                    if (page > 0) {
                        BlockStorage.addBlockInfo(b, "page", String.valueOf(page));
                        newInstance(menu, b);
                    }
                    return false;
                });

                menu.replaceExistingItem(50, new CustomItemStack(getCustomHead("c2f910c47da042e4aa28af6cc81cf48ac6caf37dab35f88db993accb9dfe516"), "&7Next Page \u21E8", "", "&c(This may take up to a Second to update)"));
                menu.addMenuClickHandler(50, (p, slot, item, action) -> {
                    int page = getPage(b) + 1;
                    BlockStorage.addBlockInfo(b, "page", String.valueOf(page));
                    newInstance(menu, b);
                    return false;
                });
            }

            int getPage(Block b) {
                String page = BlockStorage.getLocationInfo(b.getLocation(), "page");
                return page == null ? 1 : Integer.parseInt(page);
            }

            @Override
            public boolean canOpen(Block b, org.bukkit.entity.Player p) {
                return io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getProtectionManager().hasPermission(p, b.getLocation(), io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow flow) {
                return new int[0];
            }
        };
        addItemHandler(new CTBlockBreakHandler(new int[]{17, 44}));
        addItemHandler(new io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler(true) {
            @Override
            public void onPlayerPlace(@NotNull org.bukkit.event.block.BlockPlaceEvent e) {
                BlockStorage.addBlockInfo(e.getBlock(), "page", "1");
            }

            @Override
            public void onBlockPlacerPlace(@NotNull io.github.thebusybiscuit.slimefun4.api.events.BlockPlacerPlaceEvent e) {
                BlockStorage.addBlockInfo(e.getBlock(), "page", "1");
            }

        });
    }

    protected void constructMenu(BlockMenuPreset preset) {
        MenuClickHandler click = (p, slot, item, action) -> false;
        preset.addItem(45, new CustomItemStack(BLACK_STAINED_GLASS_PANE, " "), click);
        preset.addItem(46, new CustomItemStack(RED_STAINED_GLASS_PANE, "This will update shortly"));
        preset.addItem(47, new CustomItemStack(BLACK_STAINED_GLASS_PANE, " "), click);
        preset.addItem(48, new CustomItemStack(BLACK_STAINED_GLASS_PANE, " "), click);
        preset.addItem(49, new CustomItemStack(BLACK_STAINED_GLASS_PANE, " "), click);
        preset.addItem(50, new CustomItemStack(RED_STAINED_GLASS_PANE, "This will update shortly"));
        preset.addItem(51, new CustomItemStack(BLACK_STAINED_GLASS_PANE, " "), click);
        preset.addItem(7, new CustomItemStack(CYAN_STAINED_GLASS_PANE, " "), click);
        preset.addItem(8, new CustomItemStack(CYAN_STAINED_GLASS_PANE, " "), click);
        preset.addItem(16, new CustomItemStack(CYAN_STAINED_GLASS_PANE, " "), click);
        preset.addItem(25, new CustomItemStack(CYAN_STAINED_GLASS_PANE, " "), click);
        preset.addItem(26, new CustomItemStack(CYAN_STAINED_GLASS_PANE, " "), click);
        preset.addItem(34, new CustomItemStack(ORANGE_STAINED_GLASS_PANE, " "), click);
        preset.addItem(35, new CustomItemStack(ORANGE_STAINED_GLASS_PANE, " "), click);
        preset.addItem(43, new CustomItemStack(ORANGE_STAINED_GLASS_PANE, " "), click);
        preset.addItem(52, new CustomItemStack(ORANGE_STAINED_GLASS_PANE, " "), click);
        preset.addItem(53, new CustomItemStack(ORANGE_STAINED_GLASS_PANE, " "), click);
    }

    @Override
    @NotNull
    public BlockTicker getItemHandler() {
        return new BlockTicker() {
            final ItemStack item = new CustomItemStack(BARRIER, "&4No Cargo Net connected!");
            final MenuClickHandler click = (p, slot, stack, action) -> false;

            @Override
            public void tick(Block b, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem sf, me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config data) {
                if (io.github.thebusybiscuit.slimefun4.core.networks.cargo.CargoNet.getNetworkFromLocation(b.getLocation()) == null) {
                    BlockMenu menu = BlockStorage.getInventory(b);
                    for (int slot : terminalSlots) {
                        menu.replaceExistingItem(slot, item);
                        menu.addMenuClickHandler(slot, click);
                    }
                }
            }

            @Override
            public boolean isSynchronized() {
                return true;
            }
        };
    }
}