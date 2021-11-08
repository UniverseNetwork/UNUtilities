package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Enums.Features.AsyncWorldEditBossBarDisplay;
import id.universenetwork.utilities.Bukkit.Enums.Features.ShopGUIPlusSilkSpawnersConnector;
import id.universenetwork.utilities.Bukkit.Enums.Features.ViaLegacy;
import id.universenetwork.utilities.Bukkit.Hooks.ShopGUIPlusSilkSpawnersConnector.Connector;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons;
import id.universenetwork.utilities.Bukkit.UNUtilities;
import org.bukkit.Bukkit;

import static id.universenetwork.utilities.Bukkit.Hooks.AsyncWorldEditBossBarDisplay.hooks;
import static id.universenetwork.utilities.Bukkit.Hooks.AsyncWorldEditBossBarDisplay.unhooks;
import static id.universenetwork.utilities.Bukkit.Manager.Config.*;

public class Hooks {
    public static void AsyncWorldEditBossBarDisplay(String Mode) {
        if (Mode.equalsIgnoreCase("enabling")) {
            if (AWEBDBoolean(id.universenetwork.utilities.Bukkit.Enums.Features.AsyncWorldEditBossBarDisplay.ENABLED)) {
                System.out.println(UNUtilities.prefix + " §6AsyncWorldEdit BossBar Display Features is enabled on config.yml. Searching AsyncWorldEdit...");
                if (Bukkit.getPluginManager().isPluginEnabled("AsyncWorldEdit")) {
                    hooks();
                    UNUtilities.aweHook = true;
                } else
                    System.out.println(UNUtilities.prefix + " §cAsyncWorldEdit not found. You need AsyncWorldEdit to use AsyncWorldEdit BossBar Display Features");
            }
        } else if (Mode.equalsIgnoreCase("disabling") && UNUtilities.aweHook) {
            unhooks();
        } else if (Mode.equalsIgnoreCase("reloading")) {
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
            System.out.println(UNUtilities.prefix + " §6SlimeFun Addons Features is enabled on config.yml. Searching SlimeFun...");
            if (Bukkit.getPluginManager().isPluginEnabled("Slimefun")) Addons.setup();
            else
                Bukkit.getLogger().severe(UNUtilities.prefix + " §6Slimefun not found. §cYou need Slimefun to use Slimefun Addons Features");
        }
    }

    public static void SkriptAddons() {
        if (SABoolean()) {
            System.out.println(UNUtilities.prefix + " §6Skript Addons Features is enabled on config.yml. Searching Skript...");
            if (Bukkit.getPluginManager().isPluginEnabled("Skript"))
                id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons.setup();
            else
                Bukkit.getLogger().severe(UNUtilities.prefix + " §6Skript not found. §cYou need Skript to use Skript Addons Features");
        }
    }

    public static void ViaLegacy() {
        if (VLBoolean(ViaLegacy.ENABLED)) {
            System.out.println(UNUtilities.prefix + " §6ViaLegacy Features is enabled on config.yml. Searching ViaVersion...");
            if (Bukkit.getPluginManager().isPluginEnabled("ViaVersion"))
                new id.universenetwork.utilities.Bukkit.Hooks.ViaLegacy.ViaLegacy();
            else
                Bukkit.getLogger().severe(UNUtilities.prefix + " §6ViaVersion not found. §cYou need ViaVersion to use ViaLegacy Features");
        }
    }
}