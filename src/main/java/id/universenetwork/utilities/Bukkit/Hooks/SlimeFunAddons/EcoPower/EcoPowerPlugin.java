package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.EcoPower;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.EcoPower.Generators.*;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.EcoPower.Items.SteelRotor;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.gadgets.SolarHelmet;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.generators.SolarGenerator;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.Addons.Enabled;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;

public class EcoPowerPlugin {
    public EcoPowerPlugin() {
        if (Enabled("EcoPower")) {
            ItemStack categoryItem = new CustomItemStack(SlimefunUtils.getCustomHead("240775c3ad75763613f32f04986881bbe4eee4366d0c57f17f7c7514e2d0a77d"), "&2Eco-Power Generators");
            ItemGroup itemGroup = new ItemGroup(new NamespacedKey(plugin, "generators"), categoryItem, 4);
            SlimefunItemStack rotor = new SlimefunItemStack("STEEL_ROTOR", "c51944b488e11cda65177d5911d651282b3012665e63b8929e1b6a4744b7ca8", "&bSteel Rotor");
            new SteelRotor(itemGroup, rotor, new ItemStack[]{null, STEEL_INGOT, null, STEEL_INGOT, new ItemStack(Material.IRON_BLOCK), STEEL_INGOT, null, STEEL_INGOT, null}, new SlimefunItemStack(rotor, 2)).register(addon);
            SteamTurbine simpleTurbine = registerSteamTurbine(itemGroup, "STEAM_TURBINE", "aefd921cb61594324f3c09d7ac7d38185d2734333968f3ac38382cddf15f6d71", "&eSimple Steam Turbine", MachineTier.MEDIUM, 4, new ItemStack[]{null, rotor, null, STEEL_INGOT, ELECTRIC_MOTOR, STEEL_INGOT, null, COPPER_WIRE, null});
            SteamTurbine advancedTurbine = registerSteamTurbine(itemGroup, "STEAM_TURBINE_2", "161aad79fb748bff1e6e94d4b6a5a277cc961c1a9abfe2a4ed88baab8a2b5971", "&cAdvanced Steam Turbine", MachineTier.ADVANCED, 6, new ItemStack[]{null, rotor, null, BRASS_INGOT, simpleTurbine.getItem(), BRASS_INGOT, BRASS_INGOT, COPPER_WIRE, BRASS_INGOT});
            registerSteamTurbine(itemGroup, "STEAM_TURBINE_3", "b65e29a67860d82f66afe1060ec8a9ceacc8c7afe108f5d42f52ba854b0a62dc", "&4Carbonado Steam Turbine", MachineTier.END_GAME, 13, new ItemStack[]{null, rotor, null, CARBONADO, advancedTurbine.getItem(), CARBONADO, REINFORCED_ALLOY_INGOT, ELECTRIC_MOTOR, REINFORCED_ALLOY_INGOT});
            WindTurbine simpleWindTurbine = registerWindTurbine(itemGroup, "WIND_TURBINE", "d23e4ce096e00eae6aba10d356b785c3fecc5aa3d7dad4a4a2a27ed7750df981", "&eSimple Wind Turbine", MachineTier.MEDIUM, 5, new ItemStack[]{null, rotor, null, STEEL_THRUSTER, ELECTRIC_MOTOR, STEEL_THRUSTER, null, COPPER_WIRE, null});
            WindTurbine advancedWindTurbine = registerWindTurbine(itemGroup, "WIND_TURBINE_2", "2df9e595dbeac33f43b37dd4ffbc234ea0fa7c3f98aad77dc906ce5d6783c79d", "&cAdvanced Wind Turbine", MachineTier.ADVANCED, 11, new ItemStack[]{null, rotor, null, ELECTRO_MAGNET, simpleWindTurbine.getItem(), ELECTRO_MAGNET, ALUMINUM_BRASS_INGOT, COPPER_WIRE, ALUMINUM_BRASS_INGOT});
            registerWindTurbine(itemGroup, "WIND_TURBINE_3", "3fcef461b43f06ef9d58c94065bbf41b77a10050520b44082d5f66f6dbe71da0", "&4Carbonado Wind Turbine", MachineTier.END_GAME, 23, new ItemStack[]{FERROSILICON, rotor, FERROSILICON, ELECTRIC_MOTOR, advancedWindTurbine.getItem(), ELECTRIC_MOTOR, CARBONADO, FERROSILICON, CARBONADO});
            registerLightningReceptor(itemGroup, new ItemStack[]{null, new ItemStack(Material.END_ROD), null, BLISTERING_INGOT_3, POWER_CRYSTAL, BLISTERING_INGOT_3, REINFORCED_PLATE, ENERGY_REGULATOR, REINFORCED_PLATE});
            LunarGenerator lunarGenerator = registerLunarGenerator(itemGroup, new ItemStack[]{new ItemStack(Material.PHANTOM_MEMBRANE), SOLAR_GENERATOR_4, new ItemStack(Material.PHANTOM_MEMBRANE), DAMASCUS_STEEL_INGOT, CARBONADO, DAMASCUS_STEEL_INGOT, COPPER_WIRE, BLISTERING_INGOT_3, COPPER_WIRE});
            SolarGenerator solarGenerator = registerHighEnergySolarGenerator(itemGroup, "HIGH_ENERGY_SOLAR_GENERATOR", "c4fe135c311f7086edcc5e6dbc4ef4b23f819fddaa42f827dac46e3574de2287", "&9High-Energy Solar Generator", 256, new ItemStack[]{SOLAR_GENERATOR_2, lunarGenerator.getItem(), SOLAR_GENERATOR_2, CARBONADO, POWER_CRYSTAL, CARBONADO, BLISTERING_INGOT_3, new ItemStack(Material.NETHER_STAR), BLISTERING_INGOT_3});
            registerSolarHelmet(itemGroup, new ItemStack[]{null, solarGenerator.getItem(), null, REINFORCED_ALLOY_INGOT, REINFORCED_ALLOY_INGOT, REINFORCED_ALLOY_INGOT, REINFORCED_ALLOY_INGOT, null, REINFORCED_ALLOY_INGOT});
            registerHighEnergySolarGenerator(itemGroup, "RADIANT_SOLAR_GENERATOR", "240775c3ad75763613f32f04986881bbe4eee4366d0c57f17f7c7514e2d0a77d", "&9Radiant Solar Generator", 512, new ItemStack[]{lunarGenerator.getItem(), solarGenerator.getItem(), lunarGenerator.getItem(), BLISTERING_INGOT_3, POWER_CRYSTAL, BLISTERING_INGOT_3, REINFORCED_PLATE, CARBONADO, REINFORCED_PLATE});
            System.out.println(prefix + " §bSuccessfully Registered §dEcoPower §bAddon");
        }
    }

