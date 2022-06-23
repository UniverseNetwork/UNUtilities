package id.universenetwork.utilities.Bukkit.Templates;

import id.universenetwork.utilities.Bukkit.Events.ReloadConfigEvent;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events;
import id.universenetwork.utilities.Bukkit.UNUtilities;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Feature implements Listener {
    String n = getClass().getPackage().getName();
    public final String cfgPath = "Features." + n.substring(n.lastIndexOf('.') + 1) + ".";
    public ConfigurationSection cfgSection = UNUtilities.cfg.getConfigurationSection(cfgPath);

    public Feature() {
        Events.registerListeners(this);
    }

    public void Load() {
    }

    @EventHandler
    public void cfgSectionReload(ReloadConfigEvent e) {
        cfgSection = UNUtilities.cfg.getConfigurationSection(cfgPath);
    }
}