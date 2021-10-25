package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump;

public class Bump {
    public Bump() {
        if (id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.Enabled("Bump")) {
            new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Sf.Armor();
            new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Sf.Food();
            new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Sf.Machine();
            new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Sf.Stuff();
            new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Sf.Tools();
            new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Sf.Weapon();
            id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListener(new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Event.WeaponEvent(), new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Event.MachineEvent(), new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Event.ToolsEvent(), new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Event.FoodEvent());
            System.out.println(id.universenetwork.utilities.Bukkit.UNUtilities.prefix + " §bSuccessfully Registered §dBump §bAddon");
        }
    }
}
