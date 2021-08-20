package id.universenetwork.utilities.Bungee.Manager;

import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;

import static id.universenetwork.utilities.Bungee.UNUtilities.config;
import static id.universenetwork.utilities.Bungee.UNUtilities.plugin;

public class Config {
    private final static String configName = "settings.yml";

    // Finds and Generates the config file
    public static void setup() {
        try {
            if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdir();
            File configFile = new File(plugin.getDataFolder(), configName);
            if (!configFile.exists()) {
                InputStream in = plugin.getResourceAsStream(configName);
                try {
                    Files.copy(in, configFile.toPath(), new CopyOption[0]);
                } catch (Throwable v6) {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (Throwable v5) {
                            v6.addSuppressed(v5);
                        }
                    }
                    throw v6;
                }
                if (in != null) {
                    in.close();
                }
            }
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        } catch (IOException v7) {
            throw new RuntimeException("Unable to load configuration", v7);
        }
    }
}