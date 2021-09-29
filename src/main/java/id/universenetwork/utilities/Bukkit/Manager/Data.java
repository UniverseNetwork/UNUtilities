package id.universenetwork.utilities.Bukkit.Manager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class Data {
    static File file;
    static FileConfiguration data;

    public static void setup() {
        try {
            file = new File(plugin.getDataFolder(), "data.yml");
            if (!file.exists()) file.createNewFile();
            data = YamlConfiguration.loadConfiguration(file);
            data.addDefault("FlyingPlayer", new ArrayList<>());
            data.options().copyDefaults(true);
            data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FileConfiguration get() {
        return data;
    }

    public static void save() {
        try {
            data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reload() {
        YamlConfiguration.loadConfiguration(file);
    }
}
