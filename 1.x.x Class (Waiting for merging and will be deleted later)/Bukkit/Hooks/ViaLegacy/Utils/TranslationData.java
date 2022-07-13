package id.universenetwork.utilities.bukkit.Hooks.ViaLegacy.Utils;

public class TranslationData {
    final int remap, lowestVersion, highestVersion;

    public TranslationData(int remap, int lowestVersion, int highestVersion) {
        this.remap = remap;
        this.lowestVersion = lowestVersion;
        this.highestVersion = highestVersion;
    }

    public int getRemap() {
        return remap;
    }

    public int getLowestVersion() {
        return lowestVersion;
    }

    public int getHighestVersion() {
        return highestVersion;
    }
}