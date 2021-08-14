package id.universenetwork.utilities.Bukkit.Enums.Features;

public enum HatCommand {
    // Hat Command Variable Settings
    ENABLED("Features.HatCommand.enabled"),
    DISABLEDMSG("Features.HatCommand.disabledMessage"),
    MESSAGE("Features.HatCommand.message"),
    CONSOLE("Features.HatCommand.denyConsole");

    private final String configPath;

    HatCommand(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return this.configPath;
    }
}
