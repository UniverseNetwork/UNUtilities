package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons;

import id.universenetwork.utilities.Bukkit.Utils.TookTimer;
import org.bukkit.configuration.ConfigurationSection;

import static id.universenetwork.utilities.Bukkit.UNUtilities.cfg;
import static id.universenetwork.utilities.Bukkit.Utils.Logger.*;
import static org.bukkit.Bukkit.getPluginManager;

public class Main extends id.universenetwork.utilities.Bukkit.Templates.Feature {
    @Override
    public void Load() {
        if (cfg.getBoolean(configPath + "enabled")) {
            info("&eSlimefun Addons feature is enabled on config.yml. Searching Slimefun...");
            if (getPluginManager().isPluginEnabled("Slimefun")) {
                info("&aSlimefun found, &eregistring enabled addons...");
                TookTimer t = new TookTimer();
                ConfigurationSection s = cfg.getConfigurationSection(configPath + "Addons");
                for (String k : s.getKeys(false))
                    if (s.getBoolean(k + ".enabled")) try {
                        Class<?> c = Class.forName("id.universenetwork.utilities.Bukkit.Features.SlimefunAddons." + k + "." + k);
                        if (SfAddon.class.isAssignableFrom(c)) {
                            ((SfAddon) c.getConstructor().newInstance()).Load();
                            info("&bSuccessfully registered &d" + k + " &baddon!");
                        }
                    } catch (Exception e) {
                        if (e instanceof ClassNotFoundException) continue;
                        log(java.util.logging.Level.SEVERE, "Failed to register Slimefun addon class:", e);
                    }
                info("&bTook " + t.get() + "ms &ato register all enabled addons to Slimefun!");
            } else severe("&eSlimefun not found. &cYou need Slimefun to use Slimefun Addons Features!");
        }
    }
}