package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Implementation.Machines;

import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Lists.ETItems.PULVERIZER;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Lists.ETItems.extra_tools;
import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;
import static org.bukkit.Material.*;

public class Pulverizer extends AContainer implements RecipeDisplayItem {
    public Pulverizer() {
        super(extra_tools, PULVERIZER, ENHANCED_CRAFTING_TABLE, new ItemStack[]{SILICON, HARDENED_METAL_INGOT, SILICON, ELECTRIC_MOTOR, STEEL_PLATE, ELECTRIC_MOTOR, new ItemStack(IRON_PICKAXE), MEDIUM_CAPACITOR, new ItemStack(IRON_PICKAXE)});
        addItemHandler(onBreak());
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(8, new ItemStack[]{new ItemStack(STONE, 4)}, new ItemStack[]{new ItemStack(SAND)});
        registerRecipe(8, new ItemStack[]{new ItemStack(GRANITE, 4)}, new ItemStack[]{new ItemStack(SAND)});
        registerRecipe(8, new ItemStack[]{new ItemStack(DIORITE, 4)}, new ItemStack[]{new ItemStack(SAND)});
        registerRecipe(8, new ItemStack[]{new ItemStack(ANDESITE, 4)}, new ItemStack[]{new ItemStack(SAND)});
        registerRecipe(8, new ItemStack[]{new ItemStack(COBBLESTONE, 4)}, new ItemStack[]{new ItemStack(SAND)});
        registerRecipe(8, new ItemStack[]{new ItemStack(ANDESITE, 4)}, new ItemStack[]{new ItemStack(SAND)});
        registerRecipe(8, new ItemStack[]{new ItemStack(GRAVEL, 4)}, new ItemStack[]{new ItemStack(SAND)});
        registerRecipe(8, new ItemStack[]{new ItemStack(GRASS_BLOCK, 4)}, new ItemStack[]{new ItemStack(SAND)});
        registerRecipe(8, new ItemStack[]{new ItemStack(DIRT, 4)}, new ItemStack[]{new ItemStack(SAND)});
        registerRecipe(8, new ItemStack[]{new ItemStack(COARSE_DIRT, 4)}, new ItemStack[]{new ItemStack(SAND)});
        registerRecipe(8, new ItemStack[]{new ItemStack(PODZOL, 4)}, new ItemStack[]{new ItemStack(SAND)});
        registerRecipe(8, new ItemStack[]{new ItemStack(NETHERRACK, 4)}, new ItemStack[]{new ItemStack(SOUL_SAND)});
    }

    @NotNull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes = new ArrayList<>(recipes.size() * 2);
        for (MachineRecipe recipe : recipes) {
            displayRecipes.add(recipe.getInput()[0]);
            displayRecipes.add(recipe.getOutput()[recipe.getOutput().length - 1]);
        }
        return displayRecipes;
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(IRON_PICKAXE);
    }

    @NotNull
    @Override
    public String getInventoryTitle() {
        return "&cPulverizer";
    }

    @NotNull
    @Override
    public String getMachineIdentifier() {
        return "PULVERIZER";
    }

    @Override
    public int getCapacity() {
        return 256;
    }

    @Override
    public int getEnergyConsumption() {
        return 25;
    }

    @Override
    public int getSpeed() {
        return 4;
    }

    public BlockBreakHandler onBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(@NotNull BlockBreakEvent e, @NotNull ItemStack item, @NotNull List<ItemStack> drops) {
                Block b = e.getBlock();
                BlockMenu inv = BlockStorage.getInventory(b);
                if (inv != null) {
                    inv.dropItems(b.getLocation(), getInputSlots());
                    inv.dropItems(b.getLocation(), getOutputSlots());
                }
            }
        };
    }
}