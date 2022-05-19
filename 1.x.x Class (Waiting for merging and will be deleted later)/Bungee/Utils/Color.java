package id.universenetwork.utilities.Bungee.Utils;

import id.universenetwork.utilities.Bungee.UNUtilities;
import id.universenetwork.utilities.Universal.Enums.Settings;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import org.apache.commons.lang.StringUtils;

public class Color {
    public static String Translator(String Text) {
        return ChatColor.translateAlternateColorCodes('&', StringUtils.replace(Text, "%p%", UNUtilities.settings.getString(Settings.PREFIX.getConfigPath())));
    }

    public static void sendTranslate(CommandSender sender, String text) {
        sender.sendMessage(Translator(text));
    }
}