package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Listeners.AddressWhitelisterListener;
import id.universenetwork.utilities.Bukkit.Listeners.AntiRedstoneListener;
import id.universenetwork.utilities.Bukkit.Listeners.AntiZeroTickFarmListener;
import id.universenetwork.utilities.Bukkit.Listeners.ArmorStandArmsAdderListener;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class Event {
    public static void register() {
        plugin().getServer().getPluginManager().registerEvents(new AntiRedstoneListener(), plugin());
        plugin().getServer().getPluginManager().registerEvents(new ArmorStandArmsAdderListener(), plugin());
        plugin().getServer().getPluginManager().registerEvents(new AddressWhitelisterListener(), plugin());
        plugin().getServer().getPluginManager().registerEvents(new AntiZeroTickFarmListener(), plugin());
    }
}