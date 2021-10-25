package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Sf;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Util.Register;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Util.SfItemStackCreate;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.*;
import static org.bukkit.Material.*;

public class Stuff {
    public static SlimefunItemStack sunEnergy_;
    public static SlimefunItemStack mechaGeat_;
    public static SlimefunItemStack oldCoin_;
    public static SlimefunItemStack make_;
    public static SlimefunItemStack oldCPU_;
    public static SlimefunItemStack CPU_;
    public static SlimefunItemStack soulPaper_;
    public static SlimefunItemStack ksf_;
    public static SlimefunItemStack waterSugar_;
    public static SlimefunItemStack peachwood_;
    public static SlimefunItemStack upDatePower_ = new SfItemStackCreate("UPDATE_POWER", ZOMBIE_HEAD, "&eUpgrade Core", new String[]{"", "&b&k|&b- Used to upgrade some weird items...", ""});
    public ItemGroup stuff;

    static {
        sunEnergy_ = new SfItemStackCreate("SUN_ENERGY", SUNFLOWER, "&ePhotosynthetic Energy", new String[]{"", "&b&k|&b- Mutated plants will also photosynthesize...", ""});
        mechaGeat_ = new SfItemStackCreate("MECHA_GEAR", ENDER_PEARL, "&eMechanical Gear", new String[]{"", "&b&k|&b- Something eliminated by a large factory...", ""});
        oldCoin_ = new SfItemStackCreate("OLD_COIN", GOLD_NUGGET, "&eBroken Gold Coin", new String[]{"", "&b&k|&b- What can I buy...", ""});
        make_ = new SfItemStackCreate("MAKE", DIAMOND, "&eComputer Technology Core", new String[]{"", "&b&k|&b- What is it used for...", ""});
        oldCPU_ = new SfItemStackCreate("OLD_CPU", PRISMARINE_CRYSTALS, "&eBroken CPU", new String[]{"", "&b&k|&b- From which computer it was removed...", ""});
        CPU_ = new SfItemStackCreate("CPU", DIAMOND, "&eCPU", new String[]{"", "&b&k|&b- Intact...", ""});
        soulPaper_ = new SfItemStackCreate("SOUL_PAPER", PAPER, "&eSoul Talisman", new String[]{"", "&b&k|&b- The power of the soul", ""});
        ksf_ = new SfItemStackCreate("KSF_STUFF", BEETROOT_SEEDS, "&eInstant Noodle Seasoning", new String[]{"", "&b&k|&b- Sizzle~ It's spicy", ""});
        waterSugar_ = new SfItemStackCreate("WATER_SUGAR", SUGAR, "&eSoda Candy", new String[]{"", "&b&k|&b- Sprite? Or Coca-Cola?", ""});
        peachwood_ = new SfItemStackCreate("PEACH_WOOD", STICK, "&ePeach Wood", new String[]{"", "&b&k|&b- Refuge From Evil Spirits", ""});
    }

    public Stuff() {
        stuff = new ItemGroup(new NamespacedKey(plugin, "Stuff"), new CustomItemStack(NETHER_STAR, "&bBump-Magic Item", "", "&b&k|&b- Click to open >", "", "&7Some items that have mutated in life!", "They may contain unknown power..."));
        stuff.register(addon);
        SlimefunItem upDatePower = new SlimefunItem(stuff, upDatePower_, MAGIC_WORKBENCH, new ItemStack[]{SlimefunItems.POWER_CRYSTAL, SlimefunItems.LAVA_CRYSTAL, SlimefunItems.POWER_CRYSTAL, SlimefunItems.LAVA_CRYSTAL, CPU_, SlimefunItems.LAVA_CRYSTAL, SlimefunItems.GOLD_24K, SlimefunItems.GOLD_24K, SlimefunItems.GOLD_24K});
        SlimefunItem peachwood = new SlimefunItem(stuff, peachwood_, ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(ACACIA_WOOD), new ItemStack(BIRCH_WOOD), new ItemStack(DARK_OAK_WOOD)});
        SlimefunItem soulPaper = new SlimefunItem(stuff, soulPaper_, ANCIENT_ALTAR, new ItemStack[]{SlimefunItems.MAGIC_LUMP_1, SlimefunItems.ESSENCE_OF_AFTERLIFE, SlimefunItems.MAGIC_LUMP_1, SlimefunItems.SOULBOUND_RUNE, SlimefunItems.SOULBOUND_RUNE, SlimefunItems.SOULBOUND_RUNE, SlimefunItems.MAGIC_LUMP_1, SlimefunItems.ESSENCE_OF_AFTERLIFE, SlimefunItems.MAGIC_LUMP_1});
        SlimefunItem ksf = new SlimefunItem(stuff, ksf_, ENHANCED_CRAFTING_TABLE, new ItemStack[]{SlimefunItems.SALT, SlimefunItems.SALT, SlimefunItems.SALT, SlimefunItems.CARROT_FERTILIZER, SlimefunItems.CARROT_FERTILIZER, SlimefunItems.CARROT_FERTILIZER, SlimefunItems.SALT, SlimefunItems.SALT, SlimefunItems.SALT});
        SlimefunItem watersugar = new SlimefunItem(stuff, waterSugar_, PRESSURE_CHAMBER, new ItemStack[]{SlimefunItems.MAGIC_SUGAR});
        SlimefunItem sunEnergy = new SlimefunItem(stuff, sunEnergy_, MAGIC_WORKBENCH, new ItemStack[]{new ItemStack(CHORUS_FLOWER, 1), new ItemStack(SUNFLOWER, 1), new ItemStack(CHORUS_FLOWER, 1)});
        SlimefunItem mechaGeat = new SlimefunItem(stuff, mechaGeat_, ENHANCED_CRAFTING_TABLE, new ItemStack[]{SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.COPPER_WIRE, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD});
        SlimefunItem oldCoin = new SlimefunItem(stuff, oldCoin_, ARMOR_FORGE, new ItemStack[]{null, null, null, null, new CustomItemStack(DIAMOND_SHOVEL, "&e&o获取方式: ", "&7&o摸金铲也许对他有点作用...")});
        SlimefunItem make = new SlimefunItem(stuff, make_, ENHANCED_CRAFTING_TABLE, new ItemStack[]{SlimefunItems.BATTERY, SlimefunItems.BATTERY, SlimefunItems.BATTERY, SlimefunItems.COOLING_UNIT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.COOLING_UNIT, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD});
        SlimefunItem oldCPU = new SlimefunItem(stuff, oldCPU_, ENHANCED_CRAFTING_TABLE, new ItemStack[]{SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE, make.getItem(), SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE});
        SlimefunItem CPU = new SlimefunItem(stuff, CPU_, COMPRESSOR, new ItemStack[]{oldCPU.getItem()});
        new Register(upDatePower, peachwood, soulPaper, ksf, watersugar, sunEnergy, mechaGeat, oldCoin, make, oldCPU, CPU);
    }
}