package id.universenetwork.utilities.Bukkit.Templates;

public class Feature {
    String n = getClass().getPackage().getName();
    public final String configPath = "Features." + n.substring(n.lastIndexOf('.') + 1) + ".";

    public void Load() {
    }
}