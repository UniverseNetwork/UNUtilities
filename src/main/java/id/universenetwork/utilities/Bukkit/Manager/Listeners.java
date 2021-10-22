package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Enums.Features.PocketShulker;
import id.universenetwork.utilities.Bukkit.Listeners.*;
import org.bukkit.event.Listener;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getPluginManager;

public class Listeners {
    public static void register(Listener... Listeners) {
        for (Listener listener : Listeners) getPluginManager().registerEvents(listener, plugin);
    }

    public static void register() {
        System.out.println(prefix + " §eRegistering Listeners...");
        register(
                new AntiRedstoneListener(),
                new ArmorStandArmsAdderListener(),
                new AddressWhitelisterListener(),
                //new FlyFixerListener(),
                //new PerPlayerKeeperListener(),
                new AntiZeroTickFarmListener()
        );
        if (Config.PSBoolean(PocketShulker.ENABLED)) register(new PocketShulkerListener());
        System.out.println(prefix + " §aAll Events Successfully Registered");
    }
}
