package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkBee;

import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkBee.Config.Config;

public class SkBee {
    Config config;
    static SkBee instance;

    public SkBee() {
        config = new Config();
        instance = this;
    }

    /**
     * Get an instance of this addon
     *
     * @return Instance of this addon
     */
    public static SkBee getInstance() {
        return instance;
    }

    /**
     * Get an instance of this addon's {@link Config}
     *
     * @return Instance of this addon's config
     */
    public Config getPluginConfig() {
        return config;
    }
}
