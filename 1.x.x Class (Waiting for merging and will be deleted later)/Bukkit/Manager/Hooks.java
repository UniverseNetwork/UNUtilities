package id.universenetwork.utilities.bukkit.manager;

import id.universenetwork.utilities.bukkit.Enums.AsyncWorldEditBossBarDisplay;
import id.universenetwork.utilities.bukkit.Enums.ShopGUIPlusSilkSpawnersConnector;
import id.universenetwork.utilities.bukkit.Enums.ViaLegacy;
import id.universenetwork.utilities.bukkit.Hooks.ShopGUIPlusSilkSpawnersConnector.Connector;
import id.universenetwork.utilities.bukkit.Hooks.SlimefunAddons.SFInstance;
import id.universenetwork.utilities.bukkit.UNUtilities;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

import static id.universenetwork.utilities.bukkit.Hooks.AsyncWorldEditBossBarDisplay.hooks;
import static id.universenetwork.utilities.bukkit.Hooks.AsyncWorldEditBossBarDisplay.unhooks;
import static id.universenetwork.utilities.bukkit.manager.Config.*;

public class Hooks {
    public static void AsyncWorldEditBossBarDisplay(String Mode) {
        if (Mode.equalsIgnoreCase("enabling")) {
            if (AWEBDBoolean(AsyncWorldEditBossBarDisplay.ENABLED)) {
                System.out.println(UNUtilities.prefix + " §6AsyncWorldEdit BossBar Display Features is enabled on config.yml. Searching AsyncWorldEdit...");
                if (Bukkit.getPluginManager().isPluginEnabled("AsyncWorldEdit")) {
                    hooks();
                    UNUtilities.aweHook = true;
                } else
                    System.out.println(UNUtilities.prefix + " §cAsyncWorldEdit not found. You need AsyncWorldEdit to use AsyncWorldEdit BossBar Display Features");
            }
        } else if (Mode.equalsIgnoreCase("disabling") && UNUtilities.aweHook) unhooks();
        else if (Mode.equalsIgnoreCase("reloading"))
            if (AWEBDBoolean(AsyncWorldEditBossBarDisplay.ENABLED) && !UNUtilities.aweHook) {
                System.out.println(UNUtilities.prefix + " §6AsyncWorldEdit BossBar Display Features is enabled on config.yml. Searching AsyncWorldEdit...");
                if (Bukkit.getPluginManager().isPluginEnabled("AsyncWorldEdit")) {
                    System.out.println(UNUtilities.prefix + " §6Found AsyncWorldEdit. Hooking...");
                    hooks();
                    UNUtilities.aweHook = true;
                    System.out.println(UNUtilities.prefix + " §aSuccessfully hooked with AsyncWorldEdit");
                } else
                    Bukkit.getLogger().severe(UNUtilities.prefix + " §cAsyncWorldEdit not found. You need AsyncWorldEdit to use AsyncWorldEdit BossBar Display Features");
            } else if (!AWEBDBoolean(AsyncWorldEditBossBarDisplay.ENABLED) && UNUtilities.aweHook) {
                System.out.println(UNUtilities.prefix + " §cAsyncWorldEdit BossBar Display Features is disabled on config.yml. Unhooking with AsyncWorldEdit...");
                unhooks();
                UNUtilities.aweHook = false;
            }
    }

    public static void ShopGUIPlusSilkSpawnersConnector() {
        if (SGPSSCBoolean(ShopGUIPlusSilkSpawnersConnector.ENABLED)) {
            System.out.println(UNUtilities.prefix + " §6ShopGUI+ SilkSpawners Connector Features is enabled on config.yml. Searching ShopGUI+ and SilkSpawners...");
            boolean SilkSpawners = Bukkit.getPluginManager().isPluginEnabled("SilkSpawners");
            boolean ShopGUIPlus = Bukkit.getPluginManager().isPluginEnabled("ShopGUIPlus");
            if (SilkSpawners && ShopGUIPlus) Connector.hooks();
            else if (SilkSpawners)
                Bukkit.getLogger().severe(UNUtilities.prefix + " §6Found SilkSpawners, but ShopGUI+ not found. §cYou need ShopGUI+ to use ShopGUI+ SilkSpawners Connector Features");
            else if (ShopGUIPlus)
                Bukkit.getLogger().severe(UNUtilities.prefix + " §6Found ShopGUI+, but SilkSpawners not found. §cYou need SilkSpawners to use ShopGUI+ SilkSpawners Connector Features");
            else
                Bukkit.getLogger().severe(UNUtilities.prefix + " §6ShopGUI+ and SilkSpawners §cYou need SilkSpawners and ShopGUI+ to use ShopGUI+ SilkSpawners Connector Features");
        }
    }

    public static void SlimefunAddons() {
        if (SFABoolean()) {
            System.out.println(UNUtilities.prefix + " §6Slimefun Addons Features is enabled on config.yml. Searching Slimefun...");
            if (Bukkit.getPluginManager().isPluginEnabled("Slimefun")) {
                ConfigurationSection a = config.getConfigurationSection(id.universenetwork.utilities.bukkit.Enums.SlimefunAddons.ADDONS.getConfigPath());
                for (String s : a.getKeys(false))
                    if (a.getBoolean(s + ".enabled")) try {
                        Class<?> c = Class.forName("id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons." + s + "." + s);
                        if (SFInstance.class.isAssignableFrom(c)) {
                            ((SFInstance) c.getConstructor().newInstance()).Load();
                            System.out.println(UNUtilities.prefix + " §bSuccessfully registered §d" + s + " §baddon");
                        }
                    } catch (Exception ignore) {
                    }
                System.out.println(UNUtilities.prefix + " §aSuccessfully Registered All Enabled Addons to Slimefun");
            } else
                Bukkit.getLogger().severe(UNUtilities.prefix + " §6Slimefun not found. §cYou need Slimefun to use Slimefun Addons Features");
        }
    }

    public static void SkriptAddons() {
        if (SABoolean()) {
            System.out.println(UNUtilities.prefix + " §6Skript Addons Features is enabled on config.yml. Searching Skript...");
            if (Bukkit.getPluginManager().isPluginEnabled("Skript"))
                id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Addons.setup();
            else
                Bukkit.getLogger().severe(UNUtilities.prefix + " §6Skript not found. §cYou need Skript to use Skript Addons Features");
        }
    }

    public static void ViaLegacy() {
        if (VLBoolean(ViaLegacy.ENABLED)) {
            System.out.println(UNUtilities.prefix + " §6ViaLegacy Features is enabled on config.yml. Searching ViaVersion...");
            if (Bukkit.getPluginManager().isPluginEnabled("ViaVersion"))
                new id.universenetwork.utilities.bukkit.Hooks.ViaLegacy.ViaLegacy();
            else
                Bukkit.getLogger().severe(UNUtilities.prefix + " §6ViaVersion not found. §cYou need ViaVersion to use ViaLegacy Features");
        }
    }
}