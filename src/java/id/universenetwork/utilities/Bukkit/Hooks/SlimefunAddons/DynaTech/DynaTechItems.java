package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech;

import dev.j3fftw.extrautils.utils.LoreBuilderDynamic;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.MaterialHive;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.NamespacedKey;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier.*;
import static io.github.thebusybiscuit.slimefun4.core.attributes.MachineType.*;
import static io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead.getItemStack;
import static io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin.fromHashCode;
import static io.github.thebusybiscuit.slimefun4.utils.LoreBuilder.*;
import static org.bukkit.Material.*;

public class DynaTechItems {
    // Categories
    public static final NestedItemGroup DT_GENERAL = new NestedItemGroup(new NamespacedKey(plugin, "DT_GENERAL"), new CustomItemStack(CONDUIT, "&bDynaTech"));
    public static final SubItemGroup DT_RESOURCES = new SubItemGroup(new NamespacedKey(plugin, "DT_RESOURCES"), DT_GENERAL, new CustomItemStack(PUFFERFISH, "&bDynaTech Resources"));
    public static final SubItemGroup DT_TOOLS = new SubItemGroup(new NamespacedKey(plugin, "DT_TOOLS"), DT_GENERAL, new CustomItemStack(DIAMOND_AXE, "&bDynaTech Tools"));
    public static final SubItemGroup DT_MACHINES = new SubItemGroup(new NamespacedKey(plugin, "DT_MACHINES"), DT_GENERAL, new CustomItemStack(SEA_LANTERN, "&bDynaTech Machines"));
    public static final SubItemGroup DT_GENERATORS = new SubItemGroup(new NamespacedKey(plugin, "DT_GENERATORS"), DT_GENERAL, new CustomItemStack(PRISMARINE_BRICKS, "&bDynaTech Generators"));
    public static final ItemGroup DynaTechGeneral = new ItemGroup(new NamespacedKey(plugin, "dynatech"), new CustomItemStack(CONDUIT, "&bDynaTech"));
    public static final RecipeType DynaTechScoop = new RecipeType(new NamespacedKey(plugin, "dt_scoop"), new CustomItemStack(IRON_SHOVEL, "&bScoop the Bee using a Scoop"));

    // RecipeTypes
    // TODO: Make Orechid use RecipeType + Standard Conversions
    public static final RecipeType DT_SCOOP = new RecipeType(new NamespacedKey(plugin, "DT_SCOOP"), new CustomItemStack(IRON_SHOVEL, "&bScoop a Bee using a Scoop"));
    //public static final RecipeType DT_ORECHID = new RecipeType(new NamespacedKey(plugin, "DT_ORECHID"), new CustomItemStack(END_ROD, "&BTransmuted using the Orechid"));

    // General

