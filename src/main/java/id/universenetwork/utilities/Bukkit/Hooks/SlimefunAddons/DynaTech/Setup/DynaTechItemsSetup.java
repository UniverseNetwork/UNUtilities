package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Setup;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTech;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Backpacks.PicnicBasket;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.*;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Generators.*;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.GrowthChambers.*;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Transfer.*;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Misc.*;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Tools.*;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.MobData.MobData;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.MobData.MobDataCard;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTechItems.*;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.MobData.MobDataTier.HOSTILE;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;
import static io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead.getItemStack;
import static io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin.fromHashCode;
import static org.bukkit.Material.*;

public class DynaTechItemsSetup {
    public static void setup() {
        // General
        // Resources
        new SlimefunItem(DT_RESOURCES, STAINLESS_STEEL, RecipeType.SMELTERY, new ItemStack[]{new ItemStack(IRON_INGOT), IRON_DUST, ZINC_DUST, null, null, null, null, null, null,}).register(addon);
        new SlimefunItem(DT_RESOURCES, STAINLESS_STEEL_ROTOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, STAINLESS_STEEL, null, STAINLESS_STEEL, new ItemStack(IRON_BLOCK), STAINLESS_STEEL, null, STAINLESS_STEEL, null}).register(addon);
        new SlimefunItem(DT_RESOURCES, ANCIENT_MACHINE_CORE, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{LEAD_INGOT, MAGIC_LUMP_1, LEAD_INGOT, MAGIC_LUMP_1, new ItemStack(REDSTONE_BLOCK), MAGIC_LUMP_1, LEAD_INGOT, MAGIC_LUMP_1, LEAD_INGOT}).register(addon);
        new SlimefunItem(DT_RESOURCES, MACHINE_SCRAP, RecipeType.GRIND_STONE, new ItemStack[]{PROGRAMMABLE_ANDROID, null, null, null, null, null, null, null, null,}, new SlimefunItemStack(MACHINE_SCRAP, 8)).register(addon);
        new SlimefunItem(DT_RESOURCES, ADVANCED_MACHINE_SCRAP, RecipeType.GRIND_STONE, new ItemStack[]{PROGRAMMABLE_ANDROID_2, null, null, null, null, null, null, null, null,}, new SlimefunItemStack(ADVANCED_MACHINE_SCRAP, 8)).register(addon);
        new VexGem(DT_RESOURCES, VEX_GEM, RecipeType.MOB_DROP, new ItemStack[]{null, null, null, null, new CustomItemStack(getItemStack(fromHashCode("c2ec5a516617ff1573cd2f9d5f3969f56d5575c4ff4efefabd2a18dc7ab98cd")), "&aVex"), null, null, null, null}).register(addon);
        RecipeType.MAGIC_WORKBENCH.register(new ItemStack[]{null, SYNTHETIC_SAPPHIRE, null, new ItemStack(PHANTOM_MEMBRANE), MAGIC_LUMP_3, new ItemStack(PHANTOM_MEMBRANE), STAR_DUST, STAR_DUST, STAR_DUST}, VEX_GEM);
        new SlimefunItem(DT_RESOURCES, STAR_DUST, RecipeType.GRIND_STONE, new ItemStack[]{StarDustMeteor.STARDUST_METEOR, null, null, null, null, null, null, null, null}).register(addon);
        new StarDustMeteor(DT_RESOURCES).register(addon);
        new MobDropItem(DT_RESOURCES, GHOSTLY_ESSENCE, RecipeType.MOB_DROP, new ItemStack[]{null, null, null, null, new CustomItemStack(getItemStack(fromHashCode("c2ec5a516617ff1573cd2f9d5f3969f56d5575c4ff4efefabd2a18dc7ab98cd")), "&aVex"), null, null, null, null}, 80).register(addon);
        new SlimefunItem(DT_RESOURCES, TESSERACTING_OBJ, RecipeType.ANCIENT_ALTAR, new ItemStack[]{GHOSTLY_ESSENCE, ENDER_RUNE, VEX_GEM, ENDER_RUNE, new ItemStack(WITHER_ROSE), ENDER_RUNE, VEX_GEM, ENDER_RUNE, GHOSTLY_ESSENCE}).register(addon);
        new Bee(DT_RESOURCES, BEE, DynaTechScoop, new ItemStack[]{null, null, null, null, new CustomItemStack(getItemStack(fromHashCode("12724a9a4cdd68ba49415560e5be40b4a1c47cb5be1d66aedb52a30e62ef2d47")), "&aAny Bee"), null, null, null, null}, 2).register(addon);
        new Bee(DT_RESOURCES, ROBOTIC_BEE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{REINFORCED_ALLOY_INGOT, REINFORCED_ALLOY_INGOT, REINFORCED_ALLOY_INGOT, SYNTHETIC_SAPPHIRE, MACHINE_SCRAP, SYNTHETIC_SAPPHIRE, REINFORCED_ALLOY_INGOT, ANCIENT_MACHINE_CORE, REINFORCED_ALLOY_INGOT}, 7).register(addon);
        new Bee(DT_RESOURCES, ADVANCED_ROBOTIC_BEE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{REINFORCED_PLATE, REINFORCED_PLATE, REINFORCED_PLATE, VEX_GEM, ADVANCED_MACHINE_SCRAP, VEX_GEM, GOLD_24K_BLOCK, ANCIENT_MACHINE_CORE, GOLD_24K_BLOCK}, 11).register(addon);

