package id.universenetwork.utilities;

import id.universenetwork.utilities.Enums.Settings;
import id.universenetwork.utilities.Manager.Commands;
import id.universenetwork.utilities.Manager.Config;
import id.universenetwork.utilities.Manager.Proxy;
import id.universenetwork.utilities.Manager.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import static id.universenetwork.utilities.Manager.Event.register;

public final class UNUtilities extends JavaPlugin {
    private static UNUtilities plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        Config.setup();
        Proxy.setup();
        register();
        getCommand("universeutilities").setExecutor(new Commands());
        getCommand("universeutilities").setTabCompleter(new TabCompleter());
        System.out.println(Config.Settings(Settings.PREFIX) + " §dEnabling §bU§eN§9Utilities");
    }

    @Override
    public void onDisable() {
        System.out.println(Config.Settings(Settings.PREFIX) + " §cDisabling §bU§eN§9Utilities");
    }

    public static UNUtilities plugin() {
        return plugin;
    }
}
