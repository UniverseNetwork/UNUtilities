package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.ExtraTools.Lists;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;

import static io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier.ADVANCED;
import static io.github.thebusybiscuit.slimefun4.core.attributes.MachineType.MACHINE;
import static io.github.thebusybiscuit.slimefun4.utils.LoreBuilder.*;
import static org.bukkit.Material.*;

public final class ETItems {
    /* Category */
    public static final ItemGroup extra_tools = new ItemGroup(id.universenetwork.utilities.Bukkit.UNUtilities.createKey("extra_tools"), new io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack(DIAMOND_AXE, "&4Extra Tools"));
    /* Items */
    public static final SlimefunItemStack HAMMER = new SlimefunItemStack("HAMMER", IRON_PICKAXE, "&cHammer", "", "&9Pulverizes blocks");
    /* Machines */
    public static final SlimefunItemStack GOLD_TRANSMUTER = new SlimefunItemStack("GOLD_TRANSMUTER", YELLOW_TERRACOTTA, "&6Gold Transmuter", "", machine(ADVANCED, MACHINE), powerBuffer(256), powerPerSecond(18));
    public static final SlimefunItemStack ELECTRIC_COMPOSTER = new SlimefunItemStack("ELECTRIC_COMPOSTER", MAGENTA_TERRACOTTA, "&cElectric Composter", "", machine(ADVANCED, MACHINE), "&8\u21E8 &7Speed: 1x", powerBuffer(256), powerPerSecond(18));
    public static final SlimefunItemStack ELECTRIC_COMPOSTER_2 = new SlimefunItemStack("ELECTRIC_COMPOSTER_2", MAGENTA_TERRACOTTA, "&cElectric Composter &7(&eII&7)", "", machine(ADVANCED, MACHINE), "&8\u21E8 &7Speed: 4x", powerBuffer(256), powerPerSecond(50));
    public static final SlimefunItemStack COBBLESTONE_GENERATOR = new SlimefunItemStack("COBBLESTONE_GENERATOR", POLISHED_ANDESITE, "&cCobblestone Generator", "", machine(ADVANCED, MACHINE), powerBuffer(256), powerPerSecond(36));
    public static final SlimefunItemStack VAPORIZER = new SlimefunItemStack("VAPORIZER", RED_STAINED_GLASS, "&cVaporizer", "", machine(ADVANCED, MACHINE), powerBuffer(256), powerPerSecond(32));
    public static final SlimefunItemStack CONCRETE_FACTORY = new SlimefunItemStack("CONCRETE_FACTORY", BLACK_CONCRETE, "&4Concrete Factory", "", machine(ADVANCED, MACHINE), powerBuffer(256), powerPerSecond(16));
    public static final SlimefunItemStack PULVERIZER = new SlimefunItemStack("PULVERIZER", ORANGE_TERRACOTTA, "&cPulverizer", "", machine(ADVANCED, MACHINE), "&8\u21E8 &7Speed: 1x", powerBuffer(256), powerPerSecond(18));
}