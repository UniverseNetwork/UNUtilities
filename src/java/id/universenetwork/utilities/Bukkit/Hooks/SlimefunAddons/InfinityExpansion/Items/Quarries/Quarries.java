package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Quarries;


import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Gear.Gear;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.MachineLore;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Groups.Groups.ADVANCED_MACHINES;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Groups.Groups.INFINITY_CHEAT;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.InfinityExpansion.getConfig;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Blocks.InfinityWorkbench.TYPE;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Materials.Materials.*;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.SlimefunExtension.VOID_CAPACITOR;
import static io.github.thebusybiscuit.slimefun4.api.MinecraftVersion.MINECRAFT_1_17;
import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE;
import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getMinecraftVersion;
import static org.bukkit.Material.*;

@UtilityClass
public final class Quarries {
    public static final SlimefunItemStack BASIC_QUARRY = new SlimefunItemStack("BASIC_QUARRY", CHISELED_SANDSTONE, "&9Basic Quarry", "&7Automatically mines overworld ores", "", MachineLore.speed(1), MachineLore.energyPerSecond(300));
    public static final SlimefunItemStack ADVANCED_QUARRY = new SlimefunItemStack("ADVANCED_QUARRY", CHISELED_RED_SANDSTONE, "&cAdvanced Quarry", "&7Automatically mines overworld and nether ores", "", MachineLore.speed(2), MachineLore.energyPerSecond(900));
    public static final SlimefunItemStack VOID_QUARRY = new SlimefunItemStack("VOID_QUARRY", CHISELED_NETHER_BRICKS, "&8Void Quarry", "&7Automatically mines overworld and nether ores", "", MachineLore.speed(6), MachineLore.energyPerSecond(3600));
    public static final SlimefunItemStack INFINITY_QUARRY = new SlimefunItemStack("INFINITY_QUARRY", CHISELED_POLISHED_BLACKSTONE, "&bInfinity Quarry", "&7Automatically mines overworld and nether ores", "", MachineLore.speed(64), MachineLore.energyPerSecond(36000));
    public static final SlimefunItemStack DIAMOND_OSCILLATOR = Oscillator.create(DIAMOND);
    public static final SlimefunItemStack REDSTONE_OSCILLATOR = Oscillator.create(REDSTONE);
    public static final SlimefunItemStack LAPIS_OSCILLATOR = Oscillator.create(LAPIS_LAZULI);
    public static final SlimefunItemStack QUARTZ_OSCILLATOR = Oscillator.create(QUARTZ);
    public static final SlimefunItemStack EMERALD_OSCILLATOR = Oscillator.create(EMERALD);

    public static void setup() {
        ConfigurationSection section = Objects.requireNonNull(getConfig().getConfigurationSection("quarry-options.resources"));
        List<Material> outputs = new ArrayList<>();
        boolean coal = section.getBoolean("coal");
        if (coal) {
            outputs.add(COAL);
            outputs.add(COAL);
        }
        if (section.getBoolean("iron")) outputs.add(IRON_INGOT);
        if (section.getBoolean("gold")) outputs.add(GOLD_INGOT);
        if (getMinecraftVersion().isAtLeast(MINECRAFT_1_17) && section.getBoolean("copper")) {
            outputs.add(valueOf("COPPER_INGOT"));
            outputs.add(valueOf("COPPER_INGOT"));
        }
        if (section.getBoolean("redstone")) {
            new Oscillator(REDSTONE_OSCILLATOR).register(addon);
            outputs.add(REDSTONE);
        }
        if (section.getBoolean("lapis")) {
            new Oscillator(LAPIS_OSCILLATOR).register(addon);
            outputs.add(LAPIS_LAZULI);
        }
        if (section.getBoolean("emerald")) {
            new Oscillator(EMERALD_OSCILLATOR).register(addon);
            outputs.add(EMERALD);
        }
        if (section.getBoolean("diamond")) {
            new Oscillator(DIAMOND_OSCILLATOR).register(addon);
            outputs.add(DIAMOND);
        }
        new Quarry(ADVANCED_MACHINES, BASIC_QUARRY, ENHANCED_CRAFTING_TABLE, new ItemStack[]{MAGSTEEL_PLATE, SlimefunItems.CARBONADO_EDGED_CAPACITOR, MAGSTEEL_PLATE, new ItemStack(IRON_PICKAXE), SlimefunItems.GEO_MINER, new ItemStack(IRON_PICKAXE), MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT}, 1, 6, outputs.toArray(new Material[0])).energyPerTick(300).register(addon);
        if (section.getBoolean("quartz")) {
            new Oscillator(QUARTZ_OSCILLATOR).register(addon);
            outputs.add(QUARTZ);
        }
        if (section.getBoolean("netherite")) outputs.add(NETHERITE_INGOT);
        if (section.getBoolean("netherrack")) {
            outputs.add(NETHERRACK);
            outputs.add(NETHERRACK);
        }
        new Quarry(ADVANCED_MACHINES, ADVANCED_QUARRY, ENHANCED_CRAFTING_TABLE, new ItemStack[]{MACHINE_PLATE, SlimefunItems.ENERGIZED_CAPACITOR, MACHINE_PLATE, new ItemStack(DIAMOND_PICKAXE), BASIC_QUARRY, new ItemStack(DIAMOND_PICKAXE), MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT}, 2, 4, outputs.toArray(new Material[0])).energyPerTick(900).register(addon);
        if (coal) outputs.add(COAL);
        new Quarry(ADVANCED_MACHINES, VOID_QUARRY, ENHANCED_CRAFTING_TABLE, new ItemStack[]{VOID_INGOT, VOID_CAPACITOR, VOID_INGOT, new ItemStack(NETHERITE_PICKAXE), ADVANCED_QUARRY, new ItemStack(NETHERITE_PICKAXE), MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT}, 6, 2, outputs.toArray(new Material[0])).energyPerTick(3600).register(addon);
        if (coal) outputs.add(COAL);
        new Quarry(INFINITY_CHEAT, INFINITY_QUARRY, TYPE, new ItemStack[]{null, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, null, MACHINE_PLATE, Gear.PICKAXE, INFINITE_CIRCUIT, INFINITE_CIRCUIT, Gear.PICKAXE, MACHINE_PLATE, MACHINE_PLATE, VOID_QUARRY, INFINITE_CORE, INFINITE_CORE, VOID_QUARRY, MACHINE_PLATE, VOID_INGOT, null, INFINITE_INGOT, INFINITE_INGOT, null, VOID_INGOT, VOID_INGOT, null, INFINITE_INGOT, INFINITE_INGOT, null, VOID_INGOT, VOID_INGOT, null, INFINITE_INGOT, INFINITE_INGOT, null, VOID_INGOT}, 64, 1, outputs.toArray(new Material[0])).energyPerTick(36000).register(addon);
    }
}