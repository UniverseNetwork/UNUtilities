package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.API.Effects.PotionSightType;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.Items.PotionItems;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.Items.PotionRecipeType;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.Items.PotionSightItem;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.MultiBlocks.Alchemic.AlchemicStation;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.GRIND_STONE;
import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getMinecraftVersion;

public class PotionsItemSetup {
    public static void setup() {
        new AlchemicStation(PotionItems.potionCategory, PotionItems.ALCHEMIC_STATION).register(addon);
        powdersSetup();
        potionsSetup();
    }

    static void potionsSetup() {
        new PotionSightItem(PotionItems.potionCategory, PotionItems.COAL_SIGHT, PotionRecipeType.ALCHEMIC_STATION_RECIPE, new ItemStack[]{null, PotionItems.COAL_POWDER, null, null, null, null, null, PotionItems.mundanePotion, null}, PotionSightType.COAL_SIGHT).register(addon);
        new PotionSightItem(PotionItems.potionCategory, PotionItems.IRON_SIGHT, PotionRecipeType.ALCHEMIC_STATION_RECIPE, new ItemStack[]{null, PotionItems.IRON_POWDER, null, null, null, null, null, PotionItems.mundanePotion, null}, PotionSightType.IRON_SIGHT).register(addon);
        new PotionSightItem(PotionItems.potionCategory, PotionItems.DIAMOND_SIGHT, PotionRecipeType.ALCHEMIC_STATION_RECIPE, new ItemStack[]{null, PotionItems.DIAMOND_POWDER, null, null, null, null, null, PotionItems.mundanePotion, null}, PotionSightType.DIAMOND_SIGHT).register(addon);
        new PotionSightItem(PotionItems.potionCategory, PotionItems.GOLD_SIGHT, PotionRecipeType.ALCHEMIC_STATION_RECIPE, new ItemStack[]{null, PotionItems.GOLD_POWDER, null, null, null, null, null, PotionItems.mundanePotion, null}, PotionSightType.GOLD_SIGHT).register(addon);
        new PotionSightItem(PotionItems.potionCategory, PotionItems.LAPIS_SIGHT, PotionRecipeType.ALCHEMIC_STATION_RECIPE, new ItemStack[]{null, PotionItems.LAPIS_POWDER, null, null, null, null, null, PotionItems.mundanePotion, null}, PotionSightType.LAPIS_SIGHT).register(addon);
        new PotionSightItem(PotionItems.potionCategory, PotionItems.REDSTONE_SIGHT, PotionRecipeType.ALCHEMIC_STATION_RECIPE, new ItemStack[]{null, PotionItems.REDSTONE_POWDER, null, null, null, null, null, PotionItems.mundanePotion, null}, PotionSightType.REDSTONE_SIGHT).register(addon);
        new PotionSightItem(PotionItems.potionCategory, PotionItems.EMERALD_SIGHT, PotionRecipeType.ALCHEMIC_STATION_RECIPE, new ItemStack[]{null, PotionItems.EMERALD_POWDER, null, null, null, null, null, PotionItems.mundanePotion, null}, PotionSightType.EMERALD_SIGHT).register(addon);
        new PotionSightItem(PotionItems.potionCategory, PotionItems.QUARTZ_SIGHT, PotionRecipeType.ALCHEMIC_STATION_RECIPE, new ItemStack[]{null, PotionItems.QUARTZ_POWDER, null, null, null, null, null, PotionItems.mundanePotion, null}, PotionSightType.QUARTZ_SIGHT).register(addon);
        if (getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_16))
            new PotionSightItem(PotionItems.potionCategory, PotionItems.ANCIENT_DEBRIS_SIGHT, PotionRecipeType.ALCHEMIC_STATION_RECIPE, new ItemStack[]{null, PotionItems.ANCIENT_DEBRIS_POWDER, null, null, null, null, null, PotionItems.mundanePotion, null}, PotionSightType.ANCIENT_DEBRIS_SIGHT).register(addon);
        if (getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_17))
            new PotionSightItem(PotionItems.potionCategory, PotionItems.COPPER_SIGHT, PotionRecipeType.ALCHEMIC_STATION_RECIPE, new ItemStack[]{null, PotionItems.COPPER_POWDER, null, null, null, null, null, PotionItems.mundanePotion, null}, PotionSightType.COPPER_SIGHT).register(addon);
    }

    static void powdersSetup() {
        new SlimefunItem(PotionItems.potionCategory, PotionItems.COAL_POWDER, GRIND_STONE, new ItemStack[]{new ItemStack(Material.COAL_ORE), null, null, null, null, null, null, null, null}).register(addon);
        new SlimefunItem(PotionItems.potionCategory, PotionItems.IRON_POWDER, GRIND_STONE, new ItemStack[]{new ItemStack(Material.IRON_ORE), null, null, null, null, null, null, null, null}).register(addon);
        new SlimefunItem(PotionItems.potionCategory, PotionItems.DIAMOND_POWDER, GRIND_STONE, new ItemStack[]{new ItemStack(Material.DIAMOND_ORE), null, null, null, null, null, null, null, null}).register(addon);
        new SlimefunItem(PotionItems.potionCategory, PotionItems.GOLD_POWDER, GRIND_STONE, new ItemStack[]{new ItemStack(Material.GOLD_ORE), null, null, null, null, null, null, null, null}).register(addon);
        new SlimefunItem(PotionItems.potionCategory, PotionItems.LAPIS_POWDER, GRIND_STONE, new ItemStack[]{new ItemStack(Material.LAPIS_ORE), null, null, null, null, null, null, null, null}).register(addon);
        new UnplaceableBlock(PotionItems.potionCategory, PotionItems.REDSTONE_POWDER, GRIND_STONE, new ItemStack[]{new ItemStack(Material.REDSTONE_ORE), null, null, null, null, null, null, null, null}).register(addon);
        new SlimefunItem(PotionItems.potionCategory, PotionItems.EMERALD_POWDER, GRIND_STONE, new ItemStack[]{new ItemStack(Material.EMERALD_ORE), null, null, null, null, null, null, null, null}).register(addon);
        new SlimefunItem(PotionItems.potionCategory, PotionItems.QUARTZ_POWDER, GRIND_STONE, new ItemStack[]{new ItemStack(Material.NETHER_QUARTZ_ORE), null, null, null, null, null, null, null, null}).register(addon);
        if (getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_16))
            new UnplaceableBlock(PotionItems.potionCategory, PotionItems.ANCIENT_DEBRIS_POWDER, GRIND_STONE, new ItemStack[]{new ItemStack(Material.ANCIENT_DEBRIS), null, null, null, null, null, null, null, null}).register(addon);
        if (getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_17))
            new SlimefunItem(PotionItems.potionCategory, PotionItems.COPPER_POWDER, GRIND_STONE, new ItemStack[]{new ItemStack(Material.valueOf("COPPER_ORE")), null, null, null, null, null, null, null, null}).register(addon);
    }
}