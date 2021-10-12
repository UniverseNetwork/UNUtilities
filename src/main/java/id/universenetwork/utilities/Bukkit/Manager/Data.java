package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Core.ConfigBuilder;

import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class Data {
    static ConfigBuilder data;

    public static void setup() {
        System.out.println(prefix + " §ePreparing Data Manager...");
        data = new ConfigBuilder("data.yml");
        System.out.println(prefix + " §aData Manager have been prepared");
    }

    public static ConfigBuilder get() {
        return data;
    }

    public static void reload() {
        System.out.println(prefix + " §eReloading Data Manager...");
        data.reload();
        System.out.println(prefix + " §aData Manager has been reloaded");
    }

    public static void set(String path, Object value) {
        get().set(path, value);
        data.save();
    }
}
