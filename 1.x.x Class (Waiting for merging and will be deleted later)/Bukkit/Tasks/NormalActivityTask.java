package id.universenetwork.utilities.bukkit.Tasks;

import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;

import static id.universenetwork.utilities.bukkit.utils.ActivityUtils.*;
import static org.bukkit.Bukkit.getWorlds;

public class NormalActivityTask implements Runnable {
    @Override
    public void run() {
        for (World world : getWorlds())
            for (LivingEntity entity : world.getLivingEntities())
                if (entity instanceof Villager) activateVillager((Villager) entity);
    }

    public static void activateVillager(Villager villager) {
        if (!wouldBeBadActivity(villager) && !isScheduleNormal(villager)) {
            setScheduleNormal(villager);
            setActivitiesNormal(villager);
        }
        clearPlaceholderMemories(villager);
    }
}