package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.Items;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.API.Effects.PotionSightType;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.Utils.ItemUtil;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.Utils.Color.Translate;
import static org.bukkit.Color.AQUA;

public class PotionItems {
    // Normal Items
    public static final ItemStack mundanePotion = new CustomItemStack(Material.POTION, (itemMeta -> {
        PotionMeta meta = (PotionMeta) itemMeta;
        meta.setBasePotionData(new PotionData(PotionType.MUNDANE));
    }));

    // Categories
    public static final ItemGroup potionCategory = new ItemGroup(new NamespacedKey(plugin, "potionexpansion"), new CustomItemStack(Material.POTION, itemMeta -> {
        PotionMeta meta = (PotionMeta) itemMeta;
        meta.setColor(AQUA);
        meta.setDisplayName(Translate("&bPotion Expansion"));
        meta.addEnchant(Enchantment.LURE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    }));

    // Items
    public static final SlimefunItemStack ALCHEMIC_STATION = new SlimefunItemStack("ALCHEMIC_STATION", Material.BREWING_STAND, "&6Alchemic Station", "&a&oWorks like normal brewing stand but can make sight potions.");

    // Powders
    public static final SlimefunItemStack COAL_POWDER = new SlimefunItemStack("COAL_POWDER", Material.GUNPOWDER, "&8Coal Powder");
    public static final SlimefunItemStack IRON_POWDER = new SlimefunItemStack("IRON_POWDER", Material.GUNPOWDER, "&7Iron Powder");
    public static final SlimefunItemStack DIAMOND_POWDER = new SlimefunItemStack("DIAMOND_POWDER", Material.GLOWSTONE_DUST, "&bDiamond Powder");
    public static final SlimefunItemStack GOLD_POWDER = new SlimefunItemStack("GOLD_POWDER", Material.GLOWSTONE_DUST, "&eGold Powder");
    public static final SlimefunItemStack LAPIS_POWDER = new SlimefunItemStack("LAPIS_POWDER", Material.GUNPOWDER, "&9Lapis Powder");
    public static final SlimefunItemStack REDSTONE_POWDER = new SlimefunItemStack("REDSTONE_POWDER", Material.REDSTONE, "&cRedstone Powder");
    public static final SlimefunItemStack EMERALD_POWDER = new SlimefunItemStack("EMERALD_POWDER", Material.GLOWSTONE_DUST, "&aEmerald Powder");
    public static final SlimefunItemStack QUARTZ_POWDER = new SlimefunItemStack("QUARTZ_POWDER", Material.SUGAR, "&fQuartz Powder");
    public static final SlimefunItemStack ANCIENT_DEBRIS_POWDER = new SlimefunItemStack("ANCIENT_DEBRIS_POWDER", Material.REDSTONE, "&4Ancient Debris Powder");
    public static final SlimefunItemStack COPPER_POWDER = new SlimefunItemStack("COPPER_POWDER", Material.GLOWSTONE_DUST, "&6Copper Powder");

    // Sights
    public static final SlimefunItemStack COAL_SIGHT = ItemUtil.createCustomPotionItem("COAL_SIGHT", "&8&lCoalSight", PotionSightType.COAL_SIGHT.getColor());
    public static final SlimefunItemStack IRON_SIGHT = ItemUtil.createCustomPotionItem("IRON_SIGHT", "&7&lIronSight", PotionSightType.IRON_SIGHT.getColor());
    public static final SlimefunItemStack DIAMOND_SIGHT = ItemUtil.createCustomPotionItem("DIAMOND_SIGHT", "&b&lDiamondSight", PotionSightType.DIAMOND_SIGHT.getColor());
    public static final SlimefunItemStack GOLD_SIGHT = ItemUtil.createCustomPotionItem("GOLD_SIGHT", "&e&lGoldSight", PotionSightType.GOLD_SIGHT.getColor());
    public static final SlimefunItemStack LAPIS_SIGHT = ItemUtil.createCustomPotionItem("LAPIS_SIGHT", "&9&lLapisSight", PotionSightType.LAPIS_SIGHT.getColor());
    public static final SlimefunItemStack REDSTONE_SIGHT = ItemUtil.createCustomPotionItem("REDSTONE_SIGHT", "&c&lRedstoneSight", PotionSightType.REDSTONE_SIGHT.getColor());
    public static final SlimefunItemStack EMERALD_SIGHT = ItemUtil.createCustomPotionItem("EMERALD_SIGHT", "&a&lEmeraldSight", PotionSightType.EMERALD_SIGHT.getColor());
    public static final SlimefunItemStack QUARTZ_SIGHT = ItemUtil.createCustomPotionItem("QUARTZ_SIGHT", "&f&lQuartzSight", PotionSightType.QUARTZ_SIGHT.getColor());
    public static final SlimefunItemStack ANCIENT_DEBRIS_SIGHT = ItemUtil.createCustomPotionItem("ANCIENT_DEBRIS_SIGHT", "&4&lAncientDebrisSight", PotionSightType.ANCIENT_DEBRIS_SIGHT.getColor());
    public static final SlimefunItemStack COPPER_SIGHT = ItemUtil.createCustomPotionItem("COPPER_SIGHT", "&6&lCopperSight", PotionSightType.COPPER_SIGHT.getColor());
}