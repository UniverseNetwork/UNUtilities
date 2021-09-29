package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Items.Blocks;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.MachineLore;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Groups.Groups.BASIC_MACHINES;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Groups.Groups.MAIN_MATERIALS;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Items.Materials.Materials.*;
import static org.bukkit.Material.*;

@UtilityClass
public final class Blocks {
    public static final SlimefunItemStack STRAINER_BASE = new SlimefunItemStack("STRAINER_BASE", SANDSTONE_WALL, "&7Strainer Base");
    public static final SlimefunItemStack ADVANCED_ANVIL = new SlimefunItemStack("ADVANCED_ANVIL", SMITHING_TABLE, "&cAdvanced Anvil", "&7Combines tools and gear enchants and sometimes upgrades them", "&bWorks with Slimefun items", "", MachineLore.energy(100000) + "per use");
    public static final SlimefunItemStack INFINITY_FORGE = new SlimefunItemStack("INFINITY_FORGE", RESPAWN_ANCHOR, "&6Infinity Workbench", "&7Used to craft infinity items", "", MachineLore.energy(10000000) + "per item");

    public static void setup() {
        new StrainerBase(BASIC_MACHINES, STRAINER_BASE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(STICK), new ItemStack(STRING), new ItemStack(STICK), new ItemStack(STICK), new ItemStack(STRING), new ItemStack(STICK), MAGSTEEL, MAGSTEEL, MAGSTEEL,}, 48).register(addon);
        new AdvancedAnvil(MAIN_MATERIALS, ADVANCED_ANVIL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, new ItemStack(ANVIL), MACHINE_PLATE, MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT}, 100000).register(addon);
        new InfinityWorkbench(MAIN_MATERIALS, INFINITY_FORGE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{VOID_INGOT, MACHINE_PLATE, VOID_INGOT, SlimefunItems.ENERGIZED_CAPACITOR, new ItemStack(CRAFTING_TABLE), SlimefunItems.ENERGIZED_CAPACITOR, VOID_INGOT, MACHINE_PLATE, VOID_INGOT}, 10000000).register(addon);
    }
}