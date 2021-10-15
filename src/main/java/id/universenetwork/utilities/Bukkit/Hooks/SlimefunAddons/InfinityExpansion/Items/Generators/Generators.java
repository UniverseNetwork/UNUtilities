package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Generators;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Blocks.InfinityWorkbench;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.SlimefunExtension;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Groups.Groups.*;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Materials.Materials.*;
import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.MachineLore.energyBuffer;
import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.MachineLore.energyPerSecond;
import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE;

@UtilityClass
public final class Generators {
    static final int HYDRO_ENERGY = 5;
    static final int ADVANCED_HYDRO_ENERGY = 45;
    static final int GEO_ENERGY = 35;
    static final int ADVANCED_GEO_ENERGY = 210;
    static final int BASIC_SOLAR_ENERGY = 9;
    static final int ADVANCED_SOLAR_ENERGY = 150;
    static final int CELESTIAL_ENERGY = 750;
    static final int VOID_ENERGY = 3000;
    static final int INFINITY_ENERGY = 60_000;
    static final int INFINITY_REACTOR_ENERGY = 120_000;
    public static final SlimefunItemStack INFINITY_REACTOR = new SlimefunItemStack("INFINITY_REACTOR", Material.BEACON, "&bInfinity Reactor", "&7Generates power through the compression", "&7of &8Void &7and &bInfinity &7Ingots", "", energyBuffer(INFINITY_REACTOR_ENERGY * 1000), energyPerSecond(INFINITY_REACTOR_ENERGY));
    public static final SlimefunItemStack HYDRO = new SlimefunItemStack("HYDRO_GENERATOR", Material.PRISMARINE_WALL, "&9Hydro Generator", "&7Generates energy from the movement of water", "", energyBuffer(HYDRO_ENERGY * 100), energyPerSecond(HYDRO_ENERGY));
    public static final SlimefunItemStack REINFORCED_HYDRO = new SlimefunItemStack("REINFORCED_HYDRO_GENERATOR", Material.END_STONE_BRICK_WALL, "&fReinforced &9Hydro Gen", "&7Generates large amounts of energy", "&7from the movement of water", "", energyBuffer(ADVANCED_HYDRO_ENERGY * 100), energyPerSecond(ADVANCED_HYDRO_ENERGY));
    public static final SlimefunItemStack GEOTHERMAL = new SlimefunItemStack("GEOTHERMAL_GENERATOR", Material.MAGMA_BLOCK, "&cGeothermal Generator", "&7Generates energy from the heat of the world", "", energyBuffer(GEO_ENERGY * 100), energyPerSecond(GEO_ENERGY));
    public static final SlimefunItemStack REINFORCED_GEOTHERMAL = new SlimefunItemStack("REINFORCED_GEOTHERMAL_GENERATOR", Material.SHROOMLIGHT, "&fReinforced &cGeothermal Gen", "&7Generates large amounts of energy", "&7from the heat of the world", "", energyBuffer(ADVANCED_GEO_ENERGY * 100), energyPerSecond(ADVANCED_GEO_ENERGY));
    public static final SlimefunItemStack BASIC_PANEL = new SlimefunItemStack("BASIC_PANEL", Material.BLUE_GLAZED_TERRACOTTA, "&9Basic Solar Panel", "&7Generates energy from the sun", "", energyBuffer(BASIC_SOLAR_ENERGY * 100), energyPerSecond(BASIC_SOLAR_ENERGY));
    public static final SlimefunItemStack ADVANCED_PANEL = new SlimefunItemStack("ADVANCED_PANEL", Material.RED_GLAZED_TERRACOTTA, "&cAdvanced Solar Panel", "&7Generates energy from the sun", "", energyBuffer(ADVANCED_SOLAR_ENERGY * 100), energyPerSecond(ADVANCED_SOLAR_ENERGY));
    public static final SlimefunItemStack CELESTIAL_PANEL = new SlimefunItemStack("CELESTIAL_PANEL", Material.YELLOW_GLAZED_TERRACOTTA, "&eCelestial Panel", "&7Generates energy from the sun", "", energyBuffer(CELESTIAL_ENERGY * 100), energyPerSecond(CELESTIAL_ENERGY));
    public static final SlimefunItemStack VOID_PANEL = new SlimefunItemStack("VOID_PANEL", Material.LIGHT_GRAY_GLAZED_TERRACOTTA, "&8Void Panel", "&7Generates energy from darkness", "", energyBuffer(VOID_ENERGY * 100), energyPerSecond(VOID_ENERGY));
    public static final SlimefunItemStack INFINITE_PANEL = new SlimefunItemStack("INFINITE_PANEL", Material.LIGHT_BLUE_GLAZED_TERRACOTTA, "&bInfinity Panel", "&7Generates energy from the cosmos", "", energyBuffer(INFINITY_ENERGY * 100), energyPerSecond(INFINITY_ENERGY));

