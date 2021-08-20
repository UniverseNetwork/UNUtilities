package id.universenetwork.utilities.Bukkit.Enums.Features;

public enum HatCommand {
    // Hat Command Variable Settings
    ENABLED("Features.HatCommand.enabled"),
    DISABLEDMSG("Features.HatCommand.disabledMessage"),
    MESSAGE("Features.HatCommand.message");

    private final String configPath;

    HatCommand(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return this.configPath;
    }
}
