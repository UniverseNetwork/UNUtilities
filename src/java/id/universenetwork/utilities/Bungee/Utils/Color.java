package id.universenetwork.utilities.Bungee.Utils;

public class Color {
    public static String Translator(String Text) {
        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', org.apache.commons.lang3.StringUtils.replace(Text, "%p%", id.universenetwork.utilities.Bungee.UNUtilities.settings.getString(id.universenetwork.utilities.Universal.Enums.Settings.PREFIX.getConfigPath())));
    }

    public static void sendTranslate(net.md_5.bungee.api.CommandSender sender, String text) {
        sender.sendMessage(Translator(text));
    }
}