    // Resources
    public static final SlimefunItemStack STAINLESS_STEEL = new SlimefunItemStack("STAINLESS_STEEL", IRON_INGOT, "&6Stainless Steel Ingot");
    public static final SlimefunItemStack STAINLESS_STEEL_ROTOR = new SlimefunItemStack("STAINLESS_STEEL_ROTOR", IRON_BLOCK, "&6Stainless Steel Rotor");
    public static final SlimefunItemStack ANCIENT_MACHINE_CORE = new SlimefunItemStack("ANCIENT_MACHINE_CORE", LAPIS_BLOCK, "&6Ancient Machine Core");
    public static final SlimefunItemStack VEX_GEM = new SlimefunItemStack("VEX_GEM", getItemStack(fromHashCode("b91aeca7c17e66d867231b36d96e83c1ede75eaf67ccf3a88dca15d4114ae167")), "&6Vex Gem");
    public static final SlimefunItemStack MACHINE_SCRAP = new SlimefunItemStack("MACHINE_SCRAP", getItemStack(fromHashCode("13ea401c7e02d13cea1de6835ee9f5c47757d399dae5c2b9c3efde6ae63ea4a2")), "&6Machine Scrap");
    public static final SlimefunItemStack ADVANCED_MACHINE_SCRAP = new SlimefunItemStack("ADVANCED_MACHINE_SCRAP", getItemStack(fromHashCode("4b57a4c68d1d2c5de978ea6de4db91ef387ca6c37966bb8e7c8826f937e6c3")), "&6Advanced Machine Scrap");
    public static final SlimefunItemStack STAR_DUST = new SlimefunItemStack("STAR_DUST", NETHER_STAR, "&6Star Dust");
    public static final SlimefunItemStack GHOSTLY_ESSENCE = new SlimefunItemStack("GHOSTLY_ESSENCE", WHITE_DYE, "&6Ghostly Essence");
    public static final SlimefunItemStack TESSERACTING_OBJ = new SlimefunItemStack("TESSERACTING_OBJ", MUSHROOM_STEM, "&6Tesseracting Object", "&f&oIt shimmers and shifts in your hands");
    public static final SlimefunItemStack BEE = new SlimefunItemStack("BEE", getItemStack(fromHashCode("12724a9a4cdd68ba49415560e5be40b4a1c47cb5be1d66aedb52a30e62ef2d47")), "&6Bee");
    public static final SlimefunItemStack ROBOTIC_BEE = new SlimefunItemStack("ROBOTIC_BEE", getItemStack(fromHashCode("16f728c89904b2cb57f853d31d0e2061f52917981fedccb1e949528e08eb4140")), "&6Robotic Bee");
    public static final SlimefunItemStack ADVANCED_ROBOTIC_BEE = new SlimefunItemStack("ADVANCED_ROBOTIC_BEE", getItemStack(fromHashCode("c1c96e8cf83cbade55ffa667197ea6990290e5c7dc679104332caead97eef09")), "&6Advanced Robotic Bee");

