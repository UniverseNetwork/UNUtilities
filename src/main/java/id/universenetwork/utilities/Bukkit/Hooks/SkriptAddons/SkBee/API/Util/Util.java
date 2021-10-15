package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkBee.API.Util;

import ch.njol.skript.Skript;
import net.md_5.bungee.api.ChatColor;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ch.njol.skript.Skript.error;
import static ch.njol.skript.log.ErrorQuality.SEMANTIC_ERROR;
import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkBee.SkBee.getInstance;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getConsoleSender;

public class Util {
    static final Pattern HEX_PATTERN = Pattern.compile("<#([A-Fa-f0-9]){6}>");

    public static String getColString(String string) {
        Matcher matcher = HEX_PATTERN.matcher(string);
        if (Skript.isRunningMinecraft(1, 16)) {
            while (matcher.find()) {
                final ChatColor hexColor = ChatColor.of(matcher.group().substring(1, matcher.group().length() - 1));
                final String before = string.substring(0, matcher.start());
                final String after = string.substring(matcher.end());
                string = before + hexColor + after;
                matcher = HEX_PATTERN.matcher(string);
            }
        } else string = HEX_PATTERN.matcher(string).replaceAll("");
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static void log(String log) {
        getConsoleSender().sendMessage(getColString(prefix + " " + log));
    }

    public static void log(String format, Object... objects) {
        log(String.format(format, objects));
    }

    public static void skriptError(String error) {
        error(getColString(prefix + " " + error), SEMANTIC_ERROR);
    }

    public static void skriptError(String format, Object... objects) {
        skriptError(String.format(format, objects));
    }

    public static void debug(String debug) {
        if (getInstance().getPluginConfig().SETTINGS_DEBUG)
            getConsoleSender().sendMessage(getColString(prefix + " " + debug));
    }

    public static void debug(String format, Object... objects) {
        debug(String.format(format, objects));
    }

    /**
     * Convert a UUID to an int array
     * <p>Used for Minecraft 1.16+</p>
     *
     * @param uuid String UUID to convert
     * @return int array from UUID
     */
    public static int[] uuidToIntArray(String uuid) {
        return uuidToIntArray(UUID.fromString(uuid));
    }

    /**
     * Convert a UUID to an int array
     * <p>Used for Minecraft 1.16+</p>
     *
     * @param uuid UUID to convert
     * @return int array from UUID
     */
    public static int[] uuidToIntArray(UUID uuid) {
        long most = uuid.getMostSignificantBits();
        long least = uuid.getLeastSignificantBits();
        return new int[]{(int) (most >> 32), (int) most, (int) (least >> 32), (int) least};
    }
}