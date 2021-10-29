package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Gear;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Groups.Groups;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.potion.PotionEffect;

import java.util.Objects;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Blocks.InfinityWorkbench.TYPE;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Materials.Materials.*;
import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.MAGIC_WORKBENCH;
import static org.bukkit.enchantments.Enchantment.FIRE_ASPECT;
import static org.bukkit.potion.PotionEffectType.*;

@lombok.experimental.UtilityClass
public final class Gear {
    public static final SlimefunItemStack ENDER_FLAME = new SlimefunItemStack("ENDER_FLAME", Material.ENCHANTED_BOOK, "&cEnder Flame");
    public static final SlimefunItemStack CROWN = new SlimefunItemStack("INFINITY_CROWN", Material.NETHERITE_HELMET, "&bInfinity Crown", "&7Night Vision I", "&7Conduit Power I", "&7Elytra Crash Immunity");
    public static final SlimefunItemStack CHESTPLATE = new SlimefunItemStack("INFINITY_CHESTPLATE", Material.NETHERITE_CHESTPLATE, "&bInfinity Chestplate", "&7Strength II", "&7Resistance I", "&7Fire Resistance I", "&7Bee Sting Immunity");
    public static final SlimefunItemStack LEGGINGS = new SlimefunItemStack("INFINITY_LEGGINGS", Material.NETHERITE_LEGGINGS, "&bInfinity Leggings", "&7Haste III", "&7Regeneration I", "&7Saturation I", "&7Radiation Immunity");
    public static final SlimefunItemStack BOOTS = new SlimefunItemStack("INFINITY_BOOTS", Material.NETHERITE_BOOTS, "&bInfinity Boots", "&7Speed III", "&7Dolphins Grace I");
    public static final SlimefunItemStack INFINITY_MATRIX = new SlimefunItemStack("INFINITY_MATRIX", Material.NETHER_STAR, "&fInfinity Matrix", "&6Gives Unlimited Flight", "&7Right-Click to enable/disable and claim", "&7Crouch and Right-Click to remove ownership", "&bSoulbound");
    public static final SlimefunItemStack SHIELD = new SlimefunItemStack("INFINITY_SHIELD", Material.SHIELD, "&bCosmic Aegis");
    public static final SlimefunItemStack BLADE = new SlimefunItemStack("INFINITY_BLADE", Material.NETHERITE_SWORD, "&bBlade of the Cosmos");
    public static final SlimefunItemStack PICKAXE = new SlimefunItemStack("INFINITY_PICKAXE", Material.NETHERITE_PICKAXE, "&9World Breaker");
    public static final SlimefunItemStack AXE = new SlimefunItemStack("INFINITY_AXE", Material.NETHERITE_AXE, "&4Nature's Ruin");
    public static final SlimefunItemStack SHOVEL = new SlimefunItemStack("INFINITY_SHOVEL", Material.NETHERITE_SHOVEL, "&aMountain Eater");
    public static final SlimefunItemStack BOW = new SlimefunItemStack("INFINITY_BOW", Material.BOW, "&6Sky Piercer");
    public static final SlimefunItemStack VEIN_MINER_RUNE = new SlimefunItemStack("VEIN_MINER_RUNE", Material.DIAMOND, "&bVein Miner Rune", "&7Upgrades a tool to vein-mine certain materials");

