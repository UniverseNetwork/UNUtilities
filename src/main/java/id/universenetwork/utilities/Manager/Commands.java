package id.universenetwork.utilities.Manager;

import id.universenetwork.utilities.Commands.UNU;

import static id.universenetwork.utilities.UNUtilities.plugin;

public class Commands {
    public static void register() {
        plugin().getCommand("universeutilities").setExecutor(new UNU());
    }
}