package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Items;

import dev.j3fftw.extrautils.objects.NonHopperableBlock;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.items.*;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.HologramOwner;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

import java.util.List;

public class Barrel extends NonHopperableBlock implements HologramOwner {
    final int[] inputBorder = {9, 10, 11, 12, 18, 21, 27, 28, 29, 30};
    final int[] outputBorder = {14, 15, 16, 17, 23, 26, 32, 33, 34, 35};
    final int[] plainBorder = {0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 36, 37, 38, 39, 40, 41, 42, 43, 44};
    final int[] INPUT_SLOTS = {19, 20};
    final int[] OUTPUT_SLOTS = {24, 25};
    final int STATUS_SLOT = 22;
    final int DISPLAY_SLOT = 31;
    final int HOLOGRAM_TOGGLE_SLOT = 36;
    final int INSERT_ALL_SLOT = 43;
    final int EXTRACT_ALL_SLOT = 44;
    public static final int SMALL_BARREL_SIZE = 17280; // 5 Double chests
    public static final int MEDIUM_BARREL_SIZE = 34560; // 10 Double chests
    public static final int BIG_BARREL_SIZE = 69120; // 20 Double chests
    public static final int LARGE_BARREL_SIZE = 138240; // 40 Double chests
    public static final int MASSIVE_BARREL_SIZE = 276480; // 80 Double chests
    public static final int BOTTOMLESS_BARREL_SIZE = 1728000; // 500 Double chests
    final int OVERFLOW_AMOUNT = 3240;
    final int MAX_STORAGE;
    final ItemSetting<Boolean> showHologram = new ItemSetting<>(this, "show-hologram", true);
    final ItemSetting<Boolean> breakOnlyWhenEmpty = new ItemSetting<>(this, "break-only-when-empty", false);

    public Barrel(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, String name, int MAX_STORAGE) {
        super(itemGroup, item, recipeType, recipe);
        this.MAX_STORAGE = MAX_STORAGE;
        new BlockMenuPreset(getId(), name) {
            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public void newInstance(@NotNull BlockMenu menu, @NotNull Block b) {
                // Initialize an empty barrel
                if (BlockStorage.getLocationInfo(b.getLocation(), "stored") == null) {
                    menu.replaceExistingItem(STATUS_SLOT, new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, "&6Items Stored: &e0" + " / " + MAX_STORAGE, "&70%"));
                    menu.replaceExistingItem(DISPLAY_SLOT, new CustomItemStack(Material.BARRIER, "&cEmpty"));
                    BlockStorage.addBlockInfo(b, "stored", "0");
                    if (showHologram.getValue()) updateHologram(b, "&cEmpty");

                    // Change hologram settings
                } else if (!showHologram.getValue()) removeHologram(b);

                // Every time setup
                menu.addMenuClickHandler(STATUS_SLOT, ChestMenuUtils.getEmptyClickHandler());
                menu.addMenuClickHandler(DISPLAY_SLOT, ChestMenuUtils.getEmptyClickHandler());

                // Toggle hologram (Dynamic button)
                String holo = BlockStorage.getLocationInfo(b.getLocation(), "holo");
                if (holo == null || holo.equals("true"))
                    menu.replaceExistingItem(HOLOGRAM_TOGGLE_SLOT, new CustomItemStack(Material.QUARTZ_SLAB, "&3Toggle Hologram &a(On)"));
                else
                    menu.replaceExistingItem(HOLOGRAM_TOGGLE_SLOT, new CustomItemStack(Material.QUARTZ_SLAB, "&3Toggle Hologram &c(Off)"));
                menu.addMenuClickHandler(HOLOGRAM_TOGGLE_SLOT, (pl, slot, item, action) -> {
                    toggleHolo(b);
                    return false;
                });

                // Insert all
                menu.replaceExistingItem(INSERT_ALL_SLOT, new CustomItemStack(Material.CYAN_STAINED_GLASS_PANE, "&bInsert All", "&7> Click here to insert all", "&7compatible items into the barrel"));
                menu.addMenuClickHandler(INSERT_ALL_SLOT, (pl, slot, item, action) -> {
                    insertAll(pl, menu, b);
                    return false;
                });

                // Extract all
                menu.replaceExistingItem(EXTRACT_ALL_SLOT, new CustomItemStack(Material.ORANGE_STAINED_GLASS_PANE, "&6Extract All", "&7> Click here to extract", "&7all items to your inventory"));
                menu.addMenuClickHandler(EXTRACT_ALL_SLOT, (pl, slot, item, action) -> {
                    extractAll(pl, menu, b);
                    return false;
                });
            }

            @Override
            public boolean canOpen(@NotNull Block b, @NotNull Player p) {
                return Utils.canOpen(b, p);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow itemTransportFlow) {
                return new int[0];
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(DirtyChestMenu menu, ItemTransportFlow flow, ItemStack item) {
                if (flow == ItemTransportFlow.INSERT) return INPUT_SLOTS;
                else if (flow == ItemTransportFlow.WITHDRAW) return OUTPUT_SLOTS;
                else return new int[0];
            }
        };
        addItemHandler(onBreak());
        addItemSetting(showHologram, breakOnlyWhenEmpty);
    }

