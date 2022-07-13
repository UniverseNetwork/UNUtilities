package id.universenetwork.utilities.bukkit.Enums;

public enum EntityTrackerFixer {
    // Entity Tracker Fixer Variable Settings
    ENABLED("Features.EntityTrackerFixer.enabled"),
    LOG("Features.EntityTrackerFixer.log-to-console"),
    DISABLETICKUNTRACKED("Features.EntityTrackerFixer.disable-tick-for-untracked-entities"),
    ENABLEALLWORLDS("Features.EntityTrackerFixer.enable-on-all-worlds"),
    UNTRACKTICKS("Features.EntityTrackerFixer.untrack-ticks"),
    TPSLIMIT("Features.EntityTrackerFixer.tps-limit"),
    CHECKFREQUENCY("Features.EntityTrackerFixer.check-untracked-entities-frequency"),
    TRACKRANGE("Features.EntityTrackerFixer.tracking-range"),
    WORLDS("Features.EntityTrackerFixer.worlds");
    final String configPath;

    EntityTrackerFixer(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}