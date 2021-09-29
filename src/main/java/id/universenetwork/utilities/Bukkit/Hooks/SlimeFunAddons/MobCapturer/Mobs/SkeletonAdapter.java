package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.MobCapturer.Mobs;

import org.bukkit.entity.Skeleton;

public class SkeletonAdapter<T extends Skeleton> extends AbstractHumanoidAdapter<T> {
    public SkeletonAdapter(Class<T> entityClass) {
        super(entityClass);
    }
}