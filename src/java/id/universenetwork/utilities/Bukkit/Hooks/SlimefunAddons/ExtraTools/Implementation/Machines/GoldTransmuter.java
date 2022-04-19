package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Implementation.Machines;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Lists.ETItems;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;
import static org.bukkit.Material.*;

public class GoldTransmuter extends me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer implements io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem {
    public GoldTransmuter() {
        super(ETItems.extra_tools, ETItems.GOLD_TRANSMUTER, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, SILVER_INGOT, null, ELECTRIC_MOTOR, GOLD_24K_BLOCK, ELECTRIC_MOTOR, new ItemStack(GOLDEN_PICKAXE), MEDIUM_CAPACITOR, new ItemStack(GOLDEN_PICKAXE)});

        addItemHandler(onBreak());
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(7, new ItemStack[]{GOLD_24K_BLOCK}, new ItemStack[]{new ItemStack(GOLD_BLOCK)});
        registerRecipe(2, new ItemStack[]{GOLD_4K}, new ItemStack[]{new ItemStack(GOLD_NUGGET, 4)});
        registerRecipe(2, new ItemStack[]{GOLD_6K}, new ItemStack[]{new ItemStack(GOLD_NUGGET, 9)});
        registerRecipe(3, new ItemStack[]{GOLD_8K}, new ItemStack[]{new ItemStack(GOLD_NUGGET, 13)});
        registerRecipe(3, new ItemStack[]{GOLD_10K}, new ItemStack[]{new ItemStack(GOLD_NUGGET, 18)});
        registerRecipe(4, new ItemStack[]{GOLD_12K}, new ItemStack[]{new ItemStack(GOLD_NUGGET, 22)});
        registerRecipe(4, new ItemStack[]{GOLD_14K}, new ItemStack[]{new ItemStack(GOLD_NUGGET, 27)});
        registerRecipe(5, new ItemStack[]{GOLD_16K}, new ItemStack[]{new ItemStack(GOLD_NUGGET, 31)});
        registerRecipe(5, new ItemStack[]{GOLD_18K}, new ItemStack[]{new ItemStack(GOLD_NUGGET, 36)});
        registerRecipe(6, new ItemStack[]{GOLD_20K}, new ItemStack[]{new ItemStack(GOLD_NUGGET, 40)});
        registerRecipe(6, new ItemStack[]{GOLD_22K}, new ItemStack[]{new ItemStack(GOLD_NUGGET, 45)});
        registerRecipe(7, new ItemStack[]{GOLD_24K}, new ItemStack[]{new ItemStack(GOLD_NUGGET, 49)});
        registerRecipe(2, new ItemStack[]{new ItemStack(GOLD_INGOT)}, new ItemStack[]{GOLD_DUST});
    }

    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes = new java.util.ArrayList<>(recipes.size() * 2);
        for (me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe recipe : recipes) {
            displayRecipes.add(recipe.getInput()[0]);
            displayRecipes.add(recipe.getOutput()[recipe.getOutput().length - 1]);
        }
        return displayRecipes;
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(GOLDEN_PICKAXE);
    }

    @Override
    public String getInventoryTitle() {
        return "&6Gold Transmuter";
    }

    @Override
    public String getMachineIdentifier() {
        return "GOLD_TRANSMUTER";
    }

    @Override
    public int getCapacity() {
        return 256;
    }

    @Override
    public int getEnergyConsumption() {
        return 9;
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    public BlockBreakHandler onBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(org.bukkit.event.block.BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                org.bukkit.block.Block b = e.getBlock();
                me.mrCookieSlime.Slimefun.api.inventory.BlockMenu inv = me.mrCookieSlime.Slimefun.api.BlockStorage.getInventory(b);
                if (inv != null) {
                    inv.dropItems(b.getLocation(), getInputSlots());
                    inv.dropItems(b.getLocation(), getOutputSlots());
                }
            }
        };
    }
}