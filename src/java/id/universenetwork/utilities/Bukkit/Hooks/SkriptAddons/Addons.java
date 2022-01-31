package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons;

import ch.njol.skript.SkriptAddon;
import id.universenetwork.utilities.Bukkit.Enums.SkriptAddons;
import id.universenetwork.utilities.Bukkit.UNUtilities;
import org.bukkit.configuration.ConfigurationSection;

import static id.universenetwork.utilities.Bukkit.Manager.Config.get;

public abstract class Addons {
    public static SkriptAddon addon;
    final String Name;

    public Addons(String Name) {
        this.Name = Name;
        if (get().getBoolean(SkriptAddons.ADDONS.getConfigPath() + Name)) Load();
    }

    public abstract void Load();

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

    public ConfigurationSection Settings() {
        return get().getConfigurationSection(SkriptAddons.ADDONSSETTINGS.getConfigPath() + Name);
    }
}