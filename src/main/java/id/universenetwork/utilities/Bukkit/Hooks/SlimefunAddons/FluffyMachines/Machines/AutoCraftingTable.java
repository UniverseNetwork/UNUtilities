package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Machines;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.FluffyMachines;
import io.github.thebusybiscuit.slimefun4.api.events.BlockPlacerPlaceEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AutoCraftingTable extends SlimefunItem implements EnergyNetComponent {
    public static final int ENERGY_CONSUMPTION = 128;
    public static final int CAPACITY = ENERGY_CONSUMPTION * 3;
    static final int keySlot = 16;
    static final int statusSlot = 23;
    final int[] border = {0, 1, 3, 5, 13, 14, 50, 51, 52, 53};
    final int[] inputBorder = {9, 10, 11, 12, 13, 18, 22, 27, 31, 36, 40, 45, 46, 47, 48, 49};
    final int[] outputBorder = {32, 33, 34, 35, 41, 44, 50, 51, 52, 53};
    final int[] keyBorder = {6, 7, 8, 15, 17, 24, 25, 26};

    public AutoCraftingTable(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        new BlockMenuPreset(getId(), "&6Auto Crafting Table") {
            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public void newInstance(@NotNull BlockMenu menu, @NotNull Block b) {
                if (!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), "enabled") == null || BlockStorage.getLocationInfo(b.getLocation(), "enabled").equals(String.valueOf(false))) {
                    menu.replaceExistingItem(4, new CustomItemStack(Material.GUNPOWDER, "&7Enabled: &4\u2718", "", "&e> Click to enable this Machine"));
                    menu.replaceExistingItem(statusSlot, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), "&7&lDisabled"));
                    menu.addMenuClickHandler(4, (p, slot, item, action) -> {
                        BlockStorage.addBlockInfo(b, "enabled", String.valueOf(true));
                        newInstance(menu, b);
                        return false;
                    });
                } else {
                    menu.replaceExistingItem(4, new CustomItemStack(Material.REDSTONE, "&7Enabled: &2\u2714", "", "&e> Click to disable this Machine"));
                    menu.addMenuClickHandler(4, (p, slot, item, action) -> {
                        BlockStorage.addBlockInfo(b, "enabled", String.valueOf(false));
                        newInstance(menu, b);
                        return false;
                    });
                }
            }

            @Override
            public boolean canOpen(@NotNull Block b, @NotNull Player p) {
                return p.hasPermission("slimefun.inventory.bypass") || Slimefun.getProtectionManager().hasPermission(p, b.getLocation(), Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                return new int[0];
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(DirtyChestMenu menu, ItemTransportFlow flow, ItemStack item) {
                if (flow == ItemTransportFlow.WITHDRAW) return getOutputSlots();
                List<Integer> slots = new ArrayList<>();
                for (int slot : getInputSlots()) if (menu.getItemInSlot(slot) != null) slots.add(slot);
                slots.sort(compareSlots(menu));
                int[] array = new int[slots.size()];
                for (int i = 0; i < slots.size(); i++) array[i] = slots.get(i);
                return array;
            }
        };
        addItemHandler(onPlace());
        addItemHandler(onBreak());
    }

    BlockBreakHandler onBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(@NotNull BlockBreakEvent e, @NotNull ItemStack item, @NotNull List<ItemStack> drops) {
                Block b = e.getBlock();
                BlockMenu inv = BlockStorage.getInventory(b);
                Location location = b.getLocation();
                if (inv != null) {
                    inv.dropItems(location, getInputSlots());
                    inv.dropItems(location, getOutputSlots());
                    inv.dropItems(location, keySlot);
                }
            }
        };
    }

    BlockPlaceHandler onPlace() {
        return new BlockPlaceHandler(true) {
            @Override
            public void onPlayerPlace(@NotNull BlockPlaceEvent e) {
                BlockStorage.addBlockInfo(e.getBlock(), "enabled", String.valueOf(false));
            }

            @Override
            public void onBlockPlacerPlace(@NotNull BlockPlacerPlaceEvent e) {
                BlockStorage.addBlockInfo(e.getBlock(), "enabled", String.valueOf(false));
            }
        };
    }

    Comparator<Integer> compareSlots(DirtyChestMenu menu) {
        return Comparator.comparingInt(slot -> menu.getItemInSlot(slot).getAmount());
    }

    protected void constructMenu(BlockMenuPreset preset) {
        AutoAncientAltar.borders(preset, border, inputBorder, outputBorder);
        for (int i : keyBorder)
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.YELLOW_STAINED_GLASS_PANE), "&e&lKey Item Slot"), (p, slot, item, action) -> false);
        preset.addItem(statusSlot, new CustomItemStack(new ItemStack(Material.YELLOW_STAINED_GLASS_PANE), "&e&lIdle"), (p, slot, item, action) -> false);
        for (int i : getOutputSlots()) {
            preset.addMenuClickHandler(i, new AdvancedMenuClickHandler() {
                @Override
                public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
                    return false;
                }

                @Override
                public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
                    if (cursor == null) return true;
                    return cursor.getType() == Material.AIR;
                }
            });
        }
        preset.addItem(2, new CustomItemStack(new ItemStack(Material.CRAFTING_TABLE), "&eRecipe", "", "&bPut in the Recipe you want to craft", "&ePut in the item you want crafted", "&4Vanilla Crafting Table Recipes ONLY"), (p, slot, item, action) -> false);
    }

    public int[] getInputSlots() {
        return new int[]{19, 20, 21, 28, 29, 30, 37, 38, 39};
    }

    public int[] getOutputSlots() {
        return new int[]{42, 43};
    }

    @NotNull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int getCapacity() {
        return CAPACITY;
    }

    public int getEnergyConsumption() {
        return ENERGY_CONSUMPTION;
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {
            @Override
            public void tick(Block b, SlimefunItem sf, Config data) {
                AutoCraftingTable.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return false;
            }
        });
    }

    protected void tick(Block block) {
        if (BlockStorage.getLocationInfo(block.getLocation(), "enabled").equals("false")) return;
        if (getCharge(block.getLocation()) < getEnergyConsumption()) {
            BlockMenu menu = BlockStorage.getInventory(block);
            if (menu.hasViewer())
                menu.replaceExistingItem(statusSlot, new CustomItemStack(new ItemStack(Material.RED_STAINED_GLASS_PANE), "&c&lNo Power"));
            return;
        }
        getResult(block);
    }

    void getResult(Block block) {
        BlockMenu menu = BlockStorage.getInventory(block);
        ItemStack keyItem = menu.getItemInSlot(keySlot);

        // Make sure at least 1 slot is free
        for (int outSlot : getOutputSlots()) {
            ItemStack outItem = menu.getItemInSlot(outSlot);
            if (outItem == null || outItem.getAmount() < outItem.getMaxStackSize()) break;
            else if (outSlot == getOutputSlots()[1]) {
                if (menu.hasViewer())
                    menu.replaceExistingItem(statusSlot, new CustomItemStack(new ItemStack(Material.RED_STAINED_GLASS_PANE), "&c&lOutput Full"));
                return;
            }
        }

        // Make sure we have a key item
        if (keyItem == null) {
            if (menu.hasViewer())
                menu.replaceExistingItem(statusSlot, new CustomItemStack(new ItemStack(Material.RED_STAINED_GLASS_PANE), "&c&lKey Item Missing"));
            return;
        }
        List<ItemStack> existingItems = new ArrayList<>();
        int blankCounter = 0;

        // Put each input item into the array
        for (int i = 0; i < 9; i++) {
            ItemStack slotItem = menu.getItemInSlot(getInputSlots()[i]);
            if (slotItem == null) {
                blankCounter++;
                // All slots are empty, no need to proceed
                if (blankCounter == 9) {
                    if (menu.hasViewer())
                        menu.replaceExistingItem(statusSlot, new CustomItemStack(new ItemStack(Material.RED_STAINED_GLASS_PANE), "&c&lInput Missing"));
                    return;
                }
                // No need to write to the array, null by default
                continue;
            }
            ItemStack existingItem = new ItemStack(slotItem.getType());

            // Checks if each slot has at least 1 item
            if (slotItem.getAmount() == 1) {
                if (menu.hasViewer())
                    menu.replaceExistingItem(statusSlot, new CustomItemStack(new ItemStack(Material.RED_STAINED_GLASS_PANE), "&c&lYou need to have enough supplies", "&c&lto craft more than one item"));
                return;
            }
            existingItems.add(existingItem);
        }

        // New HashMap System
        // This is semi-shapeless, since it reads left to right, top to bottom, and ignores empty spaces.
        // However, this isn't a concern since we have the key item.
        if (FluffyMachines.shapedVanillaRecipes.containsKey(keyItem)) {
            for (Pair<ItemStack, List<RecipeChoice>> recipe : FluffyMachines.shapedVanillaRecipes.get(keyItem)) {
                boolean passOn = false;
                List<RecipeChoice> rc = recipe.getSecondValue();
                if (existingItems.size() != rc.size()) {
                    if (menu.hasViewer())
                        menu.replaceExistingItem(statusSlot, new CustomItemStack(new ItemStack(Material.RED_STAINED_GLASS_PANE), "&c&lIncorrect Recipe"));

                    // The sizes don't match, but it can still be shapeless.
                    passOn = true;
                }

                // If we already know this isn't a shaped recipe, no need to check.
                if (!passOn)
                    for (int i = 0; i < rc.size(); i++)
                        if (!rc.get(i).test(existingItems.get(i))) {
                            if (menu.hasViewer())
                                menu.replaceExistingItem(statusSlot, new CustomItemStack(new ItemStack(Material.RED_STAINED_GLASS_PANE), "&c&lIncorrect Recipe"));
                            // We need to pass on to shapeless in case the key is shapeless.
                            passOn = true;
                            break;
                        }

                // We found the entire recipe! No need to pass on.
                if (!passOn) {
                    craft(menu, recipe.getFirstValue().clone());
                    return;
                }
            }

        }
        if (FluffyMachines.shapelessVanillaRecipes.containsKey(keyItem)) {
            for (Pair<ItemStack, List<RecipeChoice>> recipe : FluffyMachines.shapelessVanillaRecipes.get(keyItem)) {
                List<RecipeChoice> rc = recipe.getSecondValue();
                List<RecipeChoice> rcCheck = new ArrayList<>(rc);
                if (existingItems.size() != rc.size()) if (menu.hasViewer())
                    menu.replaceExistingItem(statusSlot, new CustomItemStack(new ItemStack(Material.RED_STAINED_GLASS_PANE), "&c&lIncorrect Recipe"));

                // Chop down the list until all items are tested
                for (RecipeChoice r : rc)
                    for (ItemStack item : existingItems)
                        if (r.test(item)) {
                            existingItems.remove(item);
                            rcCheck.remove(r);
                            break;
                        }
                if (existingItems.isEmpty() && rcCheck.isEmpty()) {
                    if (menu.hasViewer())
                        menu.replaceExistingItem(statusSlot, new CustomItemStack(new ItemStack(Material.GREEN_STAINED_GLASS_PANE), "&a&lCrafting"));
                    craft(menu, recipe.getFirstValue().clone());
                    return;
                } else {
                    if (menu.hasViewer())
                        menu.replaceExistingItem(statusSlot, new CustomItemStack(new ItemStack(Material.RED_STAINED_GLASS_PANE), "&c&lIncorrect Recipe"));
                }
            }
            if (menu.hasViewer())
                menu.replaceExistingItem(statusSlot, new CustomItemStack(new ItemStack(Material.RED_STAINED_GLASS_PANE), "&c&lInvalid Key!"));
        }
    }

    void craft(BlockMenu menu, ItemStack item) {
        if (!menu.fits(item, getOutputSlots())) {
            if (menu.hasViewer())
                menu.replaceExistingItem(statusSlot, new CustomItemStack(new ItemStack(Material.RED_STAINED_GLASS_PANE), "&c&lOutput slots full"));
            return;
        }
        if (menu.hasViewer())
            menu.replaceExistingItem(statusSlot, new CustomItemStack(new ItemStack(Material.GREEN_STAINED_GLASS_PANE), "&a&lCrafting"));
        for (int slot : getInputSlots()) if (menu.getItemInSlot(slot) != null) menu.consumeItem(slot, 1);
        menu.pushItem(item, getOutputSlots());
    }
}