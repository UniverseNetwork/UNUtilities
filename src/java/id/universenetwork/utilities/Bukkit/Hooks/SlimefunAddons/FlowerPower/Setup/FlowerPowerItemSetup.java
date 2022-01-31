package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Setup;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Items.*;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.MultiBlocks.MagicBasin;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Objects.NonplaceableBlock;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.FlowerPowerItems.*;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;
import static org.bukkit.potion.PotionType.*;

/**
 * Registers all of the items
 *
 * @author NCBPFluffyBear
 */
public class FlowerPowerItemSetup {
    // Placeholder item for magic basin item frames
    static final CustomItemStack basinFrame = new CustomItemStack(Material.ITEM_FRAME, null, "&7Place on the side of the Experience Cauldron");
    static final java.util.List<Pair<SlimefunItemStack, Material>> allFlowers = new java.util.ArrayList<>(java.util.Arrays.asList(new Pair<>(GLISTENING_POPPY, Material.POPPY), new Pair<>(GLISTENING_DANDELION, Material.DANDELION), new Pair<>(GLISTENING_OXEYE_DAISY, Material.OXEYE_DAISY), new Pair<>(GLISTENING_ALLIUM, Material.ALLIUM)));
    static final ItemStack speedPotion = new ItemStack(Material.POTION);
    static final ItemStack damagePotion = new ItemStack(Material.POTION);
    static final ItemStack healthPotion = new ItemStack(Material.POTION);

    public static void setup() {
        // Multiblocks
        // This is a placeholder machine; can't be built due to frames
        new MagicBasin(FLOWERPOWER_CATEGORY, MAGIC_BASIN, new ItemStack[]{null, basinFrame, null, basinFrame, EXPERIENCE_CAULDRON, basinFrame, null, basinFrame, null}, org.bukkit.block.BlockFace.SELF).register(addon);

        // Blocks
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.MultiBlocks.ExperienceCauldron(FLOWERPOWER_CATEGORY, EXPERIENCE_CAULDRON, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{FLASK_OF_KNOWLEDGE, getItem(Material.IRON_BARS), FLASK_OF_KNOWLEDGE, getItem(Material.BUCKET), getItem(Material.CAULDRON), getItem(Material.BUCKET), FLASK_OF_KNOWLEDGE, getItem(Material.IRON_BARS), FLASK_OF_KNOWLEDGE}).register(addon);

        // Essentials
        new io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem(FLOWERPOWER_CATEGORY, MAGICAL_WAND, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, null, getItem(Material.GLOWSTONE_DUST), null, getItem(Material.BLAZE_ROD), null, getItem(Material.OXEYE_DAISY), null, null}).register(addon);
        new MagicCream(FLOWERPOWER_CATEGORY, MAGIC_CREAM, RecipeType.MOB_DROP, new ItemStack[]{null, null, null, null, new CustomItemStack(new ItemStack(Material.SLIME_SPAWN_EGG), "&aSlime", "&7Kill a Slime"), null, null, null, null}).register(addon);

        // Glistening Flowers
        for (Pair<SlimefunItemStack, Material> flower : allFlowers)
            new NonplaceableBlock(FLOWERPOWER_CATEGORY, flower.getFirstValue(), MagicBasin.BASIN_RECIPE, new ItemStack[]{getItem(flower.getSecondValue()), MAGIC_CREAM, null, null, null, null, null, null, null}).register(addon);

        // Overgrowth seed
        new OvergrowthSeed(FLOWERPOWER_CATEGORY, OVERGROWTH_SEED, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{getItem(Material.WHEAT_SEEDS), getItem(Material.WHEAT_SEEDS), getItem(Material.WHEAT_SEEDS), getItem(Material.WHEAT_SEEDS), MAGIC_CREAM, getItem(Material.WHEAT_SEEDS), getItem(Material.WHEAT_SEEDS), getItem(Material.WHEAT_SEEDS), getItem(Material.WHEAT_SEEDS)}).register(addon);

        // Flower Crystals
        new NonplaceableBlock(FLOWERPOWER_CATEGORY, RED_CRYSTAL, MagicBasin.BASIN_RECIPE, new ItemStack[]{GLISTENING_POPPY, MAGIC_CREAM, getItem(Material.QUARTZ_BLOCK), getItem(Material.GHAST_TEAR), null, null, null, null, null}).register(addon);
        new NonplaceableBlock(FLOWERPOWER_CATEGORY, YELLOW_CRYSTAL, MagicBasin.BASIN_RECIPE, new ItemStack[]{GLISTENING_DANDELION, MAGIC_CREAM, getItem(Material.QUARTZ_BLOCK), getItem(Material.GHAST_TEAR), null, null, null, null, null}).register(addon);
        new NonplaceableBlock(FLOWERPOWER_CATEGORY, WHITE_CRYSTAL, MagicBasin.BASIN_RECIPE, new ItemStack[]{GLISTENING_OXEYE_DAISY, MAGIC_CREAM, getItem(Material.QUARTZ_BLOCK), getItem(Material.GHAST_TEAR), null, null, null, null, null}).register(addon);
        new NonplaceableBlock(FLOWERPOWER_CATEGORY, PURPLE_CRYSTAL, MagicBasin.BASIN_RECIPE, new ItemStack[]{GLISTENING_ALLIUM, MAGIC_CREAM, getItem(Material.QUARTZ_BLOCK), getItem(Material.GHAST_TEAR), null, null, null, null, null}).register(addon);

