package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.SFMobDrops;

import java.util.regex.Pattern;

final class Constants {
    public static final Pattern CONSTANT = Pattern.compile("[A-Z0-9_]+");
    public static final Pattern NAMESPACE = Pattern.compile("[a-z0-9_]+:[a-z0-9_]+");

    Constants() {}
}