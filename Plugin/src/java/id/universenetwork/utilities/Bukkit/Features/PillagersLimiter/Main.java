package id.universenetwork.utilities.Bukkit.Features.PillagersLimiter;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events;
import id.universenetwork.utilities.Bukkit.Templates.Feature;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pillager;
import org.bukkit.entity.Raider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.Collection;

public class Main extends Feature {
    @Override
    public void Load() {
        Events.registerListeners(new Cmd(cfgPath));
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void Limiter(CreatureSpawnEvent e) {
        ConfigurationSection s = cfgSection.getConfigurationSection("Limiter");
        if (cfgSection.getBoolean("enabled") && e.getEntityType().equals(EntityType.PILLAGER) &&
                s.getBoolean("Enabled")) {
            Collection<Entity> n = e.getLocation().getWorld().getNearbyEntities(e.getLocation(),
                    s.getInt("Radius-X"), s.getInt("Radius-Y"), s.getInt("Radius-Z"));
            int p = 0;
            for (Entity t : n) if (t.getType().equals(EntityType.PILLAGER)) ++p;
            if (p >= s.getInt("Stop-At-Amount")) {
                if (!e.getEntity().getEquipment().getHelmet().getType().equals(Material.AIR) &&
                        s.getBoolean("Ignore-Leaders")) return;
                if (e.getEntity().getCustomName() != null && s.getBoolean("Ignore-Named")) return;
                if (e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.RAID) &&
                        s.getBoolean("Ignore-Raiders")) return;
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void Patrols(CreatureSpawnEvent e) {
        ConfigurationSection s = cfgSection.getConfigurationSection("Patrol-Remover");
        if (cfgSection.getBoolean("enabled") &&
                e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.PATROL) && s.getBoolean("Enabled"))
            if (s.getBoolean("")) {
                Raider r = (Raider) e.getEntity();
                r.setPatrolLeader(false);
                r.setPatrolTarget(null);
            } else e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void Remover(ChunkLoadEvent e) {
        ConfigurationSection s = cfgSection.getConfigurationSection("Remover");
        if (cfgSection.getBoolean("enabled") && s.getBoolean("Enabled"))
            for (Entity y : e.getChunk().getEntities())
                if (y.getType().equals(EntityType.PILLAGER)) {
                    LivingEntity p = (LivingEntity) y;
                    if (!p.getEquipment().getHelmet().getType().equals(Material.AIR) &&
                            s.getBoolean("Ignore-Leaders")) return;
                    if (p.getCustomName() != null && s.getBoolean("Ignore-Named")) return;
                    y.remove();
                }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void Stopper(CreatureSpawnEvent e) {
        ConfigurationSection s = cfgSection.getConfigurationSection("Stopper");
        if (cfgSection.getBoolean("enabled") && e.getEntityType().equals(EntityType.PILLAGER) &&
                s.getBoolean("Enabled")) {
            if (s.getBoolean("Use-Hard-Limit")) {
                int p = 0;
                for (World w : Bukkit.getWorlds())
                    p += w.getEntitiesByClass(Pillager.class).size();
                if (p <= s.getInt("Hard-Limit-Amount")) return;
                e.setCancelled(true);
            }
            if (!e.getEntity().getEquipment().getHelmet().getType().equals(Material.AIR) &&
                    s.getBoolean("Ignore-Leaders")) return;
            if (e.getEntity().getCustomName() != null && s.getBoolean("Ignore-Named")) return;
            if (e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.RAID) &&
                    s.getBoolean("Ignore-Raiders")) return;
            e.setCancelled(true);
        }
    }
}