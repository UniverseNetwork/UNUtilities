package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons;

import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders.SkriptPlaceholders;

import static id.universenetwork.utilities.Bukkit.Enums.Features.SkriptAddons.ADDONS;
import static id.universenetwork.utilities.Bukkit.Manager.Config.get;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class Addons {
    public static void setup() {
        System.out.println(prefix + " §6Found Skript. Registering Addons...");
        new SkriptPlaceholders();
        System.out.println(prefix + " §aSuccessfully Registered All Enabled Addons to Skript");
    }

    public static boolean Enabled(String AddonName) {
        return get().getBoolean(ADDONS.getConfigPath() + AddonName);
    }
}