package id.universenetwork.utilities.Bukkit.Utils;

import java.util.logging.Level;

import static id.universenetwork.utilities.Bukkit.UNUtilities.translateColor;
import static org.bukkit.Bukkit.getLogger;

@lombok.experimental.UtilityClass
public class Logger {
    public static void log(Level Level, String Message, Exception Exception) {
        if (Level == Level.WARNING) getLogger().log(Level, Exception, () -> translateColor("%p% &e" + Message));
        if (Level == Level.SEVERE) getLogger().log(Level, Exception, () -> translateColor("%p% &c" + Message));
        getLogger().log(Level, Exception, () -> translateColor("%p% &f" + Message));
    }

    public static void info(String Message) {
        getLogger().info(translateColor("%p% &f" + Message));
    }

    public static void warning(String Message) {
        getLogger().warning(translateColor("%p% &e" + Message));
    }

    public static void severe(String Message) {
        getLogger().severe(translateColor("%p% &c" + Message));
    }
}