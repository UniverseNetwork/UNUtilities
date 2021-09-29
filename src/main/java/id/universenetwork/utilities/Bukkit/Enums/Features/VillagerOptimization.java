package id.universenetwork.utilities.Bukkit.Enums.Features;

public enum VillagerOptimization {
    // Villager Optimization Variable Settings
    ENABLED("Features.VillagerOptimization.enabled"),
    TPAS("Features.VillagerOptimization.ticks-per-allow-search");
    final String configPath;

    VillagerOptimization(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}
