package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Blocks;

import com.google.common.collect.MapDifference;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Utils.Util;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.InfinityExpansion.getConfig;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Utils.Util.getDisplayItem;

public final class AdvancedAnvil extends id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Abstracts.AbstractEnergyCrafter {
    static final Map<Enchantment, Integer> MAX_LEVELS = Util.getEnchants(java.util.Objects.requireNonNull(getConfig().getConfigurationSection("advanced-anvil-max-levels")));
    static final ItemStack ANVIL_SLOT = new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " ");
    static final int[] INPUT_SLOTS = {10, 13};
    static final int[] OUTPUT_SLOTS = {16};
    static final int STATUS_SLOT = 40;
    static final int[] ANVIL_SLOTS = {30, 31, 32, 39, 41, 47, 48, 49, 50, 51};
    static final int[] BACKGROUND = {27, 28, 29, 33, 34, 35, 36, 37, 38, 42, 43, 44, 45, 46, 52, 53};

    public AdvancedAnvil(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup category, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType type, ItemStack[] recipe, int energy) {
        super(category, item, type, recipe, energy, STATUS_SLOT);
    }

    @Nullable
    static ItemStack getOutput(@NotNull ItemStack item1, @NotNull ItemStack item2) {
        Map<Enchantment, Integer> enchants1 = getEnchants(item1.getItemMeta());
        Map<Enchantment, Integer> enchants2 = getEnchants(item2.getItemMeta());
        if (enchants1.size() == 0 && enchants2.size() == 0) return null;
        return combineEnchants(com.google.common.collect.Maps.difference(enchants1, enchants2), item1, item2);
    }

    @NotNull
    static Map<Enchantment, Integer> getEnchants(@NotNull ItemMeta meta) {
        if (meta instanceof EnchantmentStorageMeta) {
            EnchantmentStorageMeta book = (EnchantmentStorageMeta) meta;
            if (book.hasStoredEnchants()) return book.getStoredEnchants();
        } else if (meta.hasEnchants()) return meta.getEnchants();
        return new HashMap<>();
    }

    static void setEnchants(@NotNull ItemStack item, @NotNull ItemMeta meta, @NotNull Map<Enchantment, Integer> enchants) {
        if (meta instanceof EnchantmentStorageMeta) {
            EnchantmentStorageMeta book = (EnchantmentStorageMeta) meta;
            for (Map.Entry<Enchantment, Integer> entry : enchants.entrySet())
                book.addStoredEnchant(entry.getKey(), entry.getValue(), true);
            item.setItemMeta(book);
        } else for (Map.Entry<Enchantment, Integer> entry : enchants.entrySet())
            item.addUnsafeEnchantment(entry.getKey(), entry.getValue());
    }

    @Nullable
    static ItemStack combineEnchants(@NotNull MapDifference<Enchantment, Integer> dif, @NotNull ItemStack item1, @NotNull ItemStack item2) {
        ItemStack item = item1.clone();
        item.setAmount(1);
        ItemMeta meta = item.getItemMeta();
        Map<Enchantment, Integer> enchants = new HashMap<>();
        boolean changed = false;

        //upgrades (same enchant and level)
        for (Map.Entry<Enchantment, Integer> e : dif.entriesInCommon().entrySet())
            if (MAX_LEVELS.containsKey(e.getKey()) && e.getValue() < MAX_LEVELS.get(e.getKey())) {
                enchants.put(e.getKey(), e.getValue() + 1);
                changed = true;
            }

        //override (same enchant different level)
        for (Map.Entry<Enchantment, MapDifference.ValueDifference<Integer>> e : dif.entriesDiffering().entrySet())
            if (e.getValue().rightValue() > e.getValue().leftValue()) {
                enchants.put(e.getKey(), e.getValue().rightValue());
                changed = true;
            }
        boolean bookOntoTool = item2.getType() == Material.ENCHANTED_BOOK && item1.getType() != Material.ENCHANTED_BOOK;

        //unique (different enchants from 2nd item)
        for (Map.Entry<Enchantment, Integer> e : dif.entriesOnlyOnRight().entrySet()) {
            if (bookOntoTool) if (!e.getKey().canEnchantItem(item)) continue;
            enchants.put(e.getKey(), e.getValue());
            changed = true;
        }
        if (changed) {
            setEnchants(item, meta, enchants);
            return item;
        } else return null;
    }

    @Override
    protected void setup(@NotNull me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(BACKGROUND);
        blockMenuPreset.drawBackground(ANVIL_SLOT, ANVIL_SLOTS);
        blockMenuPreset.drawBackground(INPUT_BORDER, new int[]{0, 1, 2, 3, 4, 5, 9, 11, 12, 14, 18, 19, 20, 21, 22, 23});
        blockMenuPreset.drawBackground(OUTPUT_BORDER, new int[]{6, 7, 8, 15, 17, 24, 25, 26});
        blockMenuPreset.addItem(STATUS_SLOT, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
    }

    @Override
    protected int[] getInputSlots() {
        return INPUT_SLOTS;
    }

    @Override
    protected int[] getOutputSlots() {
        return OUTPUT_SLOTS;
    }

    @Override
    protected void onNewInstance(@NotNull BlockMenu menu, @NotNull Block b) {
        menu.addMenuClickHandler(STATUS_SLOT, (player, i, itemStack, clickAction) -> {
            craft(menu, b, player);
            return false;
        });
    }

    void craft(BlockMenu inv, Block b, org.bukkit.entity.Player p) {
        org.bukkit.Location l = b.getLocation();
        if (getCharge(l) < this.energy) { //not enough energy
            p.sendMessage(ChatColor.RED + "Not enough energy!\n" + ChatColor.GREEN + "Charge: " + ChatColor.RED + getCharge(l) + ChatColor.GREEN + "/" + this.energy + " J");
            return;
        }
        ItemStack item1 = inv.getItemInSlot(INPUT_SLOTS[0]);
        SlimefunItem sfItem1 = SlimefunItem.getByItem(inv.getItemInSlot(INPUT_SLOTS[0]));
        ItemStack item2 = inv.getItemInSlot(INPUT_SLOTS[1]);
        SlimefunItem sfItem2 = SlimefunItem.getByItem(inv.getItemInSlot(INPUT_SLOTS[1]));
        if (item1 == null || item2 == null || (item2.getType() != Material.ENCHANTED_BOOK && item1.getType() != item2.getType())) {
            p.sendMessage(ChatColor.RED + "Invalid items!");
            return;
        }
        if (sfItem2 != null && !sfItem2.isDisenchantable()) {
            p.sendMessage(ChatColor.RED + "Slimefun item is not disenchantable!");
            return;
        }
        if (sfItem1 != null && !sfItem1.isEnchantable()) {
            p.sendMessage(ChatColor.RED + "Slimefun item is not enchantable!");
            return;
        }
        ItemStack output = getOutput(item1, item2);
        if (output == null) {
            p.sendMessage(ChatColor.RED + "No upgrades!");
            return;
        }
        if (!inv.fits(output, OUTPUT_SLOTS)) {
            p.sendMessage(ChatColor.GOLD + "Not enough room!");
            return;
        }
        p.playSound(l, org.bukkit.Sound.BLOCK_ANVIL_USE, 1, 1);
        item1.setAmount(item1.getAmount() - 1);
        item2.setAmount(item2.getAmount() - 1);
        inv.pushItem(output, OUTPUT_SLOTS);
        removeCharge(l, this.energy);
        update(inv);
    }

    @Override
    public void update(@NotNull BlockMenu inv) {
        ItemStack item1 = inv.getItemInSlot(INPUT_SLOTS[0]);
        ItemStack item2 = inv.getItemInSlot(INPUT_SLOTS[1]);
        if (item1 == null || item2 == null || (item2.getType() != Material.ENCHANTED_BOOK && item1.getType() != item2.getType())) {
            inv.replaceExistingItem(STATUS_SLOT, new CustomItemStack(Material.BARRIER, "&cInvalid items!"));
            return;
        }
        ItemStack output = getOutput(item1, item2);
        if (output == null) {
            inv.replaceExistingItem(STATUS_SLOT, new CustomItemStack(Material.BARRIER, "&cNo upgrades!"));
            return;
        }
        inv.replaceExistingItem(STATUS_SLOT, getDisplayItem(output));
    }
}