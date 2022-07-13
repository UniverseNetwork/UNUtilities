package id.universenetwork.utilities.bukkit.manager;

import id.universenetwork.utilities.bukkit.templates.Feature;
import id.universenetwork.utilities.bukkit.UNUtilities;
import id.universenetwork.utilities.bukkit.utils.Logger;
import id.universenetwork.utilities.Universal.Utils.TookTimer;
import org.bukkit.configuration.ConfigurationSection;

import java.util.logging.Level;

public class Features {
    public static void init() {
        int a = 0;
        Logger.info("&eLoading Features...");
        TookTimer t = new TookTimer();
        ConfigurationSection s = UNUtilities.cfg.getConfigurationSection("Features");
        for (String k : s.getKeys(false))
            try {
                Class<?> c = Class.forName("id.universenetwork.utilities.bukkit.features." + k + ".Main");
                if (Feature.class.isAssignableFrom(c)) {
                    ((Feature) c.getConstructor().newInstance()).Load();
                    a++;
                }
            } catch (Exception e) {
                if (e instanceof ClassNotFoundException) continue;
                Logger.log(Level.SEVERE, "Failed to register feature class!", e);
            }
        Logger.info("&bTook " + t.get() + "ms &ato load &b" + a + " &aFeatures!");
    }
}