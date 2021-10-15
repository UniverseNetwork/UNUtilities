package id.universenetwork.utilities.Bukkit.Tasks;

import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;

import static id.universenetwork.utilities.Bukkit.Utils.ActivityUtils.*;
import static org.bukkit.Bukkit.getWorlds;

public class RemoveActivityTask implements Runnable {
    @Override
    public void run() {
        for (World world : getWorlds())
            for (LivingEntity entity : world.getLivingEntities())
                if (entity instanceof Villager) {
                    if (badCurrentActivity((Villager) entity)) {
                        setScheduleEmpty((Villager) entity);
                        setActivitiesNormal((Villager) entity);
                    }
                    replaceBadMemories((Villager) entity);
                }
    }
}