        // Machines
        // Generators

        // Materials

        // Tools
        new PicnicBasket(27, DT_TOOLS, PICNIC_BASKET, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{CLOTH, CLOTH, CLOTH, new ItemStack(BAMBOO), COOLER, new ItemStack(BAMBOO), HEATING_COIL, new ItemStack(BAMBOO), COOLING_UNIT}).register(addon);
        new InventoryFilter(DT_TOOLS, INVENTORY_FILTER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{REINFORCED_CLOTH, new ItemStack(IRON_BARS), REINFORCED_CLOTH, new ItemStack(IRON_BARS), null, new ItemStack(IRON_BARS), REINFORCED_CLOTH, new ItemStack(IRON_BARS), REINFORCED_CLOTH}).register(addon);
        new ElectricalStimulator(DT_TOOLS, ELECTRICAL_STIMULATOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{STAINLESS_STEEL, null, STAINLESS_STEEL, STAINLESS_STEEL, FOOD_FABRICATOR, STAINLESS_STEEL, PLASTIC_SHEET, PLASTIC_SHEET, PLASTIC_SHEET}).register(addon);
        new AngelGem(DT_TOOLS, ANGEL_GEM, RecipeType.ANCIENT_ALTAR, new ItemStack[]{new ItemStack(NETHERITE_INGOT), NUCLEAR_REACTOR, new ItemStack(NETHERITE_INGOT), GOLD_24K_BLOCK, VEX_GEM, GOLD_24K_BLOCK, BLISTERING_INGOT_3, STAINLESS_STEEL_ROTOR, BLISTERING_INGOT_3}).register(addon);
        new Scoop(DT_TOOLS, SCOOP, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(WHITE_WOOL), new ItemStack(WHITE_WOOL), new ItemStack(WHITE_WOOL), new ItemStack(WHITE_WOOL), BATTERY, new ItemStack(WHITE_WOOL), null, new ItemStack(STICK), null}).register(addon);
        new DimensionalHome(DT_TOOLS, DIMENSIONAL_HOME, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, null, GOLD_24K_BLOCK, BRONZE_INGOT, new ItemStack(BLAZE_ROD), null, SYNTHETIC_SAPPHIRE, BRONZE_INGOT, null}).register(addon);
        new ItemBand(DT_TOOLS, ITEM_BAND_HEALTH, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{new ItemStack(GOLDEN_CARROT), new ItemStack(NETHER_STAR), new ItemStack(GOLDEN_CARROT), new ItemStack(NETHER_STAR), VEX_GEM, new ItemStack(NETHER_STAR), new ItemStack(GOLDEN_CARROT), new ItemStack(NETHER_STAR), new ItemStack(GOLDEN_CARROT)}, new PotionEffect[]{new PotionEffect(PotionEffectType.HEALTH_BOOST, 20 * 15, 1, true)}).register(addon);
        new ItemBand(DT_TOOLS, ITEM_BAND_HASTE, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{COBALT_PICKAXE, new ItemStack(NETHER_STAR), COBALT_PICKAXE, new ItemStack(NETHER_STAR), VEX_GEM, new ItemStack(NETHER_STAR), COBALT_PICKAXE, new ItemStack(NETHER_STAR), COBALT_PICKAXE}, new PotionEffect[]{new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 15, 1, true)}).register(addon);
        new TesseractBinder(DT_TOOLS, TESSERACT_BINDER, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, TESSERACTING_OBJ, null, null, STAINLESS_STEEL, null, null, STAINLESS_STEEL, null,}).register(addon);
        if (DynaTech.isInfinityExpansionInstalled) {
            new MobDataCard("Vex", HOSTILE, new ItemStack[]{new SlimefunItemStack(VEX_GEM, 16), new SlimefunItemStack(GHOSTLY_ESSENCE, 16), new SlimefunItemStack(VEX_GEM, 16), new SlimefunItemStack(GHOSTLY_ESSENCE, 16), MobData.EMPTY_DATA_CARD, new SlimefunItemStack(GHOSTLY_ESSENCE, 16), new SlimefunItemStack(VEX_GEM, 16), new SlimefunItemStack(GHOSTLY_ESSENCE, 16), new SlimefunItemStack(VEX_GEM, 16)}).addDrop(VEX_GEM, 1).addDrop(GHOSTLY_ESSENCE, 9).register(addon);
            new MobDataCard("Phantom", HOSTILE, new ItemStack[]{new ItemStack(PHANTOM_MEMBRANE, 16), new ItemStack(PHANTOM_MEMBRANE, 16), new ItemStack(PHANTOM_MEMBRANE, 16), new ItemStack(PHANTOM_MEMBRANE, 16), MobData.EMPTY_DATA_CARD, new ItemStack(PHANTOM_MEMBRANE, 16), new ItemStack(PHANTOM_MEMBRANE, 16), new ItemStack(PHANTOM_MEMBRANE, 16), new ItemStack(PHANTOM_MEMBRANE, 16),}).addDrop(PHANTOM_MEMBRANE, 0.25f).register(addon);
        }
        new WitherGolem(DT_TOOLS, WITHER_GOLEM).register(addon);

        // Machines
        if (DynaTech.isExoticGardenInstalled)
            new AutoKitchen(DT_MACHINES, AUTO_KITCHEN, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(BRICK), ELECTRIC_FURNACE, new ItemStack(BRICK), STAINLESS_STEEL, GOLD_24K_BLOCK, STAINLESS_STEEL, new ItemStack(TERRACOTTA), new ItemStack(TERRACOTTA), new ItemStack(TERRACOTTA)}).setEnergyCapacity(512).setEnergyConsumption(16).setProcessingSpeed(1).register(addon);
        new GrowthChamber(DT_MACHINES, GROWTH_CHAMBER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{HARDENED_GLASS, TREE_GROWTH_ACCELERATOR, HARDENED_GLASS, new ItemStack(BONE_BLOCK), new ItemStack(GRASS_BLOCK), new ItemStack(BONE_BLOCK), STAINLESS_STEEL, CROP_GROWTH_ACCELERATOR_2, STAINLESS_STEEL}).setEnergyCapacity(512).setEnergyConsumption(32).setProcessingSpeed(1).register(addon);
        new GrowthChamberMK2(DT_MACHINES, GROWTH_CHAMBER_MK2, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{STEEL_PLATE, GROWTH_CHAMBER, STEEL_PLATE, new ItemStack(GRASS_BLOCK), new ItemStack(LIME_STAINED_GLASS), new ItemStack(SAND), STEEL_PLATE, GROWTH_CHAMBER, STEEL_PLATE}).setEnergyCapacity(1024).setEnergyConsumption(128).setProcessingSpeed(3).register(addon);
        new GrowthChamberEnd(DT_MACHINES, GROWTH_CHAMBER_END, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{HARDENED_GLASS, new ItemStack(MAGENTA_STAINED_GLASS), HARDENED_GLASS, new ItemStack(PURPUR_BLOCK), new ItemStack(CHORUS_FLOWER), new ItemStack(END_STONE), STAINLESS_STEEL, GROWTH_CHAMBER, STAINLESS_STEEL}).setEnergyCapacity(512).setEnergyConsumption(32).setProcessingSpeed(1).register(addon);
        new GrowthChamberEndMK2(DT_MACHINES, GROWTH_CHAMBER_END_MK2, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{STEEL_PLATE, GROWTH_CHAMBER_END, STEEL_PLATE, new ItemStack(PURPUR_PILLAR), new ItemStack(PURPLE_STAINED_GLASS), new ItemStack(END_STONE_BRICKS), STEEL_PLATE, GROWTH_CHAMBER_END, STEEL_PLATE}).setEnergyCapacity(1024).setEnergyConsumption(128).setProcessingSpeed(3).register(addon);
        new GrowthChamberNether(DT_MACHINES, GROWTH_CHAMBER_NETHER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{HARDENED_GLASS, new ItemStack(RED_STAINED_GLASS), HARDENED_GLASS, new ItemStack(CRIMSON_NYLIUM), new ItemStack(SOUL_SAND), new ItemStack(WARPED_NYLIUM), STAINLESS_STEEL, GROWTH_CHAMBER, STAINLESS_STEEL}).setEnergyCapacity(512).setEnergyConsumption(32).setProcessingSpeed(1).register(addon);
        new GrowthChamberNetherMK2(DT_MACHINES, GROWTH_CHAMBER_NETHER_MK2, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{STEEL_PLATE, GROWTH_CHAMBER_NETHER, STEEL_PLATE, new ItemStack(CRIMSON_NYLIUM), new ItemStack(SOUL_SAND), new ItemStack(WARPED_NYLIUM), STEEL_PLATE, GROWTH_CHAMBER_NETHER, STEEL_PLATE}).setEnergyCapacity(1024).setEnergyConsumption(128).setProcessingSpeed(3).register(addon);
        new GrowthChamberOcean(DT_MACHINES, GROWTH_CHAMBER_OCEAN, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{HARDENED_GLASS, new ItemStack(CYAN_STAINED_GLASS), HARDENED_GLASS, new ItemStack(WATER_BUCKET), new ItemStack(SAND), new ItemStack(WATER_BUCKET), STAINLESS_STEEL, GROWTH_CHAMBER, STAINLESS_STEEL}).setEnergyCapacity(512).setEnergyConsumption(32).setProcessingSpeed(1).register(addon);
        new GrowthChamberOceanMK2(DT_MACHINES, GROWTH_CHAMBER_OCEAN_MK2, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{STEEL_PLATE, GROWTH_CHAMBER_OCEAN, STEEL_PLATE, new ItemStack(GRAVEL), new ItemStack(LIGHT_BLUE_STAINED_GLASS), new ItemStack(DIRT), STEEL_PLATE, GROWTH_CHAMBER_OCEAN, STEEL_PLATE}).setEnergyCapacity(1024).setEnergyConsumption(128).setProcessingSpeed(3).register(addon);
        new AntigravityBubble(DT_MACHINES, ANTIGRAVITY_BUBBLE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{BLISTERING_INGOT_3, new ItemStack(DIAMOND_BLOCK), BLISTERING_INGOT_3, REINFORCED_ALLOY_INGOT, BIG_CAPACITOR, REINFORCED_ALLOY_INGOT, REINFORCED_ALLOY_INGOT, STAINLESS_STEEL, REINFORCED_ALLOY_INGOT,}).setEnergyCapacity(1024).setEnergyConsumption(128).setProcessingSpeed(1).register(addon);
        new WeatherController(DT_MACHINES, WEATHER_CONTROLLER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, HARDENED_METAL_INGOT, null, new ItemStack(WATER_BUCKET), ANCIENT_MACHINE_CORE, new ItemStack(LAVA_BUCKET), STAINLESS_STEEL, new ItemStack(CRYING_OBSIDIAN), STAINLESS_STEEL}).setEnergyCapacity(512).setEnergyConsumption(32).setProcessingSpeed(1).register(addon);
        new PotionSprinkler(DT_MACHINES, POTION_SPRINKLER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(BREWING_STAND), null, new ItemStack(BREWING_STAND), new ItemStack(IRON_BARS), ANCIENT_MACHINE_CORE, new ItemStack(IRON_BARS), FERROSILICON, FERROSILICON, FERROSILICON}).setEnergyCapacity(256).setEnergyConsumption(32).setProcessingSpeed(1).register(addon);
        new BarbedWire(DT_MACHINES, BARBED_WIRE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(IRON_SWORD), new ItemStack(IRON_SWORD), new ItemStack(IRON_SWORD), STAINLESS_STEEL, new ItemStack(OAK_LOG), STAINLESS_STEEL, ZINC_INGOT, ZINC_INGOT, ZINC_INGOT}).setEnergyCapacity(128).setEnergyConsumption(16).setProcessingSpeed(1).register(addon);
        new MaterialHive(DT_MACHINES, MATERIAL_HIVE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{HARDENED_METAL_INGOT, SYNTHETIC_DIAMOND, HARDENED_METAL_INGOT, HARDENED_METAL_INGOT, new ItemStack(BEEHIVE), HARDENED_METAL_INGOT, ADVANCED_MACHINE_SCRAP, MACHINE_SCRAP, ADVANCED_MACHINE_SCRAP}).setEnergyCapacity(8192).setEnergyConsumption(1024).setProcessingSpeed(1).register(addon);
        new WirelessCharger(DT_MACHINES, WIRELESS_CHARGER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, GPS_TRANSMITTER, null, GOLD_24K, CHARGING_BENCH, GOLD_24K, null, SMALL_CAPACITOR, null}, 16).setEnergyCapacity(1024).setEnergyConsumption(16).setProcessingSpeed(1).register(addon);
        new SeedPlucker(DT_MACHINES, SEED_PLUCKER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{HARDENED_GLASS, STAINLESS_STEEL, HARDENED_GLASS, new ItemStack(BONE_BLOCK), null, new ItemStack(BONE_BLOCK), STAINLESS_STEEL, new ItemStack(SHEARS), STAINLESS_STEEL}).setEnergyCapacity(512).setEnergyConsumption(32).setProcessingSpeed(1).register(addon);
        new BandaidManager(DT_MACHINES, BANDAID_MANAGER, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{BLANK_RUNE, ANCIENT_MACHINE_CORE, BLANK_RUNE, REINFORCED_CLOTH, new ItemStack(ENCHANTING_TABLE), REINFORCED_CLOTH, null, WITHER_PROOF_OBSIDIAN, null}).setEnergyCapacity(1024).setEnergyConsumption(48).setProcessingSpeed(1).register(addon);
        new Orechid(DT_MACHINES, ORECHID, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{ENDER_RUNE, ENDER_RUNE, ENDER_RUNE, MAGIC_LUMP_3, new ItemStack(WITHER_ROSE), MAGIC_LUMP_3, HARDENED_METAL_INGOT, REINFORCED_PLATE, HARDENED_METAL_INGOT}).setEnergyCapacity(16384).setEnergyConsumption(1024).setProcessingSpeed(1).register(addon);

        // Make Wireless Energy Bank and Wireless Energy more costly
        new WirelessEnergyBank(DT_MACHINES, 10240, WIRELESS_ENERGY_BANK, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{ADVANCED_MACHINE_SCRAP, WIRELESS_CHARGER, ADVANCED_MACHINE_SCRAP, WIRELESS_CHARGER, BIG_CAPACITOR, WIRELESS_CHARGER, GHOSTLY_ESSENCE, WIRELESS_CHARGER, GHOSTLY_ESSENCE}).register(addon);
        new WirelessEnergyPoint(DT_MACHINES, 5120, 1024, WIRELESS_ENERGY_POINT, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{ENERGY_CONNECTOR, GHOSTLY_ESSENCE, ENERGY_CONNECTOR, GHOSTLY_ESSENCE, ANCIENT_MACHINE_CORE, GHOSTLY_ESSENCE, ENERGY_CONNECTOR, GHOSTLY_ESSENCE, ENERGY_CONNECTOR}).register(addon);
        new WirelessItemInput(DT_MACHINES, 1024, WIRELESS_ITEM_INPUT, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{CARGO_INPUT_NODE, GHOSTLY_ESSENCE, CARGO_INPUT_NODE, GHOSTLY_ESSENCE, ANCIENT_MACHINE_CORE, GHOSTLY_ESSENCE, CARGO_INPUT_NODE, GHOSTLY_ESSENCE, CARGO_INPUT_NODE}).register(addon);
        new WirelessItemOutput(DT_MACHINES, 1024, WIRELESS_ITEM_OUTPUT, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{ADVANCED_MACHINE_SCRAP, GHOSTLY_ESSENCE, ADVANCED_MACHINE_SCRAP, GHOSTLY_ESSENCE, BIG_CAPACITOR, GHOSTLY_ESSENCE, CARGO_OUTPUT_NODE_2, GHOSTLY_ESSENCE, CARGO_OUTPUT_NODE_2}).register(addon);
        new Tesseract(DT_MACHINES, 65535, 1024, TESSERACT, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{WIRELESS_ENERGY_BANK, TESSERACTING_OBJ, WIRELESS_ITEM_INPUT, TESSERACTING_OBJ, GHOSTLY_ESSENCE, TESSERACTING_OBJ, WIRELESS_ITEM_OUTPUT, TESSERACTING_OBJ, WIRELESS_ENERGY_POINT}).register(addon);
        new LiquidTank(DT_TOOLS, LIQUID_TANK, 16000, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{STAINLESS_STEEL, new ItemStack(BUCKET), STAINLESS_STEEL, new ItemStack(BUCKET), new ItemStack(BUCKET), new ItemStack(BUCKET), STAINLESS_STEEL, new ItemStack(BUCKET), STAINLESS_STEEL,}).register(addon);

        // Generators
        new HydroGenerator(DT_GENERATORS, 16, 256, WATER_MILL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{ALUMINUM_INGOT, SULFATE, ALUMINUM_INGOT, ALUMINUM_INGOT, ENERGY_CONNECTOR, ALUMINUM_INGOT, STAINLESS_STEEL_ROTOR, null, STAINLESS_STEEL_ROTOR,}).register(addon);
        new HydroGenerator(DT_GENERATORS, 64, 512, WATER_TURBINE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{WATER_MILL, REINFORCED_ALLOY_INGOT, WATER_MILL, REINFORCED_ALLOY_INGOT, GOLD_8K, REINFORCED_ALLOY_INGOT, WATER_MILL, REINFORCED_ALLOY_INGOT, WATER_MILL}).register(addon);
        new DragonEggGenerator(DT_GENERATORS, DRAGON_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(CRYING_OBSIDIAN), new ItemStack(END_STONE), new ItemStack(CRYING_OBSIDIAN), new ItemStack(CRYING_OBSIDIAN), LEAD_INGOT, new ItemStack(CRYING_OBSIDIAN), LEAD_INGOT, MEDIUM_CAPACITOR, LEAD_INGOT}).register(addon);
        new ChippingGenerator(DT_GENERATORS, CHIPPING_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{STAINLESS_STEEL, STAINLESS_STEEL, STAINLESS_STEEL, new ItemStack(DIAMOND_AXE), ANCIENT_MACHINE_CORE, new ItemStack(DIAMOND_AXE), STAINLESS_STEEL, STAINLESS_STEEL, STAINLESS_STEEL}).setEnergyCapacity(256).setEnergyProduction(2).register(addon);
        new CulinaryGenerator(DT_GENERATORS, CULINARY_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{ALUMINUM_BRASS_INGOT, ALUMINUM_BRASS_INGOT, ALUMINUM_BRASS_INGOT, LEAD_DUST, SMALL_CAPACITOR, LEAD_DUST, new ItemStack(CAMPFIRE), new ItemStack(CAMPFIRE), new ItemStack(CAMPFIRE)}).setEnergyCapacity(256).setEnergyProduction(16).register(addon);
        new StardustReactor(DT_GENERATORS, STARDUST_REACTOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(FIRE_CHARGE), new ItemStack(FIRE_CHARGE), new ItemStack(FIRE_CHARGE), null, NUCLEAR_REACTOR, null, ADVANCED_MACHINE_SCRAP, ANCIENT_MACHINE_CORE, ADVANCED_MACHINE_SCRAP}).setEnergyCapacity(32676).setEnergyProduction(1024).register(addon);
    }
}