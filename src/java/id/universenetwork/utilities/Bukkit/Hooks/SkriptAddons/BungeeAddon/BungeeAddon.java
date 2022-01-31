package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.BungeeAddon;

public class BungeeAddon extends id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons {
    public static final String CHANNEL = "BungeeCord";

    public BungeeAddon() {
        super("BungeeAddon");
    }

    @Override
    public void Load() {
        try {
            addon.loadClasses("id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.BungeeAddon", "Skript");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        System.out.println(id.universenetwork.utilities.Bukkit.UNUtilities.prefix + " §bSuccessfully Registered §6BungeeAddon §bAddon");
    }
}