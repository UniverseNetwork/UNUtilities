package id.universenetwork.utilities.Bukkit.Utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

import java.util.logging.Level;

@UtilityClass
public class Logger {
    public static void log(Level Tier, String Message, Exception Exception) {
        if (Tier == Level.WARNING)
            Bukkit.getLogger().log(Tier, Exception, () -> Text.translateColor("%p% &e" + Message));
        if (Tier == Level.SEVERE)
            Bukkit.getLogger().log(Tier, Exception, () -> Text.translateColor("%p% &c" + Message));
        else Bukkit.getLogger().log(Tier, Exception, () -> Text.translateColor("%p% &r" + Message));
    }

    public static void info(String Message) {
        Bukkit.getLogger().info(Text.translateColor("%p% &r" + Message));
    }

    public static void warning(String Message) {
        Bukkit.getLogger().warning(Text.translateColor("%p% &e" + Message));
    }

    public static void severe(String Message) {
        Bukkit.getLogger().severe(Text.translateColor("%p% &c" + Message));
    }
}