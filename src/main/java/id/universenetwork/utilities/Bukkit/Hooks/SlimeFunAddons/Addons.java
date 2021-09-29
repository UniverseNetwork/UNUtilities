package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.ChestTerminal.ChestTerminal;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.ColoredEnderChests.ColoredEnderChests;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.DyedBackpacks.DyedBackpacks;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.EcoPower.EcoPowerPlugin;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.ElectricSpawners.ElectricSpawners;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.ExtraGear.ExtraGear;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.ExtraHeads.ExtraHeads;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.ExtraTools.ExtraTools;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.FluffyMachines.FluffyMachines;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HeadLimiter.HeadLimiter;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.HotbarPets;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.InfinityExpansion;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.MobCapturer.MobCapturer;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.MoreTools.MoreTools;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.PotionExpansion;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PrivateStorage.PrivateStorage;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.SFMobDrops.SFMobDrops;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.SMG.SimpleMaterialGenerator;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.SlimefunOreChunks.OreChunks;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.SlimyTreeTaps.TreeTaps;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.SoulJars.SoulJars;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.SoundMuffler.SoundMuffler;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Core.Environment;
import id.universenetwork.utilities.Bukkit.Manager.Config;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

import static id.universenetwork.utilities.Bukkit.Enums.Features.SlimeFunAddons.ADDONS;
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
        System.out.println(prefix + " §6Found SlimeFun. Registering Addons...");
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
        System.out.println(prefix + " §aSuccessfully Registered All Enabled Addons to SlimeFun");
    }

    @Nonnull
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
}