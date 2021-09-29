package id.universenetwork.utilities.Bukkit.Enums.Features;

public enum SlimeFunAddons {
    // SlimeFun Addons Variable Settings
    ENABLED("Features.SlimeFunAddons.enabled"),
    ADDONS("Features.SlimeFunAddons.Addons."),
    ADDONSSETTINGS("Features.SlimeFunAddons.Settings.");
    final String configPath;

    SlimeFunAddons(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}
