package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.FluffyMachines;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.FluffyMachines.Items.*;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.FluffyMachines.Items.Tools.*;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.FluffyMachines.Machines.*;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.FluffyMachines.MultiBlocks.Components.GeneratorCore;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.FluffyMachines.MultiBlocks.Components.SuperheatedFurnace;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.FluffyMachines.MultiBlocks.CrankGenerator;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.FluffyMachines.MultiBlocks.Foundry;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.FluffyMachines.Utils.FluffyItems.*;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;
import static org.bukkit.Material.*;

public final class FluffyItemSetup {
    static final SlimefunItemStack advancedCircuitBoard = ADVANCED_CIRCUIT_BOARD;
    static final ItemStack orangeGlass = new ItemStack(ORANGE_STAINED_GLASS);
    static final ItemStack brownGlass = new ItemStack(BROWN_STAINED_GLASS);

    FluffyItemSetup() {
    }

    public static void setup() {
        // Barrels
        new Barrel(fluffybarrels, SMALL_FLUFFY_BARREL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(OAK_LOG), new ItemStack(BARREL), new ItemStack(OAK_LOG), new ItemStack(OAK_LOG), new ItemStack(BARREL), new ItemStack(OAK_LOG), new ItemStack(OAK_LOG), REINFORCED_PLATE, new ItemStack(OAK_LOG)}, "&eSmall Fluffy Barrel", Barrel.SMALL_BARREL_SIZE).register(addon);
        new Barrel(fluffybarrels, MEDIUM_FLUFFY_BARREL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(SMOOTH_STONE), SMALL_FLUFFY_BARREL, new ItemStack(SMOOTH_STONE), new ItemStack(SMOOTH_STONE), SMALL_FLUFFY_BARREL, new ItemStack(SMOOTH_STONE), new ItemStack(SMOOTH_STONE), REINFORCED_PLATE, new ItemStack(SMOOTH_STONE)}, "&6Medium Fluffy Barrel", Barrel.MEDIUM_BARREL_SIZE).register(addon);
        new Barrel(fluffybarrels, BIG_FLUFFY_BARREL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(BRICKS), MEDIUM_FLUFFY_BARREL, new ItemStack(BRICKS), new ItemStack(BRICKS), MEDIUM_FLUFFY_BARREL, new ItemStack(BRICKS), new ItemStack(BRICKS), REINFORCED_PLATE, new ItemStack(BRICKS)}, "&bBig Fluffy Barrel", Barrel.BIG_BARREL_SIZE).register(addon);
        new Barrel(fluffybarrels, LARGE_FLUFFY_BARREL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(IRON_BLOCK), BIG_FLUFFY_BARREL, new ItemStack(IRON_BLOCK), new ItemStack(IRON_BLOCK), BIG_FLUFFY_BARREL, new ItemStack(IRON_BLOCK), new ItemStack(IRON_BLOCK), REINFORCED_PLATE, new ItemStack(IRON_BLOCK)}, "&aLarge Fluffy Barrel", Barrel.LARGE_BARREL_SIZE).register(addon);
        new Barrel(fluffybarrels, MASSIVE_FLUFFY_BARREL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(OBSIDIAN), LARGE_FLUFFY_BARREL, new ItemStack(OBSIDIAN), new ItemStack(OBSIDIAN), LARGE_FLUFFY_BARREL, new ItemStack(OBSIDIAN), new ItemStack(OBSIDIAN), REINFORCED_PLATE, new ItemStack(OBSIDIAN)}, "&5Massive Fluffy Barrel", Barrel.MASSIVE_BARREL_SIZE).register(addon);
        new Barrel(fluffybarrels, BOTTOMLESS_FLUFFY_BARREL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{REINFORCED_PLATE, MASSIVE_FLUFFY_BARREL, REINFORCED_PLATE, REINFORCED_PLATE, MASSIVE_FLUFFY_BARREL, REINFORCED_PLATE, REINFORCED_PLATE, BLISTERING_INGOT_3, REINFORCED_PLATE}, "&cBottomless Fluffy Barrel", Barrel.BOTTOMLESS_BARREL_SIZE).register(addon);

        // Chargers
        new PortableCharger(fluffymachines, SMALL_PORTABLE_CHARGER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{STEEL_INGOT, COPPER_WIRE, STEEL_INGOT, STEEL_INGOT, SMALL_CAPACITOR, STEEL_INGOT, new ItemStack(BRICK), STEEL_PLATE, new ItemStack(BRICK)}, PortableCharger.Type.SMALL.chargeCapacity, PortableCharger.Type.SMALL.chargeSpeed).register(addon);
        new PortableCharger(fluffymachines, MEDIUM_PORTABLE_CHARGER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{STEEL_INGOT, COPPER_WIRE, STEEL_INGOT, STEEL_INGOT, MEDIUM_CAPACITOR, STEEL_INGOT, new ItemStack(IRON_INGOT), STEEL_PLATE, new ItemStack(IRON_INGOT)}, PortableCharger.Type.MEDIUM.chargeCapacity, PortableCharger.Type.MEDIUM.chargeSpeed).register(addon);
        new PortableCharger(fluffymachines, BIG_PORTABLE_CHARGER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{STEEL_INGOT, COPPER_WIRE, STEEL_INGOT, STEEL_INGOT, BIG_CAPACITOR, STEEL_INGOT, new ItemStack(GOLD_INGOT), STEEL_PLATE, new ItemStack(GOLD_INGOT)}, PortableCharger.Type.BIG.chargeCapacity, PortableCharger.Type.BIG.chargeSpeed).register(addon);
        new PortableCharger(fluffymachines, LARGE_PORTABLE_CHARGER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{STEEL_INGOT, COPPER_WIRE, STEEL_INGOT, STEEL_INGOT, LARGE_CAPACITOR, STEEL_INGOT, new ItemStack(NETHER_BRICK), STEEL_PLATE, new ItemStack(NETHER_BRICK)}, PortableCharger.Type.LARGE.chargeCapacity, PortableCharger.Type.LARGE.chargeSpeed).register(addon);
        new PortableCharger(fluffymachines, CARBONADO_PORTABLE_CHARGER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{STEEL_INGOT, COPPER_WIRE, STEEL_INGOT, STEEL_INGOT, CARBONADO_EDGED_CAPACITOR, STEEL_INGOT, new ItemStack(NETHERITE_INGOT), STEEL_PLATE, new ItemStack(NETHERITE_INGOT)}, PortableCharger.Type.CARBONADO.chargeCapacity, PortableCharger.Type.CARBONADO.chargeSpeed).register(addon);

        // Multiblocks
        new CrankGenerator(fluffymachines, CRANK_GENERATOR).register(addon);
        new Foundry(fluffymachines, FOUNDRY).register(addon);

        // Tools
        new WateringCan(fluffymachines, WATERING_CAN, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(IRON_INGOT), null, new ItemStack(IRON_INGOT), new ItemStack(IRON_INGOT), new ItemStack(BUCKET), new ItemStack(IRON_INGOT), null, new ItemStack(IRON_INGOT), null}).register(addon);
        new Scythe(fluffymachines, SCYTHE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, new ItemStack(IRON_INGOT), new ItemStack(IRON_INGOT), null, new ItemStack(IRON_HOE), null, null, new ItemStack(STICK), null}).register(addon);
        new FluffyWrench(fluffymachines, FLUFFY_WRENCH, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{COPPER_INGOT, null, COPPER_INGOT, COPPER_INGOT, COPPER_INGOT, COPPER_INGOT, null, COPPER_INGOT, null}, FluffyWrench.Wrench.DEFAULT).register(addon);
        new FluffyWrench(fluffymachines, REINFORCED_FLUFFY_WRENCH, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{REINFORCED_ALLOY_INGOT, null, REINFORCED_ALLOY_INGOT, REINFORCED_ALLOY_INGOT, FLUFFY_WRENCH, REINFORCED_ALLOY_INGOT, null, SYNTHETIC_DIAMOND, null}, FluffyWrench.Wrench.REINFORCED).register(addon);
        new FluffyWrench(fluffymachines, CARBONADO_FLUFFY_WRENCH, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{CARBONADO, null, CARBONADO, CARBONADO, REINFORCED_FLUFFY_WRENCH, CARBONADO, null, CARBONADO_EDGED_CAPACITOR, null}, FluffyWrench.Wrench.CARBONADO).register(addon);
        new UpgradedLumberAxe(fluffymachines, UPGRADED_LUMBER_AXE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, new ItemStack(DIAMOND), new ItemStack(DIAMOND), null, LUMBER_AXE, null, null, new ItemStack(OBSIDIAN), null}).register(addon);
        new UpgradedExplosivePickaxe(fluffymachines, UPGRADED_EXPLOSIVE_PICKAXE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{SYNTHETIC_EMERALD, SYNTHETIC_EMERALD, SYNTHETIC_EMERALD, new ItemStack(TNT), EXPLOSIVE_PICKAXE, new ItemStack(TNT), null, new ItemStack(OBSIDIAN), null}).register(addon);
        new UpgradedExplosiveShovel(fluffymachines, UPGRADED_EXPLOSIVE_SHOVEL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{SYNTHETIC_EMERALD, SYNTHETIC_EMERALD, SYNTHETIC_EMERALD, new ItemStack(TNT), EXPLOSIVE_SHOVEL, new ItemStack(TNT), null, new ItemStack(OBSIDIAN), null}).register(addon);
        new Paxel(fluffymachines, PAXEL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{SYNTHETIC_EMERALD, new ItemStack(DIAMOND_PICKAXE), SYNTHETIC_EMERALD, REINFORCED_ALLOY_INGOT, new ItemStack(DIAMOND_AXE), REINFORCED_ALLOY_INGOT, SYNTHETIC_DIAMOND, new ItemStack(DIAMOND_SHOVEL), SYNTHETIC_DIAMOND,}).register(addon);

        // Machines
        new WaterSprinkler(fluffymachines, WATER_SPRINKER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(IRON_INGOT), ELECTRIC_MOTOR, new ItemStack(IRON_INGOT), new ItemStack(BUCKET), new ItemStack(DISPENSER), new ItemStack(BUCKET), new ItemStack(IRON_INGOT), SMALL_CAPACITOR, new ItemStack(IRON_INGOT)}).register(addon);
        new AutoCraftingTable(fluffymachines, AUTO_CRAFTING_TABLE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{REINFORCED_PLATE, advancedCircuitBoard, REINFORCED_PLATE, CARGO_MOTOR, BLISTERING_INGOT_3, CARGO_MOTOR, REINFORCED_PLATE, ELECTRIC_MOTOR, REINFORCED_PLATE}).register(addon);
        new AutoAncientAltar(fluffymachines, AUTO_ANCIENT_ALTAR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{ANCIENT_PEDESTAL, MEDIUM_CAPACITOR, ANCIENT_PEDESTAL, ANCIENT_PEDESTAL, ANCIENT_ALTAR, ANCIENT_PEDESTAL, ANCIENT_PEDESTAL, ELECTRIC_MOTOR, ANCIENT_PEDESTAL}).register(addon);
        new AutoTableSaw(fluffymachines, AUTO_TABLE_SAW, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{advancedCircuitBoard, MEDIUM_CAPACITOR, advancedCircuitBoard, new ItemStack(SMOOTH_STONE_SLAB), new ItemStack(STONECUTTER), new ItemStack(SMOOTH_STONE_SLAB), ELECTRIC_MOTOR, new ItemStack(IRON_BLOCK), ELECTRIC_MOTOR}).register(addon);
        new AutoMagicWorkbench(fluffymachines, AUTO_MAGIC_WORKBENCH, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(BOOKSHELF), advancedCircuitBoard, new ItemStack(BOOKSHELF), new ItemStack(BOOKSHELF), new ItemStack(CRAFTING_TABLE), new ItemStack(DISPENSER), new ItemStack(BOOKSHELF), AUTO_CRAFTING_TABLE, new ItemStack(BOOKSHELF)}).register(addon);
        new AutoArmorForge(fluffymachines, AUTO_ARMOR_FORGE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(ANVIL), new ItemStack(ANVIL), new ItemStack(ANVIL), advancedCircuitBoard, new ItemStack(DISPENSER), advancedCircuitBoard, new ItemStack(ANVIL), AUTO_CRAFTING_TABLE, new ItemStack(ANVIL)}).register(addon);
        new AdvancedAutoDisenchanter(fluffymachines, ADVANCED_AUTO_DISENCHANTER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{REDSTONE_ALLOY, AUTO_ANVIL_2, REDSTONE_ALLOY, BLISTERING_INGOT_3, AUTO_DISENCHANTER, BLISTERING_INGOT_3, WITHER_PROOF_OBSIDIAN, WITHER_PROOF_OBSIDIAN, WITHER_PROOF_OBSIDIAN}).register(addon);
        new BackpackLoader(fluffymachines, BACKPACK_LOADER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{orangeGlass, orangeGlass, orangeGlass, new ItemStack(IRON_INGOT), new ItemStack(HOPPER), new ItemStack(IRON_INGOT), ELECTRIC_MOTOR, BIG_CAPACITOR, ELECTRIC_MOTOR}).register(addon);
        new BackpackUnloader(fluffymachines, BACKPACK_UNLOADER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{brownGlass, brownGlass, brownGlass, new ItemStack(IRON_INGOT), new ItemStack(DISPENSER), new ItemStack(IRON_INGOT), ELECTRIC_MOTOR, BIG_CAPACITOR, ELECTRIC_MOTOR}).register(addon);
        new GeneratorCore(fluffymachines, GENERATOR_CORE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(IRON_INGOT), new ItemStack(IRON_INGOT), new ItemStack(IRON_INGOT), new ItemStack(IRON_INGOT), ELECTRO_MAGNET, new ItemStack(IRON_INGOT), new ItemStack(IRON_INGOT), advancedCircuitBoard, new ItemStack(IRON_INGOT)}).register(addon);
        new SuperheatedFurnace(fluffymachines, SUPERHEATED_FURNACE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(OBSIDIAN), new ItemStack(OBSIDIAN), new ItemStack(OBSIDIAN), new ItemStack(LAVA_BUCKET), new ItemStack(BLAST_FURNACE), new ItemStack(LAVA_BUCKET), new ItemStack(OBSIDIAN), new ItemStack(OBSIDIAN), new ItemStack(OBSIDIAN)}).register(addon);

        // Misc
        new HelicopterHat(fluffymachines, HELICOPTER_HAT, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(IRON_INGOT), new ItemStack(IRON_INGOT), new ItemStack(IRON_INGOT), advancedCircuitBoard, new ItemStack(LEATHER_HELMET), advancedCircuitBoard, COMPRESSED_CARBON, ELECTRIC_MOTOR, COMPRESSED_CARBON}).register(addon);
        new FireproofRune(fluffymachines, FIREPROOF_RUNE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{SYNTHETIC_EMERALD, new ItemStack(NETHERITE_INGOT), SYNTHETIC_EMERALD, new ItemStack(OBSIDIAN), FIRE_RUNE, new ItemStack(OBSIDIAN), SYNTHETIC_EMERALD, new ItemStack(OBSIDIAN), SYNTHETIC_EMERALD}).register(addon);
        new EnderChestInsertionNode(fluffymachines, ENDER_CHEST_INSERTION_NODE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{ENDER_LUMP_2, BASIC_CIRCUIT_BOARD, ENDER_LUMP_2, new ItemStack(DISPENSER), new ItemStack(ENDER_PEARL), new ItemStack(HOPPER), ENDER_LUMP_2, BASIC_CIRCUIT_BOARD, ENDER_LUMP_2}).register(addon);
        new EnderChestExtractionNode(fluffymachines, ENDER_CHEST_EXTRACTION_NODE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{ENDER_LUMP_2, BASIC_CIRCUIT_BOARD, ENDER_LUMP_2, new ItemStack(HOPPER), new ItemStack(ENDER_PEARL), new ItemStack(DISPENSER), ENDER_LUMP_2, advancedCircuitBoard, ENDER_LUMP_2}).register(addon);
        new Dolly(fluffymachines, DOLLY, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(LEATHER), new ItemStack(LEATHER), new ItemStack(LEATHER), new ItemStack(IRON_INGOT), new ItemStack(MINECART), new ItemStack(IRON_INGOT), new ItemStack(IRON_INGOT), new ItemStack(IRON_INGOT), new ItemStack(IRON_INGOT)}).register(addon);
        new SlimefunItem(fluffymachines, ANCIENT_BOOK, RecipeType.ANCIENT_ALTAR, new ItemStack[]{new ItemStack(BOOK), FILLED_FLASK_OF_KNOWLEDGE, new ItemStack(BOOK), FILLED_FLASK_OF_KNOWLEDGE, ENCHANTMENT_RUNE, FILLED_FLASK_OF_KNOWLEDGE, new ItemStack(BOOK), FILLED_FLASK_OF_KNOWLEDGE, new ItemStack(BOOK)}).register(addon);
        new WarpPad(fluffymachines, WARP_PAD, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(ENDER_EYE), new ItemStack(ENDER_EYE), new ItemStack(ENDER_EYE), new ItemStack(ENDER_EYE), GPS_TELEPORTER_PYLON, new ItemStack(ENDER_EYE), new ItemStack(ENDER_EYE), new ItemStack(ENDER_EYE), new ItemStack(ENDER_EYE)}).register(addon);
        new WarpPadConfigurator(fluffymachines, WARP_PAD_CONFIGURATOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, new ItemStack(ENDER_EYE), null, null, MAGNESIUM_INGOT, null, null, MAGNESIUM_INGOT, null}).register(addon);
        new ElectricDustFabricator(fluffymachines, ELECTRIC_DUST_FABRICATOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{ELECTRIC_ORE_GRINDER_2, ELECTRIC_ORE_GRINDER_2, ELECTRIC_ORE_GRINDER_2, ELECTRIC_GOLD_PAN_3, BLISTERING_INGOT_3, ELECTRIC_GOLD_PAN_3, ELECTRIC_MOTOR, ELECTRIC_DUST_WASHER_3, ELECTRIC_MOTOR}).register(addon);
        new ElectricDustRecycler(fluffymachines, ELECTRIC_DUST_RECYCLER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(LAVA_BUCKET), new ItemStack(PISTON), new ItemStack(LAVA_BUCKET), new ItemStack(LAVA_BUCKET), ELECTRIFIED_CRUCIBLE_3, new ItemStack(LAVA_BUCKET), ELECTRIC_MOTOR, new ItemStack(PISTON), ELECTRIC_MOTOR}).register(addon);
        new AlternateElevatorPlate(fluffymachines, ALTERNATE_ELEVATOR_PLATE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(STONE_PRESSURE_PLATE), new ItemStack(STONE_PRESSURE_PLATE), new ItemStack(STONE_PRESSURE_PLATE), new ItemStack(PISTON), ELECTRIC_MOTOR, new ItemStack(PISTON), ALUMINUM_BRONZE_INGOT, ALUMINUM_BRONZE_INGOT, ALUMINUM_BRONZE_INGOT}, new SlimefunItemStack(ALTERNATE_ELEVATOR_PLATE, 2)).register(addon);
        new AdvancedChargingBench(fluffymachines, ADVANCED_CHARGING_BENCH, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{CORINTHIAN_BRONZE_INGOT, advancedCircuitBoard, CORINTHIAN_BRONZE_INGOT, advancedCircuitBoard, CHARGING_BENCH, advancedCircuitBoard, ELECTRIC_MOTOR, SMALL_CAPACITOR, ELECTRIC_MOTOR}).register(addon);
        new ACBUpgradeCard(fluffymachines, ACB_UPGRADE_CARD, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{CORINTHIAN_BRONZE_INGOT, advancedCircuitBoard, CORINTHIAN_BRONZE_INGOT, advancedCircuitBoard, ELECTRIC_MOTOR, advancedCircuitBoard, GOLD_24K, SMALL_CAPACITOR, GOLD_24K}).register(addon);
    }
}