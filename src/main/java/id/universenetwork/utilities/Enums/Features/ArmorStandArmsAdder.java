package id.universenetwork.utilities.Enums.Features;

public enum ArmorStandArmsAdder {
    // Armor Stand Arms Adder Variable Settings
    ENABLED("Features.ArmorStandArmsAdder.enabled"),
    LOG("Features.ArmorStandArmsAdder.log");

    private final String configPath;

    ArmorStandArmsAdder(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return this.configPath;
    }
}
