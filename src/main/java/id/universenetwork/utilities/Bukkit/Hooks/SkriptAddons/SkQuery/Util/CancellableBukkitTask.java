package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util;

import static org.bukkit.Bukkit.getScheduler;

public abstract class CancellableBukkitTask implements Runnable {
    int taskId;

    public final void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public final void cancel() {
        getScheduler().cancelTask(taskId);
    }
}