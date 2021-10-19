package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getTickerTask;

@UtilityClass
public final class MachineLore {
    static final DecimalFormat FORMAT = new DecimalFormat("###,###,###,###,###,###.#");
    static final double TPS = 20D / getTickerTask().getTickRate();
    static final String PREFIX = "&8\u21E8 &e\u26A1 &7";

    @NotNull
    public static String energyPerSecond(int energy) {
        return PREFIX + formatEnergy(energy) + " J/s";
    }

    @NotNull
    public static String energyBuffer(int energy) {
        return PREFIX + format(energy) + " J Buffer";
    }

    @NotNull
    public static String energy(int energy) {
        return PREFIX + format(energy) + " J ";
    }

    @NotNull
    public static String speed(int speed) {
        return PREFIX + "Speed: &b" + speed + 'x';
    }

    @NotNull
    public static String formatEnergy(int energy) {
        return FORMAT.format(energy * TPS);
    }

    @NotNull
    public static String format(double number) {
        return FORMAT.format(number);
    }
}