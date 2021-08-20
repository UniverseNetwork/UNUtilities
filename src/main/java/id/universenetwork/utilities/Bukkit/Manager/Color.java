package id.universenetwork.utilities.Bukkit.Manager;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Color {
    public static String Translator(String text) {
        return ChatColor.translateAlternateColorCodes('&', text.replaceAll("%p%", Config.get().getString("Settings.prefix", "&bU&eN&9Utilities &8&l>")));
    }

    public static void sendTranslate(CommandSender sender, String text) {
        sender.sendMessage(Translator(text));
    }
}
