package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.GrowthChambers;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static org.bukkit.Material.*;

public class GrowthChamberMK2 extends id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Abstracts.AMachine {
    int[] BORDER = new int[]{};
    int[] BORDER_IN = new int[]{0, 8, 9, 10, 11, 12, 14, 15, 16, 17};
    int[] BORDER_OUT = new int[]{18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 35, 36, 44, 45, 53};
    ItemSetting<Boolean> exoticGardenIntegration = new ItemSetting<>(this, "exotic-garden-integration", true);

    public GrowthChamberMK2(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemSetting(exoticGardenIntegration);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(9, new ItemStack(COCOA_BEANS), new ItemStack(COCOA_BEANS, 9));
        registerRecipe(15, new ItemStack[]{new ItemStack(MELON_SEEDS)}, new ItemStack[]{new ItemStack(MELON, 3), new ItemStack(MELON_SEEDS, 3)});
        registerRecipe(15, new ItemStack[]{new ItemStack(PUMPKIN_SEEDS)}, new ItemStack[]{new ItemStack(PUMPKIN, 3), new ItemStack(PUMPKIN_SEEDS, 3)});
        registerRecipe(15, new ItemStack[]{new ItemStack(BEETROOT_SEEDS)}, new ItemStack[]{new ItemStack(BEETROOT, 9), new ItemStack(BEETROOT_SEEDS, 6)});
        registerRecipe(12, new ItemStack[]{new ItemStack(WHEAT_SEEDS)}, new ItemStack[]{new ItemStack(WHEAT, 9), new ItemStack(WHEAT_SEEDS, 6)});
        registerRecipe(9, new ItemStack(APPLE), new ItemStack(APPLE, 9));
        registerRecipe(9, new ItemStack(BROWN_MUSHROOM), new ItemStack(BROWN_MUSHROOM, 9));
        registerRecipe(9, new ItemStack(RED_MUSHROOM), new ItemStack(RED_MUSHROOM, 9));
        registerRecipe(9, new ItemStack[]{new ItemStack(DEAD_BUSH)}, new ItemStack[]{new ItemStack(DEAD_BUSH, 9), new ItemStack(STICK, 6)});
        registerRecipe(9, new ItemStack(GRASS), new ItemStack(GRASS, 9));
        registerRecipe(12, new ItemStack(TALL_GRASS), new ItemStack(TALL_GRASS, 9));
        registerRecipe(9, new ItemStack(FERN), new ItemStack(FERN, 9));
        registerRecipe(12, new ItemStack(LARGE_FERN), new ItemStack(LARGE_FERN, 9));
        registerRecipe(9, new ItemStack(VINE), new ItemStack(VINE, 9));

        // Flowers
        registerRecipe(9, new ItemStack(DANDELION), new ItemStack(DANDELION, 9));
        registerRecipe(9, new ItemStack(POPPY), new ItemStack(POPPY, 3));
        registerRecipe(9, new ItemStack(BLUE_ORCHID), new ItemStack(BLUE_ORCHID, 9));
        registerRecipe(9, new ItemStack(ALLIUM), new ItemStack(ALLIUM, 9));
        registerRecipe(9, new ItemStack(AZURE_BLUET), new ItemStack(AZURE_BLUET, 9));
        registerRecipe(9, new ItemStack(RED_TULIP), new ItemStack(RED_TULIP, 9));
        registerRecipe(9, new ItemStack(ORANGE_TULIP), new ItemStack(ORANGE_TULIP, 9));
        registerRecipe(9, new ItemStack(WHITE_TULIP), new ItemStack(WHITE_TULIP, 9));
        registerRecipe(9, new ItemStack(PINK_TULIP), new ItemStack(PINK_TULIP, 9));
        registerRecipe(9, new ItemStack(OXEYE_DAISY), new ItemStack(OXEYE_DAISY, 9));
        registerRecipe(9, new ItemStack(CORNFLOWER), new ItemStack(CORNFLOWER, 9));
        registerRecipe(9, new ItemStack(LILY_OF_THE_VALLEY), new ItemStack(LILY_OF_THE_VALLEY, 9));
        registerRecipe(12, new ItemStack(WITHER_ROSE), new ItemStack(WITHER_ROSE, 6));
        registerRecipe(12, new ItemStack(SUNFLOWER), new ItemStack(SUNFLOWER, 6));
        registerRecipe(12, new ItemStack(LILAC), new ItemStack(LILAC, 6));
        registerRecipe(12, new ItemStack(ROSE_BUSH), new ItemStack(ROSE_BUSH, 6));
        registerRecipe(12, new ItemStack(PEONY), new ItemStack(PEONY, 6));

        registerRecipe(12, new ItemStack(CARROT), new ItemStack(CARROT, 9));
        registerRecipe(12, new ItemStack(POTATO), new ItemStack(POTATO, 9));
        registerRecipe(12, new ItemStack(SWEET_BERRIES), new ItemStack(SWEET_BERRIES, 9));
        registerRecipe(12, new ItemStack(SUGAR_CANE), new ItemStack(SUGAR_CANE, 9));
        registerRecipe(12, new ItemStack(BAMBOO), new ItemStack(BAMBOO, 9));
        registerRecipe(12, new ItemStack(CACTUS), new ItemStack(CACTUS, 9));

        registerRecipe(30, new ItemStack[]{new ItemStack(OAK_SAPLING)}, new ItemStack[]{new ItemStack(OAK_SAPLING, 9), new ItemStack(OAK_LOG, 18), new ItemStack(APPLE, 6), new ItemStack(OAK_LEAVES, 9), new ItemStack(STICK, 6)});
        registerRecipe(30, new ItemStack[]{new ItemStack(BIRCH_SAPLING)}, new ItemStack[]{new ItemStack(BIRCH_SAPLING, 9), new ItemStack(BIRCH_LOG, 18), new ItemStack(APPLE, 6), new ItemStack(BIRCH_LEAVES, 9), new ItemStack(STICK, 6)});
        registerRecipe(30, new ItemStack[]{new ItemStack(SPRUCE_SAPLING)}, new ItemStack[]{new ItemStack(SPRUCE_SAPLING, 9), new ItemStack(SPRUCE_LOG, 18), new ItemStack(APPLE, 6), new ItemStack(SPRUCE_LEAVES, 9), new ItemStack(STICK, 6)});
        registerRecipe(30, new ItemStack[]{new ItemStack(DARK_OAK_SAPLING)}, new ItemStack[]{new ItemStack(DARK_OAK_SAPLING, 9), new ItemStack(DARK_OAK_LOG, 18), new ItemStack(APPLE, 6), new ItemStack(DARK_OAK_LEAVES, 9), new ItemStack(STICK, 6)});
        registerRecipe(30, new ItemStack[]{new ItemStack(JUNGLE_SAPLING)}, new ItemStack[]{new ItemStack(JUNGLE_SAPLING, 9), new ItemStack(JUNGLE_LOG, 18), new ItemStack(APPLE, 6), new ItemStack(JUNGLE_LEAVES, 9), new ItemStack(STICK, 6)});
        registerRecipe(30, new ItemStack[]{new ItemStack(ACACIA_SAPLING)}, new ItemStack[]{new ItemStack(ACACIA_SAPLING, 9), new ItemStack(ACACIA_LOG, 18), new ItemStack(APPLE, 6), new ItemStack(ACACIA_LEAVES, 9), new ItemStack(STICK, 6)});
    }

