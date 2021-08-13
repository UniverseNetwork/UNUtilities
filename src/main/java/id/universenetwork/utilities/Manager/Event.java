package id.universenetwork.utilities.Manager;

import id.universenetwork.utilities.Listeners.AntiRedstoneListener;
import id.universenetwork.utilities.Listeners.ArmorStandArmsAdderListener;

import static id.universenetwork.utilities.UNUtilities.plugin;

public class Event {
    public static void register(){
        plugin().getServer().getPluginManager().registerEvents(new AntiRedstoneListener(), plugin());
        plugin().getServer().getPluginManager().registerEvents(new ArmorStandArmsAdderListener(), plugin());
    }
}
