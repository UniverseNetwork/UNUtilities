package id.universenetwork.utilities.Bungee.Utils;

import net.md_5.bungee.api.plugin.Listener;

import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static id.universenetwork.utilities.Bungee.UNUtilities.plugin;

public class Event {
    public static void registerListeners(Listener... Listeners) {
        for (Listener l : Listeners)
            try {
                plugin.getProxy().getPluginManager().registerListener(plugin, l);
            } catch (Exception e) {
                plugin.getLogger().severe(prefix + " §cFailed to register listener! [" + l.toString() + "]");
                e.printStackTrace();
            }
    }

    public static void unregisterListeners(Listener... Listeners) {
        for (Listener l : Listeners) plugin.getProxy().getPluginManager().unregisterListener(l);
    }
}