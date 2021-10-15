package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Implementation.Machines;

import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Lists.ETItems.GOLD_TRANSMUTER;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Lists.ETItems.extra_tools;
import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;
import static org.bukkit.Material.GOLD_INGOT;

public class GoldTransmuter extends AContainer implements RecipeDisplayItem {
    public GoldTransmuter() {
        super(extra_tools, GOLD_TRANSMUTER, ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, SILVER_INGOT, null, ELECTRIC_MOTOR, GOLD_24K_BLOCK, ELECTRIC_MOTOR, new ItemStack(Material.GOLDEN_PICKAXE), MEDIUM_CAPACITOR, new ItemStack(Material.GOLDEN_PICKAXE)});
        addItemHandler(onBreak());
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(7, new ItemStack[]{GOLD_24K_BLOCK}, new ItemStack[]{new ItemStack(Material.GOLD_BLOCK)});
        registerRecipe(2, new ItemStack[]{GOLD_4K}, new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 4)});
        registerRecipe(2, new ItemStack[]{GOLD_6K}, new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 9)});
        registerRecipe(3, new ItemStack[]{GOLD_8K}, new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 13)});
        registerRecipe(3, new ItemStack[]{GOLD_10K}, new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 18)});
        registerRecipe(4, new ItemStack[]{GOLD_12K}, new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 22)});
        registerRecipe(4, new ItemStack[]{GOLD_14K}, new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 27)});
        registerRecipe(5, new ItemStack[]{GOLD_16K}, new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 31)});
        registerRecipe(5, new ItemStack[]{GOLD_18K}, new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 36)});
        registerRecipe(6, new ItemStack[]{GOLD_20K}, new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 40)});
        registerRecipe(6, new ItemStack[]{GOLD_22K}, new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 45)});
        registerRecipe(7, new ItemStack[]{GOLD_24K}, new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 49)});
        registerRecipe(2, new ItemStack[]{new ItemStack(GOLD_INGOT)}, new ItemStack[]{GOLD_DUST});
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
        return new ItemStack(Material.GOLDEN_PICKAXE);
    }

    @NotNull
    @Override
    public String getInventoryTitle() {
        return "&6Gold Transmuter";
    }

    @NotNull
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