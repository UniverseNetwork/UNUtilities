package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.Utils;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.PotionMeta;

public class ItemUtil {
    public static SlimefunItemStack createCustomPotionItem(String i, String name, org.bukkit.Color color) {
        return new SlimefunItemStack(i, org.bukkit.Material.POTION, name, itemMeta -> {
            PotionMeta meta = (PotionMeta) itemMeta;
            meta.setColor(color);
            meta.addEnchant(org.bukkit.enchantments.Enchantment.LURE, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            meta.setLore(java.util.Collections.singletonList(org.bukkit.ChatColor.BLUE + io.github.thebusybiscuit.slimefun4.utils.ChatUtils.humanize(i) + " (" + formatTime(id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.Settings.getPotionDuration()) + ")"));
        });
    }

    static String formatTime(long secs) {
        return String.format("%d:%02d", (secs % 3600) / 60, secs % 60);
    }
}