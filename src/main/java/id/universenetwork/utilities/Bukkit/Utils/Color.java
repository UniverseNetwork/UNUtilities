package id.universenetwork.utilities.Bukkit.Utils;

public class Color {
    public static String Translator(String text) {
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', text.replaceAll("%p%", id.universenetwork.utilities.Bukkit.Manager.Config.get().getString("Settings.prefix", "&bU&eN&9Utilities &8&l>")));
    }

    public static void sendTranslate(org.bukkit.command.CommandSender sender, String text) {
        sender.sendMessage(Translator(text));
    }
}