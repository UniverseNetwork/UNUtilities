package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Enums.Features.AsyncWorldEditBossBarDisplay;
import id.universenetwork.utilities.Bukkit.Enums.Features.ShopGUIPlusSilkSpawnersConnector;
import id.universenetwork.utilities.Bukkit.Hooks.ShopGUIPlusSilkSpawnersConnector.Connector;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.Addons;
import org.bukkit.Bukkit;

import static id.universenetwork.utilities.Bukkit.Hooks.AsyncWorldEditBossBarDisplay.hooks;
import static id.universenetwork.utilities.Bukkit.Hooks.AsyncWorldEditBossBarDisplay.unhooks;
import static id.universenetwork.utilities.Bukkit.Manager.Config.*;
import static id.universenetwork.utilities.Bukkit.UNUtilities.aweHook;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class Hooks {
    public static void AsyncWorldEditBossBarDisplay(String Mode) {
        if (Mode.equalsIgnoreCase("enabling")) {
            if (AWEBDSettings(id.universenetwork.utilities.Bukkit.Enums.Features.AsyncWorldEditBossBarDisplay.ENABLED)) {
                System.out.println(prefix + " §6AsyncWorldEdit BossBar Display Features is enabled on config.yml. Searching AsyncWorldEdit...");
                if (Bukkit.getPluginManager().isPluginEnabled("AsyncWorldEdit")) {
                    hooks();
                    aweHook = true;
                } else
                    System.out.println(prefix + " §cAsyncWorldEdit not found. You need AsyncWorldEdit to use AsyncWorldEdit BossBar Display Features");
            }
        } else if (Mode.equalsIgnoreCase("disabling") && aweHook) {
            unhooks();
        } else if (Mode.equalsIgnoreCase("reloading")) {
            if (AWEBDSettings(AsyncWorldEditBossBarDisplay.ENABLED) && !aweHook) {
                System.out.println(prefix + " §6AsyncWorldEdit BossBar Display Features is enabled on config.yml. Searching AsyncWorldEdit...");
                if (Bukkit.getPluginManager().isPluginEnabled("AsyncWorldEdit")) {
                    System.out.println(prefix + " §6Found AsyncWorldEdit. Hooking...");
                    hooks();
                    aweHook = true;
                    System.out.println(prefix + " §aSuccessfully hooked with AsyncWorldEdit");
                } else
                    Bukkit.getLogger().warning(prefix + " §cAsyncWorldEdit not found. You need AsyncWorldEdit to use AsyncWorldEdit BossBar Display Features");
            } else if (!AWEBDSettings(AsyncWorldEditBossBarDisplay.ENABLED) && aweHook) {
                System.out.println(prefix + " §cAsyncWorldEdit BossBar Display Features is disabled on config.yml. Unhooking with AsyncWorldEdit...");
                unhooks();
                aweHook = false;
            }
        }
    }

    public static void ShopGUIPlusSilkSpawnersConnector() {
        if (SGPSSCSettings(ShopGUIPlusSilkSpawnersConnector.ENABLED)) {
            System.out.println(prefix + " §6ShopGUI+ SilkSpawners Connector Features is enabled on config.yml. Searching ShopGUI+ and SilkSpawners...");
            boolean SilkSpawners = Bukkit.getPluginManager().isPluginEnabled("SilkSpawners");
            boolean ShopGUIPlus = Bukkit.getPluginManager().isPluginEnabled("ShopGUIPlus");
            if (SilkSpawners && ShopGUIPlus) Connector.hooks();
            else if (SilkSpawners)
                Bukkit.getLogger().warning(prefix + " §6Found SilkSpawners, but ShopGUI+ not found. §cYou need ShopGUI+ to use ShopGUI+ SilkSpawners Connector Features");
            else if (ShopGUIPlus)
                Bukkit.getLogger().warning(prefix + " §6Found ShopGUI+, but SilkSpawners not found. §cYou need SilkSpawners to use ShopGUI+ SilkSpawners Connector Features");
            else
                Bukkit.getLogger().warning(prefix + " §6ShopGUI+ and SilkSpawners §cYou need SilkSpawners and ShopGUI+ to use ShopGUI+ SilkSpawners Connector Features");
        }
    }

    public static void SlimeFunAddons() {
        if (SFASettings()) {
            System.out.println(prefix + " §6SlimeFun Addons Features is enabled on config.yml. Searching SlimeFun...");
            if (Bukkit.getPluginManager().isPluginEnabled("Slimefun")) Addons.setup();
            else
                Bukkit.getLogger().warning(prefix + " §6SlimeFun not found. §cYou need SlimeFun to use SlimeFun Addons Features");
        }
    }

    public static void SkriptAddons() {
        if (SASettings()) {
            System.out.println(prefix + " §6Skript Addons Features is enabled on config.yml. Searching Skript...");
            if (Bukkit.getPluginManager().getPlugin("Skript") != null)
                id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons.setup();
            else
                Bukkit.getLogger().warning(prefix + " §6Skript not found. §cYou need Skript to use Skript Addons Features");
        }
    }
}