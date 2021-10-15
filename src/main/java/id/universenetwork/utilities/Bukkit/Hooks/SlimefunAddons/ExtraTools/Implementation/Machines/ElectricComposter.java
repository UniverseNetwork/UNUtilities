package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Implementation.Machines;

import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
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

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Lists.ETItems.*;
import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;

public abstract class ElectricComposter extends AContainer implements RecipeDisplayItem {
    final Tier tier;

    public ElectricComposter(Tier tier) {
        super(extra_tools, tier == Tier.ONE ? ELECTRIC_COMPOSTER : ELECTRIC_COMPOSTER_2, ENHANCED_CRAFTING_TABLE, tier.recipe);
        this.tier = tier;
        addItemHandler(onBreak());
    }

    @Override
    protected void registerDefaultRecipes() {
        for (Material leave : SlimefunTag.LEAVES.getValues())
            registerRecipe(8, new ItemStack[]{new ItemStack(leave, 8)}, new ItemStack[]{new ItemStack(Material.DIRT)});
        for (Material sapling : SlimefunTag.SAPLINGS.getValues())
            registerRecipe(8, new ItemStack[]{new ItemStack(sapling, 8)}, new ItemStack[]{new ItemStack(Material.DIRT)});
        registerRecipe(8, new ItemStack[]{new ItemStack(Material.STONE, 4)}, new ItemStack[]{new ItemStack(Material.NETHERRACK)});
        registerRecipe(8, new ItemStack[]{new ItemStack(Material.SAND, 2)}, new ItemStack[]{new ItemStack(Material.SOUL_SAND)});
        registerRecipe(8, new ItemStack[]{new ItemStack(Material.WHEAT, 4)}, new ItemStack[]{new ItemStack(Material.NETHER_WART)});
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
        return new ItemStack(Material.WOODEN_HOE);
    }

    @NotNull
    @Override
    public String getInventoryTitle() {
        return "&cElectric Composter";
    }

    @NotNull
    @Override
    public String getMachineIdentifier() {
        return "ELECTRIC_COMPOSTER_" + tier.name();
    }

    @Override
    public int getCapacity() {
        return 256;
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

    public enum Tier {
        ONE(new ItemStack[]{GILDED_IRON, MAGNESIUM_INGOT, GILDED_IRON, ELECTRIC_MOTOR, COMPOSTER, ELECTRIC_MOTOR, new ItemStack(Material.IRON_HOE), MEDIUM_CAPACITOR, new ItemStack(Material.IRON_HOE)}),
        TWO(new ItemStack[]{HARDENED_METAL_INGOT, BLISTERING_INGOT_3, HARDENED_METAL_INGOT, ELECTRIC_MOTOR, ELECTRIC_COMPOSTER, ELECTRIC_MOTOR, new ItemStack(Material.DIAMOND_HOE), LARGE_CAPACITOR, new ItemStack(Material.DIAMOND_HOE)});
        final ItemStack[] recipe;

        Tier(ItemStack[] recipe) {
            this.recipe = recipe;
        }
    }
}