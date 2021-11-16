package id.universenetwork.utilities.Bungee.Manager;

import id.universenetwork.utilities.Bungee.Enums.Features.MaxPlayerChangerCommand;
import id.universenetwork.utilities.Bungee.Enums.Features.StaffList;

import static id.universenetwork.utilities.Bungee.UNUtilities.settings;
import static id.universenetwork.utilities.Bungee.Utils.Color.Translator;

public class Config {
    // Settings Category
    public static String Settings(id.universenetwork.utilities.Bungee.Enums.Settings s) {
        return Translator(settings.getString(s.getConfigPath()));
    }


    // Max Player Changer Command Features Category
    public static boolean MPCCBoolean(MaxPlayerChangerCommand s) {
        return settings.getBoolean(s.getConfigPath());
    }

    public static String MPCCString(MaxPlayerChangerCommand s) {
        return Translator(settings.getString(s.getConfigPath()));
    }


    // Staff List Features Category
    public static boolean SLBoolean(StaffList s) {
        return settings.getBoolean(s.getConfigPath());
    }

    public static String SLString(StaffList s) {
        return Translator(settings.getString(s.getConfigPath()));
    }
}