    ItemHandler onBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(@NotNull BlockBreakEvent e, @NotNull ItemStack item, @NotNull List<ItemStack> drops) {
                Block b = e.getBlock();
                Player p = e.getPlayer();
                BlockMenu inv = BlockStorage.getInventory(b);
                int stored = getStored(b);
                if (inv != null) {
                    int itemCount = 0;
                    if (breakOnlyWhenEmpty.getValue() && stored != 0) {
                        Utils.send(p, "&cThis barrel can't be broken since it has items inside it!");
                        e.setCancelled(true);
                        return;
                    }
                    for (Entity en : p.getNearbyEntities(5, 5, 5)) if (en instanceof Item) itemCount++;
                    if (itemCount > 5) {
                        Utils.send(p, "&cPlease remove nearby items before breaking this barrel!");
                        e.setCancelled(true);
                        return;
                    }
                    inv.dropItems(b.getLocation(), INPUT_SLOTS);
                    inv.dropItems(b.getLocation(), OUTPUT_SLOTS);
                    if (stored > 0) {
                        int stackSize = inv.getItemInSlot(DISPLAY_SLOT).getMaxStackSize();
                        ItemStack unKeyed = Utils.unKeyItem(inv.getItemInSlot(DISPLAY_SLOT));
                        if (stored > OVERFLOW_AMOUNT) {
                            Utils.send(p, "&eThere are more than " + OVERFLOW_AMOUNT + " items in this barrel! " + "Dropping " + OVERFLOW_AMOUNT + " items instead!");
                            int toRemove = OVERFLOW_AMOUNT;
                            while (toRemove >= stackSize) {
                                b.getWorld().dropItemNaturally(b.getLocation(), new CustomItemStack(unKeyed, stackSize));
                                toRemove = toRemove - stackSize;
                            }
                            if (toRemove > 0)
                                b.getWorld().dropItemNaturally(b.getLocation(), new CustomItemStack(unKeyed, toRemove));
                            BlockStorage.addBlockInfo(b, "stored", String.valueOf(stored - OVERFLOW_AMOUNT));
                            updateMenu(b, inv, true);
                            e.setCancelled(true);
                        } else {
                            // Everything greater than 1 stack
                            while (stored >= stackSize) {
                                b.getWorld().dropItemNaturally(b.getLocation(), new CustomItemStack(unKeyed, stackSize));
                                stored = stored - stackSize;
                            }

                            // Drop remaining, if there is any
                            if (stored > 0)
                                b.getWorld().dropItemNaturally(b.getLocation(), new CustomItemStack(unKeyed, stored));

                            // In case they use an explosive pick
                            BlockStorage.addBlockInfo(b, "stored", "0");
                            updateMenu(b, inv, true);
                            removeHologram(b);
                        }
                    } else removeHologram(b);
                }
            }
        };
    }

    protected void constructMenu(BlockMenuPreset preset) {
        for (int i : outputBorder)
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.ORANGE_STAINED_GLASS_PANE), " "), (p, slot, item, action) -> false);
        for (int i : inputBorder)
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.CYAN_STAINED_GLASS_PANE), " "), (p, slot, item, action) -> false);
        for (int i : plainBorder)
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), " "), (p, slot, item, action) -> false);
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {
            @Override
            public void tick(Block b, SlimefunItem sf, Config data) {
                Barrel.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return true;
            }
        });
    }

    protected void tick(Block b) {
        BlockMenu inv = BlockStorage.getInventory(b);
        for (int slot : INPUT_SLOTS)
            if (inv.getItemInSlot(slot) != null) {
                int stored = getStored(b);
                ItemStack item = inv.getItemInSlot(slot);
                if (stored == 0) registerItem(b, inv, slot, item, stored);
                else if (stored > 0 && inv.getItemInSlot(DISPLAY_SLOT) != null && matchMeta(Utils.unKeyItem(inv.getItemInSlot(DISPLAY_SLOT)), item) && stored < MAX_STORAGE) {
                    // Can fit entire itemstack
                    if (stored + item.getAmount() <= MAX_STORAGE)
                        storeItem(b, inv, slot, item, stored);
                        // Split itemstack
                    else {
                        int amount = MAX_STORAGE - stored;
                        inv.consumeItem(slot, amount);
                        BlockStorage.addBlockInfo(b, "stored", String.valueOf((stored + amount)));
                        updateMenu(b, inv, false);
                    }
                }
            }
        for (int i = 0; i < OUTPUT_SLOTS.length; i++) {
            if (inv.getItemInSlot(DISPLAY_SLOT) != null && inv.getItemInSlot(DISPLAY_SLOT).getType() != Material.BARRIER) {
                int stored = getStored(b);
                ItemStack item = inv.getItemInSlot(DISPLAY_SLOT);
                if (stored > inv.getItemInSlot(DISPLAY_SLOT).getMaxStackSize()) {
                    ItemStack clone = new CustomItemStack(Utils.unKeyItem(item), item.getMaxStackSize());
                    if (inv.fits(clone, OUTPUT_SLOTS)) {
                        int amount = clone.getMaxStackSize();
                        BlockStorage.addBlockInfo(b, "stored", String.valueOf((stored - amount)));
                        inv.pushItem(clone, OUTPUT_SLOTS);
                        updateMenu(b, inv, false);
                    }
                } else if (stored != 0) {
                    ItemStack clone = new CustomItemStack(Utils.unKeyItem(item), stored);
                    if (inv.fits(clone, OUTPUT_SLOTS)) {
                        BlockStorage.addBlockInfo(b, "stored", "0");
                        inv.pushItem(clone, OUTPUT_SLOTS);
                        updateMenu(b, inv, false);
                    }
                }
            }
        }
    }

    void registerItem(Block b, BlockMenu inv, int slot, ItemStack item, int stored) {
        int amount = item.getAmount();
        inv.replaceExistingItem(DISPLAY_SLOT, new CustomItemStack(Utils.keyItem(item), 1));
        inv.consumeItem(slot, amount);
        BlockStorage.addBlockInfo(b, "stored", String.valueOf((stored + amount)));
        updateMenu(b, inv, false);
    }

    void storeItem(Block b, BlockMenu inv, int slot, ItemStack item, int stored) {
        int amount = item.getAmount();
        inv.consumeItem(slot, amount);
        BlockStorage.addBlockInfo(b, "stored", String.valueOf((stored + amount)));
        updateMenu(b, inv, false);
    }

    boolean matchMeta(ItemStack item1, ItemStack item2) {
        // It seems the meta comparisons are heavier than type checks
        return item1.getType().equals(item2.getType()) && item1.getItemMeta().equals(item2.getItemMeta());
    }

    void updateMenu(Block b, BlockMenu inv, boolean force) {
        String hasHolo = BlockStorage.getLocationInfo(b.getLocation(), "holo");
        int stored = getStored(b);
        String itemName;
        String storedPercent = doubleRoundAndFade((double) stored / (double) MAX_STORAGE * 100);
        String storedStacks = doubleRoundAndFade((double) stored / (double) inv.getItemInSlot(DISPLAY_SLOT).getMaxStackSize());

        // This helps a bit with lag, but may have visual impacts
        if (inv.hasViewer() || force)
            inv.replaceExistingItem(STATUS_SLOT, new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, "&6Items Stored: &e" + stored + " / " + MAX_STORAGE, "&b" + storedStacks + " Stacks &8| &7" + storedPercent + "&7%"));
        if (inv.getItemInSlot(DISPLAY_SLOT) != null && inv.getItemInSlot(DISPLAY_SLOT).getItemMeta().hasDisplayName())
            itemName = inv.getItemInSlot(DISPLAY_SLOT).getItemMeta().getDisplayName();
        else itemName = WordUtils.capitalizeFully(inv.getItemInSlot(DISPLAY_SLOT).getType().name().replace("_", " "));
        if (showHologram.getValue() && (hasHolo == null || hasHolo.equals("true")))
            updateHologram(b, itemName + " &9x" + stored + " &7(" + storedPercent + "&7%)");
        if (stored == 0) {
            inv.replaceExistingItem(DISPLAY_SLOT, new CustomItemStack(Material.BARRIER, "&cEmpty"));
            if (showHologram.getValue() && (hasHolo == null || hasHolo.equals("true"))) updateHologram(b, "&cEmpty");
        }
    }

    void toggleHolo(Block b) {
        String toggle = BlockStorage.getLocationInfo(b.getLocation(), "holo");
        BlockMenu menu = BlockStorage.getInventory(b);
        if (toggle == null || toggle.equals("true")) {
            BlockStorage.addBlockInfo(b.getLocation(), "holo", "false");
            menu.replaceExistingItem(HOLOGRAM_TOGGLE_SLOT, new CustomItemStack(Material.QUARTZ_SLAB, "&3Toggle Hologram &c(Off)"));
            removeHologram(b);
        } else {
            BlockStorage.addBlockInfo(b.getLocation(), "holo", "true");
            menu.replaceExistingItem(HOLOGRAM_TOGGLE_SLOT, new CustomItemStack(Material.QUARTZ_SLAB, "&3Toggle Hologram &a(On)"));
            updateMenu(b, BlockStorage.getInventory(b), false);
        }
    }

    public void insertAll(Player p, BlockMenu menu, Block b) {
        ItemStack storedItem = Utils.unKeyItem(menu.getItemInSlot(DISPLAY_SLOT));
        PlayerInventory inv = p.getInventory();
        int stored = getStored(b);
        for (int i = 0; i < inv.getContents().length; i++) {
            ItemStack item = inv.getItem(i);
            if (item == null) continue;
            int amount = item.getAmount();
            if (matchMeta(item, storedItem) && stored + amount <= MAX_STORAGE) {
                inv.setItem(i, null);
                stored += amount;
            }
        }
        BlockStorage.addBlockInfo(b.getLocation(), "stored", String.valueOf(stored));
        updateMenu(b, menu, false);
    }

    public void extractAll(Player p, BlockMenu menu, Block b) {
        ItemStack storedItem = Utils.unKeyItem(menu.getItemInSlot(DISPLAY_SLOT));
        PlayerInventory inv = p.getInventory();
        ItemStack[] contents = inv.getStorageContents().clone();
        int stored = getStored(b);
        int maxStackSize = storedItem.getMaxStackSize();
        int outI = 0;
        for (int i = 0; i < contents.length; i++)
            if (contents[i] == null) {
                if (stored >= maxStackSize) {
                    inv.setItem(i, new CustomItemStack(storedItem, maxStackSize));
                    stored -= maxStackSize;
                } else if (stored > 0) {
                    inv.setItem(i, new CustomItemStack(storedItem, stored));
                    stored = 0;
                } else {
                    if (outI > 1) break;
                    ItemStack item = menu.getItemInSlot(OUTPUT_SLOTS[outI]);
                    if (item == null) continue;
                    inv.setItem(i, item.clone());
                    menu.replaceExistingItem(OUTPUT_SLOTS[outI], null);
                    outI++;
                }
            }
        BlockStorage.addBlockInfo(b.getLocation(), "stored", String.valueOf(stored));
        updateMenu(b, menu, false);
    }

    public static String doubleRoundAndFade(double num) {
        // Using same format that is used on lore power
        String formattedString = Utils.powerFormat.format(num);
        if (formattedString.indexOf('.') != -1)
            return formattedString.substring(0, formattedString.indexOf('.')) + ChatColor.DARK_GRAY + formattedString.substring(formattedString.indexOf('.')) + ChatColor.GRAY;
        else return formattedString;
    }

    int getStored(Block b) {
        return Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "stored"));
    }

    @NotNull
    @Override
    public Vector getHologramOffset(@NotNull Block b) {
        return new Vector(0.5, 0.9, 0.5);
    }
}