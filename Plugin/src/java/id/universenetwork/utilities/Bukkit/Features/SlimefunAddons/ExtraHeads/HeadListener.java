package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.ExtraHeads;

import id.universenetwork.utilities.Bukkit.UNUtilities;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class HeadListener implements Listener {
    final ExtraHeads addon;

    public HeadListener(ExtraHeads addon) {
        this.addon = addon;
    }

    @EventHandler(ignoreCancelled = true)
    public void onKill(EntityDeathEvent e) {
        if (!addon.getMobDrops().containsKey(e.getEntityType())) return;
        double chance = UNUtilities.cfg.getDouble(addon.cfgPath + "chances." + e.getEntityType());
        Player killer = e.getEntity().getKiller();
        if (killer != null && SlimefunUtils.isItemSimilar(killer.getInventory().getItemInMainHand(), SlimefunItems.SWORD_OF_BEHEADING, true))
            chance *= UNUtilities.cfg.getDouble(addon.cfgPath + "sword-of-beheading-multiplier");
        if (Math.random() * 100 < chance)
            e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), addon.getMobDrops().get(e.getEntityType()));
    }
}