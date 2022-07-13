package id.universenetwork.utilities.bukkit.features.SlimefunAddons.MobCapturer.Mobs;

import id.universenetwork.utilities.bukkit.features.SlimefunAddons.MobCapturer.MobAdapter;

public class StandardMobAdapter<T extends org.bukkit.entity.Mob> implements MobAdapter<T> {
    final Class<T> entityClass;

    public StandardMobAdapter(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }
}