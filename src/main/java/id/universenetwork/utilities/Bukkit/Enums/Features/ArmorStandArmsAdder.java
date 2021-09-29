package id.universenetwork.utilities.Bukkit.Enums.Features;

public enum ArmorStandArmsAdder {
    // Armor Stand Arms Adder Variable Settings
    ENABLED("Features.ArmorStandArmsAdder.enabled"),
    LOG("Features.ArmorStandArmsAdder.log");
    final String configPath;

    ArmorStandArmsAdder(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}
