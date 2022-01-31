package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech;

import org.bukkit.WorldCreator;
import org.jetbrains.annotations.NotNull;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.Enabled;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getLogger;

public class DynaTech implements org.bukkit.event.Listener {
    static DynaTech instance;
    public static boolean isExoticGardenInstalled;
    public static boolean isInfinityExpansionInstalled;
    int tickInterval;

    public DynaTech() {
        if (Enabled("DynaTech")) {
            instance = this;
            final int TICK_TIME = io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getTickerTask().getTickRate();
            isExoticGardenInstalled = plugin.getServer().getPluginManager().isPluginEnabled("ExoticGarden");
            isInfinityExpansionInstalled = Enabled("InfinityExpansion");
            if (!id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.Settings("DynaTech").getBoolean("disable-dimensionalhome-world")) {
                WorldCreator worldCreator = new WorldCreator("dimensionalhome");
                worldCreator.generator(new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Misc.DimensionalHomeDimension());
                org.bukkit.World dimensionalHome = worldCreator.createWorld();
                new me.mrCookieSlime.Slimefun.api.BlockStorage(dimensionalHome);
            }
            id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Setup.DynaTechItemsSetup.setup();
            new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Listeners.PicnicBasketListener((id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Backpacks.PicnicBasket) DynaTechItems.PICNIC_BASKET.getItem());
            new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Listeners.ElectricalStimulatorListener((id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Tools.ElectricalStimulator) DynaTechItems.ELECTRICAL_STIMULATOR.getItem());
            new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Listeners.InventoryFilterListener((id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Tools.InventoryFilter) DynaTechItems.INVENTORY_FILTER.getItem());

            // Tasks
            plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Tasks.ItemBandTask(), 0L, 5 * 20L);
            plugin.getServer().getScheduler().runTaskTimer(plugin, () -> tickInterval++, 0, TICK_TIME);

            if (System.getProperty("java.version").startsWith("1.8")) {
                getLogger().warning(prefix + "§e           DynaTech will be switching to JAVA 11        ");
                getLogger().warning(prefix + "§e                Please Update to JAVA 11                ");
            }
            if (isExoticGardenInstalled && !isInfinityExpansionInstalled)
                System.out.println(prefix + " §bSuccessfully Registered §dDynaTech §bAddon With §dExoticGarden §bSupport");
            else if (!isExoticGardenInstalled && isInfinityExpansionInstalled)
                System.out.println(prefix + " §bSuccessfully Registered §dDynaTech §bAddon With §dInfinityExpansion §bSupport");
            else if (isExoticGardenInstalled && isInfinityExpansionInstalled)
                System.out.println(prefix + " §bSuccessfully Registered §dDynaTech §bAddon With §dExoticGarden & InfinityExpansion §bSupport");
            else System.out.println(prefix + " §bSuccessfully Registered §dDynaTech §bAddon");
        }
    }

    @org.bukkit.event.EventHandler
    public void onDisable(id.universenetwork.utilities.Bukkit.Events.UNUtilitiesDisableEvent e) {
        instance = null;
        plugin.getServer().getScheduler().cancelTasks(plugin);
    }

    @NotNull
    public static DynaTech getInstance() {
        return instance;
    }

    public int getTickInterval() {
        return tickInterval;
    }

    @org.jetbrains.annotations.Nullable
    public static org.bukkit.scheduler.BukkitTask runSync(@NotNull Runnable runnable) {
        org.apache.commons.lang.Validate.notNull(runnable, "Cannot run null");
        if (plugin == null || !plugin.isEnabled()) return null;
        return plugin.getServer().getScheduler().runTask(plugin, runnable);
    }
}