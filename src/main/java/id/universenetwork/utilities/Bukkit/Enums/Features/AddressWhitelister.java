package id.universenetwork.utilities.Bukkit.Enums.Features;

public enum AddressWhitelister {
    // Address Whielister Variable Settings
    ENABLED("Features.AddressWhitelister.enabled"),
    ADDRESS("Features.AddressWhitelister.address"),
    KICKMSG("Features.AddressWhitelister.kickMessage");
    final String configPath;

    AddressWhitelister(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}
