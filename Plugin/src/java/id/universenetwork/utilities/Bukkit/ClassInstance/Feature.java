package id.universenetwork.utilities.Bukkit.ClassInstance;

public class Feature {
    String n = getClass().getPackage().getName();
    public final String configPath = "Features." + n.substring(n.lastIndexOf('.') + 1) + ".";

    public void Load() {
    }
}