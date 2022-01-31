package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Sf;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Util.SfItemStackCreate;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;
import static org.bukkit.Material.ANVIL;
import static org.bukkit.Material.BELL;

public class Machine {
    public static SlimefunItem appraisal;
    public ItemGroup machine;

    public Machine() {
        machine = new ItemGroup(new NamespacedKey(plugin, "Machine"), new CustomItemStack(ANVIL, "&bBump-Machine", "", "&b&k|&b- Click to open >", "", "&7We can still continue to work!", "Zila, Zila, zizi..."));
        machine.register(addon);
        appraisal = new SlimefunItem(machine, new SfItemStackCreate("APPRAISAL", BELL, "&bAppraisal instrument", new String[]{"", "&c&k|&7- Oh, you are a defective product...", ""}), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{BATTERY, ELECTRO_MAGNET, BATTERY, Stuff.mechaGeat_, Stuff.CPU_, Stuff.mechaGeat_, ADVANCED_CIRCUIT_BOARD, COOLING_UNIT, ADVANCED_CIRCUIT_BOARD});
        appraisal.register(addon);
    }
}