package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils.updateProgressbar;
import static org.bukkit.Material.*;

public class AdvancedAutoDisenchanter extends SlimefunItem implements io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent {
    public static final int ENERGY_CONSUMPTION = 1024;
    public static final int CAPACITY = 4096;
    static final int[] plainBorder = {0, 1, 2, 3, 4, 5, 6, 7, 8, 12, 14, 21, 22, 23, 36, 37, 38, 42, 43, 44, 45, 46, 47, 51, 52, 53};
    static final int[] inputItemBorder = {9, 10, 11, 18, 20, 27, 28, 29};
    static final int[] outputBorder = {21, 22, 23, 30, 32, 39, 41, 48, 49, 50};
    static final int[] inputBookBorder = {15, 16, 17, 24, 26, 33, 34, 35};
    static final int ITEM_SLOT = 19;
    static final int BOOK_SLOT = 25;
    static final int[] OUTPUT_SLOTS = {31, 40};
    static final int SELECTION_SLOT = 4;
    static final int PROGRESS_SLOT = 13;
    static final int REQUIRED_TICKS = 60; // "Number of seconds", except 1 Slimefun "second" = 1.6 IRL seconds
    static final Map<BlockPosition, Integer> progress = new java.util.HashMap<>();
    static final NamespacedKey selection = id.universenetwork.utilities.Bukkit.UNUtilities.createKey("selection");
    static final ItemStack selectionItem = new CustomItemStack(ENCHANTED_BOOK, "&6Selected Enchant", "&a> Click here to cycle through enchants", "&5Current Enchant: None");
    static final ItemStack progressItem = new CustomItemStack(EXPERIENCE_BOTTLE, "&aProgress");

    // Why am I doing this... TODO: Replace with BlockStorage
    static {
        ItemMeta meta = selectionItem.getItemMeta();
        meta.getPersistentDataContainer().set(selection, PersistentDataType.INTEGER, -1);
        selectionItem.setItemMeta(meta);
    }

    final ItemSetting<Boolean> useLevelLimit = new ItemSetting<>(this, "use-enchant-level-limit", false);
    final IntRangeSetting levelLimit = new IntRangeSetting(this, "enchant-level-limit", 0, 10, Short.MAX_VALUE);

    public AdvancedAutoDisenchanter(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(onBreak());
        addItemSetting(useLevelLimit, levelLimit);
        new BlockMenuPreset(getId(), "&cAdvanced Auto Disenchanter") {
            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public void newInstance(BlockMenu menu, Block b) {
                menu.replaceExistingItem(SELECTION_SLOT, selectionItem.clone());
                menu.addMenuClickHandler(SELECTION_SLOT, (p, slot, item, action) -> {
                    cycleEnchants(menu, item);
                    return false;
                });
                menu.addMenuClickHandler(ITEM_SLOT, (p, slot, item, action) -> {
                    menu.replaceExistingItem(SELECTION_SLOT, selectionItem.clone());
                    return true;
                });
            }

            @Override
            public boolean canOpen(Block b, org.bukkit.entity.Player p) {
                return (p.hasPermission("slimefun.inventory.bypass") || io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getProtectionManager().hasPermission(p, b.getLocation(), io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction.INTERACT_BLOCK));
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow itemTransportFlow) {
                return new int[0];
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu menu, ItemTransportFlow flow, ItemStack item) {
                if (flow == ItemTransportFlow.INSERT) {
                    if (item.getType() == BOOK) return new int[]{BOOK_SLOT};
                    else return new int[]{ITEM_SLOT};
                } else if (flow == ItemTransportFlow.WITHDRAW)
                    return OUTPUT_SLOTS;
                else return new int[0];
            }
        };
    }

