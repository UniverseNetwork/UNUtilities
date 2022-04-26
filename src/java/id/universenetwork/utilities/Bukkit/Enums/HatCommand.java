package id.universenetwork.utilities.Bukkit.Enums;

public enum HatCommand {
    // Hat Command Variable Settings
    ENABLED("Features.HatCommand.enabled"),
    DISABLEDMSG("Features.HatCommand.disabledMessage"),
    MESSAGE("Features.HatCommand.message"),
    EMPTY("Features.HatCommand.empty");
    final String configPath;

    HatCommand(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}
