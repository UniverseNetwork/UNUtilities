package id.universenetwork.utilities.Bukkit.Tasks;

import org.bukkit.Bukkit;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class MainTask implements Runnable {
    final NormalActivityTask activityTask;
    final RemoveActivityTask removeTask;

    public MainTask() {
        activityTask = new NormalActivityTask();
        removeTask = new RemoveActivityTask();
        run();
    }

    @Override
    public void run() {
        activityTask.run();
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, removeTask, 1);
    }
}