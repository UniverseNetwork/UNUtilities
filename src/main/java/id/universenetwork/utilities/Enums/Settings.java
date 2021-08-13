package id.universenetwork.utilities.Enums;

public enum Settings {
    PREFIX("Settings.prefix"),
    NOPERMISSION("Settings.denyMessage"),
    RELOAD("Settings.reloadMessage");

    private String configPath;

    Settings(String configPath){
        this.configPath = configPath;
    }

    public String getConfigPath(){
        return this.configPath;
    }
}
