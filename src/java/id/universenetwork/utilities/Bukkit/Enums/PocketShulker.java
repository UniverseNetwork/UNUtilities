package id.universenetwork.utilities.Bukkit.Enums;

public enum PocketShulker {
    // Pocket Shulker Variable Settings
    ENABLED("Features.PocketShulker.enabled"),
    BLACKLISTINV("Features.PocketShulker.blacklistedinventories"),
    AIROPEN("Features.PocketShulker.canopeninair"),
    INVOPEN("Features.PocketShulker.canopenininventory"),
    CHESTOPEN("Features.PocketShulker.canopeninchests"),
    ECOPEN("Features.PocketShulker.canopeninenderchest"),
    BARRELOPEN("Features.PocketShulker.canopeninbarrels"),
    SHULKERPLACE("Features.PocketShulker.canplaceshulker"),
    DISABLECOMBAT("Features.PocketShulker.disable-in-combat"),
    DISABLECOMBATMSG("Features.PocketShulker.disable-in-combat-message"),
    DEFNAME("Features.PocketShulker.defaultname"),
    SHIFTOPEN("Features.PocketShulker.shiftclicktoopen"),
    SHULKERVOL("Features.PocketShulker.shulkervolume");
    final String configPath;

    PocketShulker(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}