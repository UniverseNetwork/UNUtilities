package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons;

import id.universenetwork.utilities.Bukkit.Annotations.Dependency;
import id.universenetwork.utilities.Bukkit.Utils.TookTimer;
import org.apache.commons.lang.StringUtils;
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
                            Dependency d = c.getDeclaredAnnotation(Dependency.class);
                            int l = 0;
                            if (d != null) {
                                l = 1;
                                for (String v : d.value())
                                    if (!org.bukkit.Bukkit.getPluginManager().isPluginEnabled(v)) {
                                        l = 2;
                                        break;
                                    }
                            }
                            if (l == 0 || l == 1) ((SfAddon) c.getConstructor().newInstance()).Load();
                            switch (l) {
                                case 0:
                                    info("&bSuccessfully registered &d" + k + " &baddon!");
                                    break;
                                case 1:
                                    info("&a" + convertArraysToString(d.value()) + " found. &bSuccessfully registered &d" + k + " &baddon!");
                                    break;
                                case 2:
                                    severe("&e" + convertArraysToString(d.value()) + " not found. &cYou need " + convertArraysToString(d.value()) + " to use &d" + k + " &caddon!");
                            }
                        }
                    } catch (Exception e) {
                        if (e instanceof ClassNotFoundException) continue;
                        log(java.util.logging.Level.SEVERE, "Failed to register Slimefun addon class:", e);
                    }
                info("&bTook " + t.get() + "ms &ato register all enabled addons to Slimefun!");
            } else severe("&eSlimefun not found. &cYou need Slimefun to use Slimefun Addons Features!");
        }
    }

    public String convertArraysToString(String[] v) {
        String s = StringUtils.remove(StringUtils.remove(java.util.Arrays.toString(v), "["), "]");
        if (v.length == 2) return StringUtils.replace(s, ",", " and");
        if (v.length > 2) {
            int i = 0;
            StringBuilder b = new StringBuilder();
            for (String o : v) {
                ++i;
                if (i == v.length) b.append("and ").append(o);
                else b.append(o).append(", ");
            }
            return b.toString();
        }
        return s;
    }
}