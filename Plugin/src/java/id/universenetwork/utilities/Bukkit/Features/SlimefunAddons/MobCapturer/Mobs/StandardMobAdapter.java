package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.MobCapturer.Mobs;

import id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.MobCapturer.MobAdapter;

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