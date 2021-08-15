package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Enums.Features.*;
import id.universenetwork.utilities.Bukkit.Enums.Settings;
import org.bukkit.configuration.file.FileConfiguration;

import java.lang.reflect.Array;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class Config {

    //Finds and Generates the config file
    public static void setup() {
        if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdir();
        get().options().copyDefaults(true);
        get().options().copyHeader(true);
        save();
    }

    public static FileConfiguration get() {
        return plugin.getConfig();
    }

    public static void save() {
        plugin.saveDefaultConfig();
    }

    public static void reload() {
        plugin.reloadConfig();
    }

    // Config Value Changer
    public static void setString(String key, String value) {
        get().set(key, value);
    }

    public static void setNumber(String key, Number value) {
        get().set(key, value);
    }

    public static void setBoolean(String key, Boolean value) {
        get().set(key, value);
    }

    public static void setArray(String key, Array value) {
        get().set(key, value);
    }


    // Settings Category
    public static String Settings(Settings s) {
        return Color.Translator(get().getString(s.getConfigPath()));
    }


    // Anti Redstone Features Category
    public static Boolean ARSettings(AntiRedstone s) {
        return get().getBoolean(s.getConfigPath());
    }

    public static String ARMessage(AntiRedstone s) {
        return Color.Translator(get().getString(s.getConfigPath()));
    }


    // Armor Stand Arms Adder Features Category
    public static Boolean ASAASettings(ArmorStandArmsAdder s) {
        return get().getBoolean(s.getConfigPath());
    }


    // Address Whitelister Features Category
    public static Boolean AWSettings(AddressWhitelister s) {
        return get().getBoolean(s.getConfigPath());
    }

    public static String AWMessage(AddressWhitelister s) {
        return Color.Translator(get().getString(s.getConfigPath()));
    }


    // Anti Zero Tick Farm Features Category
    public static Boolean AZTFSettings(AntiZeroTickFarm s) {
        return get().getBoolean(s.getConfigPath());
    }


    // Hat Command Features Category
    public static Boolean HCSettings(HatCommand s) {
        return get().getBoolean(s.getConfigPath());
    }

    public static String HCMessage(HatCommand s) {
        return Color.Translator(get().getString(s.getConfigPath()));
    }


    // Max Player Changer Command Features Category
    public static Boolean MPCCSettings(MaxPlayerChangerCommand s) {
        return get().getBoolean(s.getConfigPath());
    }

    public static String MPCCMessage(MaxPlayerChangerCommand s) {
        return Color.Translator(get().getString(s.getConfigPath()));
    }
}