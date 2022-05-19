package id.universenetwork.utilities.Bukkit.Enums;

public enum ViaLegacy {
    // ViaLegacy Variable Settings
    ENABLED("Features.ViaLegacy.enabled"),
    BREWING_STAND_GUI_FIX("Features.ViaLegacy.brewing-stand-gui-fix"),
    ENCHANTING_GUI_FIX("Features.ViaLegacy.enchanting-gui-fix"),
    LILY_PAD_FIX("Features.ViaLegacy.lily-pad-fix"),
    LADDER_FIX("Features.ViaLegacy.ladder-fix"),
    SOUND_FIX("Features.ViaLegacy.sound-fix"),
    SLIME_FIX("Features.ViaLegacy.slime-fix"),
    ELYTRA_FIX("Features.ViaLegacy.elytra-fix"),
    POTION_FIX("Features.ViaLegacy.potion-fix"),
    AREA_EFFECT_CLOUD_PARTICLES("Features.ViaLegacy.area-effect-cloud-particles"),
    VERSIONINFO_ACTIVE("Features.ViaLegacy.versioninfo.active"),
    VERSIONINFO_MAX_VERSION("Features.ViaLegacy.versioninfo.max-version"),
    VERSIONINFO_INTERVAL("Features.ViaLegacy.versioninfo.interval"),
    VERSIONINFO_MSG("Features.ViaLegacy.versioninfo.message");
    final String configPath;

    ViaLegacy(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}