package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Objects.FPNotPlaceable;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Utils.GlowEnchant;
import org.bukkit.enchantments.Enchantment;

import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

/**
 * The main class of the FlowerPower addon
 *
 * @author NCBPFluffyBear
 */
public class FlowerPowerPlugin implements org.bukkit.event.Listener {
    public FlowerPowerPlugin() {
        if (id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.Enabled("FlowerPower")) {
            try {
                if (!Enchantment.isAcceptingRegistrations()) {
                    java.lang.reflect.Field accepting = Enchantment.class.getDeclaredField("acceptingNew");
                    accepting.setAccessible(true);
                    accepting.set(null, true);
                }
            } catch (IllegalAccessException | NoSuchFieldException ignore) {
                org.bukkit.Bukkit.getLogger().warning(prefix + " §eFailed to register enchantment.");
            }
            Enchantment glowEnchant = new GlowEnchant(id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Utils.Constants.GLOW_ENCHANT, new String[]{"GLISTENING_POPPY", "GLISTENING_DANDELION", "GLISTENING_OXEYE_DAISY", "GLISTENING_ALLIUM", "OVERGROWTH_SEED", "INFINITY_BANDAGE", "RECALL_CHARM"});

            // Prevent double-registration errors
            if (Enchantment.getByKey(glowEnchant.getKey()) == null) Enchantment.registerEnchantment(glowEnchant);

            // Register Listener
            id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListeners(this);

            // Register All Items
            id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Setup.FlowerPowerItemSetup.setup();

            // Register All Researches
            id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Setup.ResearchSetup.setup();

            System.out.println(prefix + " §bSuccessfully Registered §dFlowerPower §bAddon");
        }
    }

    /**
     * Prevents {@link FPNotPlaceable} items from being placed
     */
    @org.bukkit.event.EventHandler
    void onFPNotPlaceablePlace(org.bukkit.event.block.BlockPlaceEvent e) {
        if (io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem.getByItem(e.getItemInHand()) instanceof FPNotPlaceable)
            e.setCancelled(true);
    }
}