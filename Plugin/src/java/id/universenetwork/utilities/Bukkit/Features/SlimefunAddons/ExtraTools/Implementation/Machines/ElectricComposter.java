package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.ExtraTools.Implementation.Machines;

import id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.ExtraTools.Lists.ETItems;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;

public abstract class ElectricComposter extends me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer implements io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem {
    final Tier tier;

    public ElectricComposter(Tier tier) {
        super(ETItems.extra_tools, tier == Tier.ONE ? ETItems.ELECTRIC_COMPOSTER : ETItems.ELECTRIC_COMPOSTER_2, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE, tier.recipe);
        this.tier = tier;
        addItemHandler(onBreak());
    }

    @Override
    protected void registerDefaultRecipes() {
        for (Material leave : Tag.LEAVES.getValues())
            registerRecipe(8, new ItemStack[]{new ItemStack(leave, 8)}, new ItemStack[]{new ItemStack(Material.DIRT)});
        for (Material sapling : Tag.SAPLINGS.getValues())
            registerRecipe(8, new ItemStack[]{new ItemStack(sapling, 8)}, new ItemStack[]{new ItemStack(Material.DIRT)});
        registerRecipe(8, new ItemStack[]{new ItemStack(Material.STONE, 4)}, new ItemStack[]{new ItemStack(Material.NETHERRACK)});
        registerRecipe(8, new ItemStack[]{new ItemStack(Material.SAND, 2)}, new ItemStack[]{new ItemStack(Material.SOUL_SAND)});
        registerRecipe(8, new ItemStack[]{new ItemStack(Material.WHEAT, 4)}, new ItemStack[]{new ItemStack(Material.NETHER_WART)});
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
        return new ItemStack(Material.WOODEN_HOE);
    }

    @Override
    public String getInventoryTitle() {
        return getId().equals("ELECTRIC_COMPOSTER") ? "&cElectric Composter" : "&cElectric Composter &7(&eII&7)";
    }

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

    public enum Tier {
        ONE(new ItemStack[]{GILDED_IRON, MAGNESIUM_INGOT, GILDED_IRON, ELECTRIC_MOTOR, COMPOSTER, ELECTRIC_MOTOR, new ItemStack(Material.IRON_HOE), MEDIUM_CAPACITOR, new ItemStack(Material.IRON_HOE)}),
        TWO(new ItemStack[]{HARDENED_METAL_INGOT, BLISTERING_INGOT_3, HARDENED_METAL_INGOT, ELECTRIC_MOTOR, ETItems.ELECTRIC_COMPOSTER, ELECTRIC_MOTOR, new ItemStack(Material.DIAMOND_HOE), LARGE_CAPACITOR, new ItemStack(Material.DIAMOND_HOE)});
        final ItemStack[] recipe;

        Tier(ItemStack[] recipe) {
            this.recipe = recipe;
        }
    }
}