    // Tools
    public static final SlimefunItemStack PICNIC_BASKET = new SlimefunItemStack("PICNIC_BASKET", new CustomItemStack(getItemStack(fromHashCode("7a6bf916e28ccb80b4ebfacf98686ad6af7c4fb257e57a8cb78c71d19dccb2"))), "&6Picnic Basket", "", "&fAllows you to store food", "&fAutomatically consumes them when you're hungry", "&fMust be in your inventory", "", "&fSize: &e27", "", "&7ID: <ID>", "", "&eRight Click &7to open.");
    public static final SlimefunItemStack SOULBOUND_PICNIC_BASKET = new SlimefunItemStack("SOULBOUND_PICNIC_BASKET", new CustomItemStack(getItemStack(fromHashCode("7a6bf916e28ccb80b4ebfacf98686ad6af7c4fb257e57a8cb78c71d19dccb2"))), "&6Soulbound Picnic Basket", "", "&fAllows you to store food", "&fAutomatically consumes them when you're hungry", "&fMust be in your inventory", "", "&fSize: &e27", "", "&7ID: <ID>", "", "&eRight Click &7to open.", "", "&dSoulbound");
    public static final SlimefunItemStack INVENTORY_FILTER = new SlimefunItemStack("INVENTORY_FILTER", IRON_BARS, "&6Inventory Filter", "", "&fFilters out items on the floor that are in it's inventory", "", "&7ID: <ID>", "", "&eRight Click &7to open.");
    public static final SlimefunItemStack ELECTRICAL_STIMULATOR = new SlimefunItemStack("ELECTRICAL_STIMULATOR", new CustomItemStack(getItemStack(fromHashCode("82a319cf66a4de12e3330e8bc4c82c985ccc3cb2230868c336a88fc4a22082a"))), "&6Electrical Stimulator", "", "&fAutomatically feed you for energy", "", "&f&oStimulate your senses.", "", powerCharged(0, 1024));
    public static final SlimefunItemStack ANGEL_GEM = new SlimefunItemStack("ANGEL_GEM", NETHERITE_BLOCK, "&6Flight Gem", "", "&fPermanent Creative Flight.", "&fHas some speed adjustment settings.", "", "&f&oFly just like a bird~", "", "&7Flight: <enabled>", "&7Flight Speed: <speed>");
    public static final SlimefunItemStack SCOOP = new SlimefunItemStack("SCOOP", IRON_SHOVEL, "&6Scoop", "", "&fUsed to capture bees.", "", "&f&oMake sure not to get stung", "", powerCharged(0, 512));
    public static final SlimefunItemStack DIMENSIONAL_HOME = new SlimefunItemStack("DIMENSIONAL_HOME", new CustomItemStack(getItemStack(fromHashCode("eb18cf9e1bf7ec57304ae92f2b00d91643cf0b65067dead34fb48baf18e3c385"))), "&6Dimensional Home", "", "&fTeleports you to a", "&fseperate dimensional home and back", "", "&f&oHome Sweet Home", "", "&7CHUNK ID: <id>");
    public static final SlimefunItemStack ITEM_BAND_HEALTH = new SlimefunItemStack("ITEM_BAND_HEALTH", new CustomItemStack(getItemStack(fromHashCode("f1e2428cb359988f4c4ff0e61de21385c62269de19a69762d773223b75dd1666"))), "&6Healthy Item Band", "", "&fWhen applied to armor or tools", "&fgives you 2 levels of Health Boost", "", "&f&oPowerup!");
    public static final SlimefunItemStack ITEM_BAND_HASTE = new SlimefunItemStack("ITEM_BAND_HASTE", new CustomItemStack(getItemStack(fromHashCode("4f01ec6331a3bc30a8204ec56398d08ca38788556bca9b81d776f6238d567367"))), "&6Hasty Item Band", "", "&fWhen applied to armor or tools", "&fgives you 2 levels of Haste", "", "&f&oPowerup!");
    public static final SlimefunItemStack TESSERACT_BINDER = new SlimefunItemStack("TESSERACT_BINDER", NETHERITE_HOE, "&6Tesseract Binder", "", "&f Used to bind 2 Tesseract together.", "", "&fRight click to get Location of Tesseract", "&fCrouch Right Click to bind location to Tesseract", "");
    public static final SlimefunItemStack LIQUID_TANK = new SlimefunItemStack("LIQUID_TANK", BUCKET, "&6Portable Liquid Tank", "", "&fSimple Liquid Snatcher.", "", "Right click to grab a fluid", "Shift click to place a fluid", "", "&fFluid Held: NO_FLUID", "&fAmount: 0mb / 16000", "");
    public static final SlimefunItemStack WITHER_GOLEM = new SlimefunItemStack("WITHER_GOLEM", WITHER_SKELETON_SKULL, "&6Wither Golem MultiBlock", "", "Spawns a Wither Skeleton", "");

