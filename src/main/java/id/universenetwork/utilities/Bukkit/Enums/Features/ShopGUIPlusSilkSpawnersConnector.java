package id.universenetwork.utilities.Bukkit.Enums.Features;

public enum ShopGUIPlusSilkSpawnersConnector {
    // ShopGUI+ SilkSpawners Connector Variable Settings
    ENABLED("Features.ShopGUI+SilkSpawnersConnector.enabled");
    final String configPath;

    ShopGUIPlusSilkSpawnersConnector(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}