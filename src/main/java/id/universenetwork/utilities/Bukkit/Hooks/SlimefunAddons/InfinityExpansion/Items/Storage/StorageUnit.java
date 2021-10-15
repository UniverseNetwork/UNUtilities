package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Storage;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Groups.Groups;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.PersistentType;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.MenuBlock;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Scheduler.run;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

@ParametersAreNonnullByDefault
public final class StorageUnit extends MenuBlock {
    /* Namespaced keys */
    static final NamespacedKey EMPTY_KEY = new NamespacedKey(plugin, "empty"); // key for empty item
    static final NamespacedKey DISPLAY_KEY = new NamespacedKey(plugin, "display"); // key for display item
    static final NamespacedKey ITEM_KEY = new NamespacedKey(plugin, "item"); // item key for item pdc
    static final NamespacedKey AMOUNT_KEY = new NamespacedKey(plugin, "stored"); // amount key for item pdc

    /* Menu slots */
    static final int INPUT_SLOT = 10;
    static final int DISPLAY_SLOT = 13;
    static final int STATUS_SLOT = 4;
    static final int OUTPUT_SLOT = 16;
    static final int INTERACT_SLOT = 22;

    /* Menu items */
    static final ItemStack INTERACTION_ITEM = new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, "&aQuick Actions", "&bLeft Click: &7Withdraw 1 item", "&bRight Click: &7Withdraw 1 stack", "&bShift Left Click: &7Deposit inventory", "&bShift Right Click: &7Withdraw inventory");
    static final ItemStack LOADING_ITEM = new CustomItemStack(Material.CYAN_STAINED_GLASS_PANE, "&bStatus", "&7Loading...");

    /* Instance constants */
    final Map<Location, StorageCache> caches = new HashMap<>();
    final int max;

    public StorageUnit(SlimefunItemStack item, int max, ItemStack[] recipe) {
        super(Groups.STORAGE, item, StorageForge.TYPE, recipe);
        this.max = max;
        addItemHandler(new BlockTicker() {
            @Override
            public boolean isSynchronized() {
                return true;
            }

            @Override
            public void tick(Block b, SlimefunItem item, Config data) {
                StorageCache cache = caches.get(b.getLocation());
                if (cache != null) cache.tick(b);
            }
        }, new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                BlockMenu menu = BlockStorage.getInventory(e.getBlock());
                StorageCache cache = caches.remove(menu.getLocation());
                if (cache != null && !cache.isEmpty()) cache.destroy(menu.getLocation(), e, drops);
                else drops.add(getItem().clone());
                menu.dropItems(menu.getLocation(), INPUT_SLOT, OUTPUT_SLOT);
            }
        });
    }

    @Override
    protected void onNewInstance(@NotNull BlockMenu menu, @NotNull Block b) {
        if (BlockStorage.getInventory(b) == menu) caches.put(b.getLocation(), new StorageCache(this, menu));
    }

    @NotNull
    @Override
    public Collection<ItemStack> getDrops() {
        return Collections.emptyList();
    }

    @Override
    protected void onPlace(@NotNull BlockPlaceEvent e, @NotNull Block b) {
        Pair<ItemStack, Integer> data = loadFromStack(e.getItemInHand());
        if (data != null) run(() -> {
            StorageCache cache = caches.get(b.getLocation());
            cache.load(data.getFirstValue(), data.getFirstValue().getItemMeta());
            cache.amount(data.getSecondValue());
        });
    }

    @Override
    protected void setup(@NotNull BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(INPUT_BORDER, new int[]{0, 1, 2, 9, 11, 18, 19, 20});
        blockMenuPreset.drawBackground(BACKGROUND_ITEM, new int[]{3, 5, 12, 14, 21, 23});
        blockMenuPreset.drawBackground(OUTPUT_BORDER, new int[]{6, 7, 8, 15, 17, 24, 25, 26});
        blockMenuPreset.addMenuClickHandler(DISPLAY_SLOT, ChestMenuUtils.getEmptyClickHandler());
        blockMenuPreset.addItem(INTERACT_SLOT, INTERACTION_ITEM);
        blockMenuPreset.addItem(STATUS_SLOT, LOADING_ITEM);
    }

    @NotNull
    @Override
    protected int[] getInputSlots(DirtyChestMenu dirtyChestMenu, ItemStack itemStack) {
        StorageCache cache = caches.get(((BlockMenu) dirtyChestMenu).getLocation());
        if (cache != null && (cache.isEmpty() || cache.matches(itemStack))) {
            cache.input();
            return new int[]{INPUT_SLOT};
        } else return new int[0];
    }

    @Override
    protected int[] getInputSlots() {
        return new int[]{INPUT_SLOT};
    }

    @Override
    protected int[] getOutputSlots() {
        return new int[]{OUTPUT_SLOT};
    }

    public void reloadCache(Block b) {
        caches.get(b.getLocation()).reloadData();
    }

    static void transferToStack(@NotNull ItemStack source, @NotNull ItemStack target) {
        Pair<ItemStack, Integer> data = loadFromStack(source);
        if (data != null)
            target.setItemMeta(saveToStack(target.getItemMeta(), data.getFirstValue(), ItemUtils.getItemName(data.getFirstValue()), data.getSecondValue()));
    }

    static ItemMeta saveToStack(ItemMeta meta, ItemStack displayItem, String displayName, int amount) {
        if (meta.hasLore()) {
            List<String> lore = meta.getLore();
            lore.add(ChatColor.GOLD + "Stored: " + displayName + ChatColor.YELLOW + " x " + amount);
            meta.setLore(lore);
        }
        meta.getPersistentDataContainer().set(ITEM_KEY, PersistentType.ITEM_STACK_OLD, displayItem);
        meta.getPersistentDataContainer().set(AMOUNT_KEY, PersistentDataType.INTEGER, amount);
        return meta;
    }

    @Nullable
    static Pair<ItemStack, Integer> loadFromStack(ItemStack source) {
        if (source.hasItemMeta()) {
            PersistentDataContainer con = source.getItemMeta().getPersistentDataContainer();
            Integer amount = con.get(AMOUNT_KEY, PersistentDataType.INTEGER);
            if (amount != null) {
                ItemStack item = con.get(ITEM_KEY, PersistentType.ITEM_STACK_OLD);
                if (item != null) return new Pair<>(item, amount);
            }
        }
        return null;
    }
}