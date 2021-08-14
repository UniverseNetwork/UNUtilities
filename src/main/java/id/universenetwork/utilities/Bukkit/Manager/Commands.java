package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Commands.UNU;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class Commands {
    public static void register() {
        plugin().getCommand("universeutilities").setExecutor(new UNU());
    }
}