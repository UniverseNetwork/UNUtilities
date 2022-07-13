package id.universenetwork.utilities.bukkit.features.SlimefunAddons;

import id.universenetwork.utilities.bukkit.annotations.Dependency;
import id.universenetwork.utilities.bukkit.templates.Feature;
import id.universenetwork.utilities.bukkit.utils.Logger;
import id.universenetwork.utilities.Universal.Utils.TookTimer;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Arrays;
import java.util.logging.Level;

public class Main extends Feature {
    @Override
    public void Load() {
        if (cfgSection.getBoolean("enabled")) {
            Logger.info("&eSlimefun Addons feature is enabled on config.yml. Searching Slimefun...");
            if (Bukkit.getPluginManager().isPluginEnabled("Slimefun")) {
                Logger.info("&aSlimefun found, &eregistring enabled addons...");
                TookTimer t = new TookTimer();
                ConfigurationSection s = cfgSection.getConfigurationSection("Addons");
                for (String k : s.getKeys(false))
                    if (s.getBoolean(k + ".enabled")) try {
                        Class<?> c = Class.forName(getClass().getPackage().getName() + "." + k + "." + k);
                        if (SfAddon.class.isAssignableFrom(c)) {
                            Dependency d = c.getDeclaredAnnotation(Dependency.class);
                            int l = 0;
                            if (d != null) {
                                l = 1;
                                for (String v : d.value())
                                    if (!Bukkit.getPluginManager().isPluginEnabled(v)) {
                                        l = 2;
                                        break;
                                    }
                            }
                            if (l == 0 || l == 1) ((SfAddon) c.getConstructor().newInstance()).Load();
                            switch (l) {
                                case 0:
                                    Logger.info("&bSuccessfully registered &d" + k + " &baddon!");
                                    break;
                                case 1:
                                    Logger.info("&a" + convertArraysToString(d.value()) + " found. &bSuccessfully registered &d" + k + " &baddon!");
                                    break;
                                case 2:
                                    Logger.severe("&e" + convertArraysToString(d.value()) + " not found. &cYou need " + convertArraysToString(d.value()) + " to use &d" + k + " &caddon!");
                            }
                        }
                    } catch (Exception e) {
                        if (e instanceof ClassNotFoundException) continue;
                        Logger.log(Level.SEVERE, "Failed to register Slimefun addon class!", e);
                    }
                Logger.info("&bTook " + t.get() + "ms &ato register all enabled addons to Slimefun!");
            } else Logger.severe("&eSlimefun not found. &cYou need Slimefun to use Slimefun Addons Features!");
        }
    }

    public String convertArraysToString(String[] v) {
        String s = StringUtils.remove(StringUtils.remove(Arrays.toString(v), "["), "]");
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