package id.universenetwork.utilities.Bukkit;

import id.universenetwork.utilities.Bukkit.Enums.Features.MaxPlayerChangerCommand;
import id.universenetwork.utilities.Bukkit.Enums.Settings;
import id.universenetwork.utilities.Bukkit.Manager.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

public final class UNUtilities extends JavaPlugin {
    public static UNUtilities plugin;

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
        if (Config.MPCCSettings(MaxPlayerChangerCommand.SOR) && Config.MPCCSettings(MaxPlayerChangerCommand.ENABLED))
            updateServerProperties();
    }

    private void updateServerProperties() {
        Properties properties = new Properties();
        File propertiesFile = new File("server.properties");
        try {
            FileInputStream is = new FileInputStream(propertiesFile);
            try {
                properties.load(is);
            } catch (Throwable v10) {
                try {
                    is.close();
                } catch (Throwable v8) {
                    v10.addSuppressed(v8);
                }
                throw v10;
            }
            is.close();
            String maxPlayers = Integer.toString(getServer().getMaxPlayers());
            if (properties.getProperty("max-players").equals(maxPlayers)) return;
            System.out.println(Config.Settings(Settings.PREFIX) + " §6Saving max players to server.properties...");
            properties.setProperty("max-players", maxPlayers);
            FileOutputStream os = new FileOutputStream(propertiesFile);
            try {
                properties.store(os, "Minecraft server properties");
            } catch (Throwable v9) {
                try {
                    os.close();
                } catch (Throwable v7) {
                    v9.addSuppressed(v7);
                }
                throw v9;
            }
            os.close();
        } catch (IOException v11) {
            getLogger().log(Level.SEVERE, "Error while saving max players in server properties", v11);
        }
    }
}