package id.universenetwork.utilities.bukkit.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static id.universenetwork.utilities.bukkit.UNUtilities.plugin;

public class ConfigurationUtil {
    public static YamlConfiguration getConfig(String path) {
        final File dataFolder = plugin.getDataFolder();
        final File file = new File(path.replace("%datafolder%", dataFolder.toPath().toString()));
        if (file.exists()) return YamlConfiguration.loadConfiguration(file);
        else return new YamlConfiguration();
    }
}
