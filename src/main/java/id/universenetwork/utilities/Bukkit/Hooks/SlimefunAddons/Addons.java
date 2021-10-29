package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Core.Environment;
import id.universenetwork.utilities.Bukkit.Manager.Config;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import static id.universenetwork.utilities.Bukkit.Enums.Features.SlimefunAddons.ADDONS;
import static id.universenetwork.utilities.Bukkit.Enums.Features.SlimefunAddons.ADDONSSETTINGS;
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
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraGear.ExtraGear();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PrivateStorage.PrivateStorage();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DyedBackpacks.DyedBackpacks();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SlimefunOreChunks.OreChunks();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraHeads.ExtraHeads();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer.MobCapturer();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.ExtraTools();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SMG.SimpleMaterialGenerator();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.PotionExpansion();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SoulJars.SoulJars();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ColoredEnderChests.ColoredEnderChests();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.EcoPower.EcoPowerPlugin();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SlimyTreeTaps.TreeTaps();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MoreTools.MoreTools();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ChestTerminal.ChestTerminal();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HeadLimiter.HeadLimiter();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SoundMuffler.SoundMuffler();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SFMobDrops.SFMobDrops();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.FluffyMachines();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.HotbarPets();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ElectricSpawners.ElectricSpawners();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.InfinityExpansion();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Bump();
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTech();
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