package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream;

@id.universenetwork.utilities.bukkit.annotations.AddonName("Skream")
public class Skream extends id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Addons {
    @Override
    public void Load() {
        try {
            addon.loadClasses("");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        System.out.println(id.universenetwork.utilities.bukkit.UNUtilities.prefix + " §bSuccessfully Registered §6Skream §bAddon");
    }
}