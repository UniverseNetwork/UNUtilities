package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer.Mobs;

import org.bukkit.entity.AbstractHorse;

public class UndeadHorseAdapter<T extends AbstractHorse> extends AbstractHorseAdapter<T> {
    public UndeadHorseAdapter(Class<T> entityClass) {
        super(entityClass);
    }
}