package id.universenetwork.utilities.Bukkit.Enums;

public enum MaxPlayerChangerCommand {
    // Max Player Changer Command Variable Settings
    ENABLED("Features.MaxPlayerChangerCommand.enabled"),
    SOR("Features.MaxPlayerChangerCommand.SaveOnRestart"),
    DISABLEDMSG("Features.MaxPlayerChangerCommand.disabledMessage"),
    SUCCESSMSG("Features.MaxPlayerChangerCommand.successMessage"),
    NOARGMSG("Features.MaxPlayerChangerCommand.noArgumentMessage"),
    NONUMMSG("Features.MaxPlayerChangerCommand.invalidNumberMessage"),
    ERRMSG("Features.MaxPlayerChangerCommand.errorMessage");
    final String configPath;

    MaxPlayerChangerCommand(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}
