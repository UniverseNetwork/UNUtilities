package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Enums.Features.*;
import id.universenetwork.utilities.Bukkit.Enums.Settings;
import id.universenetwork.utilities.Bukkit.Events.UNUtilitiesReloadConfigEvent;
import id.universenetwork.utilities.Bukkit.Handlers.BookExploitHandler;
import org.bukkit.configuration.file.FileConfiguration;

import static id.universenetwork.utilities.Bukkit.Manager.Color.Translator;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getPluginManager;

public class Config {
    // Finds and Generates the config file
    public static void setup() {
        plugin.getLogger().info("§ePreparing Config Manager...");
        if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdir();
        get().options().copyDefaults(true);
        get().options().copyHeader(true);
        save();
        prefix = Config.Settings(Settings.PREFIX);
        getLogger().info(prefix + " §aConfig Manager have been prepared");
        Proxy.setup();
        Data.setup();
        new BookExploitHandler();
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
        Hooks.AsyncWorldEditBossBarDisplay("reloading");
        getPluginManager().callEvent(new UNUtilitiesReloadConfigEvent());
        BookExploitHandler.setup();
    }

    // Config Value Changer
    public static void set(String key, Object value) {
        get().set(key, value);
    }


    // Settings Category
    public static String Settings(Settings s) {
        return Translator(get().getString(s.getConfigPath()));
    }


    // Anti Redstone Features Category
    public static boolean ARSettings(AntiRedstone s) {
        return get().getBoolean(s.getConfigPath());
    }

    public static String ARMessage(AntiRedstone s) {
        return Translator(get().getString(s.getConfigPath()));
    }


    // Armor Stand Arms Adder Features Category
    public static boolean ASAASettings(ArmorStandArmsAdder s) {
        return get().getBoolean(s.getConfigPath());
    }


    // Address Whitelister Features Category
    public static boolean AWSettings(AddressWhitelister s) {
        return get().getBoolean(s.getConfigPath());
    }

    public static String AWMessage(AddressWhitelister s) {
        return Translator(get().getString(s.getConfigPath()));
    }


    // Anti Zero Tick Farm Features Category
    public static boolean AZTFSettings(AntiZeroTickFarm s) {
        return get().getBoolean(s.getConfigPath());
    }


    // Hat Command Features Category
    public static boolean HCSettings(HatCommand s) {
        return get().getBoolean(s.getConfigPath());
    }

    public static String HCMessage(HatCommand s) {
        return Translator(get().getString(s.getConfigPath()));
    }


    // Max Player Changer Command Features Category
    public static boolean MPCCSettings(MaxPlayerChangerCommand s) {
        return get().getBoolean(s.getConfigPath());
    }

    public static String MPCCMessage(MaxPlayerChangerCommand s) {
        return Translator(get().getString(s.getConfigPath()));
    }


    // AsyncWorldEdit BossBar Display Features Category
    public static boolean AWEBDSettings(AsyncWorldEditBossBarDisplay s) {
        return get().getBoolean(s.getConfigPath());
    }

    public static String AWEBDMessage(AsyncWorldEditBossBarDisplay s) {
        return Translator(get().getString(s.getConfigPath()));
    }


    // ShopGUI+ SilkSpawners Connector Features Category
    public static boolean SGPSSCSettings(ShopGUIPlusSilkSpawnersConnector s) {
        return get().getBoolean(s.getConfigPath());
    }


    // SlimeFun Addons Features Category
    public static boolean SFASettings() {
        return get().getBoolean(SlimeFunAddons.ENABLED.getConfigPath());
    }


    // Fly Fixer Features Category
    public static boolean FFSettings(FlyFixer s) {
        return get().getBoolean(s.getConfigPath());
    }


    // Villager Optimization Features Category
    public static boolean VOEnabled() {
        return get().getBoolean(VillagerOptimization.ENABLED.getConfigPath());
    }

    public static long VOTPAS() {
        return get().getLong(VillagerOptimization.TPAS.getConfigPath(), 600L /* Default value, if config does not contain the entry */);
    }


    // SlimeFun Addons Features Category
    public static boolean SASettings() {
        return get().getBoolean(SkriptAddons.ENABLED.getConfigPath());
    }


    // Anti Book Exploit Features Category
    public static boolean ABEEnabled() {
        return get().getBoolean(AntiBookExploit.ENABLED.getConfigPath());
    }

    public static String ABEMessage(AntiBookExploit s) {
        return Translator(get().getString(s.getConfigPath()));
    }
}