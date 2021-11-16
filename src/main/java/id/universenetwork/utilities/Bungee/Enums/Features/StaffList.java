package id.universenetwork.utilities.Bungee.Enums.Features;

public enum StaffList {
    // Staff List Variable Settings
    ENABLED("Features.StaffList.enabled"),
    JM("Features.StaffList.join-msg"),
    LM("Features.StaffList.leave-msg"),
    DM("Features.StaffList.disabled-msg");
    final String configPath;

    StaffList(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}