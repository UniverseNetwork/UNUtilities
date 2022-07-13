package id.universenetwork.utilities.bukkit.NMS;

import org.bukkit.scheduler.BukkitTask;

import static id.universenetwork.utilities.bukkit.Enums.EntityTrackerFixer.ENABLED;
import static id.universenetwork.utilities.bukkit.manager.Config.ETFBoolean;
import static id.universenetwork.utilities.bukkit.manager.Config.set;
import static id.universenetwork.utilities.bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public final class ETF {
    static NMS nms;
    static BukkitTask untrack;
    static BukkitTask track;
    static boolean enabled = false;

    public static void setup() {
        if (ETFBoolean(ENABLED)) {
            nms = getNMS();
            if (nms != null) {
                untrack = nms.startUntrackerTask();
                track = nms.startUCheckTask();
                enabled = true;
            } else set(ENABLED.getConfigPath(), false);
        }
    }

    public static void reload() {
        if (ETFBoolean(ENABLED)) {
            nms = getNMS();
            if (nms != null) {
                if (enabled) {
                    untrack.cancel();
                    track.cancel();
                }
                untrack = nms.startUntrackerTask();
                track = nms.startUCheckTask();
                enabled = true;
            } else set(ENABLED.getConfigPath(), false);
        } else if (!ETFBoolean(ENABLED) && enabled) {
            untrack.cancel();
            track.cancel();
            enabled = false;
        }
    }

    static NMS getNMS() {
        String packageName = getServer().getClass().getPackage().getName();
        String version = packageName.substring(packageName.lastIndexOf('.') + 1);
        try {
            final Class<?> clazz = Class.forName("id.universenetwork.utilities.Bukkit.NMS." + version + ".NMSHandler");
            if (NMS.class.isAssignableFrom(clazz)) return (NMS) clazz.getConstructor().newInstance();
        } catch (final Exception e) {
            e.printStackTrace();
            getLogger().severe(prefix + " Entity Tracker Fixer Features does not support this version of spigot!");
            return null;
        }
        return null;
    }
}