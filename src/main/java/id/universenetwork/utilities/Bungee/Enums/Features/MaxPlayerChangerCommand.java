package id.universenetwork.utilities.Bungee.Enums.Features;

public enum MaxPlayerChangerCommand {
    // Max Player Changer Command Variable Settings
    ENABLED("Features.MaxPlayerChangerCommand.enabled"),
    SOR("Features.MaxPlayerChangerCommand.SaveOnRestart"),
    USP("Features.MaxPlayerChangerCommand.UpdateServerPing"),
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
