package id.universenetwork.utilities.Bukkit.Enums;

public enum AsyncWorldEditBossBarDisplay {
    // AsyncWorldEdit BossBar Display Variable Settings
    ENABLED("Features.AsyncWorldEditBossBarDisplay.enabled"),
    TITLE("Features.AsyncWorldEditBossBarDisplay.titleFormat"),
    COLOR("Features.AsyncWorldEditBossBarDisplay.barColor");
    final String configPath;

    AsyncWorldEditBossBarDisplay(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}
