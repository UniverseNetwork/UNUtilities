package id.universenetwork.utilities.bukkit.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

import java.util.logging.Level;

@UtilityClass
public class Logger {
    public void log(Level Tier, String Message, Exception Exception) {
        if (Tier == Level.WARNING)
            Bukkit.getLogger().log(Tier, Exception, () -> Text.translateColor("%p% &e" + Message));
        if (Tier == Level.SEVERE)
            Bukkit.getLogger().log(Tier, Exception, () -> Text.translateColor("%p% &c" + Message));
        else Bukkit.getLogger().log(Tier, Exception, () -> Text.translateColor("%p% &r" + Message));
    }

    public void info(String Message) {
        Bukkit.getLogger().info(Text.translateColor("%p% &r" + Message));
    }

    public void warning(String Message) {
        Bukkit.getLogger().warning(Text.translateColor("%p% &e" + Message));
    }

    public void severe(String Message) {
        Bukkit.getLogger().severe(Text.translateColor("%p% &c" + Message));
    }
}