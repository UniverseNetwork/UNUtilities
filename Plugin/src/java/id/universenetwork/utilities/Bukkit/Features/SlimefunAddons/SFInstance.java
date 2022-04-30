package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons;

public class SFInstance implements io.github.thebusybiscuit.slimefun4.api.SlimefunAddon {
    public final String configPath = "Features.SlimefunAddons.Addons." + getClass().getSimpleName() + ".";

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