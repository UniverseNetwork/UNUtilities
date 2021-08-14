package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.TabCompleter.UNU;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class TabCompleter {
    public static void register() {
        plugin().getCommand("universeutilities").setTabCompleter(new UNU());
    }
}
