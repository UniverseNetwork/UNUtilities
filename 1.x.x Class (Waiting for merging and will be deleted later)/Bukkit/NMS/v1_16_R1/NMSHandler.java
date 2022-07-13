package id.universenetwork.utilities.bukkit.NMS.v1_16_R1;

import id.universenetwork.utilities.bukkit.NMS.v1_16_R1.Tasks.CheckTask;
import id.universenetwork.utilities.bukkit.NMS.v1_16_R1.Tasks.UntrackerTask;
import org.bukkit.scheduler.BukkitTask;

import static id.universenetwork.utilities.bukkit.Enums.EntityTrackerFixer.CHECKFREQUENCY;
import static id.universenetwork.utilities.bukkit.Enums.EntityTrackerFixer.UNTRACKTICKS;
import static id.universenetwork.utilities.bukkit.manager.Config.ETFInt;
import static id.universenetwork.utilities.bukkit.UNUtilities.plugin;

public class NMSHandler implements id.universenetwork.utilities.bukkit.NMS.NMS {
    @Override
    public BukkitTask startUntrackerTask() {
        return new UntrackerTask().runTaskTimer(plugin, ETFInt(UNTRACKTICKS), ETFInt(UNTRACKTICKS));
    }

    @Override
    public BukkitTask startUCheckTask() {
        return new CheckTask().runTaskTimer(plugin, ETFInt(UNTRACKTICKS) + 1, ETFInt(CHECKFREQUENCY));
    }
}