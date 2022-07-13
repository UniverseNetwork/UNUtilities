package id.universenetwork.utilities.bukkit.Enums;

public enum VillagerOptimization {
    // Villager Optimization Variable Settings
    ENABLED("Features.VillagerOptimization.enabled"),
    TPAS("Features.VillagerOptimization.ticks-per-allow-search"),
    VCPP("Features.VillagerOptimization.vanilla-chunks-per-player"),
    DISABLEDMSG("Features.VillagerOptimization.disabledMessage");
    final String configPath;

    VillagerOptimization(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}
