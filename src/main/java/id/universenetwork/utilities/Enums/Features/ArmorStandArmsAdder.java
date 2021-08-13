package id.universenetwork.utilities.Enums.Features;

public enum ArmorStandArmsAdder {
    ENABLED("Features.ArmorStandArmsAdder.enabled"),
    LOG("Features.ArmorStandArmsAdder.log");

    private String configPath;

    ArmorStandArmsAdder(String configPath){
        this.configPath = configPath;
    }

    public String getConfigPath(){
        return this.configPath;
    }
}
