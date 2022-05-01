package id.universenetwork.utilities.Bukkit.Utils;

import java.util.logging.Level;

import static org.bukkit.Bukkit.getLogger;

@lombok.experimental.UtilityClass
public class Logger {
    public static void log(Level Tier, String Message, Exception Exception) {
        if (Tier == Level.WARNING) getLogger().log(Tier, Exception, () -> Text.translateColor("%p% &e" + Message));
        if (Tier == Level.SEVERE) getLogger().log(Tier, Exception, () -> Text.translateColor("%p% &c" + Message));
        else getLogger().log(Tier, Exception, () -> Text.translateColor("%p% &r" + Message));
    }

    public static void info(String Message) {
        getLogger().info(Text.translateColor("%p% &r" + Message));
    }

    public static void warning(String Message) {
        getLogger().warning(Text.translateColor("%p% &e" + Message));
    }

    public static void severe(String Message) {
        getLogger().severe(Text.translateColor("%p% &c" + Message));
    }
}