        // Charms
        new AttributeCharms(FLOWERPOWER_CATEGORY, MOVEMENT_SPEED_CHARM, MagicBasin.BASIN_RECIPE, new ItemStack[]{RED_CRYSTAL, speedPotion, TALISMAN_TRAVELLER, getItem(Material.NETHER_STAR), null, null, null, null, null}, AttributeCharms.Charm.MOVEMENT_SPEED).register(addon);
        new AttributeCharms(FLOWERPOWER_CATEGORY, ATTACK_SPEED_CHARM, MagicBasin.BASIN_RECIPE, new ItemStack[]{PURPLE_CRYSTAL, speedPotion, TALISMAN_HUNTER, getItem(Material.NETHER_STAR), null, null, null, null, null}, AttributeCharms.Charm.ATTACK_SPEED).register(addon);
        new AttributeCharms(FLOWERPOWER_CATEGORY, FLY_SPEED_CHARM, MagicBasin.BASIN_RECIPE, new ItemStack[]{RED_CRYSTAL, getItem(Material.ELYTRA), TALISMAN_TRAVELLER, getItem(Material.NETHER_STAR), null, null, null, null, null}, AttributeCharms.Charm.FLY_SPEED).register(addon);
        new AttributeCharms(FLOWERPOWER_CATEGORY, DAMAGE_CHARM, MagicBasin.BASIN_RECIPE, new ItemStack[]{WHITE_CRYSTAL, damagePotion, TALISMAN_WARRIOR, getItem(Material.NETHER_STAR), null, null, null, null, null}, AttributeCharms.Charm.DAMAGE).register(addon);
        new AttributeCharms(FLOWERPOWER_CATEGORY, HEALTH_CHARM, MagicBasin.BASIN_RECIPE, new ItemStack[]{YELLOW_CRYSTAL, healthPotion, TALISMAN_KNIGHT, getItem(Material.NETHER_STAR), null, null, null, null, null}, AttributeCharms.Charm.MAX_HEALTH).register(addon);

        new ExperienceTome(FLOWERPOWER_CATEGORY, EXPERIENCE_TOME, MagicBasin.BASIN_RECIPE, new ItemStack[]{getItem(Material.WRITABLE_BOOK), EXPERIENCE_CAULDRON, ENCHANTMENT_RUNE, getItem(Material.NETHER_STAR), null, null, null, null, null}).register(addon);
        new InfinityApple(FLOWERPOWER_CATEGORY, INFINITY_APPLE, MagicBasin.BASIN_RECIPE, new ItemStack[]{getItem(Material.APPLE), RED_CRYSTAL, ENCHANTMENT_RUNE, getItem(Material.NETHER_STAR), null, null, null, null, null}).register(addon);
        new InfinityBandage(FLOWERPOWER_CATEGORY, INFINITY_BANDAGE, MagicBasin.BASIN_RECIPE, new ItemStack[]{BANDAGE, YELLOW_CRYSTAL, ENCHANTMENT_RUNE, getItem(Material.NETHER_STAR), null, null, null, null, null}).register(addon);
        new RecallCharm(FLOWERPOWER_CATEGORY, RECALL_CHARM, MagicBasin.BASIN_RECIPE, new ItemStack[]{MAGIC_EYE_OF_ENDER, PURPLE_CRYSTAL, ENCHANTMENT_RUNE, getItem(Material.NETHER_STAR), null, null, null, null, null}).register(addon);

    }

    static ItemStack getItem(Material mat) {
        return new ItemStack(mat);
    }

    static {
        // Build vanilla potion types for recipes
        PotionMeta speedPotionMeta = (PotionMeta) speedPotion.getItemMeta();
        speedPotionMeta.setBasePotionData(new PotionData(SPEED, false, true));
        speedPotion.setItemMeta(speedPotionMeta);
        PotionMeta damagePotionMeta = (PotionMeta) damagePotion.getItemMeta();
        damagePotionMeta.setBasePotionData(new PotionData(STRENGTH, false, true));
        damagePotion.setItemMeta(speedPotionMeta);
        PotionMeta healthPotionMeta = (PotionMeta) healthPotion.getItemMeta();
        healthPotionMeta.setBasePotionData(new PotionData(INSTANT_HEAL, false, true));
        healthPotion.setItemMeta(speedPotionMeta);
    }
}