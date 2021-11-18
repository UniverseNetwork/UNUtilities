package id.universenetwork.utilities.Bungee.Utils;

import id.universenetwork.utilities.Universal.Enums.Settings;

public class Color {
    public static String Translator(String Text) {
        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', Text.replaceAll("%p%", id.universenetwork.utilities.Bungee.UNUtilities.settings.getString(Settings.PREFIX.getConfigPath())));
    }

    public static void sendTranslate(net.md_5.bungee.api.CommandSender sender, String text) {
        sender.sendMessage(Translator(text));
    }
}