package id.universenetwork.utilities.Bungee;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

public class UNUtilities extends Plugin {
    public static UNUtilities plugin;
    public static Configuration config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        System.out.println("§bU§eN§9Utilities §dhas been enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("§bU§eN§9Utilities §chas been disabled");
    }
}
