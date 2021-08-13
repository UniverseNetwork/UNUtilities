package id.universenetwork.utilities.Manager;

import id.universenetwork.utilities.TabCompleter.UNU;

import static id.universenetwork.utilities.UNUtilities.plugin;

public class TabCompleter {
    public static void register() {
        plugin().getCommand("universeutilities").setTabCompleter(new UNU());
    }
}
