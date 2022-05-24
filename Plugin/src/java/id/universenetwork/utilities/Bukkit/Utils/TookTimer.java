package id.universenetwork.utilities.Bukkit.Utils;

public class TookTimer {
    private final long s;

    public TookTimer() {
        s = System.currentTimeMillis();
    }

    public long get() {
        return System.currentTimeMillis() - s;
    }
}