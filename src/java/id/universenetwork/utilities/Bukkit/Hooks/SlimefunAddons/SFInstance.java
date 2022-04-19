package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons;

public class SFInstance implements io.github.thebusybiscuit.slimefun4.api.SlimefunAddon {
    public final String configPath = id.universenetwork.utilities.Bukkit.Enums.SlimefunAddons.ADDONS.getConfigPath() + "." + getClass().getSimpleName() + ".";

    @Override
    public org.bukkit.plugin.java.JavaPlugin getJavaPlugin() {
        return id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/UniverseNetwork/UNUtilities/issues";
    }

    @Override
    public java.util.logging.Logger getLogger() {
        return org.bukkit.Bukkit.getLogger();
    }

    public void Load() {
    }

    public void Disable() {
    }
}