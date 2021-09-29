package id.universenetwork.utilities.Bukkit.Enums.Features;

public enum FlyFixer {
    // Fly Fixer Variable Settings
    ENABLED("Features.FlyFixer.enabled");

    private final String configPath;

    FlyFixer(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}