package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Tools;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Optional;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI.*;
import static org.bukkit.ChatColor.WHITE;
import static org.bukkit.Material.*;

public class LiquidTank extends SlimefunItem implements io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable {
    static final NamespacedKey FLUID_NAME = new NamespacedKey(plugin, "liquid-name");
    static final NamespacedKey FLUID_AMOUNT = new NamespacedKey(plugin, "liquid-amount");
    final int maxLiquidAmount;

    public LiquidTank(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, int maxLiquidAmount, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.maxLiquidAmount = maxLiquidAmount;
        addItemHandler(onRightClick());
    }

    public static List<String> getPlaceableFluids() {
        List<String> PLACEABLE_FLUIDS = new java.util.ArrayList<>();
        PLACEABLE_FLUIDS.add("WATER");
        PLACEABLE_FLUIDS.add("LAVA");
        return PLACEABLE_FLUIDS;
    }

    io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler onRightClick() {
        return e -> {
            e.cancel();
            Optional<Block> b = e.getClickedBlock();
            Optional<SlimefunItem> item = e.getSlimefunItem();
            ItemStack itemStack = e.getItem();
            if (b.isPresent() && item.isPresent() && item.get() instanceof LiquidTank && io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getProtectionManager().hasPermission(e.getPlayer(), b.get().getLocation(), io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction.PLACE_BLOCK)) {
                Block liquid = b.get().getRelative(e.getClickedFace());
                org.bukkit.block.BlockState liquidState = io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib.getBlockState(liquid, false).getState();
                LiquidTank liquidTank = (LiquidTank) item.get();
                String fluidName = getLiquid(itemStack).getFirstValue();
                int fluidAmount = getLiquid(itemStack).getSecondValue();
                if (liquid.getType() == AIR && fluidName != null && e.getPlayer().isSneaking() && fluidAmount >= 1000) {
                    if (fluidName.equals("WATER")) {
                        removeLiquid(itemStack, fluidName, 1000);
                        liquidState.setType(WATER);
                        liquidState.update(true, true);
                        updateLore(itemStack);
                    } else if (fluidName.equals("LAVA")) {
                        removeLiquid(itemStack, fluidName, 1000);
                        liquidState.setType(LAVA);
                        liquidState.update(true, true);
                        updateLore(itemStack);
                    }
                } else if ((liquid.getType() == LAVA || liquid.getType() == WATER) && fluidName != null && fluidAmount <= liquidTank.getMaxLiquidAmount() && liquid.isLiquid()) {
                    addLiquid(itemStack, liquid.getType().name(), 1000);
                    liquidState.setType(AIR);
                    liquidState.update(true, true);
                    updateLore(itemStack);
                }
            }
        };
    }

    public int getMaxLiquidAmount() {
        return maxLiquidAmount;
    }

    public void addLiquid(ItemStack item, String fluidName, int fluidAmount) {
        ItemMeta im = item.getItemMeta();
        String itemFluidName = getString(im, FLUID_NAME);
        int itemFluidAmount = getInt(im, FLUID_AMOUNT);
        int resultFluidAmount = itemFluidAmount + fluidAmount;
        if (itemFluidName != null && itemFluidName.equals(fluidName) && itemFluidAmount != 0 && resultFluidAmount <= getMaxLiquidAmount())
            setLiquid(item, fluidName, resultFluidAmount);
        else if (resultFluidAmount >= getMaxLiquidAmount()) setLiquid(item, fluidName, getMaxLiquidAmount());
        else setLiquid(item, fluidName, fluidAmount);
    }

    public void removeLiquid(ItemStack item, String fluidName, int fluidAmount) {
        ItemMeta im = item.getItemMeta();
        String itemFluidName = getString(im, FLUID_NAME);
        int itemFluidAmount = getInt(im, FLUID_AMOUNT);
        int resultFluidAmount = itemFluidAmount - fluidAmount;
        if (itemFluidName != null && itemFluidName.equals(fluidName) && itemFluidAmount != 0 && resultFluidAmount > 0)
            setLiquid(item, fluidName, resultFluidAmount);
        else setLiquid(item, "NO_FLUID", 0);
    }

    public void setLiquid(ItemStack item, String fluidName, int fluidAmount) {
        ItemMeta im = item.getItemMeta();
        setString(im, FLUID_NAME, fluidName);
        setInt(im, FLUID_AMOUNT, fluidAmount);
        item.setItemMeta(im);
    }

    public Pair<String, Integer> getLiquid(ItemStack item) {
        String fluidName = getString(item.getItemMeta(), FLUID_NAME);
        int fluidAmount = getInt(item.getItemMeta(), FLUID_AMOUNT);
        if (item.hasItemMeta() && fluidName != null && fluidAmount != 0) return new Pair<>(fluidName, fluidAmount);
        return new Pair<>("NO_FLUID", 0);
    }

    public void updateLore(ItemStack item) {
        String fluidName = getString(item.getItemMeta(), FLUID_NAME);
        int fluidAmount = getInt(item.getItemMeta(), FLUID_AMOUNT);
        ItemMeta im = item.getItemMeta();
        List<String> lore = im.getLore();
        if (fluidName == null) return;
        for (int i = 0; i < lore.size(); i++) {
            if (lore.get(i).contains("Fluid Held: ")) lore.set(i, WHITE + "Fluid Held: " + fluidName);
            if (lore.get(i).contains("Amount: "))
                lore.set(i, WHITE + "Amount: " + fluidAmount + "mb / " + getMaxLiquidAmount());
        }
        im.setLore(lore);
        item.setItemMeta(im);
    }
}