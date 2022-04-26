package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Enums.PocketShulker;
import id.universenetwork.utilities.Bukkit.Listeners.*;

import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListeners;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public final class Listeners {
    public static void register() {
        System.out.println(prefix + " §eRegistering Listeners...");
        registerListeners(
                new AntiRedstoneListener(),
                new ArmorStandArmsAdderListener(),
                new AddressWhitelisterListener(),
                new AntiZeroTickFarmListener(),
                new PillagersLimiterListener()
        );
        if (Config.PSBoolean(PocketShulker.ENABLED)) registerListeners(new PocketShulkerListener());
        System.out.println(prefix + " §aAll Listeners Successfully Registered");
    }
}