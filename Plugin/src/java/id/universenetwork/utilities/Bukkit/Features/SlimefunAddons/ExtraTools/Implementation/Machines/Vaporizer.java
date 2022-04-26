package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.ExtraTools.Implementation.Machines;

import id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.ExtraTools.Lists.ETItems;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;
import static org.bukkit.Material.*;

public class Vaporizer extends me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer implements io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem {
    public Vaporizer() {
        super(ETItems.extra_tools, ETItems.VAPORIZER, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(MAGMA_BLOCK), ELECTRIC_MOTOR, new ItemStack(MAGMA_BLOCK), HEATING_COIL, FLUID_PUMP, HEATING_COIL, new ItemStack(MAGMA_BLOCK), MEDIUM_CAPACITOR, new ItemStack(MAGMA_BLOCK)});
        addItemHandler(onBreak());
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(8, new ItemStack[]{new ItemStack(WATER_BUCKET)}, new ItemStack[]{new ItemStack(BUCKET), new CustomItemStack(SALT, 4)});
        registerRecipe(8, new ItemStack[]{new ItemStack(LAVA_BUCKET)}, new ItemStack[]{new ItemStack(BUCKET), new CustomItemStack(SULFATE, 16)});
        registerRecipe(3, new ItemStack[]{new ItemStack(MAGMA_BLOCK)}, new ItemStack[]{SULFATE});
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
        return new ItemStack(IRON_HOE);
    }

    @Override
    public String getInventoryTitle() {
        return "&cVaporizer";
    }

    @Override
    public String getMachineIdentifier() {
        return "VAPORIZER";
    }

    @Override
    public int getCapacity() {
        return 256;
    }

    @Override
    public int getEnergyConsumption() {
        return 16;
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