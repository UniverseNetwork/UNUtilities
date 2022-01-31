package id.universenetwork.utilities.Bukkit.NMS.v1_16_R3;

import id.universenetwork.utilities.Bukkit.NMS.v1_16_R3.Tasks.CheckTask;
import id.universenetwork.utilities.Bukkit.NMS.v1_16_R3.Tasks.UntrackerTask;
import org.bukkit.scheduler.BukkitTask;

import static id.universenetwork.utilities.Bukkit.Enums.EntityTrackerFixer.CHECKFREQUENCY;
import static id.universenetwork.utilities.Bukkit.Enums.EntityTrackerFixer.UNTRACKTICKS;
import static id.universenetwork.utilities.Bukkit.Manager.Config.ETFInt;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class NMSHandler implements id.universenetwork.utilities.Bukkit.NMS.NMS {
    @Override
    public BukkitTask startUntrackerTask() {
        return new UntrackerTask().runTaskTimer(plugin, ETFInt(UNTRACKTICKS), ETFInt(UNTRACKTICKS));
    }

    @Override
    public BukkitTask startUCheckTask() {
        return new CheckTask().runTaskTimer(plugin, ETFInt(UNTRACKTICKS) + 1, ETFInt(CHECKFREQUENCY));
    }
}