package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Listeners.*;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static org.bukkit.Bukkit.getPluginManager;

public class Event {
    public static void register() {
        getPluginManager().registerEvents(new AntiRedstoneListener(), plugin);
        getPluginManager().registerEvents(new ArmorStandArmsAdderListener(), plugin);
        getPluginManager().registerEvents(new AddressWhitelisterListener(), plugin);
        getPluginManager().registerEvents(new AntiZeroTickFarmListener(), plugin);
        getPluginManager().registerEvents(new FlyFixerListener(), plugin);
    }
}