    WindTurbine registerWindTurbine(ItemGroup itemGroup, String id, String texture, String name, MachineTier tier, int power, ItemStack[] recipe) {
        SlimefunItemStack turbineItem = new SlimefunItemStack(id, texture, name, "&7Component of the " + name + " Generator");
        WindTurbine turbine = new WindTurbine(itemGroup, turbineItem, power, ENHANCED_CRAFTING_TABLE, recipe);
        turbine.register(addon);
        SlimefunItemStack multiblockItem = new SlimefunItemStack(id + "_MULTIBLOCK", texture, name + " Generator", "", LoreBuilder.machine(tier, MachineType.GENERATOR), LoreBuilder.powerBuffer(0), LoreBuilder.powerPerSecond(power * 2));
        new WindTurbineMultiblock(itemGroup, multiblockItem, turbine).register(addon);
        return turbine;
    }

    SteamTurbine registerSteamTurbine(ItemGroup itemGroup, String id, String texture, String name, MachineTier tier, int power, ItemStack[] recipe) {
        SlimefunItemStack turbineItem = new SlimefunItemStack(id, texture, name, "&7Component of the " + name + " Generator");
        SteamTurbine turbine = new SteamTurbine(itemGroup, turbineItem, power, ENHANCED_CRAFTING_TABLE, recipe);
        turbine.register(addon);
        SlimefunItemStack multiblockItem = new SlimefunItemStack(id + "_MULTIBLOCK", texture, name + " Generator", "", LoreBuilder.machine(tier, MachineType.GENERATOR), LoreBuilder.powerBuffer(0), LoreBuilder.powerPerSecond(power * 2));
        new SteamTurbineMultiblock(itemGroup, multiblockItem, turbine).register(addon);
        return turbine;
    }

    void registerLightningReceptor(ItemGroup itemGroup, ItemStack[] recipe) {
        final String texture = "31a3cd9b016b1228ec01fd6f0992c64f3b9b7b29773fa46439ab3f3c8a347704";
        SlimefunItemStack item = new SlimefunItemStack("LIGHTNING_RECEPTOR", texture, "&eLightning Receptor", "", "&fThis machine can channel energy", "&ffrom lightning strikes during", "&fa thunderstorm", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.GENERATOR), LoreBuilder.power(512, " - " + 8192 + " J per lightning strike"));
        LightningReceptor receptor = new LightningReceptor(itemGroup, item, 512, 8192, ENHANCED_CRAFTING_TABLE, recipe);
        receptor.register(addon);
    }

    LunarGenerator registerLunarGenerator(ItemGroup itemGroup, ItemStack[] recipe) {
        final String texture = "afdd9e588d2461d2d3d058cb3e0af2b3a3367607aa14d124ed92a833f25fb112";
        SlimefunItemStack item = new SlimefunItemStack("LUNAR_GENERATOR", texture, "&5Lunar Generator", "", "&fThis Lunar Generator only", "&fruns at night!", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.GENERATOR), LoreBuilder.powerBuffer(0), LoreBuilder.powerPerSecond(128 * 2));
        LunarGenerator generator = new LunarGenerator(itemGroup, item, ENHANCED_CRAFTING_TABLE, recipe, 128);
        generator.register(addon);
        return generator;
    }

    HighEnergySolarGenerator registerHighEnergySolarGenerator(ItemGroup itemGroup, String id, String texture, String name, int power, ItemStack[] recipe) {
        SlimefunItemStack item = new SlimefunItemStack(id, texture, name, "", "&fThis Solar Generator runs", "&fall day and night!", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.GENERATOR), LoreBuilder.powerBuffer(0), LoreBuilder.powerPerSecond(power * 2));
        HighEnergySolarGenerator generator = new HighEnergySolarGenerator(itemGroup, item, ENHANCED_CRAFTING_TABLE, recipe, power);
        generator.register(addon);
        return generator;
    }

    void registerSolarHelmet(ItemGroup itemGroup, ItemStack[] recipe) {
        SlimefunItemStack item = new SlimefunItemStack("HIGH_ENERGY_SOLAR_HELMET", Material.IRON_HELMET, "&9High-Energy Solar Helmet", "", "&fThis Solar Helmet charges", "&fany held or worn items", "", LoreBuilder.power(5, "/charge"));
        SolarHelmet solarHelmet = new SolarHelmet(itemGroup, item, ENHANCED_CRAFTING_TABLE, recipe, 5);
        solarHelmet.register(addon);
    }
}