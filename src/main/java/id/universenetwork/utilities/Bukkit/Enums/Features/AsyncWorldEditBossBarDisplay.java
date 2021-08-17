package id.universenetwork.utilities.Bukkit.Enums.Features;

public enum AsyncWorldEditBossBarDisplay {
    // AsyncWorldEdit BossBar Display Variable Settings
    ENABLED("Features.AsyncWorldEditBossBarDisplay.enabled"),
    TITLE("Features.AsyncWorldEditBossBarDisplay.titleFormat"),
    COLOR("Features.AsyncWorldEditBossBarDisplay.barColor");

    private final String configPath;

    AsyncWorldEditBossBarDisplay(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return this.configPath;
    }
}
