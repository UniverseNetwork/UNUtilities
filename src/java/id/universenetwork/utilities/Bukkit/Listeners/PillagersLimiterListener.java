package id.universenetwork.utilities.Bukkit.Listeners;

import id.universenetwork.utilities.Bukkit.Manager.Config;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.Iterator;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Enums.PillagersLimiter.*;
import static org.bukkit.Material.AIR;
import static org.bukkit.event.EventPriority.HIGHEST;

public class PillagersLimiterListener implements org.bukkit.event.Listener {
    @EventHandler(ignoreCancelled = true, priority = HIGHEST)
    public void Limiter(CreatureSpawnEvent e) {
        if (Config.PLBoolean(ENABLED) && e.getEntityType().equals(EntityType.PILLAGER) && Config.PLBoolean(LIMITER_ENABLED)) {
            List<Entity> nearbyEntites = (List) e.getLocation().getWorld().getNearbyEntities(e.getLocation(), Config.PLInt(LIMITER_RADIUS_X), Config.PLInt(LIMITER_RADIUS_Y), Config.PLInt(LIMITER_RADIUS_Z));
            int pillagerCount = 0;
            for (Entity entity : nearbyEntites) if (entity.getType().equals(EntityType.PILLAGER)) ++pillagerCount;
            if (pillagerCount >= Config.PLInt(LIMITER_STOP_AT_AMOUNT)) {
                if (!e.getEntity().getEquipment().getHelmet().getType().equals(AIR) && Config.PLBoolean(LIMITER_IGNORE_LEADERS))
                    return;
                if (e.getEntity().getCustomName() != null && Config.PLBoolean(LIMITER_IGNORE_NAMED)) return;
                if (e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.RAID) && Config.PLBoolean(LIMITER_IGNORE_RAIDERS))
                    return;
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = HIGHEST)
    public void Patrols(CreatureSpawnEvent e) {
        if (Config.PLBoolean(ENABLED) && e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.PATROL) && Config.PLBoolean(PATROL_REMOVER_ENABLED)) {
            if (Config.PLBoolean(PATROL_ONLY_REMOVE_TARGET)) {
                Raider raider = (Raider) e.getEntity();
                raider.setPatrolLeader(false);
                raider.setPatrolTarget(null);
                return;
            }
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = HIGHEST)
    public void Remover(org.bukkit.event.world.ChunkLoadEvent e) {
        if (Config.PLBoolean(ENABLED) && Config.PLBoolean(REMOVER_ENABLED)) {
            Entity[] entities = e.getChunk().getEntities();
            for (Entity entity : entities)
                if (entity.getType().equals(EntityType.PILLAGER)) {
                    LivingEntity pillager = (LivingEntity) entity;
                    if (!pillager.getEquipment().getHelmet().getType().equals(AIR) && Config.PLBoolean(REMOVER_IGNORE_LEADERS))
                        return;
                    if (pillager.getCustomName() != null && Config.PLBoolean(REMOVER_IGNORE_NAMED))
                        return;
                    entity.remove();
                }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = HIGHEST)
    public void Stopper(CreatureSpawnEvent e) {
        if (Config.PLBoolean(ENABLED) && e.getEntityType().equals(EntityType.PILLAGER) && Config.PLBoolean(STOPPER_ENABLED)) {
            if (Config.PLBoolean(STOPPER_USE_HARD_LIMIT)) {
                int pillagerCount = 0;
                World world;
                for (Iterator<World> i = org.bukkit.Bukkit.getWorlds().iterator(); i.hasNext(); pillagerCount += world.getEntitiesByClasses(new Class[]{Pillager.class}).size())
                    world = i.next();
                if (pillagerCount <= Config.PLInt(STOPPER_HARD_LIMIT_AMOUNT)) return;
                e.setCancelled(true);
            }
            if (!e.getEntity().getEquipment().getHelmet().getType().equals(AIR) && Config.PLBoolean(STOPPER_IGNORE_LEADERS))
                return;
            if (e.getEntity().getCustomName() != null && Config.PLBoolean(STOPPER_IGNORE_NAMED)) return;
            if (e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.RAID) && Config.PLBoolean(STOPPER_IGNORE_RAIDERS))
                return;
            e.setCancelled(true);
        }
    }
}