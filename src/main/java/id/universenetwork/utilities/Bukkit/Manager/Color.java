package id.universenetwork.utilities.Bukkit.Manager;

import org.bukkit.ChatColor;

public class Color {
    public static String Translator(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
