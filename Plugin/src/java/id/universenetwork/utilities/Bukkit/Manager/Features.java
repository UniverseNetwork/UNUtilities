package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Templates.Feature;
import id.universenetwork.utilities.Bukkit.Utils.Logger;
import id.universenetwork.utilities.Bukkit.Utils.TookTimer;
import org.bukkit.configuration.ConfigurationSection;

import static id.universenetwork.utilities.Bukkit.UNUtilities.cfg;

public class Features {
    public static void init() {
        int a = 0;
        Logger.info("&eLoading Features...");
        TookTimer t = new TookTimer();
        ConfigurationSection s = cfg.getConfigurationSection("Features");
        for (String k : s.getKeys(false))
            try {
                Class<?> c = Class.forName("id.universenetwork.utilities.Bukkit.Features." + k + ".Main");
                if (Feature.class.isAssignableFrom(c)) {
                    ((Feature) c.getConstructor().newInstance()).Load();
                    a++;
                }
            } catch (Exception e) {
                if (e instanceof ClassNotFoundException) continue;
                Logger.log(java.util.logging.Level.SEVERE, "Failed to register feature class:", e);
            }
        Logger.info("&bTook " + t.get() + "ms &ato load &b" + a + " &aFeatures!");
    }
}