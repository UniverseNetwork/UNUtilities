package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Groups.Groups;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Blocks.InfinityWorkbench;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Materials.Materials;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.MachineLore;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.Capacitor;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines.ChargingBench;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines.ElectricSmeltery;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines.enchanting.AutoDisenchanter;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines.enchanting.AutoEnchanter;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.reactors.NetherStarReactor;
import io.github.thebusybiscuit.slimefun4.implementation.items.geo.GEOMiner;
import io.github.thebusybiscuit.slimefun4.utils.HeadTexture;
import lombok.experimental.UtilityClass;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.RayTraceResult;
import org.jetbrains.annotations.NotNull;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.slimefunTickCount;
import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Scheduler.run;

@UtilityClass
public final class SlimefunExtension {
    static final int INFINITY_CAPACITY = 2000000000;
    static final int VOID_CAPACITY = 16000000;
    static final int ADVANCED_GEO_SPEED = 4;
    static final int ADVANCED_GEO_ENERGY = 120;
    static final int ADVANCED_EN_SPEED = 5;
    static final int ADVANCED_EN_ENERGY = 180;
    static final int ADVANCED_DIS_SPEED = 5;
    static final int ADVANCED_DIS_ENERGY = 180;
    static final int INFINITY_EN_SPEED = 75;
    static final int INFINITY_EN_ENERGY = 12000;
    static final int INFINITY_DIS_SPEED = 90;
    static final int INFINITY_DIS_ENERGY = 12000;
    static final int ADVANCED_CHARGER_SPEED = 30;
    static final int ADVANCED_CHARGER_ENERGY = 180;
    static final int INFINITY_CHARGER_SPEED = 6000;
    static final int INFINITY_CHARGER_ENERGY = 60000;
    static final int ADVANCED_SMELTERY_ENERGY = 240;
    static final int ADVANCED_SMELTERY_SPEED = 24;
    static final int STAR_ENERGY = 1800;
    static final int STAR_BUFFER = 90000;
    public static final SlimefunItemStack ADVANCED_GEO_MINER = new SlimefunItemStack("ADVANCED_GEO_MINER", HeadTexture.GEO_MINER, "&cAdvanced &fGeoMiner", "&7A faster geo-miner", "", MachineLore.speed(SlimefunExtension.ADVANCED_GEO_SPEED), MachineLore.energyPerSecond(SlimefunExtension.ADVANCED_GEO_ENERGY));
    public static final SlimefunItemStack ADVANCED_SMELTERY = new SlimefunItemStack("ADVANCED_SMELTERY", Material.FURNACE, "&cAdvanced &7Smeltery", "&7A faster smeltery", "", MachineLore.speed(ADVANCED_SMELTERY_SPEED), MachineLore.energyPerSecond(ADVANCED_SMELTERY_ENERGY));
    public static final SlimefunItemStack ADVANCED_CHARGER = new SlimefunItemStack("ADVANCED_CHARGER", Material.HONEYCOMB_BLOCK, "&cAdvanced Charger", "&7Quickly charges items", "", MachineLore.speed(SlimefunExtension.ADVANCED_CHARGER_SPEED), MachineLore.energyPerSecond(SlimefunExtension.ADVANCED_CHARGER_ENERGY));
    public static final SlimefunItemStack INFINITY_CHARGER = new SlimefunItemStack("INFINITY_CHARGER", Material.SEA_LANTERN, "&bInfinity Charger", "&7Instantly charges items", "", MachineLore.speed(SlimefunExtension.INFINITY_CHARGER_SPEED), MachineLore.energy(SlimefunExtension.INFINITY_CHARGER_ENERGY) + "per use");
    public static final SlimefunItemStack ADVANCED_NETHER_STAR_REACTOR = new SlimefunItemStack("ADVANCED_NETHER_STAR_REACTOR", HeadTexture.NETHER_STAR_REACTOR, "&cAdvanced Nether Star Reactor", "&fRuns on Nether Stars", "&bMust be surrounded by Water", "&bMust be supplied with Nether Ice Coolant Cells", "&4Causes nearby Entities to get Withered", "", MachineLore.energyBuffer(SlimefunExtension.STAR_BUFFER), MachineLore.energyPerSecond(SlimefunExtension.STAR_ENERGY));
    public static final SlimefunItemStack ADVANCED_ENCHANTER = new SlimefunItemStack("ADVANCED_ENCHANTER", Material.ENCHANTING_TABLE, "&cAdvanced Enchanter", "", MachineLore.speed(SlimefunExtension.ADVANCED_EN_SPEED), MachineLore.energyPerSecond(SlimefunExtension.ADVANCED_EN_ENERGY));
    public static final SlimefunItemStack ADVANCED_DISENCHANTER = new SlimefunItemStack("ADVANCED_DISENCHANTER", Material.ENCHANTING_TABLE, "&cAdvanced Disenchanter", "", MachineLore.speed(SlimefunExtension.ADVANCED_DIS_SPEED), MachineLore.energyPerSecond(SlimefunExtension.ADVANCED_DIS_ENERGY));
    public static final SlimefunItemStack INFINITY_ENCHANTER = new SlimefunItemStack("INFINITY_ENCHANTER", Material.ENCHANTING_TABLE, "&bInfinity Enchanter", "", MachineLore.speed(SlimefunExtension.INFINITY_EN_SPEED), MachineLore.energy(SlimefunExtension.INFINITY_EN_ENERGY) + "per use");
    public static final SlimefunItemStack INFINITY_DISENCHANTER = new SlimefunItemStack("INFINITY_DISENCHANTER", Material.ENCHANTING_TABLE, "&bInfinity Disenchanter", "", MachineLore.speed(SlimefunExtension.INFINITY_DIS_SPEED), MachineLore.energy(SlimefunExtension.INFINITY_DIS_ENERGY) + "per use");
    public static final SlimefunItemStack INFINITY_CAPACITOR = new SlimefunItemStack("INFINITY_CAPACITOR", HeadTexture.CAPACITOR_25, "&bInfinite Capacitor", "&c&oDo not use more than ", "&c&o1 per energy network", "", "&8\u21E8 &e\u26A1 " + MachineLore.format(INFINITY_CAPACITY) + " &7J Capacity");
    public static final SlimefunItemStack VOID_CAPACITOR = new SlimefunItemStack("VOID_CAPACITOR", HeadTexture.CAPACITOR_25, "&8Void Capacitor", "", "&8\u21E8 &e\u26A1 " + MachineLore.format(VOID_CAPACITY) + " &7J Capacity");

