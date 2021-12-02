package id.universenetwork.utilities.Bukkit.Utils;

public class Color {
    public static String Translate(String Text) {
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', org.apache.commons.lang.StringUtils.replace(Text, "%p%", id.universenetwork.utilities.Bukkit.Manager.Config.get().getString(id.universenetwork.utilities.Universal.Enums.Settings.PREFIX.getConfigPath())));
    }

    public static void sendTranslated(org.bukkit.command.CommandSender Sender, String Text) {
        Sender.sendMessage(Translate(Text));
    }
}