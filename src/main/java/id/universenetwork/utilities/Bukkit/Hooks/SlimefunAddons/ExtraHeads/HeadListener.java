package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraHeads;

import id.universenetwork.utilities.Bukkit.Manager.Config;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import static id.universenetwork.utilities.Bukkit.Enums.Features.SlimefunAddons.ADDONSSETTINGS;
import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListener;

class HeadListener implements Listener {
    final ExtraHeads Main;

    public HeadListener(ExtraHeads Main) {
        this.Main = Main;
        registerListener(this);
    }

    @EventHandler(ignoreCancelled = true)
    public void onKill(EntityDeathEvent e) {
        if (Main.getMobDrops().containsKey(e.getEntityType())) {
            double chance = Config.get().getDouble(ADDONSSETTINGS.getConfigPath() + "ExtraHeads." + "Chances." + e.getEntityType(), 5.0);
            Player killer = e.getEntity().getKiller();
            if (killer != null && SlimefunUtils.isItemSimilar(killer.getInventory().getItemInMainHand(), SlimefunItems.SWORD_OF_BEHEADING, true))
                chance *= Config.get().getDouble(ADDONSSETTINGS.getConfigPath() + "ExtraHeads." + "sword-of-beheading-multiplier");
            if (Math.random() * 100.0D < chance)
                e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), Main.getMobDrops().get(e.getEntityType()));
        }
    }
}