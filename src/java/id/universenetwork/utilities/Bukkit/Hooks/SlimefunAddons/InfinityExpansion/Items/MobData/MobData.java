package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.MobData;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Core.Environment;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.MachineLore;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.environment;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Groups.Groups.MOB_SIMULATION;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.InfinityExpansion.getConfig;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Materials.Materials.*;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.MobData.MobDataTier.*;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;
import static org.bukkit.Bukkit.getLogger;

@UtilityClass
public final class MobData {
    static int CHAMBER_INTERVAL() {
        int v = getConfig().getInt("mob-simulation-options.ticks-per-output", 20);
        if (v < 1 || v > 1000) {
            getLogger().warning(prefix + " §6ticks-per-output in mob-simulation-options on §dInfinityExpansion §6Addon Settings is less than 1 or greater than 1000!");
            return 20;
        }
        return v;
    }

    static final int CHAMBER_BUFFER = 15000;
    static final int CHAMBER_ENERGY = 150;
    static final int INFUSER_ENERGY = 20000;
    public static final SlimefunItemStack EMPTY_DATA_CARD = new SlimefunItemStack("EMPTY_DATA_CARD", Material.CHAINMAIL_CHESTPLATE, "&8Empty Data Card", "&7Infuse with a mob's items to fill");
    public static final SlimefunItemStack INFUSER = new SlimefunItemStack("DATA_INFUSER", Material.LODESTONE, "&8Mob Data Infuser", "&7Infused empty data cards with mob items", "", MachineLore.energy(INFUSER_ENERGY) + "per use");
    public static final SlimefunItemStack CHAMBER = new SlimefunItemStack("MOB_SIMULATION_CHAMBER", Material.GILDED_BLACKSTONE, "&8Mob Simulation Chamber", "&7Use mob data cards to activate", "", MachineLore.energyBuffer(CHAMBER_BUFFER), MachineLore.energyPerSecond(CHAMBER_ENERGY));
    public static final SlimefunItemStack COW = MobDataCard.create("Cow", PASSIVE);
    public static final SlimefunItemStack SHEEP = MobDataCard.create("Sheep", PASSIVE);
    public static final SlimefunItemStack CHICKEN = MobDataCard.create("Chicken", PASSIVE);
    public static final SlimefunItemStack VILLAGER = MobDataCard.create("Villager", PASSIVE);
    public static final SlimefunItemStack BEE = MobDataCard.create("Bee", NEUTRAL);
    public static final SlimefunItemStack SLIME = MobDataCard.create("Slime", NEUTRAL);
    public static final SlimefunItemStack MAGMA_CUBE = MobDataCard.create("Magma Cube", NEUTRAL);
    public static final SlimefunItemStack WITCH = MobDataCard.create("Witch", HOSTILE);
    public static final SlimefunItemStack ZOMBIE = MobDataCard.create("Zombie", HOSTILE);
    public static final SlimefunItemStack SPIDER = MobDataCard.create("Spider", HOSTILE);
    public static final SlimefunItemStack SKELETON = MobDataCard.create("Skeleton", HOSTILE);
    public static final SlimefunItemStack CREEPER = MobDataCard.create("Creeper", HOSTILE);
    public static final SlimefunItemStack WITHER_SKELETON = MobDataCard.create("Wither Skeleton", ADVANCED);
    public static final SlimefunItemStack ENDERMEN = MobDataCard.create("Endermen", ADVANCED);
    public static final SlimefunItemStack GUARDIAN = MobDataCard.create("Guardian", ADVANCED);
    public static final SlimefunItemStack IRON_GOLEM = MobDataCard.create("Iron Golem", ADVANCED);
    public static final SlimefunItemStack BLAZE = MobDataCard.create("Blaze", ADVANCED);
    public static final SlimefunItemStack WITHER = MobDataCard.create("Wither", BOSS);
    public static final SlimefunItemStack ENDER_DRAGON = MobDataCard.create("Ender Dragon", BOSS);

