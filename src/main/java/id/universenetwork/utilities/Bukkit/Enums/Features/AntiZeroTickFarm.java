package id.universenetwork.utilities.Bukkit.Enums.Features;

public enum AntiZeroTickFarm {
    // Anti Zero Tick Farm Variable Settings
    ENABLED("Features.AntiZeroTickFarm.enabled");
    final String configPath;

    AntiZeroTickFarm(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}
