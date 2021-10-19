package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer.Mobs;

import org.bukkit.entity.Stray;

public class StrayAdapter<T extends Stray> extends AbstractHumanoidAdapter<T> {
    public StrayAdapter(Class<T> entityClass) {
        super(entityClass);
    }
}