    // Machines
    public static final SlimefunItemStack AUTO_KITCHEN = new SlimefunItemStack("AUTO_KITCHEN", SMOKER, "&6Auto Kitchen", "", "&fAutomatically makes Kitchen recipes", "", "&f&oSmells like cookies", "", machine(MEDIUM, MACHINE), speed(1), LoreBuilderDynamic.powerPerSecond(16));
    public static final SlimefunItemStack GROWTH_CHAMBER = new SlimefunItemStack("GROWTH_CHAMBER", GREEN_STAINED_GLASS, "&6Growth Chamber", "", "&fAutomatically grows &eplants&f.", "", "&f&oIts like a small greenhouse!", "", machine(MEDIUM, MACHINE), speed(1), LoreBuilderDynamic.powerPerSecond(32));
    public static final SlimefunItemStack GROWTH_CHAMBER_MK2 = new SlimefunItemStack("GROWTH_CHAMBER_MK2", LIME_STAINED_GLASS, "&6Growth Chamber MK2", "", "&fAutomatically grows &eplants&f.", "", "&f&oIts like a small greenhouse!", "", "&c3x production.", machine(MEDIUM, MACHINE), speed(3), LoreBuilderDynamic.powerPerSecond(128));
    public static final SlimefunItemStack GROWTH_CHAMBER_END = new SlimefunItemStack("GROWTH_CHAMBER_END", MAGENTA_STAINED_GLASS, "&dEnd Growth Chamber", "", "&fAutomatically grows &dchorus flowers.", "", machine(MEDIUM, MACHINE), speed(1), LoreBuilderDynamic.powerPerSecond(32));
    public static final SlimefunItemStack GROWTH_CHAMBER_END_MK2 = new SlimefunItemStack("GROWTH_CHAMBER_END_MK2", PURPLE_STAINED_GLASS, "&dEnd Growth Chamber MK2", "", "&fAutomatically grows &dchorus flowers.", "", "&c3x production.", machine(MEDIUM, MACHINE), speed(3), LoreBuilderDynamic.powerPerSecond(128));
    public static final SlimefunItemStack GROWTH_CHAMBER_NETHER = new SlimefunItemStack("GROWTH_CHAMBER_NETHER", RED_STAINED_GLASS, "&cNether Growth Chamber", "", "&fAutomatically grows &cnether &fplants.", "", machine(MEDIUM, MACHINE), speed(1), LoreBuilderDynamic.powerPerSecond(32));
    public static final SlimefunItemStack GROWTH_CHAMBER_NETHER_MK2 = new SlimefunItemStack("GROWTH_CHAMBER_NETHER_MK2", RED_STAINED_GLASS, "&cNether Growth Chamber MK2", "", "&fAutomatically grows &cnether &fplants.", "", "&c3x production.", machine(MEDIUM, MACHINE), speed(3), LoreBuilderDynamic.powerPerSecond(128));
    public static final SlimefunItemStack GROWTH_CHAMBER_OCEAN = new SlimefunItemStack("GROWTH_CHAMBER_OCEAN", CYAN_STAINED_GLASS, "&bOcean Growth Chamber", "", "&fAutomatically grows &9water &fplants.", "Can revive dead coral!", "", machine(MEDIUM, MACHINE), speed(1), LoreBuilderDynamic.powerPerSecond(32));
    public static final SlimefunItemStack GROWTH_CHAMBER_OCEAN_MK2 = new SlimefunItemStack("GROWTH_CHAMBER_OCEAN_MK2", LIGHT_BLUE_STAINED_GLASS, "&bOcean Growth Chamber MK2", "", "&fAutomatically grows &9water &fplants.", "Can revive dead coral!", "", "&c3x production.", machine(MEDIUM, MACHINE), speed(3), LoreBuilderDynamic.powerPerSecond(128));
    public static final SlimefunItemStack ANTIGRAVITY_BUBBLE = new SlimefunItemStack("ANTIGRAVITY_BUBBLE", OBSIDIAN, "&6Antigravity Bubble", "", "&f Creative Flight within an 45 block area", "", machine(END_GAME, MACHINE), LoreBuilderDynamic.powerPerSecond(128));
    public static final SlimefunItemStack WEATHER_CONTROLLER = new SlimefunItemStack("WEATHER_CONTROLLER", BLUE_STAINED_GLASS, "&6Weather Controller", "", "&fControls the weather when given a key item.", "", machine(MEDIUM, MACHINE), LoreBuilderDynamic.powerPerSecond(32));
    public static final SlimefunItemStack POTION_SPRINKLER = new SlimefunItemStack("POTION_SPRINKLER", new CustomItemStack(getItemStack(fromHashCode("8d302104180cb79d5f4cf423649ddfa8ffb31a1875fa02a983cd248c72dfb0ea"))), "&6Potion Sprinkler", "", "&fRanged Multiple person potion effect applier.", "", machine(MEDIUM, MACHINE), LoreBuilderDynamic.powerPerSecond(32));
    public static final SlimefunItemStack BARBED_WIRE = new SlimefunItemStack("BARBED_WIRE", new CustomItemStack(getItemStack(fromHashCode("b2ac6c219004d82dfa627ffab664f29c53ecc112d91c9d7a9c915c426832412"))), "&6Barbed Wire", "", "&fPushes mobs away in a radius.", "", machine(MEDIUM, MACHINE), LoreBuilderDynamic.powerPerSecond(16));
    public static final SlimefunItemStack MATERIAL_HIVE = new SlimefunItemStack("MATERIAL_HIVE", BEEHIVE, "&6Material Hive", "", "&fUsing power and bees, slowly generates materials.", "", radioactive(io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity.HIGH), "", machine(END_GAME, MACHINE), LoreBuilderDynamic.powerPerSecond(1024));
    public static final SlimefunItemStack WIRELESS_CHARGER = new SlimefunItemStack("WIRELESS_CHARGER", CLAY, "&6Wireless Charger", "", "&fWireless charge items in your inventory", "", machine(MEDIUM, MACHINE), LoreBuilderDynamic.powerPerSecond(16));
    public static final SlimefunItemStack SEED_PLUCKER = new SlimefunItemStack("SEED_PLUCKER", ORANGE_STAINED_GLASS, "&6Seed Plucker", "", "&fPull seeds out of plant based items.", "", machine(MEDIUM, MACHINE), LoreBuilderDynamic.powerPerSecond(32));
    public static final SlimefunItemStack BANDAID_MANAGER = new SlimefunItemStack("BANDAID_MANAGER", LAPIS_BLOCK, "&6Item Band Manager", "", "&fManages Item Bands", "", machine(ADVANCED, MACHINE), LoreBuilderDynamic.powerPerSecond(48));
    public static final SlimefunItemStack ORECHID = new SlimefunItemStack("ORECHID", END_ROD, "&6Orechid", "", "&fUsing Stone or Netherack and power, it makes their respective ores.", "", machine(END_GAME, MACHINE), LoreBuilderDynamic.power(1024, " per block converted."));
    public static final SlimefunItemStack WIRELESS_ENERGY_POINT = new SlimefunItemStack("WIRELESS_ENERGY_POINT", new CustomItemStack(getItemStack(fromHashCode("335a21d95e8597759fb259c951ea68e1ad3374ca41e56ef126ffabfe03c1e0"))), "&6Wireless Energy Point", "", "&fTransfers Energy Wirelessly", "&ffrom a Wireless Energy Bank", "Right Click on the Wireless Energy Bank to connect!", "", machine(MEDIUM, MACHINE), powerBuffer(5120), LoreBuilderDynamic.powerPerSecond(1024), "");
    public static final SlimefunItemStack WIRELESS_ENERGY_BANK = new SlimefunItemStack("WIRELESS_ENERGY_BANK", SNOW_BLOCK, "&6Wireless Energy Bank", "", "&fStores power for an", "&fWireless Energy Point to use.", "", machine(MEDIUM, CAPACITOR), powerBuffer(10240), LoreBuilderDynamic.powerPerSecond(1024), "");
    public static final SlimefunItemStack WIRELESS_ITEM_INPUT = new SlimefunItemStack("WIRELESS_ITEM_INPUT", new CustomItemStack(getItemStack(fromHashCode("abb55560c695d976b346e188d3df2bcd8c5aa32b933141a9715c42f64cb6cee"))), "&6Wireless Item Input", "", "&fTransfers Items Wirelessly", "&f to Wireless Item Output", "", machine(MEDIUM, MACHINE), powerBuffer(1024), LoreBuilderDynamic.power(8, " per stack of items"), "");
    public static final SlimefunItemStack WIRELESS_ITEM_OUTPUT = new SlimefunItemStack("WIRELESS_ITEM_OUTPUT", new CustomItemStack(getItemStack(fromHashCode("c510d9b61ca333d2946c61a26cb17e374d4adb573b46afdebaf89f65ba5d4ae2"))), "&6Wireless Item Output", "", "&fTransfer Items Wirelessly", "&ffrom Wireless Item Input", "Right Click on the Wireless Item Output to connect!", "", machine(MEDIUM, MACHINE), powerBuffer(1024), LoreBuilderDynamic.power(8, " per stack of items"), "");
    public static final SlimefunItemStack TESSERACT = new SlimefunItemStack("TESSERACT", PURPUR_BLOCK, "&6Tesseract", "", "&fTransfers Items and Energy Wirelessly", "&fThese are even 2-way!", "Right Click on another Tesseract to connect!", "", machine(END_GAME, MACHINE), powerBuffer(65535), LoreBuilderDynamic.powerPerSecond(1024), "");
    public static final SlimefunItemStack EXTERNAL_HEATER = new SlimefunItemStack("DT_EXTERNAL_HEATER", BRICKS, "&6External Heater", "", "&fExternally heats Furnaces, Blast Furnaces,", "&fand Smokers.", "", machine(MEDIUM, MACHINE), powerBuffer(2048), LoreBuilderDynamic.power(128, " per heated block"));

