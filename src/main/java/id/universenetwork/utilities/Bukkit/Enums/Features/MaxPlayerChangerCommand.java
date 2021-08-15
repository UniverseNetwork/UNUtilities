package id.universenetwork.utilities.Bukkit.Enums.Features;

public enum MaxPlayerChangerCommand {
    // Max Player Changer Command Variable Settings
    ENABLED("Features.MaxPlayerChangerCommand.enabled"),
    SOR("Features.MaxPlayerChangerCommand.SaveOnRestart"),
    DISABLEDMSG("Features.MaxPlayerChangerCommand.disabledMessage"),
    SUCCESSMSG("Features.MaxPlayerChangerCommand.successMessage"),
    NOARGMSG("Features.MaxPlayerChangerCommand.noArgumentMessage"),
    NONUMMSG("Features.MaxPlayerChangerCommand.invalidNumberMessage"),
    ERRMSG("Features.MaxPlayerChangerCommand.errorMessage");

    private final String configPath;

    MaxPlayerChangerCommand(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return this.configPath;
    }
}
