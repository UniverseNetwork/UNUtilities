package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Implementation.Machines;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Lists.ETItems;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE;
import static org.bukkit.Material.*;

public class ConcreteFactory extends AContainer implements RecipeDisplayItem {
    public ConcreteFactory() {
        super(ETItems.extra_tools, ETItems.CONCRETE_FACTORY, ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(WATER_BUCKET), SlimefunItems.GILDED_IRON, new ItemStack(WATER_BUCKET), SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD, new ItemStack(WATER_BUCKET), SlimefunItems.SMALL_CAPACITOR, new ItemStack(WATER_BUCKET)});
        addItemHandler(onBreak());
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(4, new ItemStack[]{new ItemStack(WHITE_CONCRETE_POWDER, 8)}, new ItemStack[]{new ItemStack(WHITE_CONCRETE, 8)});
        registerRecipe(4, new ItemStack[]{new ItemStack(ORANGE_CONCRETE_POWDER, 8)}, new ItemStack[]{new ItemStack(ORANGE_CONCRETE, 8)});
        registerRecipe(4, new ItemStack[]{new ItemStack(MAGENTA_CONCRETE_POWDER, 8)}, new ItemStack[]{new ItemStack(MAGENTA_CONCRETE, 8)});
        registerRecipe(4, new ItemStack[]{new ItemStack(LIGHT_BLUE_CONCRETE_POWDER, 8)}, new ItemStack[]{new ItemStack(LIGHT_BLUE_CONCRETE, 8)});
        registerRecipe(4, new ItemStack[]{new ItemStack(YELLOW_CONCRETE_POWDER, 8)}, new ItemStack[]{new ItemStack(YELLOW_CONCRETE, 8)});
        registerRecipe(4, new ItemStack[]{new ItemStack(LIME_CONCRETE_POWDER, 8)}, new ItemStack[]{new ItemStack(LIME_CONCRETE, 8)});
        registerRecipe(4, new ItemStack[]{new ItemStack(PINK_CONCRETE_POWDER, 8)}, new ItemStack[]{new ItemStack(PINK_CONCRETE, 8)});
        registerRecipe(4, new ItemStack[]{new ItemStack(GRAY_CONCRETE_POWDER, 8)}, new ItemStack[]{new ItemStack(GRAY_CONCRETE, 8)});
        registerRecipe(4, new ItemStack[]{new ItemStack(LIGHT_GRAY_CONCRETE_POWDER, 8)}, new ItemStack[]{new ItemStack(LIGHT_GRAY_CONCRETE, 8)});
        registerRecipe(4, new ItemStack[]{new ItemStack(CYAN_CONCRETE_POWDER, 8)}, new ItemStack[]{new ItemStack(CYAN_CONCRETE, 8)});
        registerRecipe(4, new ItemStack[]{new ItemStack(PURPLE_CONCRETE_POWDER, 8)}, new ItemStack[]{new ItemStack(PURPLE_CONCRETE, 8)});
        registerRecipe(4, new ItemStack[]{new ItemStack(BLUE_CONCRETE_POWDER, 8)}, new ItemStack[]{new ItemStack(BLUE_CONCRETE, 8)});
        registerRecipe(4, new ItemStack[]{new ItemStack(BROWN_CONCRETE_POWDER, 8)}, new ItemStack[]{new ItemStack(BROWN_CONCRETE, 8)});
        registerRecipe(4, new ItemStack[]{new ItemStack(GREEN_CONCRETE_POWDER, 8)}, new ItemStack[]{new ItemStack(GREEN_CONCRETE, 8)});
        registerRecipe(4, new ItemStack[]{new ItemStack(RED_CONCRETE_POWDER, 8)}, new ItemStack[]{new ItemStack(RED_CONCRETE, 8)});
        registerRecipe(4, new ItemStack[]{new ItemStack(BLACK_CONCRETE_POWDER, 8)}, new ItemStack[]{new ItemStack(BLACK_CONCRETE, 8)});
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
        return new ItemStack(IRON_SHOVEL);
    }

    @NotNull
    @Override
    public String getInventoryTitle() {
        return "&cConcrete Factory";
    }

    @NotNull
    @Override
    public String getMachineIdentifier() {
        return "CONCRETE_FACTORY";
    }

    @Override
    public int getCapacity() {
        return 256;
    }

    @Override
    public int getEnergyConsumption() {
        return 8;
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    public BlockBreakHandler onBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            @ParametersAreNonnullByDefault
            public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
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