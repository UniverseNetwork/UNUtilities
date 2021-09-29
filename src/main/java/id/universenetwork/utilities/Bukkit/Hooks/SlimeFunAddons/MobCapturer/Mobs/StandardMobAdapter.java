package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.MobCapturer.Mobs;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.MobCapturer.MobAdapter;
import org.bukkit.entity.Mob;

public class StandardMobAdapter<T extends Mob> implements MobAdapter<T> {
    final Class<T> entityClass;

    public StandardMobAdapter(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }
}