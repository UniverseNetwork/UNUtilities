package id.universenetwork.utilities.Manager;

import id.universenetwork.utilities.Enums.Features.AntiRedstone;
import id.universenetwork.utilities.Enums.Features.ArmorStandArmsAdder;
import id.universenetwork.utilities.Enums.Settings;
import org.bukkit.configuration.file.FileConfiguration;

import java.lang.reflect.Array;

import static id.universenetwork.utilities.UNUtilities.plugin;

public class Config {

    //Finds and Generates the config file
    public static void setup(){
        if(!plugin().getDataFolder().exists()) plugin().getDataFolder().mkdir();
        get().options().copyDefaults(true);
        get().options().copyHeader(true); save();
    }

    public static FileConfiguration get(){
        return plugin().getConfig();
    }
    public static void save(){
        plugin().saveDefaultConfig();
    }
    public static void reload(){
        plugin().reloadConfig();
    }

 // Config Value Changer
    public static void setString(String key, String value){
        get().set(key, value);
    }
    public static void setNumber(String key, Number value){
        get().set(key, value);
    }
    public static void setBoolean(String key, Boolean value){
        get().set(key, value);
    }
    public static void setArray(String key, Array value){
        get().set(key, value);
    }

 // Settings Category
    public static String Settings(Settings s){
        return Color.Translator(get().getString(s.getConfigPath()));
    }

 // Anti Redstone Features Category
    public static Boolean AntiRedstoneSettings(AntiRedstone s){
        return get().getBoolean(s.getConfigPath());
    }
    public static String AntiRedstoneMessage(AntiRedstone s){
        return Color.Translator(get().getString(s.getConfigPath()));
    }

 // Armor Stand Arms Adder Features Category
    public static Boolean ArmorStandArmsAdderSettings(ArmorStandArmsAdder s){
        return get().getBoolean(s.getConfigPath());
    }
}