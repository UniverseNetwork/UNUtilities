package id.universenetwork.utilities.Bungee.Manager;

import id.universenetwork.utilities.Bungee.UNUtilities;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static net.md_5.bungee.config.ConfigurationProvider.getProvider;

public class Settings {
    final String configName = "settings.yml";
    net.md_5.bungee.config.Configuration c;

    // Finds and Generates the config file
    public Settings(boolean Reload) {
        if (Reload) System.out.println(UNUtilities.prefix + " §eReloading Settings Manager...");
        else UNUtilities.plugin.getLogger().info("§ePreparing Settings Manager...");
        try {
            if (!UNUtilities.plugin.getDataFolder().exists()) UNUtilities.plugin.getDataFolder().mkdir();
            File configFile = new File(UNUtilities.plugin.getDataFolder(), configName);
            if (!configFile.exists())
                try (java.io.InputStream in = UNUtilities.plugin.getResourceAsStream(configName)) {
                    java.nio.file.Files.copy(in, configFile.toPath());
                }
            c = getProvider(YamlConfiguration.class).load(configFile);
            if (Reload) System.out.println(UNUtilities.prefix + " §aSettings Manager have been reloaded");
        } catch (IOException e) {
            throw new RuntimeException("Unable to load configuration", e);
        }
    }

    public void save() {
        try {
            File configFile = new File(UNUtilities.plugin.getDataFolder(), configName);
            if (configFile.exists()) getProvider(YamlConfiguration.class).save(c, configFile);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save configuration", e);
        }
    }

    public Configuration getSection(String Path) {
        return c.getSection(Path);
    }

    public void set(String Path, Object Value) {
        c.set(Path, Value);
        save();
    }

    public Object get(String Path) {
        return c.get(Path);
    }

    public Object get(String Path, Object Default) {
        return c.get(Path, Default);
    }

    @SuppressWarnings("unchecked")
    public <T> T getOrSetDefault(@NotNull String path, T value) {
        Object val = get(path);
        if (value.getClass().isInstance(val)) return (T) val;
        else {
            set(path, value);
            return value;
        }
    }

    public Object getDefault(String Path) {
        return c.getDefault(Path);
    }

    public String getString(String Path) {
        return c.getString(Path);
    }

    public String getString(String Path, String Default) {
        return c.getString(Path, Default);
    }

    public List<String> getStringList(String Path) {
        return c.getStringList(Path);
    }

    public boolean getBoolean(String Path) {
        return c.getBoolean(Path);
    }

    public boolean getBoolean(String Path, boolean Default) {
        return c.getBoolean(Path, Default);
    }

    public List<Boolean> getBooleanList(String Path) {
        return c.getBooleanList(Path);
    }

    public int getInt(String Path) {
        return c.getInt(Path);
    }

    public int getInt(String Path, int Default) {
        return c.getInt(Path, Default);
    }

    public int getInt(String path, int min, int max) {
        int val = getInt(path);
        if (val < min || val > max) set(path, val = (int) getDefault(path));
        return val;
    }

    public List<Integer> getIntList(String Path) {
        return c.getIntList(Path);
    }

    public double getDouble(String Path) {
        return c.getDouble(Path);
    }

    public double getDouble(String Path, double Default) {
        return c.getDouble(Path, Default);
    }

    public double getDouble(String path, double min, double max) {
        double val = getDouble(path);
        if (val < min || val > max) set(path, val = (double) getDefault(path));
        return val;
    }

    public List<Double> getDoubleList(String Path) {
        return c.getDoubleList(Path);
    }

    public long getLong(String Path) {
        return c.getLong(Path);
    }

    public long getLong(String Path, long Default) {
        return c.getLong(Path, Default);
    }

    public List<Long> getLongList(String Path) {
        return c.getLongList(Path);
    }

    public byte getByte(String Path) {
        return c.getByte(Path);
    }

    public byte getByte(String Path, byte Default) {
        return c.getByte(Path, Default);
    }

    public List<Byte> getByteList(String Path) {
        return c.getByteList(Path);
    }

    public float getFloat(String Path) {
        return c.getFloat(Path);
    }

    public float getFloat(String Path, float Default) {
        return c.getFloat(Path, Default);
    }

    public List<Float> getFloatList(String Path) {
        return c.getFloatList(Path);
    }

    public char getChar(String Path) {
        return c.getChar(Path);
    }

    public char getChar(String Path, char Default) {
        return c.getChar(Path, Default);
    }

    public List<Character> getCharList(String Path) {
        return c.getCharList(Path);
    }

    public short getShort(String Path) {
        return c.getShort(Path);
    }

    public short getShort(String Path, short Default) {
        return c.getShort(Path, Default);
    }

    public List<Short> getShortList(String Path) {
        return c.getShortList(Path);
    }

    public List<?> getList(String Path) {
        return c.getList(Path);
    }

    public List<?> getList(String Path, List<?> Default) {
        return c.getList(Path, Default);
    }
}