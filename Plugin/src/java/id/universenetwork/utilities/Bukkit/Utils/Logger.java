package id.universenetwork.utilities.Bukkit.Utils;

import java.util.logging.Level;

import static id.universenetwork.utilities.Bukkit.UNUtilities.translateColor;
import static org.bukkit.Bukkit.getLogger;

@lombok.experimental.UtilityClass
public class Logger {
    public static void log(Level lvl, String msg, Object param1) {
        if (lvl == Level.WARNING) getLogger().log(lvl, translateColor(translateColor("%p% §e" + msg)), param1);
        if (lvl == Level.SEVERE) getLogger().log(lvl, translateColor(translateColor("%p% §c" + msg)), param1);
        getLogger().log(lvl, translateColor(translateColor("%p% §f" + msg)), param1);
    }

    public static void info(String msg) {
        getLogger().info(translateColor("%p% &f" + msg));
    }

    public static void warning(String msg) {
        getLogger().warning(translateColor("%p% &e" + msg));
    }

    public static void severe(String msg) {
        getLogger().severe(translateColor("%p% &c" + msg));
    }
}