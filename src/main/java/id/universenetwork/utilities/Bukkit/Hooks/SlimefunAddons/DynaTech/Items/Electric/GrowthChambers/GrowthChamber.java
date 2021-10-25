package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.GrowthChambers;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Material.*;

public class GrowthChamber extends id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Abstracts.AMachine {
    final ItemSetting<Boolean> exoticGardenIntegration = new ItemSetting<>(this, "exotic-garden-integration", true);

    public GrowthChamber(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemSetting(exoticGardenIntegration);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(9, new ItemStack(COCOA_BEANS), new ItemStack(COCOA_BEANS, 3));
        registerRecipe(15, new ItemStack[]{new ItemStack(MELON_SEEDS)}, new ItemStack[]{new ItemStack(MELON, 1), new ItemStack(MELON_SEEDS, 1)});
        registerRecipe(15, new ItemStack[]{new ItemStack(PUMPKIN_SEEDS)}, new ItemStack[]{new ItemStack(PUMPKIN, 1), new ItemStack(PUMPKIN_SEEDS, 1)});
        registerRecipe(15, new ItemStack[]{new ItemStack(BEETROOT_SEEDS)}, new ItemStack[]{new ItemStack(BEETROOT, 3), new ItemStack(BEETROOT_SEEDS, 2)});
        registerRecipe(12, new ItemStack[]{new ItemStack(WHEAT_SEEDS)}, new ItemStack[]{new ItemStack(WHEAT, 3), new ItemStack(WHEAT_SEEDS, 2)});
        registerRecipe(9, new ItemStack(APPLE), new ItemStack(APPLE, 3));
        registerRecipe(9, new ItemStack(BROWN_MUSHROOM), new ItemStack(BROWN_MUSHROOM, 3));
        registerRecipe(9, new ItemStack(RED_MUSHROOM), new ItemStack(RED_MUSHROOM, 3));
        registerRecipe(9, new ItemStack[]{new ItemStack(DEAD_BUSH)}, new ItemStack[]{new ItemStack(DEAD_BUSH, 3), new ItemStack(STICK, 2)});
        registerRecipe(9, new ItemStack(GRASS), new ItemStack(GRASS, 3));
        registerRecipe(12, new ItemStack(TALL_GRASS), new ItemStack(TALL_GRASS, 3));
        registerRecipe(9, new ItemStack(FERN), new ItemStack(FERN, 3));
        registerRecipe(12, new ItemStack(LARGE_FERN), new ItemStack(LARGE_FERN, 3));
        registerRecipe(9, new ItemStack(VINE), new ItemStack(VINE, 3));

        // Flowers
        registerRecipe(9, new ItemStack(DANDELION), new ItemStack(DANDELION, 3));
        registerRecipe(9, new ItemStack(POPPY), new ItemStack(POPPY, 3));
        registerRecipe(9, new ItemStack(BLUE_ORCHID), new ItemStack(BLUE_ORCHID, 3));
        registerRecipe(9, new ItemStack(ALLIUM), new ItemStack(ALLIUM, 3));
        registerRecipe(9, new ItemStack(AZURE_BLUET), new ItemStack(AZURE_BLUET, 3));
        registerRecipe(9, new ItemStack(RED_TULIP), new ItemStack(RED_TULIP, 3));
        registerRecipe(9, new ItemStack(ORANGE_TULIP), new ItemStack(ORANGE_TULIP, 3));
        registerRecipe(9, new ItemStack(WHITE_TULIP), new ItemStack(WHITE_TULIP, 3));
        registerRecipe(9, new ItemStack(PINK_TULIP), new ItemStack(PINK_TULIP, 3));
        registerRecipe(9, new ItemStack(OXEYE_DAISY), new ItemStack(OXEYE_DAISY, 3));
        registerRecipe(9, new ItemStack(CORNFLOWER), new ItemStack(CORNFLOWER, 3));
        registerRecipe(9, new ItemStack(LILY_OF_THE_VALLEY), new ItemStack(LILY_OF_THE_VALLEY, 3));
        registerRecipe(12, new ItemStack(WITHER_ROSE), new ItemStack(WITHER_ROSE, 2));
        registerRecipe(12, new ItemStack(SUNFLOWER), new ItemStack(SUNFLOWER, 2));
        registerRecipe(12, new ItemStack(LILAC), new ItemStack(LILAC, 2));
        registerRecipe(12, new ItemStack(ROSE_BUSH), new ItemStack(ROSE_BUSH, 2));
        registerRecipe(12, new ItemStack(PEONY), new ItemStack(PEONY, 2));

        registerRecipe(12, new ItemStack(CARROT), new ItemStack(CARROT, 3));
        registerRecipe(12, new ItemStack(POTATO), new ItemStack(POTATO, 3));
        registerRecipe(12, new ItemStack(SWEET_BERRIES), new ItemStack(SWEET_BERRIES, 3));
        registerRecipe(12, new ItemStack(SUGAR_CANE), new ItemStack(SUGAR_CANE, 3));
        registerRecipe(12, new ItemStack(BAMBOO), new ItemStack(BAMBOO, 3));
        registerRecipe(12, new ItemStack(CACTUS), new ItemStack(CACTUS, 3));

        registerRecipe(30, new ItemStack[]{new ItemStack(OAK_SAPLING)}, new ItemStack[]{new ItemStack(OAK_SAPLING, 3), new ItemStack(OAK_LOG, 6)});
        registerRecipe(30, new ItemStack[]{new ItemStack(BIRCH_SAPLING)}, new ItemStack[]{new ItemStack(BIRCH_SAPLING, 3), new ItemStack(BIRCH_LOG, 6)});
        registerRecipe(30, new ItemStack[]{new ItemStack(SPRUCE_SAPLING)}, new ItemStack[]{new ItemStack(SPRUCE_SAPLING, 3), new ItemStack(SPRUCE_LOG, 6)});
        registerRecipe(30, new ItemStack[]{new ItemStack(DARK_OAK_SAPLING)}, new ItemStack[]{new ItemStack(DARK_OAK_SAPLING, 3), new ItemStack(DARK_OAK_LOG, 6)});
        registerRecipe(30, new ItemStack[]{new ItemStack(JUNGLE_SAPLING)}, new ItemStack[]{new ItemStack(JUNGLE_SAPLING, 3), new ItemStack(JUNGLE_LOG, 6)});
        registerRecipe(30, new ItemStack[]{new ItemStack(ACACIA_SAPLING)}, new ItemStack[]{new ItemStack(ACACIA_SAPLING, 3), new ItemStack(ACACIA_LOG, 6)});
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
                            MachineRecipe recipe = new MachineRecipe(21, new ItemStack[]{sfItem.getItem()}, new ItemStack[]{sfItem.getItem(), fruit});
                            inv.consumeItem(inputSlot);
                            return recipe;
                        } else if (sfItem.getId().contains("_PLANT")) {
                            ItemStack fruit = SlimefunItem.getById(sfItem.getId().replace("_PLANT", "")) != null ? SlimefunItem.getById(sfItem.getId().replace("_PLANT", "")).getItem() : SlimefunItem.getById(sfItem.getId().replace("_PLANT", "_ESSENCE")).getItem();
                            MachineRecipe recipe = new MachineRecipe(12, new ItemStack[]{sfItem.getItem()}, new ItemStack[]{sfItem.getItem(), fruit});
                            inv.consumeItem(inputSlot);
                            return recipe;
                        } else if (sfItem.getId().contains("_SAPLING")) {
                            ItemStack fruit = SlimefunItem.getById(sfItem.getId().replace("_SAPLING", "")).getItem();
                            fruit.setAmount(3);
                            MachineRecipe recipe = new MachineRecipe(30, new ItemStack[]{sfItem.getItem()}, new ItemStack[]{sfItem.getItem(), fruit});
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
    public ItemStack getProgressBar() {
        return new ItemStack(IRON_HOE);
    }

    @Override
    public String getMachineIdentifier() {
        return "GROWTH_CHAMBER";
    }
}