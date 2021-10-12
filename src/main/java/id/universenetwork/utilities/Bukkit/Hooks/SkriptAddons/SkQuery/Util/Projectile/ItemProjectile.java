package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Projectile;

import ch.njol.skript.aliases.ItemType;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.CancellableBukkitTask;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static org.bukkit.Bukkit.getPluginManager;
import static org.bukkit.Bukkit.getScheduler;

public class ItemProjectile {
    final ItemType projectile;

    public ItemProjectile(ItemType projectile) {
        this.projectile = projectile;
    }

    public ItemType getProjectile() {
        return projectile;
    }

    public ItemProjectile shoot(LivingEntity shooter, Vector velocity) {
        Item item = shooter.getWorld().dropItem(shooter.getEyeLocation(), projectile.getRandom());
        item.setVelocity(velocity);
        item.setPickupDelay(Integer.MAX_VALUE);
        CancellableBukkitTask task = new CancellableBukkitTask() {
            @Override
            public void run() {
                if (!item.isValid() || (item.getNearbyEntities(0, 0, 0).size() != 0 && !item.getNearbyEntities(0, 0, 0).contains(shooter)) || item.isOnGround()) {
                    getPluginManager().callEvent(new ItemProjectileHitEvent(item, shooter));
                    item.remove();
                    cancel();
                }
            }
        };
        task.setTaskId(getScheduler().scheduleSyncRepeatingTask(plugin, task, 0, 1));
        return this;
    }
}