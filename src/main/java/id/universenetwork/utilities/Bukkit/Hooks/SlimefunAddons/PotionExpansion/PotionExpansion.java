package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.Tasks.EffectsTask;
import org.apache.commons.lang.Validate;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class PotionExpansion {
    EffectsTask effectsTask;
    public static PotionExpansion Main;

    public PotionExpansion() {
        if (id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.Enabled("PotionExpansion")) {
            Main = this;
            Settings.load();
            PotionsItemSetup.setup();
            ResearchSetup.setup();
            new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.Commands.PotionExpansionCommand();
            id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListener(new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.Listeners.DrinkMilkListener());
            effectsTask = new EffectsTask();
            System.out.println(id.universenetwork.utilities.Bukkit.UNUtilities.prefix + " §bSuccessfully Registered §dPotionExpansion §bAddon");
        }
    }

    public EffectsTask getEffectsTask() {
        return effectsTask;
    }


    public static @org.jetbrains.annotations.Nullable org.bukkit.scheduler.BukkitTask runSync(@org.jetbrains.annotations.NotNull Runnable runnable, long delay) {
        Validate.notNull(runnable, "Cannot run null");
        Validate.isTrue(delay >= 0, "The delay cannot be negative");
        if (plugin == null || !plugin.isEnabled()) return null;
        return plugin.getServer().getScheduler().runTaskLater(plugin, runnable, delay);
    }
}