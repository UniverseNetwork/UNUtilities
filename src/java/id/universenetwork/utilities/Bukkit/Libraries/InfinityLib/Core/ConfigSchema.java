package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ConfigSchema {
    int getInt(String path, int min, int max);

    double getDouble(String path, double min, double max);

    <T> T getOrSetDefault(@NotNull String path, T value);

    /**
     * Sets the Value for the specified path
     * (If the path does not yet exist)
     *
     * @param path  The path in the {@link YamlBuilder} file
     * @param value The Value for that path
     */
    void setDefaultValue(@NotNull String path, @Nullable Object value);
}