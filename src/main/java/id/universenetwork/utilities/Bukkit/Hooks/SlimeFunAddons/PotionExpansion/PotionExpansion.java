package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.Commands.PotionExpansionCommand;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.Commands.PotionExpansionTab;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.Listeners.DrinkMilkListener;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.Tasks.EffectsTask;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitTask;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.Addons.Enabled;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class PotionExpansion {
    EffectsTask effectsTask;
    public static boolean enabled;
    public static PotionExpansion Main;

    public PotionExpansion() {
        enabled = Enabled("PotionExpansion");
        plugin.getCommand("potionexpansion").setExecutor(new PotionExpansionCommand());
        plugin.getCommand("potionexpansion").setTabCompleter(new PotionExpansionTab());
        if (enabled) {
            Main = this;
            Settings.load();
            PotionsItemSetup.setup();
            ResearchSetup.setup();
            PluginManager pm = Bukkit.getPluginManager();
            pm.registerEvents(new DrinkMilkListener(), plugin);
            effectsTask = new EffectsTask();
            System.out.println(prefix + " §bSuccessfully Registered §dPotionExpansion §bAddon");
        }
    }

    public EffectsTask getEffectsTask() {
        return effectsTask;
    }


    public static @Nullable
    BukkitTask runSync(@Nonnull Runnable runnable, long delay) {
        Validate.notNull(runnable, "Cannot run null");
        Validate.isTrue(delay >= 0, "The delay cannot be negative");
        if (plugin == null || !plugin.isEnabled()) return null;
        return plugin.getServer().getScheduler().runTaskLater(plugin, runnable, delay);
    }
}