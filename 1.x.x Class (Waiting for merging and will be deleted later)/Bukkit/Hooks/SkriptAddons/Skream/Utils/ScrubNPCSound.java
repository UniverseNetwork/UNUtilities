package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Utils;

public class ScrubNPCSound {
    public static String getSound(String exprs) {
        return exprs.replace("_", ".").toLowerCase();
    }
}