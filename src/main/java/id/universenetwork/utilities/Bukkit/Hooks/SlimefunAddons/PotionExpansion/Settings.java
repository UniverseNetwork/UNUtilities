package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion;

import id.universenetwork.utilities.Bukkit.Manager.Config;

import static id.universenetwork.utilities.Bukkit.Enums.Features.SlimeFunAddons.ADDONSSETTINGS;

public class Settings {
    static int potionDuration;
    static int searchRadius;

    public static void load() {
        potionDuration = Config.get().getInt(ADDONSSETTINGS.getConfigPath() + "PotionExpansion.Potions-Sight.Duration", 120);
        searchRadius = Config.get().getInt(ADDONSSETTINGS.getConfigPath() + "PotionExpansion.Potions-Sight.Radius", 10);
    }

    public static int getPotionDuration() {
        return potionDuration;
    }

    public static int getSearchRadius() {
        return searchRadius;
    }
}
