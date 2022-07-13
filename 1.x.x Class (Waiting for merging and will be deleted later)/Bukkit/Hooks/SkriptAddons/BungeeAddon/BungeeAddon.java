package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.BungeeAddon;

@id.universenetwork.utilities.bukkit.annotations.AddonName("BungeeAddon")
public class BungeeAddon extends id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Addons {
    public static final String CHANNEL = "BungeeCord";

    @Override
    public void Load() {
        try {
            addon.loadClasses("id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.BungeeAddon", "Skript");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        System.out.println(id.universenetwork.utilities.bukkit.UNUtilities.prefix + " §bSuccessfully Registered §6BungeeAddon §bAddon");
    }
}