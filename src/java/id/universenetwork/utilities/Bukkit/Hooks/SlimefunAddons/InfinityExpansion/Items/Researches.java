package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import lombok.experimental.UtilityClass;
import org.bukkit.NamespacedKey;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Blocks.Blocks.*;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Gear.Gear.*;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Generators.Generators.*;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Machines.Machines.*;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Materials.Materials.*;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.MobData.MobData.*;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Quarries.Quarries.*;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Storage.Storage.*;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

@UtilityClass
public final class Researches {
    static final int FIRST_RESEARCH_ID = 314000000;

    public static void setup() {
        create(0, "harvesting_void", "Harvesting the Void", 35, VOID_BIT, VOID_DUST, VOID_INGOT, VOID_HARVESTER);
        create(1, "infinity_gear", "Infinity Gear", 60, AXE, BLADE, BOW, PICKAXE, SHOVEL, SHIELD, CROWN, CHESTPLATE, LEGGINGS, BOOTS, INFINITY_MATRIX);
        create(2, "singularities", "Creating Singularities", 35, ALUMINUM_SINGULARITY, SILVER_SINGULARITY, COPPER_SINGULARITY, LEAD_SINGULARITY, ZINC_SINGULARITY, TIN_SINGULARITY, MAGNESIUM_SINGULARITY, COAL_SINGULARITY, IRON_SINGULARITY, GOLD_SINGULARITY, DIAMOND_SINGULARITY, EMERALD_SINGULARITY, LAPIS_SINGULARITY, REDSTONE_SINGULARITY, NETHERITE_SINGULARITY, QUARTZ_SINGULARITY, SINGULARITY_CONSTRUCTOR, RESOURCE_SYNTHESIZER);
        create(3, "infinity_ingot", "Creating Infinity", 40, EARTH_SINGULARITY, FORTUNE_SINGULARITY, MAGIC_SINGULARITY, METAL_SINGULARITY, INFINITE_INGOT, INFINITY_FORGE);
        create(4, "harvesting_end", "Harvesting the End", 20, ENDER_ESSENCE, ENDER_FLAME, VEIN_MINER_RUNE);
        create(5, "mob_data", "Virtual Mob Farms", 30, CHAMBER, EMPTY_DATA_CARD, INFUSER, COW, SHEEP, CHICKEN);
        create(6, "oscillators", "Improving Quarries", 30, DIAMOND_OSCILLATOR, EMERALD_OSCILLATOR, LAPIS_OSCILLATOR, REDSTONE_OSCILLATOR, QUARTZ_OSCILLATOR);
        create(7, "machine_materials", "Machine Materials", 20, MAGSTEEL, MAGSTEEL_PLATE, MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_PLATE);
        create(8, "compressed_cobble", "Compressing Cobblestone", 15, COBBLE_1, COBBLE_2, COBBLE_3, COBBLE_4, COBBLE_5, COBBLE_PRESS);
        create(9, "starter_machines", "Starter Machines", 15, BASIC_COBBLE, BASIC_GROWER, BASIC_TREE, BASIC_QUARRY);
        create(10, "strainers", "Material Strainers", 10, STRAINER_BASE, BASIC_STRAINER, ADVANCED_STRAINER, REINFORCED_STRAINER);
        create(11, "starter_power", "Starter Power", 15, BASIC_PANEL, HYDRO);
        create(12, "advanced_power", "Advanced Power", 35, ADVANCED_PANEL, GEOTHERMAL, REINFORCED_HYDRO, REINFORCED_GEOTHERMAL, CELESTIAL_PANEL, SlimefunExtension.ADVANCED_NETHER_STAR_REACTOR);
        create(13, "advanced_machines", "Advanced Machines", 40, DUST_EXTRACTOR, EXTREME_FREEZER, GEO_QUARRY, DECOMPRESSOR, STONEWORKS_FACTORY, BASIC_OBSIDIAN, INGOT_FORMER, ADVANCED_ANVIL, URANIUM_EXTRACTOR, GEAR_TRANSFORMER);
        create(14, "upgraded_machines", "Upgraded Machines", 40, SlimefunExtension.ADVANCED_CHARGER, SlimefunExtension.ADVANCED_ENCHANTER, SlimefunExtension.ADVANCED_DISENCHANTER, SlimefunExtension.ADVANCED_SMELTERY, ADVANCED_COBBLE, ADVANCED_GROWER, ADVANCED_TREE, SlimefunExtension.ADVANCED_GEO_MINER, ADVANCED_QUARRY);
        create(15, "infinity_upgrades", "Infinity Upgrades", 80, INFINITE_VOID_HARVESTER, INFINITY_COBBLE, INFINITY_CONSTRUCTOR, INFINITY_GROWER, INFINITY_TREE, INFINITY_INGOT_FORMER, INFINITE_PANEL, INFINITY_REACTOR, INFINITY_STORAGE, SlimefunExtension.INFINITY_CAPACITOR, SlimefunExtension.INFINITY_CHARGER, SlimefunExtension.INFINITY_DISENCHANTER, SlimefunExtension.INFINITY_ENCHANTER, INFINITY_QUARRY, INFINITY_DUST_EXTRACTOR);
        create(16, "void_upgrades", "Void Upgrades", 45, VOID_PANEL, SlimefunExtension.VOID_CAPACITOR, VOID_STORAGE, POWERED_BEDROCK, VOID_QUARRY);
        create(17, "advanced_alloys", "Advanced Alloys", 30, TITANIUM, ADAMANTITE, MAGNONIUM, MYTHRIL);
        create(18, "big_storage", "Big Storage", 20, STORAGE_FORGE, BASIC_STORAGE, ADVANCED_STORAGE, REINFORCED_STORAGE);
        create(19, "infinity_materials", "Infinity Materials", 40, INFINITY_SINGULARITY, INFINITE_CORE, INFINITE_CIRCUIT);
        create(20, "neutral_mob_data", "Neutral Mob Data", 25, SLIME, MAGMA_CUBE, BEE, VILLAGER);
        create(21, "hostile_mob_data", "Hostile Mob Data", 30, ZOMBIE, SPIDER, SKELETON, CREEPER, GUARDIAN, WITCH);
        create(22, "advanced_mob_data", "Advanced Mob Data", 45, WITHER_SKELETON, ENDERMEN, IRON_GOLEM, BLAZE);
        create(23, "boss_mob_data", "Boss Mob Data", 60, WITHER, ENDER_DRAGON);
    }

    static void create(int id, String key, String name, int cost, SlimefunItemStack... items) {
        new Research(new NamespacedKey(plugin, key), FIRST_RESEARCH_ID + id, name, cost).addItems(items).register();
    }
}