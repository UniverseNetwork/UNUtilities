package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.SMG;

import id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.SMG.Items.BrokenGenerator;
import id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.SMG.Items.MaterialGenerator;
import id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.SfAddon;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.COMPOSTER;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;
import static org.bukkit.Material.*;

public final class SMG extends SfAddon {
    @Override
    public void Load() {
        new id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.SMG.Items.GeneratorMultiblock(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_MULTIBLOCK).register(this);
		/*	Template for adding more items
		new MaterialGenerator(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, null, null, null, null, null, null, null, null}).setItem().setRate(1).register(this);*/
        new MaterialGenerator(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_COBBLESTONE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(SMOOTH_STONE), new ItemStack(SMOOTH_STONE), new ItemStack(SMOOTH_STONE), new ItemStack(LAVA_BUCKET), new ItemStack(IRON_PICKAXE), new ItemStack(WATER_BUCKET), new ItemStack(SMOOTH_STONE), new ItemStack(SMOOTH_STONE), new ItemStack(SMOOTH_STONE)}, 4).setItem(COBBLESTONE).register(this);
        new BrokenGenerator(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_STONE_BROKEN, RecipeType.SMELTERY, new ItemStack[]{SMGItems.SMG_GENERATOR_COBBLESTONE, null, null, null, null, null, null, null, null}).register(this);
        new MaterialGenerator(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_STONE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(STONE), SOLDER_INGOT, new ItemStack(STONE), SOLDER_INGOT, SMGItems.SMG_GENERATOR_STONE_BROKEN, SOLDER_INGOT, new ItemStack(STONE), SOLDER_INGOT, new ItemStack(STONE)}, 8).setItem(STONE).register(this);
        new BrokenGenerator(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_SMOOTH_STONE_BROKEN, RecipeType.SMELTERY, new ItemStack[]{SMGItems.SMG_GENERATOR_STONE, null, null, null, null, null, null, null, null}).register(this);
        new MaterialGenerator(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_SMOOTH_STONE, RecipeType.SMELTERY, new ItemStack[]{new ItemStack(SMOOTH_STONE), STEEL_INGOT, new ItemStack(SMOOTH_STONE), new ItemStack(IRON_BARS), SMGItems.SMG_GENERATOR_SMOOTH_STONE_BROKEN, new ItemStack(IRON_BARS), new ItemStack(SMOOTH_STONE), STEEL_INGOT, new ItemStack(SMOOTH_STONE)}, 12).setItem(SMOOTH_STONE).register(this);
        new BrokenGenerator(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_GRAVEL_BROKEN, RecipeType.GRIND_STONE, new ItemStack[]{SMGItems.SMG_GENERATOR_COBBLESTONE, null, null, null, null, null, null, null, null}).register(this);
        new MaterialGenerator(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_GRAVEL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(STONE_BRICKS), BILLON_INGOT, new ItemStack(STONE_BRICKS), BILLON_INGOT, SMGItems.SMG_GENERATOR_GRAVEL_BROKEN, BILLON_INGOT, new ItemStack(STONE_BRICKS), BILLON_INGOT, new ItemStack(STONE_BRICKS)}, 6).setItem(GRAVEL).register(this);
        new BrokenGenerator(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_SAND_BROKEN, RecipeType.ORE_CRUSHER, new ItemStack[]{SMGItems.SMG_GENERATOR_GRAVEL, null, null, null, null, null, null, null, null}).register(this);
        new MaterialGenerator(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_SAND, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(SANDSTONE), DURALUMIN_INGOT, new ItemStack(SANDSTONE), DURALUMIN_INGOT, SMGItems.SMG_GENERATOR_SAND_BROKEN, DURALUMIN_INGOT, new ItemStack(SANDSTONE), DURALUMIN_INGOT, new ItemStack(SANDSTONE)}, 8).setItem(SAND).register(this);
        new MaterialGenerator(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_GLASS, RecipeType.SMELTERY, new ItemStack[]{BRONZE_INGOT, GOLD_8K, BRONZE_INGOT, BRASS_INGOT, SMGItems.SMG_GENERATOR_SAND, BRASS_INGOT, null, null, null}, 12).setItem(GLASS).register(this);
        new MaterialGenerator(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_NETHERRACK, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{SOLDER_INGOT, SOLDER_INGOT, SOLDER_INGOT, SMGItems.SMG_GENERATOR_STONE, new ItemStack(LAVA_BUCKET), SMGItems.SMG_GENERATOR_STONE, SMGItems.SMG_GENERATOR_STONE, COMPOSTER, SMGItems.SMG_GENERATOR_STONE}, 6).setItem(NETHERRACK).register(this);
        new MaterialGenerator(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_SOUL_SAND, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{STEEL_INGOT, new ItemStack(SPIDER_EYE), STEEL_INGOT, new ItemStack(ROTTEN_FLESH), new ItemStack(WATER_BUCKET), new ItemStack(BONE), SMGItems.SMG_GENERATOR_SAND, COMPOSTER, SMGItems.SMG_GENERATOR_SAND}, 8).setItem(SOUL_SAND).register(this);
        new BrokenGenerator(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_REDSTONE_BADLY_FORMED, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{SMGItems.SMG_GENERATOR_SAND, null, SMGItems.SMG_GENERATOR_GRAVEL, null, null, null, null, null, null}).register(this);
        new BrokenGenerator(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_REDSTONE_BROKEN, RecipeType.ORE_CRUSHER, new ItemStack[]{SMGItems.SMG_GENERATOR_REDSTONE_BADLY_FORMED, null, null, null, null, null, null, null, null}).register(this);
        new MaterialGenerator(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_REDSTONE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{SMGItems.SMG_GENERATOR_REDSTONE_BROKEN, new ItemStack(REDSTONE_ORE), SMGItems.SMG_GENERATOR_REDSTONE_BROKEN, SMGItems.SMG_GENERATOR_REDSTONE_BROKEN, SMGItems.SMG_GENERATOR_GRAVEL, SMGItems.SMG_GENERATOR_REDSTONE_BROKEN, DAMASCUS_STEEL_INGOT, DURALUMIN_INGOT, DAMASCUS_STEEL_INGOT}, 24).setItem(REDSTONE).register(this);
        new BrokenGenerator(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_OBSIDIAN_BADLY_FORMED, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{SMGItems.SMG_GENERATOR_COBBLESTONE, null, SMGItems.SMG_GENERATOR_STONE, null, COBALT_PICKAXE, null, SMGItems.SMG_GENERATOR_SMOOTH_STONE, null, SMGItems.SMG_GENERATOR_NETHERRACK}).register(this);
        new BrokenGenerator(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_OBSIDIAN_BROKEN, RecipeType.ORE_CRUSHER, new ItemStack[]{SMGItems.SMG_GENERATOR_OBSIDIAN_BADLY_FORMED, null, null, null, null, null, null, null, null}).register(this);
        new MaterialGenerator(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_OBSIDIAN, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{REINFORCED_ALLOY_INGOT, new ItemStack(WATER_BUCKET), REINFORCED_ALLOY_INGOT, SMGItems.SMG_GENERATOR_OBSIDIAN_BROKEN, SMGItems.SMG_GENERATOR_STONE, SMGItems.SMG_GENERATOR_OBSIDIAN_BROKEN, SMGItems.SMG_GENERATOR_OBSIDIAN_BROKEN, new ItemStack(LAVA_BUCKET), SMGItems.SMG_GENERATOR_OBSIDIAN_BROKEN}, 128).setItem(OBSIDIAN).register(this);
    }
}