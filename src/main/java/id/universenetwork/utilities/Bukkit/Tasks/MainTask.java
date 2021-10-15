package id.universenetwork.utilities.Bukkit.Tasks;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static org.bukkit.Bukkit.getScheduler;

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
        getScheduler().scheduleSyncDelayedTask(plugin, removeTask, 1);
    }
}