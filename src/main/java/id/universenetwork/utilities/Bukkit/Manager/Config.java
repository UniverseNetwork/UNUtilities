package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Enums.Features.*;
import id.universenetwork.utilities.Bukkit.Enums.Settings;
import id.universenetwork.utilities.Bukkit.Events.UNUtilitiesReloadConfigEvent;
import id.universenetwork.utilities.Bukkit.Handlers.BookExploitHandler;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Core.ConfigBuilder;
import id.universenetwork.utilities.Bukkit.NMS.ETF;

import java.util.List;

import static id.universenetwork.utilities.Bukkit.Manager.Color.Translator;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getPluginManager;

public class Config {
    static ConfigBuilder config;

    // Finds and Generates the config file
    public static void setup() {
        plugin.getLogger().info("§ePreparing Config Manager...");
        try {
            config = new ConfigBuilder("config.yml");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        prefix = Config.Settings(Settings.PREFIX);
        getLogger().info(prefix + " §aConfig Manager have been prepared");
        Data.setup();
        Proxy.setup();
        new BookExploitHandler();
        ETF.setup();
    }

    public static ConfigBuilder get() {
        return config;
    }

    public static void save() {
        get().save();
    }

    public static void reload() {
        config.reload();
        Data.reload();
        Proxy.reload();
        Hooks.AsyncWorldEditBossBarDisplay("reloading");
        getPluginManager().callEvent(new UNUtilitiesReloadConfigEvent());
        BookExploitHandler.setup();
        ETF.reload();
    }

    // Config Value Changer
    public static void set(String path, Object value) {
        get().set(path, value);
    }


    // Settings Category
    public static String Settings(Settings s) {
        return Translator(get().getString(s.getConfigPath()));
    }


    // Anti Redstone Features Category
    public static boolean ARBoolean(AntiRedstone s) {
        return get().getBoolean(s.getConfigPath());
    }

    public static String ARMessage(AntiRedstone s) {
        return Translator(get().getString(s.getConfigPath()));
    }


    // Armor Stand Arms Adder Features Category
    public static boolean ASAABoolean(ArmorStandArmsAdder s) {
        return get().getBoolean(s.getConfigPath());
    }


    // Address Whitelister Features Category
    public static boolean AWBoolean(AddressWhitelister s) {
        return get().getBoolean(s.getConfigPath());
    }

    public static String AWMessage(AddressWhitelister s) {
        return Translator(get().getString(s.getConfigPath()));
    }


    // Anti Zero Tick Farm Features Category
    public static boolean AZTFBoolean(AntiZeroTickFarm s) {
        return get().getBoolean(s.getConfigPath());
    }


    // Hat Command Features Category
    public static boolean HCBoolean(HatCommand s) {
        return get().getBoolean(s.getConfigPath());
    }

    public static String HCMessage(HatCommand s) {
        return Translator(get().getString(s.getConfigPath()));
    }


    // Max Player Changer Command Features Category
    public static boolean MPCCBoolean(MaxPlayerChangerCommand s) {
        return get().getBoolean(s.getConfigPath());
    }

    public static String MPCCMessage(MaxPlayerChangerCommand s) {
        return Translator(get().getString(s.getConfigPath()));
    }


    // AsyncWorldEdit BossBar Display Features Category
    public static boolean AWEBDBoolean(AsyncWorldEditBossBarDisplay s) {
        return get().getBoolean(s.getConfigPath());
    }

    public static String AWEBDMessage(AsyncWorldEditBossBarDisplay s) {
        return Translator(get().getString(s.getConfigPath()));
    }


    // ShopGUI+ SilkSpawners Connector Features Category
    public static boolean SGPSSCBoolean(ShopGUIPlusSilkSpawnersConnector s) {
        return get().getBoolean(s.getConfigPath());
    }


    // SlimeFun Addons Features Category
    public static boolean SFABoolean() {
        return get().getBoolean(SlimeFunAddons.ENABLED.getConfigPath());
    }


    // Villager Optimization Features Category
    public static boolean VOEnabled() {
        return get().getBoolean(VillagerOptimization.ENABLED.getConfigPath());
    }

    public static long VOTPAS() {
        return get().getLong(VillagerOptimization.TPAS.getConfigPath(), 600L /* Default value, if config does not contain the entry */);
    }


    // SlimeFun Addons Features Category
    public static boolean SABoolean() {
        return get().getBoolean(SkriptAddons.ENABLED.getConfigPath());
    }


    // Anti Book Exploit Features Category
    public static boolean ABEEnabled() {
        return get().getBoolean(AntiBookExploit.ENABLED.getConfigPath());
    }

    public static String ABEMessage(AntiBookExploit s) {
        return Translator(get().getString(s.getConfigPath()));
    }


    // Per-Player Keeper Features Category
    public static boolean PPKBoolean() {
        return get().getBoolean(PerPlayerKeeper.ENABLED.getConfigPath());
    }

    public static String PPKMessage(PerPlayerKeeper s) {
        return get().getString(s.getConfigPath());
    }


    // Fly Fixer Features Category
    public static boolean FFEnabled() {
        return get().getBoolean(FlyFixer.ENABLED.getConfigPath());
    }


    // Pocket Shulker Features Category
    public static boolean PSBoolean(PocketShulker s) {
        return get().getBoolean(s.getConfigPath());
    }

    public static String PSString(PocketShulker s) {
        return get().getString(s.getConfigPath());
    }

    public static List<String> PSStringList(PocketShulker s) {
        return get().getStringList(s.getConfigPath());
    }

    public static float PSFloat(PocketShulker s) {
        return (float) get().getDouble(s.getConfigPath());
    }


    // Entity Tracker Fixer Category
    public static boolean ETFBoolean(EntityTrackerFixer s) {
        return get().getBoolean(s.getConfigPath());
    }

    public static int ETFInt(EntityTrackerFixer s) {
        return get().getInt(s.getConfigPath());
    }

    public static double ETFDouble(EntityTrackerFixer s) {
        return get().getDouble(s.getConfigPath());
    }

    public static List<String> ETFStringList(EntityTrackerFixer s) {
        return get().getStringList(s.getConfigPath());
    }
}