    public static void setup() {
        addInfinityEnchants(CROWN, CHESTPLATE, LEGGINGS, BOOTS, AXE, BLADE, PICKAXE, SHIELD, SHOVEL, BOW);
        EnchantmentStorageMeta storageMeta = (EnchantmentStorageMeta) ENDER_FLAME.getItemMeta();
        storageMeta.addStoredEnchant(FIRE_ASPECT, 10, true);
        ENDER_FLAME.setItemMeta(storageMeta);
        new io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem(Groups.MAIN_MATERIALS, ENDER_FLAME, MAGIC_WORKBENCH, new ItemStack[]{ENDER_ESSENCE, ENDER_ESSENCE, ENDER_ESSENCE, ENDER_ESSENCE, new ItemStack(Material.BOOK), ENDER_ESSENCE, ENDER_ESSENCE, ENDER_ESSENCE, ENDER_ESSENCE}).register(addon);
        new InfinityArmor(CROWN, new PotionEffect[]{new PotionEffect(NIGHT_VISION, 600, 0, false, false, false), new PotionEffect(CONDUIT_POWER, 600, 0, false, false, false),}, new ItemStack[]{null, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, null, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, INFINITE_INGOT, null, INFINITE_INGOT, null, null, INFINITE_INGOT, null, null, null, null, null, null, null, null, null, null, null, null, null}).register(addon);
        new InfinityArmor(CHESTPLATE, new PotionEffect[]{new PotionEffect(DAMAGE_RESISTANCE, 600, 0, false, false, false), new PotionEffect(INCREASE_DAMAGE, 600, 1, false, false, false), new PotionEffect(FIRE_RESISTANCE, 600, 0, false, false, false)}, new ItemStack[]{null, INFINITE_INGOT, null, null, INFINITE_INGOT, null, INFINITE_INGOT, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, INFINITE_INGOT, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, VOID_INGOT, INFINITE_INGOT, VOID_INGOT, VOID_INGOT, INFINITE_INGOT, VOID_INGOT, null, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, null, null, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, null}).register(addon);
        new InfinityArmor(LEGGINGS, new PotionEffect[]{new PotionEffect(FAST_DIGGING, 600, 2, false, false, false), new PotionEffect(REGENERATION, 600, 0, false, false, false), new PotionEffect(SATURATION, 600, 0, false, false, false),}, new ItemStack[]{null, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, null, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, INFINITE_INGOT, null, null, INFINITE_INGOT, VOID_INGOT, VOID_INGOT, INFINITE_INGOT, null, null, INFINITE_INGOT, VOID_INGOT, VOID_INGOT, INFINITE_INGOT, null, null, INFINITE_INGOT, VOID_INGOT, null, INFINITE_INGOT, null, null, INFINITE_INGOT, null}).register(addon);
        new InfinityArmor(BOOTS, new PotionEffect[]{new PotionEffect(SPEED, 600, 2, false, false, false), new PotionEffect(DOLPHINS_GRACE, 600, 0, false, false, false),}, new ItemStack[]{null, null, null, null, null, null, INFINITE_INGOT, INFINITE_INGOT, null, null, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, null, null, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, VOID_INGOT, null, null, VOID_INGOT, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, null, null, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, null, null, INFINITE_INGOT, INFINITE_INGOT}).register(addon);
        new InfinityTool(SHIELD, new ItemStack[]{INFINITE_INGOT, INFINITE_INGOT, null, null, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, INFINITE_INGOT, null, INFINITE_INGOT, VOID_INGOT, VOID_INGOT, INFINITE_INGOT, null, null, INFINITE_INGOT, VOID_INGOT, VOID_INGOT, INFINITE_INGOT, null}).register(addon);
        new InfinityBow(Groups.INFINITY_CHEAT, BOW, TYPE, new ItemStack[]{null, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, null, null, INFINITE_INGOT, null, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, null, VOID_INGOT, null, null, ENDER_FLAME, INFINITE_INGOT, VOID_INGOT, null, VOID_INGOT, null, null, INFINITE_INGOT, INFINITE_INGOT, null, null, VOID_INGOT, null, null, INFINITE_INGOT, null, null, null, VOID_INGOT, INFINITE_INGOT, null}).register(addon);
        new InfinityTool(AXE, new ItemStack[]{null, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, null, null, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, null, null, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, null, null, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, null, VOID_INGOT, null, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, VOID_INGOT, null, null, null, VOID_INGOT, null}).register(addon);
        new InfinityTool(BLADE, new ItemStack[]{null, null, null, null, INFINITE_INGOT, INFINITE_INGOT, null, null, null, INFINITE_INGOT, VOID_INGOT, INFINITE_INGOT, null, null, INFINITE_INGOT, VOID_INGOT, INFINITE_INGOT, null, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, INFINITE_INGOT, null, null, null, VOID_INGOT, INFINITE_INGOT, null, null, null, VOID_INGOT, null, INFINITE_INGOT, null, null, null}).register(addon);
        new InfinityTool(SHOVEL, new ItemStack[]{null, null, null, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, null, null, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, null, null, INFINITE_INGOT, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, null, null, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, null, null, VOID_INGOT, null, null, null, null, VOID_INGOT, null, null, null, null, null}).register(addon);
        new InfinityTool(PICKAXE, new ItemStack[]{null, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, INFINITE_INGOT, null, null, null, null, INFINITE_INGOT, VOID_INGOT, INFINITE_INGOT, null, null, null, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, null, null, VOID_INGOT, null, null, INFINITE_INGOT, null, VOID_INGOT, null, null, null, VOID_INGOT, VOID_INGOT, null, null, null, null, null}).register(addon);
        new InfinityMatrix(Groups.INFINITY_CHEAT, INFINITY_MATRIX, TYPE, new ItemStack[]{INFINITE_INGOT, null, INFINITE_INGOT, INFINITE_INGOT, null, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, VOID_INGOT, VOID_INGOT, VOID_INGOT, INFINITE_INGOT, VOID_INGOT, VOID_INGOT, new ItemStack(Material.ELYTRA), new ItemStack(Material.ELYTRA), VOID_INGOT, VOID_INGOT, VOID_INGOT, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, VOID_INGOT, INFINITE_INGOT, VOID_INGOT, VOID_INGOT, VOID_INGOT, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, null, INFINITE_INGOT, INFINITE_INGOT, null, INFINITE_INGOT}).register(addon);
        new VeinMinerRune(Groups.MAIN_MATERIALS, VEIN_MINER_RUNE, MAGIC_WORKBENCH, new ItemStack[]{MAGSTEEL_PLATE, SlimefunItems.PICKAXE_OF_VEIN_MINING, MAGSTEEL_PLATE, ENDER_ESSENCE, SlimefunItems.BLANK_RUNE, ENDER_ESSENCE, MAGSTEEL_PLATE, SlimefunItems.PICKAXE_OF_VEIN_MINING, MAGSTEEL_PLATE,}).register(addon);
    }

