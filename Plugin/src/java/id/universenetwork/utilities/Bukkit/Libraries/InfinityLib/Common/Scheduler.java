package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common;

import id.universenetwork.utilities.Bukkit.UNUtilities;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

/**
 * A class for scheduling tasks
 *
 * @author Mooy1
 */
@UtilityClass
public class Scheduler {
    public static void run(Runnable runnable) {
        Bukkit.getScheduler().runTask(UNUtilities.plugin, runnable);
    }

    public static void runAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(UNUtilities.plugin, runnable);
    }

    public static void run(int delayTicks, Runnable runnable) {
        Bukkit.getScheduler().runTaskLater(plugin, runnable, delayTicks);
    }

    public static void runAsync(int delayTicks, Runnable runnable) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, runnable, delayTicks);
    }

    public static void repeat(int intervalTicks, Runnable runnable) {
        repeat(intervalTicks, 1, runnable);
    }

    public static void repeatAsync(int intervalTicks, Runnable runnable) {
        repeatAsync(intervalTicks, 1, runnable);
    }

    public static void repeat(int intervalTicks, int delayTicks, Runnable runnable) {
        Bukkit.getScheduler().runTaskTimer(plugin, runnable, delayTicks, Math.max(1, intervalTicks));
    }

    public static void repeatAsync(int intervalTicks, int delayTicks, Runnable runnable) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, runnable, delayTicks, Math.max(1, intervalTicks));
    }
}