package id.universenetwork.utilities.bukkit.manager;

import id.universenetwork.utilities.bukkit.Enums.PocketShulker;
import id.universenetwork.utilities.bukkit.Listeners.*;

import static id.universenetwork.utilities.bukkit.libraries.InfinityLib.Common.Events.registerListeners;
import static id.universenetwork.utilities.bukkit.UNUtilities.prefix;

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