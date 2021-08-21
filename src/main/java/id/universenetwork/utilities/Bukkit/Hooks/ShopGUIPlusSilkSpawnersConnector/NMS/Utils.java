package id.universenetwork.utilities.Bukkit.Hooks.ShopGUIPlusSilkSpawnersConnector.NMS;

import net.brcdev.shopgui.exception.UnsupportedMinecraftVersionException;
import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static final Pattern MINECRAFT_SHORT_VERSION_PATTERN = Pattern.compile("(v\\d_\\d+)");

    public static boolean isNmsVersionAtLeast(Version requiredNmsVersion) {
        Version currentVersion = readNmsVersion();
        return currentVersion.extractVersionNumber() >= requiredNmsVersion.extractVersionNumber();
    }

    public static boolean isNmsVersionLowerThan(Version maximalNmsVersion) {
        return !isNmsVersionAtLeast(maximalNmsVersion);
    }

    private static Version readNmsVersion() {
        return parseShortNmsVersion(extractNmsVersion(MINECRAFT_SHORT_VERSION_PATTERN));
    }

    private static String extractNmsVersion(Pattern pattern) {
        String nmsClasspath = Bukkit.getServer().getClass().getPackage().getName();
        Matcher matcher = pattern.matcher(nmsClasspath);
        if (matcher.find()) {
            return matcher.group();
        } else {
            throw new UnsupportedMinecraftVersionException();
        }
    }

    private static Version parseShortNmsVersion(String version) {
        try {
            Version nmsVersion = Version.valueOf(version);
            return nmsVersion;
        } catch (IllegalArgumentException var3) {
            throw new UnsupportedMinecraftVersionException();
        }
    }
}