    static void addInfinityEnchants(SlimefunItemStack... items) {
        ConfigurationSection typeSection = id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.InfinityExpansion.getConfig().getConfigurationSection("infinity-enchant-levels");
        if (typeSection == null) {
            org.bukkit.Bukkit.getLogger().severe(id.universenetwork.utilities.Bukkit.UNUtilities.prefix + " §4Config section \"infinity-enchant-levels\" missing, Check your config and report this!");
            return;
        }
        for (SlimefunItemStack item : items) {
            org.bukkit.inventory.meta.ItemMeta meta = item.getItemMeta();

            // lore
            java.util.List<String> lore;
            if (meta.hasLore()) lore = meta.getLore();
            else lore = new java.util.ArrayList<>();
            lore.add(org.bukkit.ChatColor.AQUA + "Soulbound");
            meta.setLore(lore);

            // find path
            String itemPath = item.getItemId().replace("INFINITY_", "").toLowerCase();
            ConfigurationSection itemSection = typeSection.getConfigurationSection(itemPath);

            // unbreakable and enchants
            meta.setUnbreakable(Objects.requireNonNull(itemSection).getBoolean("unbreakable"));
            for (java.util.Map.Entry<Enchantment, Integer> entry : id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Utils.Util.getEnchants(itemSection).entrySet())
                meta.addEnchant(entry.getKey(), entry.getValue(), true);
            item.setItemMeta(meta);
        }
    }
}