package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders.Skript.Util;

import id.universenetwork.utilities.Bukkit.Manager.Color;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Nullable;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders.SkriptPlaceholders.hasMVdW;
import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders.SkriptPlaceholders.hasPAPI;

public class PlaceholderUtils {
    @Nullable
    public static String getPlaceholder(String placeholder, @Nullable OfflinePlayer player, boolean colorize) {
        String value;
        if (hasMVdW) {
            if (placeholder.charAt(0) == '{' && placeholder.charAt(placeholder.length() - 1) == '}') {
                value = be.maximvdw.placeholderapi.PlaceholderAPI.replacePlaceholders(player, placeholder);
                return value.equals(placeholder) ? null : (colorize ? Color.Translator(value) : value);
            }
        }
        if (hasPAPI) {
            // Try to add percentage signs manually
            if (placeholder.indexOf('%') == -1) placeholder = "%" + placeholder + "%";
            value = PlaceholderAPI.setPlaceholders(player, placeholder);
            if (value.isEmpty() || value.equalsIgnoreCase(placeholder)) return null;
            return colorize ? Color.Translator(value) : value;
        }
        return null;
    }
}