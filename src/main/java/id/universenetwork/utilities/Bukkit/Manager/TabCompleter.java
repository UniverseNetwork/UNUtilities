package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.TabCompleter.Empty;
import id.universenetwork.utilities.Bukkit.TabCompleter.UNU;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class TabCompleter {
    public static void register() {
        plugin.getCommand("universeutilities").setTabCompleter(new UNU());
        plugin.getCommand("hat").setTabCompleter(new Empty());
        plugin.getCommand("changeslots").setTabCompleter(new Empty());
    }
}
