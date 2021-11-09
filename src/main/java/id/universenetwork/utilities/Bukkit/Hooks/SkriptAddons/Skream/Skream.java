package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream;

public class Skream extends id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons {
    public Skream() {
        super("Skream");
    }

    @Override
    public void Load() {
        try {
            addon.loadClasses("");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        System.out.println(id.universenetwork.utilities.Bukkit.UNUtilities.prefix + " §bSuccessfully Registered §6Skream §bAddon");
    }
}