package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Projectile;

import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ItemProjectileHitEvent extends Event {
    static final HandlerList handlers = new HandlerList();
    final LivingEntity shooter;
    final Item projectile;

    public ItemProjectileHitEvent(Item projectile, LivingEntity shooter) {
        this.projectile = projectile;
        this.shooter = shooter;
    }

    public Item getProjectile() {
        return projectile;
    }

    public LivingEntity getShooter() {
        return shooter;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}