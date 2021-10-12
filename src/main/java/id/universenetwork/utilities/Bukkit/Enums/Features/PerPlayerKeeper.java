package id.universenetwork.utilities.Bukkit.Enums.Features;

public enum PerPlayerKeeper {
    // Hat Command Variable Settings
    ENABLED("Features.Per-PlayerKeeper.enabled"),
    DISABLEDMSG("Features.Per-PlayerKeeper.disabledMessage"),
    XPMESSAGE("Features.Per-PlayerKeeper.xpMessage"),
    INVMESSAGE("Features.Per-PlayerKeeper.invMessage");
    final String configPath;

    PerPlayerKeeper(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}
