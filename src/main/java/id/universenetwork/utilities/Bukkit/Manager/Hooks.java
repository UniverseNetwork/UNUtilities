package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Enums.Features.ShopGUIPlusSilkSpawnersConnector;
import id.universenetwork.utilities.Bukkit.Hooks.AsyncWorldEditBossBarDisplay;
import id.universenetwork.utilities.Bukkit.Hooks.ShopGUIPlusSilkSpawnersConnector.Connector;
import org.bukkit.Bukkit;

import static id.universenetwork.utilities.Bukkit.Manager.Config.AWEBDSettings;
import static id.universenetwork.utilities.Bukkit.UNUtilities.aweHook;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class Hooks {
    public static void AsyncWorldEditBossBarDisplay(String Mode) {
        if (Mode.equalsIgnoreCase("enabling")) {
            if (AWEBDSettings(id.universenetwork.utilities.Bukkit.Enums.Features.AsyncWorldEditBossBarDisplay.ENABLED)) {
                System.out.println(prefix + " §6AsyncWorldEdit BossBar Display Features is enabled on config.yml. Searching AsyncWorldEdit...");
                if (Bukkit.getPluginManager().isPluginEnabled("AsyncWorldEdit")) {
                    AsyncWorldEditBossBarDisplay.hooks();
                    aweHook = true;
                } else
                    System.out.println(prefix + " §cAsyncWorldEdit not found. You need AsyncWorldEdit to use AsyncWorldEdit BossBar Display Features");
            }
        } else if (Mode.equalsIgnoreCase("disabling") && aweHook) {
            AsyncWorldEditBossBarDisplay.unhooks();
        } else if (Mode.equalsIgnoreCase("reloading")) {
            if (AWEBDSettings(id.universenetwork.utilities.Bukkit.Enums.Features.AsyncWorldEditBossBarDisplay.ENABLED) && !aweHook) {
                System.out.println(prefix + " §6AsyncWorldEdit BossBar Display Features is enabled on config.yml. Searching AsyncWorldEdit...");
                if (Bukkit.getPluginManager().isPluginEnabled("AsyncWorldEdit")) {
                    System.out.println(prefix + " §6Found AsyncWorldEdit. Hooking...");
                    AsyncWorldEditBossBarDisplay.hooks();
                    aweHook = true;
                    System.out.println(prefix + " §aSuccessfully hooked with AsyncWorldEdit");
                } else
                    System.out.println(prefix + " §cAsyncWorldEdit not found. You need AsyncWorldEdit to use AsyncWorldEdit BossBar Display Features");
            }

            if (!AWEBDSettings(id.universenetwork.utilities.Bukkit.Enums.Features.AsyncWorldEditBossBarDisplay.ENABLED) && aweHook) {
                System.out.println(prefix + " §cAsyncWorldEdit BossBar Display Features is disabled on config.yml. Unhooking with AsyncWorldEdit...");
                AsyncWorldEditBossBarDisplay.unhooks();
                aweHook = false;
            }
        }
    }

    public static void ShopGUIPlusSilkSpawnersConnector() {
        if (Config.SGPSSCSettings(ShopGUIPlusSilkSpawnersConnector.ENABLED)) {
            System.out.println(prefix + " §6ShopGUI+ SilkSpawners Connector Features is enabled on config.yml. Searching ShopGUI+ and SilkSpawners...");
            boolean SilkSpawners = Bukkit.getPluginManager().isPluginEnabled("SilkSpawners");
            boolean ShopGUIPlus = Bukkit.getPluginManager().isPluginEnabled("ShopGUIPlus");
            if (SilkSpawners && ShopGUIPlus) {
                Connector.hooks();
            } else if (SilkSpawners) {
                System.out.println(prefix + "§6Found SilkSpawners, but ShopGUI+ not found. §cYou need ShopGUI+ to use ShopGUI+ SilkSpawners Connector Features");
            } else if (ShopGUIPlus) {
                System.out.println(prefix + "§6Found ShopGUI+, but SilkSpawners not found. §cYou need SilkSpawners to use ShopGUI+ SilkSpawners Connector Features");
            } else
                System.out.println(prefix + " §cYou need SilkSpawners and ShopGUI+ to use ShopGUI+ SilkSpawners Connector Features");
        }
    }
}
