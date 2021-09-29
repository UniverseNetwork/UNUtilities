package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.ExtraTools.Implementation.Machines;

import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.ExtraTools.Lists.ETItems.VAPORIZER;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.ExtraTools.Lists.ETItems.extra_tools;
import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;
import static org.bukkit.Material.*;

public class Vaporizer extends AContainer implements RecipeDisplayItem {
    public Vaporizer() {
        super(extra_tools, VAPORIZER, ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(MAGMA_BLOCK), ELECTRIC_MOTOR, new ItemStack(MAGMA_BLOCK), HEATING_COIL, FLUID_PUMP, HEATING_COIL, new ItemStack(MAGMA_BLOCK), MEDIUM_CAPACITOR, new ItemStack(MAGMA_BLOCK)});
        addItemHandler(onBreak());
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(8, new ItemStack[]{new ItemStack(WATER_BUCKET)}, new ItemStack[]{new ItemStack(BUCKET), new CustomItemStack(SALT, 4)});
        registerRecipe(8, new ItemStack[]{new ItemStack(LAVA_BUCKET)}, new ItemStack[]{new ItemStack(BUCKET), new CustomItemStack(SULFATE, 16)});
        registerRecipe(3, new ItemStack[]{new ItemStack(MAGMA_BLOCK)}, new ItemStack[]{SULFATE});
    }

    @Nonnull
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
        return new ItemStack(IRON_HOE);
    }

    @Nonnull
    @Override
    public String getInventoryTitle() {
        return "&cVaporizer";
    }

    @Nonnull
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
            public void onPlayerBreak(@Nonnull BlockBreakEvent e, @Nonnull ItemStack item, @Nonnull List<ItemStack> drops) {
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