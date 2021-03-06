package id.universenetwork.utilities.bukkit.features.SlimefunAddons;

import id.universenetwork.utilities.bukkit.UNUtilities;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import org.bukkit.plugin.java.JavaPlugin;

public class SfAddon implements SlimefunAddon {
    public final String cfgPath = "Features.SlimefunAddons.Addons." + getClass().getSimpleName() + ".";

    @Override
    public JavaPlugin getJavaPlugin() {
        return UNUtilities.plugin;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/UniverseNetwork/UNUtilities/issues";
    }

    public void Load() {
    }

    public void Disable() {
    }
}