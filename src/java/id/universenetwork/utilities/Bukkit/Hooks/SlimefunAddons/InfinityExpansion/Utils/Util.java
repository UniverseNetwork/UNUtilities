package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Utils;

import lombok.experimental.UtilityClass;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.slimefunTickCount;
import static org.bukkit.Bukkit.getLogger;

@UtilityClass
public final class Util {
    @NotNull
    public static ItemStack getDisplayItem(@NotNull ItemStack output) {
        ItemMeta meta = output.getItemMeta();
        List<String> lore;
        if (meta.hasLore()) lore = meta.getLore();
        else lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GREEN + "-------------------");
        lore.add(ChatColor.GREEN + "\u21E8 Click to craft");
        lore.add(ChatColor.GREEN + "-------------------");
        output.setItemMeta(meta);
        return output;
    }

    @NotNull
    public static Map<Enchantment, Integer> getEnchants(@NotNull ConfigurationSection section) {
        Map<Enchantment, Integer> enchants = new HashMap<>();
        for (String path : section.getKeys(false)) {
            Enchantment e = enchantmentByPath(path);
            if (e != null) {
                int level = section.getInt(path);
                if (level > 0 && level <= Short.MAX_VALUE) enchants.put(e, level);
                else if (level != 0) {
                    section.set(path, 0);
                    getLogger().log(Level.WARNING, "Enchantment level " + level + " is out of bounds for " + e.getKey() + ", resetting to default!");
                }
            }
        }
        return enchants;
    }

    @Nullable
    static Enchantment enchantmentByPath(@NotNull String path) {
        switch (path) {
            case "sharpness":
                return Enchantment.DAMAGE_ALL;
            case "smite":
                return Enchantment.DAMAGE_UNDEAD;
            case "bane-of-arthropods":
                return Enchantment.DAMAGE_ARTHROPODS;
            case "efficiency":
                return Enchantment.DIG_SPEED;
            case "protection":
                return Enchantment.PROTECTION_ENVIRONMENTAL;
            case "fire-aspect":
                return Enchantment.FIRE_ASPECT;
            case "fortune":
                return Enchantment.LOOT_BONUS_BLOCKS;
            case "looting":
                return Enchantment.LOOT_BONUS_MOBS;
            case "silk-touch":
                return Enchantment.SILK_TOUCH;
            case "thorns":
                return Enchantment.THORNS;
            case "aqua-affinity":
                return Enchantment.WATER_WORKER;
            case "power":
                return Enchantment.ARROW_DAMAGE;
            case "flame":
                return Enchantment.ARROW_FIRE;
            case "infinity":
                return Enchantment.ARROW_INFINITE;
            case "punch":
                return Enchantment.ARROW_KNOCKBACK;
            case "feather-falling":
                return Enchantment.PROTECTION_FALL;
            case "unbreaking":
                return Enchantment.DURABILITY;
            default:
                return null;
        }
    }

    public static boolean isWaterLogged(@NotNull Block b) {
        if (slimefunTickCount % 63 == 0) {
            BlockData blockData = b.getBlockData();
            if (blockData instanceof Waterlogged) {
                Waterlogged waterLogged = (Waterlogged) blockData;
                if (waterLogged.isWaterlogged()) {
                    BlockStorage.addBlockInfo(b.getLocation(), "water_logged", "true");
                    return true;
                } else {
                    BlockStorage.addBlockInfo(b.getLocation(), "water_logged", "false");
                    return false;
                }
            } else return false;
        } else return "true".equals(BlockStorage.getLocationInfo(b.getLocation(), "water_logged"));
    }

    public static int getIntData(String key, Location block) {
        String val = BlockStorage.getLocationInfo(block, key);
        if (val == null) {
            BlockStorage.addBlockInfo(block, key, "0");
            return 0;
        }
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException x) {
            BlockStorage.addBlockInfo(block, key, "0");
            return 0;
        }
    }
}