    // Generators
    public static final SlimefunItemStack WATER_MILL = new SlimefunItemStack("WATER_MILL", COBBLESTONE_WALL, "&6Hydro Generator", "", "&fCreates energy from flowing water", "", machine(MEDIUM, GENERATOR), powerBuffer(128), LoreBuilderDynamic.powerPerSecond(16));
    public static final SlimefunItemStack WATER_TURBINE = new SlimefunItemStack("WATER_TURBINE", PRISMARINE_WALL, "&6Hydro Turbine", "", "&fCreates energy from flowing water", "", machine(MEDIUM, GENERATOR), powerBuffer(512), LoreBuilderDynamic.powerPerSecond(64));
    public static final SlimefunItemStack DRAGON_GENERATOR = new SlimefunItemStack("DRAGON_GENERATOR", GRAY_CONCRETE, "&6Dragon Egg Generator", "", "&fCreates energy from the warmth of a Dragon Egg", "", machine(MEDIUM, GENERATOR), powerBuffer(512), LoreBuilderDynamic.powerPerSecond(32));
    public static final SlimefunItemStack CHIPPING_GENERATOR = new SlimefunItemStack("CHIPPING_GENERATOR", SPRUCE_WOOD, "&6Chipping Generator", "", "&fCreates energy from broken items", "&f(Durability based items)", "", machine(MEDIUM, GENERATOR), powerBuffer(256), LoreBuilderDynamic.power(8, " per durability point"));
    public static final SlimefunItemStack CULINARY_GENERATOR = new SlimefunItemStack("CULINARY_GENERATOR", BLAST_FURNACE, "&6Culinary Generator", "", "&fCreates energy from food energy", "", machine(MEDIUM, GENERATOR), powerBuffer(256), LoreBuilderDynamic.powerPerSecond(8));
    public static final SlimefunItemStack STARDUST_REACTOR = new SlimefunItemStack("STARDUST_REACTOR", IRON_BLOCK, "&6Stardust Reactor", "", "&6Uses Star Dust to produce larges amount of power.", "", machine(END_GAME, GENERATOR), powerBuffer(32768), LoreBuilderDynamic.powerPerSecond(1024));
    public static final RecipeType DT_MATERIAL_HIVE = new RecipeType(new NamespacedKey(plugin, "DT_MATERIAL_HIVE"), MATERIAL_HIVE, (recipe, output) -> {
        MaterialHive materialHive = ((MaterialHive) MATERIAL_HIVE.getItem());
        materialHive.getMachineRecipes().add(new me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe(1800, recipe, new org.bukkit.inventory.ItemStack[]{output}));
    });
}