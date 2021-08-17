package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Enums.Features.AsyncWorldEditBossBarDisplay;
import id.universenetwork.utilities.Bukkit.Hooks.AsyncWorldEdit;
import org.bukkit.Bukkit;

import static id.universenetwork.utilities.Bukkit.Manager.Config.AWEBDSettings;
import static id.universenetwork.utilities.Bukkit.UNUtilities.aweHook;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class Hooks {
    public static void AsyncWorldEdit(String mode) {
        if (mode.equalsIgnoreCase("enabling")) {
            if (AWEBDSettings(AsyncWorldEditBossBarDisplay.ENABLED)) {
                System.out.println(prefix + " §6AsyncWorldEdit BossBar Display Features is enabled on config.yml. Searching AsyncWorldEdit...");
                if (Bukkit.getPluginManager().isPluginEnabled("AsyncWorldEdit")) {
                    AsyncWorldEdit.hooks();
                    aweHook = true;
                } else
                    System.out.println(prefix + " §cAsyncWorldEdit not found. You need AsyncWorldEdit to use AsyncWorldEdit BossBar Display Features");
            }
        } else if (mode.equalsIgnoreCase("disabling") && aweHook) {
            AsyncWorldEdit.unhooks();
        } else if (mode.equalsIgnoreCase("reloading")) {
            if (AWEBDSettings(AsyncWorldEditBossBarDisplay.ENABLED) && !aweHook) {
                System.out.println(prefix + " §6AsyncWorldEdit BossBar Display Features is enabled on config.yml. Searching AsyncWorldEdit...");
                if (Bukkit.getPluginManager().isPluginEnabled("AsyncWorldEdit")) {
                    System.out.println(prefix + " §6Found AsyncWorldEdit. Hooking...");
                    AsyncWorldEdit.hooks();
                    aweHook = true;
                    System.out.println(prefix + " §aSuccessfully hooked with AsyncWorldEdit");
                } else
                    System.out.println(prefix + " §cAsyncWorldEdit not found. You need AsyncWorldEdit to use AsyncWorldEdit BossBar Display Features");
            }

            if (!AWEBDSettings(AsyncWorldEditBossBarDisplay.ENABLED) && aweHook) {
                System.out.println(prefix + " §cAsyncWorldEdit BossBar Display Features is disabled on config.yml. Unhooking with AsyncWorldEdit...");
                AsyncWorldEdit.unhooks();
                aweHook = false;
            }
        }
    }
}
