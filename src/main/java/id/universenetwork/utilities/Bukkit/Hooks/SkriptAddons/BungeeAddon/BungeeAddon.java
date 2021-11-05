package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.BungeeAddon;

public class BungeeAddon {
    public static final String CHANNEL = "BungeeCord";

    public BungeeAddon() {
        if (id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons.Enabled("BungeeAddon")) {
            try {
                id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons.addon.loadClasses("id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.BungeeAddon", "Skript");
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            System.out.println(id.universenetwork.utilities.Bukkit.UNUtilities.prefix + " §bSuccessfully Registered §6BungeeAddon §bAddon");
        }
    }
}