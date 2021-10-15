package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ChestTerminal.ChestTerminal;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ColoredEnderChests.ColoredEnderChests;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DyedBackpacks.DyedBackpacks;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.EcoPower.EcoPowerPlugin;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ElectricSpawners.ElectricSpawners;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraGear.ExtraGear;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraHeads.ExtraHeads;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.ExtraTools;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.FluffyMachines;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HeadLimiter.HeadLimiter;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.HotbarPets;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.InfinityExpansion;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer.MobCapturer;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MoreTools.MoreTools;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.PotionExpansion;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PrivateStorage.PrivateStorage;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SFMobDrops.SFMobDrops;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SMG.SimpleMaterialGenerator;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SlimefunOreChunks.OreChunks;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SlimyTreeTaps.TreeTaps;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SoulJars.SoulJars;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SoundMuffler.SoundMuffler;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Core.Environment;
import id.universenetwork.utilities.Bukkit.Manager.Config;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import static id.universenetwork.utilities.Bukkit.Enums.Features.SlimeFunAddons.ADDONS;
import static id.universenetwork.utilities.Bukkit.Enums.Features.SlimeFunAddons.ADDONSSETTINGS;
import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Scheduler.repeat;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getTickerTask;

@SuppressWarnings("ALL")
public class Addons implements SlimefunAddon {
    public static int slimefunTickCount;
    public static SlimefunAddon addon = new Addons();
    public static Environment environment;

    public static void setup() {
        environment = Environment.LIVE;
        repeat(getTickerTask().getTickRate(), () -> slimefunTickCount++);
        System.out.println(prefix + " §6Found Slimefun. Registering Addons...");
        new ExtraGear();
        new PrivateStorage();
        new DyedBackpacks();
        new OreChunks();
        new ExtraHeads();
        new MobCapturer();
        new ExtraTools();
        new SimpleMaterialGenerator();
        new PotionExpansion();
        new SoulJars();
        new ColoredEnderChests();
        new EcoPowerPlugin();
        new TreeTaps();
        new MoreTools();
        new ChestTerminal();
        new HeadLimiter();
        new SoundMuffler();
        new SFMobDrops();
        new FluffyMachines();
        new HotbarPets();
        new ElectricSpawners();
        new InfinityExpansion();
        System.out.println(prefix + " §aSuccessfully Registered All Enabled Addons to Slimefun");
    }

    @NotNull
    @Override
    public JavaPlugin getJavaPlugin() {
        return plugin;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/UniverseNetwork/UNUtilities/issues";
    }

    public static boolean Enabled(String AddonName) {
        return Config.get().getBoolean(ADDONS.getConfigPath() + AddonName);
    }

    public static ConfigurationSection Settings(String AddonName) {
        return Config.get().getConfigurationSection(ADDONSSETTINGS.getConfigPath() + AddonName);
    }
}