    BlockBreakHandler onBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(org.bukkit.event.block.BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                Block b = e.getBlock();
                BlockMenu inv = BlockStorage.getInventory(b);
                if (inv != null) {
                    inv.dropItems(b.getLocation(), ITEM_SLOT);
                    inv.dropItems(b.getLocation(), BOOK_SLOT);
                    inv.dropItems(b.getLocation(), OUTPUT_SLOTS);
                }
            }
        };
    }

    @Override
    public void preRegister() {
        addItemHandler(new me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker() {
            @Override
            public void tick(Block b, SlimefunItem sf, me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config data) {
                AdvancedAutoDisenchanter.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return false;
            }
        });
    }

    protected void constructMenu(BlockMenuPreset preset) {
        for (int i : plainBorder)
            preset.addItem(i, new CustomItemStack(new ItemStack(GRAY_STAINED_GLASS_PANE), " "), (p, slot, item, action) -> false);
        for (int i : inputItemBorder)
            preset.addItem(i, new CustomItemStack(new ItemStack(CYAN_STAINED_GLASS_PANE), " "), (p, slot, item, action) -> false);
        for (int i : inputBookBorder)
            preset.addItem(i, new CustomItemStack(new ItemStack(YELLOW_STAINED_GLASS_PANE), " "), (p, slot, item, action) -> false);
        for (int i : outputBorder)
            preset.addItem(i, new CustomItemStack(new ItemStack(ORANGE_STAINED_GLASS_PANE), " "), (p, slot, item, action) -> false);
        preset.addItem(PROGRESS_SLOT, progressItem, (p, slot, item, action) -> false);
    }

    protected void tick(Block b) {
        if (getCharge(b.getLocation()) < ENERGY_CONSUMPTION) return;
        BlockMenu inv = BlockStorage.getInventory(b);
        final BlockPosition pos = new BlockPosition(b.getWorld(), b.getX(), b.getY(), b.getZ());
        int currentProgress = progress.getOrDefault(pos, 0);
        ItemMeta meta = inv.getItemInSlot(SELECTION_SLOT).getItemMeta();
        int selectionIndex = meta.getPersistentDataContainer().get(selection, PersistentDataType.INTEGER);
        ItemStack input = inv.getItemInSlot(ITEM_SLOT);
        SlimefunItem sfItem = SlimefunItem.getByItem(input);
        if (sfItem != null && !sfItem.isDisenchantable()) return;
        if (selectionIndex != -1 && input != null && inv.getItemInSlot(BOOK_SLOT) != null && io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.isItemSimilar(inv.getItemInSlot(BOOK_SLOT), id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.FluffyItems.ANCIENT_BOOK.getItem().getItem(), false, false) && !input.getEnchantments().isEmpty()) {
            // We need both slots empty
            for (int slot : OUTPUT_SLOTS) if (inv.getItemInSlot(slot) != null) return;

            // Don't produce the item if didn't finish
            if (currentProgress < REQUIRED_TICKS) {
                progress.put(pos, ++currentProgress);
                updateProgressbar(inv, PROGRESS_SLOT, REQUIRED_TICKS - currentProgress, REQUIRED_TICKS, progressItem);
                removeCharge(b.getLocation(), ENERGY_CONSUMPTION);
                return;
            }

            ItemStack item = inv.getItemInSlot(ITEM_SLOT).clone();
            List<NamespacedKey> enchants = new ArrayList<>();
            List<Integer> levels = new ArrayList<>();
            Map<Enchantment, Integer> enchantMap = item.getEnchantments();
            if (enchantMap.size() == 0) return;
            enchantMap.forEach((enchant, level) -> {
                if (useLevelLimit.getValue()) if (level > levelLimit.getValue()) return; // Equivalent of continue
                enchants.add(enchant.getKey());
                levels.add(level);
            });
            ItemStack enchantedBook = new ItemStack(ENCHANTED_BOOK);
            EnchantmentStorageMeta enchantedMeta = (EnchantmentStorageMeta) enchantedBook.getItemMeta();
            enchantedMeta.addStoredEnchant(Enchantment.getByKey(enchants.get(selectionIndex)), levels.get(selectionIndex), true);
            enchantedBook.setItemMeta(enchantedMeta);
            item.removeEnchantment(Enchantment.getByKey(enchants.get(selectionIndex)));
            inv.consumeItem(ITEM_SLOT);
            inv.consumeItem(BOOK_SLOT);
            inv.pushItem(item, OUTPUT_SLOTS);
            inv.pushItem(enchantedBook, OUTPUT_SLOTS);

            // Reset the selection item
            inv.replaceExistingItem(SELECTION_SLOT, selectionItem);

        }
        progress.put(pos, 0);
        currentProgress = progress.getOrDefault(pos, 0);
        updateProgressbar(inv, PROGRESS_SLOT, REQUIRED_TICKS - currentProgress, REQUIRED_TICKS, progressItem);
    }

    void cycleEnchants(BlockMenu inv, ItemStack clickedItem) {
        if (inv.getItemInSlot(ITEM_SLOT) != null) {
            ItemStack item = inv.getItemInSlot(ITEM_SLOT);
            List<String> enchants = new ArrayList<>();
            Map<Enchantment, Integer> enchantMap = item.getEnchantments();
            if (enchantMap.size() == 0) return;

            // Convert to a list
            enchantMap.forEach((enchant, level) -> {
                if (useLevelLimit.getValue()) if (level > levelLimit.getValue()) return; // Equivalent of continue
                enchants.add(org.apache.commons.lang.WordUtils.capitalizeFully(org.apache.commons.lang.StringUtils.replaceEach(enchant.getKey().toString(), new String[]{"minecraft:", "_"}, new String[]{"", " "})) + " " + id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.Utils.toRoman(level));
            });
            ItemMeta meta = clickedItem.getItemMeta();
            List<String> lore = meta.getLore();
            int selectionIndex = meta.getPersistentDataContainer().get(selection, PersistentDataType.INTEGER);
            if (enchants.size() - 1 > selectionIndex) {
                selectionIndex++; // 0
                meta.getPersistentDataContainer().set(selection, PersistentDataType.INTEGER, selectionIndex);
                lore.set(1, ChatColor.DARK_PURPLE + "Current Enchant: " + ChatColor.YELLOW + enchants.get(selectionIndex)); // 0
            } else {
                selectionIndex = 0;
                meta.getPersistentDataContainer().set(selection, PersistentDataType.INTEGER, selectionIndex);
                lore.set(1, ChatColor.DARK_PURPLE + "Current Enchant: " + ChatColor.YELLOW + enchants.get(0));
            }
            meta.setLore(lore);
            clickedItem.setItemMeta(meta);
        }
    }

    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int getCapacity() {
        return CAPACITY;
    }
}