    public static void setup() {
        new MobSimulationChamber(MOB_SIMULATION, CHAMBER, ENHANCED_CRAFTING_TABLE, new ItemStack[]{MAGSTEEL_PLATE, MACHINE_PLATE, MAGSTEEL_PLATE, MACHINE_CIRCUIT, PROGRAMMABLE_ANDROID_BUTCHER, MACHINE_CIRCUIT, MAGSTEEL_PLATE, MACHINE_PLATE, MAGSTEEL_PLATE,}, CHAMBER_ENERGY, CHAMBER_INTERVAL()).register(addon);
        new MobDataInfuser(MOB_SIMULATION, INFUSER, ENHANCED_CRAFTING_TABLE, new ItemStack[]{MACHINE_CIRCUIT, REINFORCED_ALLOY_INGOT, MACHINE_CIRCUIT, REINFORCED_ALLOY_INGOT, MACHINE_CORE, REINFORCED_ALLOY_INGOT, MACHINE_CIRCUIT, REINFORCED_ALLOY_INGOT, MACHINE_CIRCUIT}, INFUSER_ENERGY).register(addon);
        new SlimefunItem(MOB_SIMULATION, EMPTY_DATA_CARD, ENHANCED_CRAFTING_TABLE, new ItemStack[]{MAGNESIUM_INGOT, MACHINE_CIRCUIT, MAGNESIUM_INGOT, SYNTHETIC_SAPPHIRE, SYNTHETIC_DIAMOND, SYNTHETIC_EMERALD, MAGNESIUM_INGOT, MACHINE_CIRCUIT, MAGNESIUM_INGOT}).register(addon);

        // There is some issues with player skull items in randomized sets when testing
        if (environment == Environment.TESTING) return;
        new MobDataCard(ZOMBIE, HOSTILE, new ItemStack[]{new ItemStack(Material.IRON_SWORD, 1), new ItemStack(Material.ROTTEN_FLESH, 16), new ItemStack(Material.IRON_SHOVEL, 1), new ItemStack(Material.IRON_INGOT, 64), EMPTY_DATA_CARD, new ItemStack(Material.IRON_INGOT, 1), new ItemStack(Material.CARROT, 64), new ItemStack(Material.ROTTEN_FLESH, 16), new ItemStack(Material.POTATO, 64)}).addDrop(Material.ROTTEN_FLESH, 1).register(addon);
        new MobDataCard(SLIME, NEUTRAL, new ItemStack[]{new ItemStack(Material.SLIME_BLOCK, 16), new ItemStack(Material.LIME_DYE, 16), new ItemStack(Material.SLIME_BLOCK, 16), new ItemStack(Material.LIME_DYE, 16), EMPTY_DATA_CARD, new ItemStack(Material.LIME_DYE, 16), new ItemStack(Material.SLIME_BLOCK, 16), new ItemStack(Material.LIME_DYE, 16), new ItemStack(Material.SLIME_BLOCK, 16)}).addDrop(Material.SLIME_BALL, 1).register(addon);
        new MobDataCard(MAGMA_CUBE, NEUTRAL, new ItemStack[]{new ItemStack(Material.MAGMA_BLOCK, 64), new ItemStack(Material.MAGMA_CREAM, 16), new ItemStack(Material.MAGMA_BLOCK, 64), new ItemStack(Material.SLIME_BLOCK, 16), EMPTY_DATA_CARD, new ItemStack(Material.SLIME_BLOCK, 16), new ItemStack(Material.MAGMA_BLOCK, 64), new ItemStack(Material.MAGMA_CREAM, 16), new ItemStack(Material.MAGMA_BLOCK, 64)}).addDrop(Material.MAGMA_CREAM, 1).register(addon);
        new MobDataCard(COW, PASSIVE, new ItemStack[]{new ItemStack(Material.LEATHER, 64), new ItemStack(Material.BEEF, 64), new ItemStack(Material.LEATHER, 64), new ItemStack(Material.COOKED_BEEF, 64), EMPTY_DATA_CARD, new ItemStack(Material.COOKED_BEEF, 64), new ItemStack(Material.LEATHER, 64), new ItemStack(Material.BEEF, 64), new ItemStack(Material.LEATHER, 64)}).addDrop(Material.LEATHER, 1).addDrop(Material.BEEF, 1).register(addon);
        new MobDataCard(SHEEP, PASSIVE, new ItemStack[]{new ItemStack(Material.WHITE_WOOL, 64), new ItemStack(Material.MUTTON, 64), new ItemStack(Material.WHITE_WOOL, 64), new ItemStack(Material.COOKED_MUTTON, 64), EMPTY_DATA_CARD, new ItemStack(Material.COOKED_MUTTON, 64), new ItemStack(Material.WHITE_WOOL, 64), new ItemStack(Material.MUTTON, 64), new ItemStack(Material.WHITE_WOOL, 64)}).addDrop(Material.WHITE_WOOL, 1).addDrop(Material.MUTTON, 1).addDrop(Material.PINK_WOOL, 10000).register(addon);
        new MobDataCard(SPIDER, HOSTILE, new ItemStack[]{new ItemStack(Material.COBWEB, 8), new ItemStack(Material.STRING, 64), new ItemStack(Material.COBWEB, 8), new ItemStack(Material.SPIDER_EYE, 32), EMPTY_DATA_CARD, new ItemStack(Material.SPIDER_EYE, 32), new ItemStack(Material.COBWEB, 8), new ItemStack(Material.STRING, 64), new ItemStack(Material.COBWEB, 8)}).addDrop(Material.STRING, 1).addDrop(Material.SPIDER_EYE, 2).register(addon);
        new MobDataCard(SKELETON, HOSTILE, new ItemStack[]{new ItemStack(Material.LEATHER_HELMET, 1), new ItemStack(Material.BONE, 64), new ItemStack(Material.LEATHER_HELMET, 1), new ItemStack(Material.ARROW, 64), EMPTY_DATA_CARD, new ItemStack(Material.ARROW, 64), new ItemStack(Material.BOW, 1), new ItemStack(Material.BONE, 64), new ItemStack(Material.BOW, 1)}).addDrop(Material.BONE, 1).addDrop(Material.ARROW, 3).register(addon);
        new MobDataCard(WITHER_SKELETON, ADVANCED, new ItemStack[]{new ItemStack(Material.WITHER_SKELETON_SKULL, 8), new ItemStack(Material.BONE, 64), new ItemStack(Material.WITHER_SKELETON_SKULL, 8), new ItemStack(Material.COAL_BLOCK, 64), EMPTY_DATA_CARD, new ItemStack(Material.COAL_BLOCK, 64), new ItemStack(Material.STONE_SWORD, 1), new ItemStack(Material.BONE, 64), new ItemStack(Material.STONE_SWORD, 1)}).addDrop(Material.COAL, 2, 1).addDrop(Material.BONE, 3).addDrop(Material.WITHER_SKELETON_SKULL, 15).register(addon);
        new MobDataCard(ENDERMEN, ADVANCED, new ItemStack[]{new ItemStack(Material.ENDER_EYE, 16), new ItemStack(Material.OBSIDIAN, 64), new ItemStack(Material.ENDER_EYE, 16), new ItemStack(Material.ENDER_PEARL, 16), EMPTY_DATA_CARD, new ItemStack(Material.ENDER_PEARL, 16), new ItemStack(Material.ENDER_EYE, 16), new ItemStack(Material.OBSIDIAN, 64), new ItemStack(Material.ENDER_EYE, 16)}).addDrop(Material.ENDER_PEARL, 1).register(addon);
        new MobDataCard(CREEPER, HOSTILE, new ItemStack[]{new ItemStack(Material.TNT, 16), new ItemStack(Material.GREEN_DYE, 64), new ItemStack(Material.TNT, 16), new ItemStack(Material.GUNPOWDER, 16), EMPTY_DATA_CARD, new ItemStack(Material.GUNPOWDER, 16), new ItemStack(Material.TNT, 16), new ItemStack(Material.GREEN_DYE, 64), new ItemStack(Material.TNT, 16)}).addDrop(Material.GUNPOWDER, 1).register(addon);
        new MobDataCard(GUARDIAN, HOSTILE, new ItemStack[]{new ItemStack(Material.COD, 16), new ItemStack(Material.PRISMARINE_SHARD, 64), new ItemStack(Material.PRISMARINE_CRYSTALS, 64), new ItemStack(Material.SPONGE, 4), EMPTY_DATA_CARD, new ItemStack(Material.PUFFERFISH, 4), new ItemStack(Material.PRISMARINE_CRYSTALS, 64), new ItemStack(Material.PRISMARINE_SHARD, 64), new ItemStack(Material.COOKED_COD, 16)}).addDrop(Material.PRISMARINE_SHARD, 1).addDrop(Material.PRISMARINE_CRYSTALS, 2).addDrop(Material.COD, 3).addDrop(Material.SPONGE, 40).register(addon);
        new MobDataCard(CHICKEN, PASSIVE, new ItemStack[]{new ItemStack(Material.CHICKEN, 64), new ItemStack(Material.FEATHER, 64), new ItemStack(Material.COOKED_CHICKEN, 64), new ItemStack(Material.EGG, 16), EMPTY_DATA_CARD, new ItemStack(Material.EGG, 16), new ItemStack(Material.COOKED_CHICKEN, 64), new ItemStack(Material.FEATHER, 64), new ItemStack(Material.CHICKEN, 64)}).addDrop(Material.CHICKEN, 1).addDrop(Material.FEATHER, 2).register(addon);
        new MobDataCard(IRON_GOLEM, ADVANCED, new ItemStack[]{new ItemStack(Material.IRON_BLOCK, 64), new ItemStack(Material.PUMPKIN, 16), new ItemStack(Material.IRON_BLOCK, 64), new ItemStack(Material.POPPY, 16), EMPTY_DATA_CARD, new ItemStack(Material.POPPY, 16), new ItemStack(Material.IRON_BLOCK, 64), new ItemStack(Material.PUMPKIN, 16), new ItemStack(Material.IRON_BLOCK, 64)}).addDrop(Material.IRON_INGOT, 2, 1).addDrop(Material.POPPY, 3).addDrop(BASIC_CIRCUIT_BOARD, 3).register(addon);
        new MobDataCard(BLAZE, ADVANCED, new ItemStack[]{new ItemStack(Material.MAGMA_BLOCK, 64), new ItemStack(Material.BLAZE_ROD, 64), new ItemStack(Material.MAGMA_BLOCK, 64), new ItemStack(Material.BLAZE_ROD, 64), EMPTY_DATA_CARD, new ItemStack(Material.BLAZE_ROD, 64), new ItemStack(Material.MAGMA_BLOCK, 64), new ItemStack(Material.BLAZE_ROD, 64), new ItemStack(Material.MAGMA_BLOCK, 64)}).addDrop(Material.BLAZE_ROD, 1).register(addon);
        new MobDataCard(WITHER, MINI_BOSS, new ItemStack[]{new ItemStack(Material.WITHER_SKELETON_SKULL, 64), new ItemStack(Material.WITHER_SKELETON_SKULL, 64), new ItemStack(Material.WITHER_SKELETON_SKULL, 64), new SlimefunItemStack(WITHER_PROOF_OBSIDIAN, 64), EMPTY_DATA_CARD, new SlimefunItemStack(WITHER_PROOF_OBSIDIAN, 64), new SlimefunItemStack(VOID_INGOT, 4), new SlimefunItemStack(WITHER_ASSEMBLER, 4), new SlimefunItemStack(VOID_INGOT, 4)}).addDrop(Material.NETHER_STAR, 1).addDrop(COMPRESSED_CARBON, 8, 2).register(addon);
        new MobDataCard(ENDER_DRAGON, BOSS, new ItemStack[]{new ItemStack(Material.END_CRYSTAL, 64), new SlimefunItemStack(VOID_INGOT, 32), new ItemStack(Material.CHORUS_FLOWER, 64), INFUSED_ELYTRA, EMPTY_DATA_CARD, new ItemStack(Material.DRAGON_HEAD, 1), new SlimefunItemStack(ENDER_LUMP_3, 64), new SlimefunItemStack(VOID_INGOT, 32), new ItemStack(Material.DRAGON_BREATH, 64)}).addDrop(VOID_DUST, 1).addDrop(ENDER_ESSENCE, 4).addDrop(Material.DRAGON_EGG, 1_000_000).register(addon);
        new MobDataCard(BEE, NEUTRAL, new ItemStack[]{new ItemStack(Material.HONEYCOMB_BLOCK, 16), new ItemStack(Material.HONEY_BLOCK, 16), new ItemStack(Material.HONEYCOMB_BLOCK, 16), new ItemStack(Material.HONEY_BLOCK, 16), EMPTY_DATA_CARD, new ItemStack(Material.HONEY_BLOCK, 16), new ItemStack(Material.HONEYCOMB_BLOCK, 16), new ItemStack(Material.HONEY_BLOCK, 16), new ItemStack(Material.HONEYCOMB_BLOCK, 16)}).addDrop(Material.HONEYCOMB, 1).register(addon);
        new MobDataCard(VILLAGER, ADVANCED, new ItemStack[]{new ItemStack(Material.EMERALD, 64), new ItemStack(Material.POTATO, 64), new ItemStack(Material.EMERALD, 64), new ItemStack(Material.CARROT, 64), EMPTY_DATA_CARD, new ItemStack(Material.WHEAT, 64), new ItemStack(Material.EMERALD, 64), new ItemStack(Material.PUMPKIN, 64), new ItemStack(Material.EMERALD, 64)}).addDrop(Material.EMERALD, 1).register(addon);
        new MobDataCard(WITCH, ADVANCED, new ItemStack[]{new ItemStack(Material.REDSTONE_BLOCK, 64), new ItemStack(Material.GLASS, 64), new ItemStack(Material.SUGAR, 64), new ItemStack(Material.GLOWSTONE, 64), EMPTY_DATA_CARD, new ItemStack(Material.GLOWSTONE, 64), new ItemStack(Material.SUGAR, 64), new ItemStack(Material.GLASS, 64), new ItemStack(Material.REDSTONE_BLOCK, 64)}).addDrop(Material.SUGAR, 1).addDrop(Material.REDSTONE, 1).addDrop(Material.GLASS_BOTTLE, 1).addDrop(Material.GLOWSTONE_DUST, 1).register(addon);
    }
}