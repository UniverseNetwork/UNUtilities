package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Core;

import org.apache.commons.lang.StringUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class ConfigBuilder extends YamlConfiguration {
    final YamlConfiguration defaults = new YamlConfiguration();
    final Map<String, String> comments = new HashMap<>();
    final File file;

    public ConfigBuilder(String name) {
        file = new File(plugin.getDataFolder(), name);
        super.defaults = defaults;
        loadDefaults(name);
    }

    public ConfigBuilder(String name, String path) {
        file = new File(plugin.getDataFolder() + path, name);
        super.defaults = defaults;
        loadDefaults(name);
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
    public void save(@Nonnull File file) throws IOException {
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

    @Nonnull
    @Override
    public YamlConfiguration getDefaults() {
        return defaults;
    }

    @Nullable
    String getComment(String key) {
        return comments.get(key);
    }

    @Nonnull
    @Override
    protected String buildHeader() {
        return "";
    }

    @Nonnull
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

    String readDefaults(@Nonnull InputStream inputStream) throws IOException {
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
            } else if (pathBuilder.inMainSection()) {
                // The main section should always have spaces between keys
                comments.put(pathBuilder.build(), "\n");
            }
        }
        input.close();
        return yamlBuilder.toString();
    }
}
