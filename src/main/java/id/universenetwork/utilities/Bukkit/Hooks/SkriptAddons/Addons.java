package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons;

import ch.njol.skript.SkriptAddon;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.SkQuery;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders.SkriptPlaceholders;

import static ch.njol.skript.Skript.registerAddon;
import static id.universenetwork.utilities.Bukkit.Enums.Features.SkriptAddons.ADDONS;
import static id.universenetwork.utilities.Bukkit.Manager.Config.get;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class Addons {
    public static SkriptAddon addon;

    public static void setup() {
        System.out.println(prefix + " §6Found Skript. Registering Addons...");
        addon = registerAddon(plugin);
        new SkriptPlaceholders();
        new SkQuery();
        System.out.println(prefix + " §aSuccessfully Registered All Enabled Addons to Skript");
    }

    public static boolean Enabled(String AddonName) {
        return get().getBoolean(ADDONS.getConfigPath() + AddonName);
    }
}