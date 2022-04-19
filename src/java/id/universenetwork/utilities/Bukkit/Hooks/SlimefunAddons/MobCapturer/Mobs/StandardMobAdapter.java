package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer.Mobs;

public class StandardMobAdapter<T extends org.bukkit.entity.Mob> implements id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer.MobAdapter<T> {
    final Class<T> entityClass;

    public StandardMobAdapter(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }
}