package id.universenetwork.utilities;

import id.universenetwork.utilities.Enums.Settings;
import id.universenetwork.utilities.Manager.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class UNUtilities extends JavaPlugin {
    private static UNUtilities plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        Config.setup();
        Proxy.setup();
        Event.register();
        Commands.register();
        TabCompleter.register();
        System.out.println(Config.Settings(Settings.PREFIX) + " §dEnabling §bU§eN§9Utilities");
    }

    @Override
    // Plugin shutdown logic
    public void onDisable() {
        System.out.println(Config.Settings(Settings.PREFIX) + " §cDisabling §bU§eN§9Utilities");
    }

    public static UNUtilities plugin() {
        return plugin;
    }
}