    public static void setup() {
        new InfinityReactor(INFINITY_CHEAT, INFINITY_REACTOR, InfinityWorkbench.TYPE, new ItemStack[]{null, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, null, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, INFINITE_INGOT, INFINITE_INGOT, MACHINE_PLATE, SlimefunExtension.ADVANCED_NETHER_STAR_REACTOR, SlimefunExtension.ADVANCED_NETHER_STAR_REACTOR, MACHINE_PLATE, INFINITE_INGOT, INFINITE_INGOT, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, INFINITE_INGOT, INFINITE_INGOT, INFINITE_CIRCUIT, INFINITE_CORE, INFINITE_CORE, INFINITE_CIRCUIT, INFINITE_INGOT}, INFINITY_REACTOR_ENERGY).register(addon);
        new EnergyGenerator(BASIC_MACHINES, HYDRO, ENHANCED_CRAFTING_TABLE, new ItemStack[]{MAGSTEEL, MACHINE_CIRCUIT, MAGSTEEL, new ItemStack(Material.BUCKET), SlimefunItems.ELECTRO_MAGNET, new ItemStack(Material.BUCKET), MAGSTEEL, MACHINE_CIRCUIT, MAGSTEEL}, HYDRO_ENERGY, GenerationType.HYDROELECTRIC).register(addon);
        new EnergyGenerator(ADVANCED_MACHINES, REINFORCED_HYDRO, ENHANCED_CRAFTING_TABLE, new ItemStack[]{HYDRO, MACHINE_CIRCUIT, HYDRO, MAGSTEEL_PLATE, MACHINE_CORE, MAGSTEEL_PLATE, HYDRO, MACHINE_CIRCUIT, HYDRO}, ADVANCED_HYDRO_ENERGY, GenerationType.HYDROELECTRIC).register(addon);
        new EnergyGenerator(ADVANCED_MACHINES, GEOTHERMAL, ENHANCED_CRAFTING_TABLE, new ItemStack[]{MAGSTEEL_PLATE, MAGSTEEL_PLATE, MAGSTEEL_PLATE, SlimefunItems.LAVA_GENERATOR_2, SlimefunItems.LAVA_GENERATOR_2, SlimefunItems.LAVA_GENERATOR_2, MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT}, GEO_ENERGY, GenerationType.GEOTHERMAL).register(addon);
        new EnergyGenerator(ADVANCED_MACHINES, REINFORCED_GEOTHERMAL, ENHANCED_CRAFTING_TABLE, new ItemStack[]{GEOTHERMAL, MACHINE_CIRCUIT, GEOTHERMAL, MACHINE_PLATE, MACHINE_CORE, MACHINE_PLATE, GEOTHERMAL, MACHINE_CIRCUIT, GEOTHERMAL}, ADVANCED_GEO_ENERGY, GenerationType.GEOTHERMAL).register(addon);
        new EnergyGenerator(BASIC_MACHINES, BASIC_PANEL, ENHANCED_CRAFTING_TABLE, new ItemStack[]{MAGSTEEL, MAGSTEEL_PLATE, MAGSTEEL, SlimefunItems.SOLAR_PANEL, SlimefunItems.SOLAR_PANEL, SlimefunItems.SOLAR_PANEL, MACHINE_CIRCUIT, MACHINE_CIRCUIT, MACHINE_CIRCUIT}, BASIC_SOLAR_ENERGY, GenerationType.SOLAR).register(addon);
        new EnergyGenerator(ADVANCED_MACHINES, ADVANCED_PANEL, ENHANCED_CRAFTING_TABLE, new ItemStack[]{BASIC_PANEL, BASIC_PANEL, BASIC_PANEL, TITANIUM, SlimefunItems.SOLAR_GENERATOR_4, TITANIUM, MACHINE_CIRCUIT, MACHINE_CIRCUIT, MACHINE_CIRCUIT}, ADVANCED_SOLAR_ENERGY, GenerationType.SOLAR).register(addon);
        new EnergyGenerator(ADVANCED_MACHINES, CELESTIAL_PANEL, ENHANCED_CRAFTING_TABLE, new ItemStack[]{MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, ADVANCED_PANEL, ADVANCED_PANEL, ADVANCED_PANEL, MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT}, CELESTIAL_ENERGY, GenerationType.SOLAR).register(addon);
        new EnergyGenerator(ADVANCED_MACHINES, VOID_PANEL, ENHANCED_CRAFTING_TABLE, new ItemStack[]{VOID_INGOT, VOID_INGOT, VOID_INGOT, CELESTIAL_PANEL, CELESTIAL_PANEL, CELESTIAL_PANEL, MAGNONIUM, MAGNONIUM, MAGNONIUM}, VOID_ENERGY, GenerationType.LUNAR).register(addon);
        new EnergyGenerator(INFINITY_CHEAT, INFINITE_PANEL, InfinityWorkbench.TYPE, new ItemStack[]{CELESTIAL_PANEL, CELESTIAL_PANEL, CELESTIAL_PANEL, CELESTIAL_PANEL, CELESTIAL_PANEL, CELESTIAL_PANEL, CELESTIAL_PANEL, CELESTIAL_PANEL, CELESTIAL_PANEL, CELESTIAL_PANEL, CELESTIAL_PANEL, CELESTIAL_PANEL, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_CIRCUIT, INFINITE_CORE, INFINITE_CORE, INFINITE_CIRCUIT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, VOID_PANEL, VOID_PANEL, VOID_PANEL, VOID_PANEL, VOID_PANEL, VOID_PANEL}, INFINITY_ENERGY, GenerationType.INFINITY).register(addon);
    }
}