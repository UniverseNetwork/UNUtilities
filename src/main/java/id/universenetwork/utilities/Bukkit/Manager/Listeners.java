package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Enums.Features.PocketShulker;
import id.universenetwork.utilities.Bukkit.Listeners.*;

import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListener;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public final class Listeners {
    public static void register() {
        System.out.println(prefix + " §eRegistering Listeners...");
        registerListener(
                new AntiRedstoneListener(),
                new ArmorStandArmsAdderListener(),
                new AddressWhitelisterListener(),
                new AntiZeroTickFarmListener(),
                new PillagersLimiterListener()
        );
        if (Config.PSBoolean(PocketShulker.ENABLED)) registerListener(new PocketShulkerListener());
        System.out.println(prefix + " §aAll Listeners Successfully Registered");
    }
}