    @Override
    public MachineRecipe findNextRecipe(me.mrCookieSlime.Slimefun.api.inventory.BlockMenu inv) {
        if (id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTech.isExoticGardenInstalled && exoticGardenIntegration.getValue()) {
            for (int inputSlot : getInputSlots()) {
                ItemStack item = inv.getItemInSlot(inputSlot);
                if (item != null && SlimefunItem.getByItem(item) != null) {
                    SlimefunItem sfItem = SlimefunItem.getByItem(item);
                    if (sfItem.getId().contains("_BUSH") || sfItem.getId().contains("_PLANT") || sfItem.getId().contains("_SAPLING")) {
                        if (sfItem.getId().contains("_BUSH")) {
                            ItemStack fruit = SlimefunItem.getById(sfItem.getId().replace("_BUSH", "")).getItem();
                            MachineRecipe recipe = new MachineRecipe(20, new ItemStack[]{sfItem.getItem()}, new ItemStack[]{sfItem.getItem(), fruit});
                            inv.consumeItem(inputSlot);
                            return recipe;
                        } else if (sfItem.getId().contains("_PLANT")) {
                            ItemStack fruit = SlimefunItem.getById(sfItem.getId().replace("_PLANT", "")) != null ? SlimefunItem.getById(sfItem.getId().replace("_PLANT", "")).getItem() : SlimefunItem.getById(sfItem.getId().replace("_PLANT", "_ESSENCE")).getItem();
                            MachineRecipe recipe = new MachineRecipe(10, new ItemStack[]{sfItem.getItem()}, new ItemStack[]{sfItem.getItem(), fruit});
                            inv.consumeItem(inputSlot);
                            return recipe;
                        } else if (sfItem.getId().contains("_SAPLING")) {
                            ItemStack fruit = SlimefunItem.getById(sfItem.getId().replace("_SAPLING", "")).getItem();
                            MachineRecipe recipe = new MachineRecipe(60, new ItemStack[]{sfItem.getItem()}, new ItemStack[]{sfItem.getItem(), fruit});
                            inv.consumeItem(inputSlot);
                            return recipe;
                        }
                    }
                }
            }
        }
        return super.findNextRecipe(inv);
    }

    @Override
    public boolean isGraphical() {
        return true;
    }

    @Override
    public String getMachineIdentifier() {
        return "GROWTH_CHAMBER_MK2";
    }

    @Override
    public List<int[]> getBorders() {
        List<int[]> borders = new java.util.ArrayList<>();
        borders.add(BORDER);
        borders.add(BORDER_IN);
        borders.add(BORDER_OUT);
        return borders;
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{1, 2, 3, 4, 5, 6, 7};
    }

    @Override
    public int[] getOutputSlots() {
        return new int[]{28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43, 46, 47, 48, 49, 50, 51, 52};
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(DIAMOND_HOE);
    }

    @Override
    public int getProgressBarSlot() {
        return 13;
    }
}