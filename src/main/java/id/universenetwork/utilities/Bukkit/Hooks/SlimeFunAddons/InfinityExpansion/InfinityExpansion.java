package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Groups.Groups;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Items.Blocks.Blocks;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Items.Gear.Gear;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Items.Generators.Generators;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Items.Machines.Machines;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Items.Materials.Materials;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Items.MobData.MobData;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Items.Quarries.Quarries;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Items.Researches;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Items.SlimefunExtension;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Items.Storage.Storage;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

import static id.universenetwork.utilities.Bukkit.Enums.Features.SlimeFunAddons.ADDONSSETTINGS;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.Addons.Enabled;
import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Scheduler.run;
import static id.universenetwork.utilities.Bukkit.Manager.Config.get;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class InfinityExpansion {
    public InfinityExpansion() {
        boolean enabled = Enabled("InfinityExpansion");
        new Commands(enabled);
        if (enabled) {
            Plugin lx = getServer().getPluginManager().getPlugin("LiteXpansion");
            if (lx != null && lx.getConfig().getBoolean("options.nerf-other-addons"))
                run(() -> getLogger().warning("\n########################################################\n" + "LiteXpansion nerfs energy generation in InfinityExpansion addon.\n" + "You can disable these nerfs in the LiteXpansion config.\n" + "Under 'options:' add 'nerf-other-addons: false'\n" + "########################################################"));
            Groups.setup();
            MobData.setup();
            Materials.setup();
            Machines.setup();
            Quarries.setup();
            Gear.setup();
            Blocks.setup();
            Storage.setup();
            Generators.setup();
            SlimefunExtension.setup();
            if (getConfig().getBoolean("balance-options.enable-researches")) Researches.setup();
            System.out.println(prefix + " §bSuccessfully Registered §dInfinityExpansion §bAddon");
        }
    }

    public static ConfigurationSection getConfig() {
        return get().getConfigurationSection(ADDONSSETTINGS.getConfigPath() + "InfinityExpansion");
    }
}
