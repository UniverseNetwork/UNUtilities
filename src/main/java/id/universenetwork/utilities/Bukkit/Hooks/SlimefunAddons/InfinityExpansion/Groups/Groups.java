package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Groups;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Groups.MultiGroup;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Groups.SubGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;


public final class Groups {
    public static final ItemGroup INFINITY = new InfinityGroup(new NamespacedKey(plugin, "infinity_recipes"), new CustomItemStack(Material.RESPAWN_ANCHOR, "&bInfinity &7Recipes"), 3);
    public static final ItemGroup MAIN_MATERIALS = new SubGroup("main_materials", new CustomItemStack(Material.NETHER_STAR, "&bInfinity &7Materials"));
    public static final ItemGroup BASIC_MACHINES = new SubGroup("basic_machines", new CustomItemStack(Material.LOOM, "&9Basic &7Machines"));
    public static final ItemGroup ADVANCED_MACHINES = new SubGroup("advanced_machines", new CustomItemStack(Material.BLAST_FURNACE, "&cAdvanced &7Machines"));
    public static final ItemGroup STORAGE = new SubGroup("storage", new CustomItemStack(Material.BEEHIVE, "&6Storage"));
    public static final ItemGroup MOB_SIMULATION = new SubGroup("mob_simulation", new CustomItemStack(Material.BEACON, "&bMob Simulation"));
    public static final ItemGroup INFINITY_MATERIALS = new SubGroup("infinity_materials", new CustomItemStack(Material.NETHERITE_BLOCK, "&bInfinity &aMaterials"));
    public static final ItemGroup MAIN_CATEGORY = new MultiGroup("main", new CustomItemStack(Material.NETHER_STAR, "&bInfinity &7Expansion"), 3, MAIN_MATERIALS, BASIC_MACHINES, ADVANCED_MACHINES, STORAGE, MOB_SIMULATION, INFINITY_MATERIALS, INFINITY);
    public static final ItemGroup INFINITY_CHEAT = new SubGroup("infinity_cheat", new CustomItemStack(Material.RESPAWN_ANCHOR, "&bInfinity &7Recipes &c- INCORRECT RECIPES"));

    public static void setup() {
        INFINITY.register(addon);
        MAIN_CATEGORY.register(addon);
        INFINITY_CHEAT.register(addon);
    }
}