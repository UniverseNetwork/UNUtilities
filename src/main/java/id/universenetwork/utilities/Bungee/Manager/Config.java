package id.universenetwork.utilities.Bungee.Manager;

import id.universenetwork.utilities.Bungee.Enums.MaxPlayerChangerCommand;
import id.universenetwork.utilities.Bungee.Enums.StaffList;
import id.universenetwork.utilities.Universal.Enums.Settings;

import java.util.List;

import static id.universenetwork.utilities.Bungee.UNUtilities.settings;
import static id.universenetwork.utilities.Bungee.Utils.Color.Translator;

public class Config {
    // Settings Category
    public static String Settings(Settings s) {
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

    // Discord Features Category
    public static String DString(id.universenetwork.utilities.Bungee.Enums.Features.Discord s) {
        return Translator(settings.getString(s.getConfigPath()));
    }

    public static List<String> DList(id.universenetwork.utilities.Bungee.Enums.Features.Discord s) {
        return settings.getStringList(s.getConfigPath());
    }

    public static long DLong(id.universenetwork.utilities.Bungee.Enums.Features.Discord s) {
        return settings.getLong(s.getConfigPath());
    }
}