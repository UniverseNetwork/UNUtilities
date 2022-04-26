package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.MobCapturer.Mobs;

public class SkeletonAdapter<T extends org.bukkit.entity.AbstractSkeleton> extends AbstractHumanoidAdapter<T> {
    public SkeletonAdapter(Class<T> entityClass) {
        super(entityClass);
    }
}