package id.universenetwork.utilities.bukkit.manager;

import id.universenetwork.utilities.bukkit.libraries.InfinityLib.Core.YamlBuilder;

import static id.universenetwork.utilities.bukkit.UNUtilities.prefix;

public class Data {
    static YamlBuilder data;

    public static void setup() {
        System.out.println(prefix + " §ePreparing Data Manager...");
        data = new YamlBuilder("data.yml");
        System.out.println(prefix + " §aData Manager have been prepared");
    }

    public static YamlBuilder get() {
        return data;
    }

    public static void reload() {
        System.out.println(prefix + " §eReloading Data Manager...");
        data.reload();
        System.out.println(prefix + " §aData Manager has been reloaded");
    }

    public static void set(String path, Object value) {
        get().set(path, value);
    }
}
