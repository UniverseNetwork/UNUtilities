package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SMG;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

import static org.bukkit.Material.*;

public final class SMGItems {
    public static final ItemGroup SMG_ITEM_CATEGORY = new ItemGroup(id.universenetwork.utilities.Bukkit.UNUtilities.createKey("simplematerialgenerators"), new CustomItemStack(SMOOTH_STONE, "&9Simple Material Generators"));
    public static final SlimefunItemStack SMG_GENERATOR_MULTIBLOCK = new SlimefunItemStack("SMG_GENERATOR_MULTIBLOCK", BEDROCK, "&9Generator multiblock", "", "&dBuild any of this addon's", "&dgenerators like this.", "&aThey will only output to a chest", "&adirectly above it.");
	
	/*	Template for adding more generator items
	public static final SlimefunItemStack SMG_GENERATOR_ = new SlimefunItemStack(
		"SMG_GENERATOR_",
		,
		"& generator",
		"&6Rate: &e ticks",
		"",
		"&9&oSimpleMaterialGenerators"
	);*/

    public static final SlimefunItemStack SMG_GENERATOR_COBBLESTONE = new SlimefunItemStack("SMG_GENERATOR_COBBLESTONE", COBBLESTONE, "&7Cobblestone generator", "&6Rate: &e4 ticks", "", "&9&oSimpleMaterialGenerators");
    public static final SlimefunItemStack SMG_GENERATOR_STONE_BROKEN = new SlimefunItemStack("SMG_GENERATOR_STONE_BROKEN", STONE, "&7Stone generator &8(Broken)", "&8Needs to be repaired", "", "&9&oSimpleMaterialGenerators");
    public static final SlimefunItemStack SMG_GENERATOR_STONE = new SlimefunItemStack("SMG_GENERATOR_STONE", STONE, "&7Stone generator", "&6Rate: &e8 ticks", "", "&9&oSimpleMaterialGenerators");
    public static final SlimefunItemStack SMG_GENERATOR_SMOOTH_STONE_BROKEN = new SlimefunItemStack("SMG_GENERATOR_SMOOTH_STONE_BROKEN", SMOOTH_STONE, "&7Smooth stone generator &8(Broken)", "&8Needs to be repaired", "", "&9&oSimpleMaterialGenerators");
    public static final SlimefunItemStack SMG_GENERATOR_SMOOTH_STONE = new SlimefunItemStack("SMG_GENERATOR_SMOOTH_STONE", SMOOTH_STONE, "&7Smooth stone generator", "&6Rate: &e12 ticks", "", "&9&oSimpleMaterialGenerators");
    public static final SlimefunItemStack SMG_GENERATOR_GRAVEL_BROKEN = new SlimefunItemStack("SMG_GENERATOR_GRAVEL_BROKEN", GRAVEL, "&7Gravel generator &8(Broken)", "&8Needs to be repaired", "", "&9&oSimpleMaterialGenerators");
    public static final SlimefunItemStack SMG_GENERATOR_GRAVEL = new SlimefunItemStack("SMG_GENERATOR_GRAVEL", ANDESITE, "&7Gravel generator", "&6Rate: &e6 ticks", "", "&9&oSimpleMaterialGenerators");
    public static final SlimefunItemStack SMG_GENERATOR_SAND_BROKEN = new SlimefunItemStack("SMG_GENERATOR_SAND_BROKEN", SAND, "&eSand generator &8(Broken)", "&8Needs to be repaired", "", "&9&oSimpleMaterialGenerators");
    public static final SlimefunItemStack SMG_GENERATOR_SAND = new SlimefunItemStack("SMG_GENERATOR_SAND", SANDSTONE, "&eSand generator", "&6Rate: &e8 ticks", "", "&9&oSimpleMaterialGenerators");
    public static final SlimefunItemStack SMG_GENERATOR_GLASS = new SlimefunItemStack("SMG_GENERATOR_GLASS", GLASS, "&fGlass generator", "&6Rate: &e12 ticks", "", "&9&oSimpleMaterialGenerators");
    public static final SlimefunItemStack SMG_GENERATOR_NETHERRACK = new SlimefunItemStack("SMG_GENERATOR_NETHERRACK", NETHERRACK, "&cNetherrack generator", "&6Rate: &e6 ticks", "", "&9&oSimpleMaterialGenerators");
    public static final SlimefunItemStack SMG_GENERATOR_SOUL_SAND = new SlimefunItemStack("SMG_GENERATOR_SOUL_SAND", SOUL_SAND, "&8Soul sand generator", "&6Rate: &e8 ticks", "", "&9&oSimpleMaterialGenerators");
    public static final SlimefunItemStack SMG_GENERATOR_REDSTONE_BADLY_FORMED = new SlimefunItemStack("SMG_GENERATOR_REDSTONE_BADLY_FORMED", REDSTONE_BLOCK, "&cRedstone generator &8(Badly Formed)", "&8Needs to be reformed", "", "&9&oSimpleMaterialGenerators");
    public static final SlimefunItemStack SMG_GENERATOR_REDSTONE_BROKEN = new SlimefunItemStack("SMG_GENERATOR_REDSTONE_BROKEN", REDSTONE_BLOCK, "&cRedstone generator &8(Broken)", "&8Needs to be repaired", "", "&9&oSimpleMaterialGenerators");
    public static final SlimefunItemStack SMG_GENERATOR_REDSTONE = new SlimefunItemStack("SMG_GENERATOR_REDSTONE", REDSTONE_BLOCK, "&cRedstone generator", "&6Rate: &e24 ticks", "", "&9&oSimpleMaterialGenerators");
    public static final SlimefunItemStack SMG_GENERATOR_OBSIDIAN_BADLY_FORMED = new SlimefunItemStack("SMG_GENERATOR_OBSIDIAN_BADLY_FORMED", OBSIDIAN, "&5Obsidian generator &8(Badly Formed)", "&8Needs to be reformed", "", "&9&oSimpleMaterialGenerators");
    public static final SlimefunItemStack SMG_GENERATOR_OBSIDIAN_BROKEN = new SlimefunItemStack("SMG_GENERATOR_OBSIDIAN_BROKEN", OBSIDIAN, "&5Obsidian generator &8(Broken)", "&8Needs to be repaired", "", "&9&oSimpleMaterialGenerators");
    public static final SlimefunItemStack SMG_GENERATOR_OBSIDIAN = new SlimefunItemStack("SMG_GENERATOR_OBSIDIAN", OBSIDIAN, "&5Obsidian generator", "&6Rate: &e128 ticks", "", "&9&oSimpleMaterialGenerators");
}