package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons;

import id.universenetwork.utilities.Bukkit.Enums.Features.SkriptAddons;
import id.universenetwork.utilities.Bukkit.UNUtilities;
import org.bukkit.configuration.ConfigurationSection;

import static id.universenetwork.utilities.Bukkit.Manager.Config.get;

public class Addons {
    public static ch.njol.skript.SkriptAddon addon;

    public static void setup() {
        System.out.println(UNUtilities.prefix + " §6Found Skript. Registering Addons...");
        addon = ch.njol.skript.Skript.registerAddon(UNUtilities.plugin);
        new id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders.SkriptPlaceholders();
        new id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptVotifierHook.SkriptVotifierHook();
        new id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkWhitelist.SkWhitelist();
        new id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.BungeeAddon.BungeeAddon();
        new id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkMusic.SkMusic();
        System.out.println(UNUtilities.prefix + " §aSuccessfully Registered All Enabled Addons to Skript");
    }

    public static ConfigurationSection Settings(String AddonName) {
        return get().getConfigurationSection(SkriptAddons.ADDONSSETTINGS.getConfigPath() + AddonName);
    }

    public static boolean Enabled(String AddonName) {
        return get().getBoolean(SkriptAddons.ADDONS.getConfigPath() + AddonName);
    }
}