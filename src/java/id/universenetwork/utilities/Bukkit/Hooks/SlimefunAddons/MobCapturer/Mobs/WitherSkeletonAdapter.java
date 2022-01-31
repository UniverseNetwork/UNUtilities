package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer.Mobs;

import org.bukkit.entity.WitherSkeleton;

public class WitherSkeletonAdapter<T extends WitherSkeleton> extends AbstractHumanoidAdapter<T> {
    public WitherSkeletonAdapter(Class<T> entityClass) {
        super(entityClass);
    }
}