    public static void setup() {
        new Capacitor(Groups.INFINITY_CHEAT, INFINITY_CAPACITY, INFINITY_CAPACITOR, InfinityWorkbench.TYPE, new ItemStack[]{null, Materials.INFINITE_INGOT, Materials.VOID_INGOT, Materials.VOID_INGOT, Materials.INFINITE_INGOT, null, null, Materials.INFINITE_INGOT, Materials.INFINITE_CIRCUIT, Materials.INFINITE_CIRCUIT, Materials.INFINITE_INGOT, null, null, Materials.INFINITE_INGOT, SlimefunItems.ENERGIZED_CAPACITOR, SlimefunItems.ENERGIZED_CAPACITOR, Materials.INFINITE_INGOT, null, null, Materials.INFINITE_INGOT, SlimefunItems.ENERGIZED_CAPACITOR, SlimefunItems.ENERGIZED_CAPACITOR, Materials.INFINITE_INGOT, null, null, Materials.INFINITE_INGOT, Materials.INFINITE_CIRCUIT, Materials.INFINITE_CIRCUIT, Materials.INFINITE_INGOT, null, null, Materials.INFINITE_INGOT, Materials.VOID_INGOT, Materials.VOID_INGOT, Materials.INFINITE_INGOT, null}).register(addon);
        new Capacitor(Groups.ADVANCED_MACHINES, VOID_CAPACITY, VOID_CAPACITOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{Materials.VOID_INGOT, Materials.REDSTONE_SINGULARITY, Materials.VOID_INGOT, Materials.VOID_INGOT, SlimefunItems.ENERGIZED_CAPACITOR, Materials.VOID_INGOT, Materials.VOID_INGOT, Materials.REDSTONE_SINGULARITY, Materials.VOID_INGOT}).register(addon);
        new AutoEnchanter(Groups.ADVANCED_MACHINES, ADVANCED_ENCHANTER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{Materials.MAGSTEEL, Materials.MAGSTEEL, Materials.MAGSTEEL, Materials.MAGSTEEL_PLATE, SlimefunItems.AUTO_ENCHANTER, Materials.MAGSTEEL_PLATE, Materials.MACHINE_CIRCUIT, Materials.MACHINE_CORE, Materials.MACHINE_CIRCUIT}) {
            @Override
            public ItemStack getProgressBar() {
                return new ItemStack(Material.NETHERITE_CHESTPLATE);
            }
        }.setCapacity(ADVANCED_EN_ENERGY).setEnergyConsumption(ADVANCED_EN_ENERGY).setProcessingSpeed(ADVANCED_EN_SPEED).register(addon);
        new AutoDisenchanter(Groups.ADVANCED_MACHINES, ADVANCED_DISENCHANTER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{Materials.MAGSTEEL, Materials.MAGSTEEL, Materials.MAGSTEEL, Materials.MAGSTEEL_PLATE, SlimefunItems.AUTO_DISENCHANTER, Materials.MAGSTEEL_PLATE, Materials.MACHINE_CIRCUIT, Materials.MACHINE_CORE, Materials.MACHINE_CIRCUIT}) {
            @Override
            public ItemStack getProgressBar() {
                return new ItemStack(Material.ENCHANTED_BOOK);
            }
        }.setCapacity(ADVANCED_DIS_ENERGY).setEnergyConsumption(ADVANCED_DIS_ENERGY).setProcessingSpeed(ADVANCED_DIS_SPEED).register(addon);
        new AutoEnchanter(Groups.INFINITY_CHEAT, INFINITY_ENCHANTER, InfinityWorkbench.TYPE, new ItemStack[]{null, null, null, null, null, null, Materials.VOID_INGOT, null, null, null, null, Materials.VOID_INGOT, Materials.VOID_INGOT, Materials.VOID_INGOT, ADVANCED_ENCHANTER, ADVANCED_ENCHANTER, Materials.VOID_INGOT, Materials.VOID_INGOT, Materials.MACHINE_PLATE, Materials.VOID_INGOT, Materials.INFINITE_CIRCUIT, Materials.INFINITE_CIRCUIT, Materials.VOID_INGOT, Materials.MACHINE_PLATE, Materials.MACHINE_PLATE, Materials.VOID_INGOT, Materials.INFINITE_CORE, Materials.INFINITE_CORE, Materials.VOID_INGOT, Materials.MACHINE_PLATE, Materials.MACHINE_PLATE, Materials.INFINITE_INGOT, Materials.INFINITE_INGOT, Materials.INFINITE_INGOT, Materials.INFINITE_INGOT, Materials.MACHINE_PLATE}) {
            @Override
            public ItemStack getProgressBar() {
                return new ItemStack(Material.NETHERITE_CHESTPLATE);
            }
        }.setCapacity(INFINITY_EN_ENERGY).setEnergyConsumption(INFINITY_EN_ENERGY).setProcessingSpeed(INFINITY_EN_SPEED).register(addon);
        new AutoDisenchanter(Groups.INFINITY_CHEAT, INFINITY_DISENCHANTER, InfinityWorkbench.TYPE, new ItemStack[]{null, null, null, null, null, null, Materials.VOID_INGOT, null, null, null, null, Materials.VOID_INGOT, Materials.VOID_INGOT, Materials.VOID_INGOT, ADVANCED_DISENCHANTER, ADVANCED_DISENCHANTER, Materials.VOID_INGOT, Materials.VOID_INGOT, Materials.MACHINE_PLATE, Materials.VOID_INGOT, Materials.INFINITE_CIRCUIT, Materials.INFINITE_CIRCUIT, Materials.VOID_INGOT, Materials.MACHINE_PLATE, Materials.MACHINE_PLATE, Materials.VOID_INGOT, Materials.INFINITE_CORE, Materials.INFINITE_CORE, Materials.VOID_INGOT, Materials.MACHINE_PLATE, Materials.MACHINE_PLATE, Materials.INFINITE_INGOT, Materials.INFINITE_INGOT, Materials.INFINITE_INGOT, Materials.INFINITE_INGOT, Materials.MACHINE_PLATE}) {
            @Override
            public ItemStack getProgressBar() {
                return new ItemStack(Material.ENCHANTED_BOOK);
            }
        }.setCapacity(INFINITY_DIS_ENERGY).setEnergyConsumption(INFINITY_DIS_ENERGY).setProcessingSpeed(INFINITY_DIS_SPEED).register(addon);
        new ChargingBench(Groups.ADVANCED_MACHINES, ADVANCED_CHARGER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{Materials.MAGSTEEL_PLATE, Materials.MACHINE_CIRCUIT, Materials.MAGSTEEL_PLATE, Materials.MACHINE_CIRCUIT, SlimefunItems.CHARGING_BENCH, Materials.MACHINE_CIRCUIT, Materials.MAGSTEEL_PLATE, Materials.MACHINE_CORE, Materials.MAGSTEEL_PLATE,}).setCapacity(ADVANCED_CHARGER_ENERGY).setEnergyConsumption(ADVANCED_CHARGER_ENERGY).setProcessingSpeed(ADVANCED_CHARGER_SPEED).register(addon);
        new ChargingBench(Groups.INFINITY_CHEAT, INFINITY_CHARGER, InfinityWorkbench.TYPE, new ItemStack[]{null, null, null, null, null, null, Materials.VOID_INGOT, Materials.MACHINE_CIRCUIT, Materials.MACHINE_CIRCUIT, Materials.MACHINE_CIRCUIT, Materials.MACHINE_CIRCUIT, Materials.VOID_INGOT, Materials.VOID_INGOT, Materials.MACHINE_CIRCUIT, ADVANCED_CHARGER, ADVANCED_CHARGER, Materials.MACHINE_CIRCUIT, Materials.VOID_INGOT, Materials.VOID_INGOT, Materials.MACHINE_CIRCUIT, ADVANCED_CHARGER, ADVANCED_CHARGER, Materials.MACHINE_CIRCUIT, Materials.VOID_INGOT, Materials.VOID_INGOT, Materials.INFINITE_CIRCUIT, Materials.INFINITE_CORE, Materials.INFINITE_CORE, Materials.INFINITE_CIRCUIT, Materials.VOID_INGOT, Materials.INFINITE_INGOT, Materials.INFINITE_INGOT, Materials.INFINITE_INGOT, Materials.INFINITE_INGOT, Materials.INFINITE_INGOT, Materials.INFINITE_INGOT}).setCapacity(INFINITY_CHARGER_ENERGY).setEnergyConsumption(INFINITY_CHARGER_ENERGY).setProcessingSpeed(INFINITY_CHARGER_SPEED).register(addon);
        new GEOMiner(Groups.ADVANCED_MACHINES, ADVANCED_GEO_MINER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{Materials.MAGSTEEL_PLATE, Materials.MAGSTEEL_PLATE, Materials.MAGSTEEL_PLATE, SlimefunItems.COBALT_PICKAXE, SlimefunItems.GEO_MINER, SlimefunItems.COBALT_PICKAXE, Materials.MACHINE_CIRCUIT, Materials.MACHINE_CORE, Materials.MACHINE_CIRCUIT}).setCapacity(ADVANCED_GEO_ENERGY).setProcessingSpeed(ADVANCED_GEO_SPEED).setEnergyConsumption(ADVANCED_GEO_ENERGY).register(addon);
        new NetherStarReactor(Groups.ADVANCED_MACHINES, ADVANCED_NETHER_STAR_REACTOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.WITHER_PROOF_GLASS, Materials.MACHINE_CIRCUIT, SlimefunItems.NETHER_STAR_REACTOR, Materials.MACHINE_CIRCUIT, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_OBSIDIAN,}) {
            @Override
            public int getCapacity() {
                return STAR_BUFFER;
            }

            @Override
            public int getEnergyProduction() {
                return STAR_ENERGY;
            }

            @Override
            protected void registerDefaultFuelTypes() {
                registerFuel(new MachineFuel(600, new ItemStack(Material.NETHER_STAR)));
            }

            @Override
            public void extraTick(@NotNull Location l) {
                if (slimefunTickCount % 4 != 0) return;
                run(() -> {
                    Location check = l.clone().add(0, 1, 0);
                    World w = check.getWorld();
                    if (w == null) return;
                    boolean checkWitherProof = check.getBlock().getType() == Material.AIR;
                    for (Entity entity : w.getNearbyEntities(check, 8, 8, 8))
                        if (entity instanceof LivingEntity && entity.isValid()) {
                            if (checkWitherProof) {
                                RayTraceResult result = w.rayTraceBlocks(check, entity.getLocation().subtract(check).toVector(), 16);
                                if (result != null) {
                                    Block hit = result.getHitBlock();
                                    if (hit != null) {
                                        String id = BlockStorage.getLocationInfo(hit.getLocation(), "id");
                                        if (id != null && id.contains("WITHER_PROOF")) continue;
                                    }
                                }
                            }
                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 2));
                        }
                });
            }
        }.register(addon);
        new ElectricSmeltery(Groups.ADVANCED_MACHINES, ADVANCED_SMELTERY, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{SlimefunItems.ELECTRIC_SMELTERY_2, SlimefunItems.ELECTRIC_SMELTERY_2, SlimefunItems.ELECTRIC_SMELTERY_2, SlimefunItems.ELECTRIC_SMELTERY_2, SlimefunItems.ELECTRIC_SMELTERY_2, SlimefunItems.ELECTRIC_SMELTERY_2, Materials.MACHINE_CIRCUIT, Materials.MACHINE_CORE, Materials.MACHINE_CIRCUIT}).setCapacity(ADVANCED_SMELTERY_ENERGY).setProcessingSpeed(ADVANCED_SMELTERY_SPEED).setEnergyConsumption(ADVANCED_SMELTERY_ENERGY).register(addon);
    }
}