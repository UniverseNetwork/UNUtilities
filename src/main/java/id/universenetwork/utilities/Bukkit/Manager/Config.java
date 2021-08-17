package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Enums.Features.*;
import id.universenetwork.utilities.Bukkit.Enums.Settings;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.primesoft.asyncworldedit.api.IAsyncWorldEdit;

import java.lang.reflect.Array;

import static id.universenetwork.utilities.Bukkit.UNUtilities.*;

public class Config {
    //Finds and Generates the config file
    public static void setup() {
        plugin.getLogger().info("§ePreparing Config Manager...");
        if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdir();
        get().options().copyDefaults(true);
        get().options().copyHeader(true);
        save();
        prefix = Config.Settings(Settings.PREFIX);
        System.out.println(prefix + " §aConfig Manager have been prepared");
        Proxy.setup();
    }

    public static FileConfiguration get() {
        return plugin.getConfig();
    }

    public static void save() {
        plugin.saveDefaultConfig();

    }

    public static void reload() {
        plugin.reloadConfig();
        Proxy.reload();
        if (AWEBDSettings(AsyncWorldEditBossBarDisplay.ENABLED) && !aweHook) {
            System.out.println(prefix + " §6AsyncWorldEdit BossBar Display Features is enabled on config.yml. Searching AsyncWorldEdit...");
            if (Bukkit.getPluginManager().getPlugin("AsyncWorldEdit").isEnabled()) {
                System.out.println(prefix + " §6Found AsyncWorldEdit. Hooking...");
                IAsyncWorldEdit awe = (IAsyncWorldEdit) Bukkit.getPluginManager().getPlugin("AsyncWorldEdit");
                awe.getProgressDisplayManager().registerProgressDisplay(plugin);
                aweHook = true;
                System.out.println(prefix + " §aSuccessfully hooked with AsyncWorldEdit");
            } else {
                System.out.println(prefix + " §cAsyncWorldEdit not found. You need AsyncWorldEdit to use AsyncWorldEdit BossBar Display Features");
                setBoolean("Features.AsyncWorldEditBossBarDisplay.enabled", false);
            }
        }

        if (!AWEBDSettings(AsyncWorldEditBossBarDisplay.ENABLED) && aweHook) {
            System.out.println(prefix + " §cAsyncWorldEdit BossBar Display Features is disabled on config.yml. Unhooking with AsyncWorldEdit...");
            IAsyncWorldEdit awe = (IAsyncWorldEdit) Bukkit.getPluginManager().getPlugin("AsyncWorldEdit");
            awe.getProgressDisplayManager().unregisterProgressDisplay(plugin);
            aweHook = false;
            System.out.println(prefix + " §cSuccessfully unhooked with AsyncWorldEdit");
        }
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


    // AsyncWorldEdit BossBar Display Features Category
    public static Boolean AWEBDSettings(AsyncWorldEditBossBarDisplay s) {
        return get().getBoolean(s.getConfigPath());
    }

    public static String AWEBDMessage(AsyncWorldEditBossBarDisplay s) {
        return Color.Translator(get().getString(s.getConfigPath()));
    }
}