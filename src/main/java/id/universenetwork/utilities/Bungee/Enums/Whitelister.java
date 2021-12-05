package id.universenetwork.utilities.Bungee.Enums;

public enum Whitelister {
    // Staff List Variable Settings
    ENABLED("Features.Whitelister.enabled"),
    EM("Features.Whitelister.enabled-msg"),
    AEM("Features.Whitelister.already-enabled-msg"),
    DM("Features.Whitelister.disabled-msg"),
    ADM("Features.Whitelister.already-disabled-msg"),
    AM("Features.Whitelister.add-msg"),
    AAM("Features.Whitelister.already-add-msg"),
    RM("Features.Whitelister.remove-msg"),
    NWM("Features.Whitelister.not-whitelisted-msg"),
    LM("Features.Whitelister.list-msg"),
    LNWM("Features.Whitelister.list-no-whitelisted-message"),
    KM("Features.Whitelister.kick-msg"),
    DFM("Features.Whitelister.disabled-feature-msg"),
    UM("Features.Whitelister.usage-msg");
    final String configPath;

    Whitelister(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}