package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Core;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

/**
 * A config which is able to save all of its comments and has some additional utility methods
 *
 * @author Mooy1
 * @author ARVIN3108 ID
 */
public class YamlBuilder extends YamlConfiguration {
    final YamlConfiguration defaults = new YamlConfiguration();
    final Map<String, String> comments = new HashMap<>();
    final File file;

    public YamlBuilder(String Name) {
        file = new File(plugin.getDataFolder(), Name);
        super.defaults = defaults;
        loadDefaults(Name);
    }

    public YamlBuilder(String Name, String Path) {
        file = new File(Path, Name);
        super.defaults = defaults;
        loadDefaults(Name);
    }

    public int getInt(String path, int min, int max) {
        int val = getInt(path);
        if (val < min || val > max) set(path, val = getDefaults().getInt(path));
        return val;
    }

    public double getDouble(String path, double min, double max) {
        double val = getDouble(path);
        if (val < min || val > max) set(path, val = getDefaults().getDouble(path));
        return val;
    }

    /**
     * Removes unused/old keys from the users config
     */
    public void removeUnusedKeys() {
        for (String key : getKeys(true)) if (!defaults.contains(key)) set(key, null);
    }

    public void save() {
        try {
            save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(File file) throws IOException {
        super.save(file);
    }

    public void reload() {
        if (file.exists()) try {
            load(file);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        save();
    }

    @Override
    public YamlConfiguration getDefaults() {
        return defaults;
    }

    String getComment(String key) {
        return comments.get(key);
    }

    @Override
    protected String buildHeader() {
        return "";
    }

    @Override
    public String saveToString() {
        options().copyDefaults(true).copyHeader(false).indent(2);
        String defaultSave = super.saveToString();
        try {
            String[] lines = defaultSave.split("\n");
            StringBuilder save = new StringBuilder();
            PathBuilder pathBuilder = new PathBuilder();
            for (String line : lines) {
                if (line.contains(":")) {
                    String comment = getComment(pathBuilder.append(line).build());
                    if (comment != null) save.append(comment);
                }
                save.append(line).append('\n');
            }
            return save.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return defaultSave;
        }
    }

    void loadDefaults(String name) {
        InputStream stream = plugin.getResource(name);
        if (stream == null) throw new IllegalStateException("No default config for " + name + "!");
        else try {
            defaults.loadFromString(readDefaults(stream));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        reload();
    }

    String readDefaults(@NotNull InputStream inputStream) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuilder yamlBuilder = new StringBuilder();
        StringBuilder commentBuilder = new StringBuilder("\n");
        PathBuilder pathBuilder = new PathBuilder();
        String line;
        while ((line = input.readLine()) != null) {
            yamlBuilder.append(line).append('\n');
            // Skip
            if (StringUtils.isBlank(line)) continue;
            if (line.contains("#")) {
                // Add to comment of next path
                commentBuilder.append(line).append('\n');
                continue;
            }
            // Its part of a path
            if (line.contains(":")) pathBuilder.append(line);
            else continue;
            if (commentBuilder.length() != 1) {
                // Add the comment to the path and clear
                comments.put(pathBuilder.build(), commentBuilder.toString());
                commentBuilder = new StringBuilder("\n");
            } else if (pathBuilder.inMainSection())
                comments.put(pathBuilder.build(), "\n"); // The main section should always have spaces between keys
        }
        input.close();
        return yamlBuilder.toString();
    }

    public <T> T getOrSetDefault(@NotNull String path, T value) {
        Object val = get(path);
        if (value.getClass().isInstance(val)) return (T) val;
        else {
            set(path, value);
            return value;
        }
    }

    /**
     * Sets the Value for the specified path
     *
     * @param path  The path in the Config File
     * @param value The Value for that path
     */
    @Override
    public void set(String path, Object value) {
        if (value == null) super.set(path, value);
        else if (value instanceof Optional) super.set(path, ((Optional<?>) value).orElse(null));
        else if (value instanceof Inventory) {
            super.set(path + ".size", ((Inventory) value).getSize());
            for (int i = 0; i < ((Inventory) value).getSize(); i++)
                super.set(path + "." + i, ((Inventory) value).getItem(i));
        } else if (value instanceof Date) super.set(path, String.valueOf(((Date) value).getTime()));
        else if (value instanceof Long) super.set(path, String.valueOf(value));
        else if (value instanceof UUID) super.set(path, value.toString());
        else if (value instanceof Sound) super.set(path, String.valueOf(value));
        else if (value instanceof Location) {
            super.set(path + ".x", ((Location) value).getX());
            super.set(path + ".y", ((Location) value).getY());
            super.set(path + ".z", ((Location) value).getZ());
            super.set(path + ".pitch", ((Location) value).getPitch());
            super.set(path + ".yaw", ((Location) value).getYaw());
            super.set(path + ".world", ((Location) value).getWorld().getName());
        } else if (value instanceof Chunk) {
            super.set(path + ".x", ((Chunk) value).getX());
            super.set(path + ".z", ((Chunk) value).getZ());
            super.set(path + ".world", ((Chunk) value).getWorld().getName());
        } else if (value instanceof World) super.set(path, ((World) value).getName());
        else
            super.set(path, value);
        save();
    }

    /**
     * Sets the Value for the specified path
     * (If the path does not yet exist)
     *
     * @param path  The path in the {@link YamlBuilder} file
     * @param value The Value for that path
     */
    public void setDefaultValue(String path, Object value) {
        if (!contains(path)) set(path, value);
    }
}