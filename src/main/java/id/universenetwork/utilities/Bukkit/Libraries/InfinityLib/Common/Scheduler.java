package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common;

import lombok.experimental.UtilityClass;

import javax.annotation.ParametersAreNonnullByDefault;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static org.bukkit.Bukkit.getScheduler;

@UtilityClass
@ParametersAreNonnullByDefault
public class Scheduler {
    public static void run(Runnable runnable) {
        getScheduler().runTask(plugin, runnable);
    }

    public static void runAsync(Runnable runnable) {
        getScheduler().runTaskAsynchronously(plugin, runnable);
    }

    public static void run(int delayTicks, Runnable runnable) {
        getScheduler().runTaskLater(plugin, runnable, delayTicks);
    }

    public static void runAsync(int delayTicks, Runnable runnable) {
        getScheduler().runTaskLaterAsynchronously(plugin, runnable, delayTicks);
    }

    public static void repeat(int intervalTicks, Runnable runnable) {
        repeat(intervalTicks, 1, runnable);
    }

    public static void repeatAsync(int intervalTicks, Runnable runnable) {
        repeatAsync(intervalTicks, 1, runnable);
    }

    public static void repeat(int intervalTicks, int delayTicks, Runnable runnable) {
        getScheduler().runTaskTimer(plugin, runnable, delayTicks, Math.max(1, intervalTicks));
    }

    public static void repeatAsync(int intervalTicks, int delayTicks, Runnable runnable) {
        getScheduler().runTaskTimerAsynchronously(plugin, runnable, delayTicks, Math.max(1, intervalTicks));
    }
}