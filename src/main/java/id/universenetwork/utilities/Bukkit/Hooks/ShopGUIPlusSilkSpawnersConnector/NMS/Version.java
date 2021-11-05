package id.universenetwork.utilities.Bukkit.Hooks.ShopGUIPlusSilkSpawnersConnector.NMS;

import net.brcdev.shopgui.exception.UnsupportedMinecraftVersionException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Version {
    v1_7,
    v1_8,
    v1_9,
    v1_10,
    v1_11,
    v1_12,
    v1_13,
    v1_14,
    v1_15,
    v1_16,
    v1_17;

    static final Pattern ONE_POINT_VERSION_NUMBER_PATTERN = Pattern.compile("v\\d_(\\d+)");

    public int extractVersionNumber() {
        Matcher matcher = ONE_POINT_VERSION_NUMBER_PATTERN.matcher(this.name());
        matcher.find();
        if (matcher.groupCount() < 1) {
            throw new UnsupportedMinecraftVersionException();
        } else {
            return Integer.parseInt(matcher.group(1));
        }
    }
}
