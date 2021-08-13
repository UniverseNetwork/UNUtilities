package id.universenetwork.utilities.Enums;

public enum Settings {
    // Plugin Settings Variable
    PREFIX("Settings.prefix"),
    NOPERMISSION("Settings.denyMessage"),
    RELOAD("Settings.reloadMessage");

    private final String configPath;

    Settings(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return this.configPath;
    }
}
