package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons;

import ch.njol.skript.SkriptAddon;
import id.universenetwork.utilities.Bukkit.Enums.SkriptAddons;
import id.universenetwork.utilities.Bukkit.UNUtilities;
import org.bukkit.configuration.ConfigurationSection;

import static id.universenetwork.utilities.Bukkit.Manager.Config.get;

public abstract class Addons {
    public static ConfigurationSection settings;
    public static SkriptAddon addon;
    final String n;

    public Addons() {
        n = java.util.Objects.requireNonNull(getClass().getDeclaredAnnotation(id.universenetwork.utilities.Bukkit.Annotations.AddonName.class), "Addon must be have AddonName Annotation").value();
        settings = get().getConfigurationSection(SkriptAddons.ADDONSSETTINGS.getConfigPath() + n);
        if (AddonIsEnabled(n)) Load();
    }

    public static void setup() {
        System.out.println(UNUtilities.prefix + " §6Found Skript. Registering Addons...");
        addon = ch.njol.skript.Skript.registerAddon(UNUtilities.plugin);
        new id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders.SkriptPlaceholders();
        new id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptVotifierHook.SkriptVotifierHook();
        new id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkWhitelist.SkWhitelist();
        new id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.BungeeAddon.BungeeAddon();
        new id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkMusic.SkMusic();
        new id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Skream();
        System.out.println(UNUtilities.prefix + " §aSuccessfully Registered All Enabled Addons to Skript");
    }

    public abstract void Load();

    public boolean AddonIsEnabled(String Name) {
        return get().getBoolean(SkriptAddons.ADDONS.getConfigPath() + Name);
    }
}