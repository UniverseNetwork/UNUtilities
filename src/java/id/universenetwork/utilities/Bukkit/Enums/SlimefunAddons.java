package id.universenetwork.utilities.Bukkit.Enums;

public enum SlimefunAddons {
    // SlimeFun Addons Variable Settings
    ENABLED("Features.SlimefunAddons.enabled"),
    ADDONS("Features.SlimefunAddons.Addons");
    final String configPath;

    SlimefunAddons(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}
