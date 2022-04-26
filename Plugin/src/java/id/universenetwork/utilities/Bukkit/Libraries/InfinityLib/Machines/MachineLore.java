package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines;

import java.text.DecimalFormat;

@lombok.experimental.UtilityClass
public final class MachineLore {
    static final DecimalFormat FORMAT = new DecimalFormat("###,###,###,###,###,###.#");
    static final double TPS = 20D / io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getTickerTask().getTickRate();
    static final String PREFIX = "&8\u21E8 &e\u26A1 &7";

    public static String energyPerSecond(int energy) {
        return PREFIX + formatEnergy(energy) + " J/s";
    }

    public static String energyBuffer(int energy) {
        return PREFIX + format(energy) + " J Buffer";
    }

    public static String energy(int energy) {
        return PREFIX + format(energy) + " J ";
    }

    public static String speed(int speed) {
        return PREFIX + "Speed: &b" + speed + 'x';
    }

    public static String formatEnergy(int energy) {
        return FORMAT.format(energy * TPS);
    }

    public static String format(double number) {
        return FORMAT.format(number);
    }
}