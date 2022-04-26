package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.ExtraHeads;

import static id.universenetwork.utilities.Bukkit.UNUtilities.cfg;

public class HeadListener implements org.bukkit.event.Listener {
    final ExtraHeads addon;

    public HeadListener(ExtraHeads addon) {
        this.addon = addon;
    }

    @org.bukkit.event.EventHandler(ignoreCancelled = true)
    public void onKill(org.bukkit.event.entity.EntityDeathEvent e) {
        if (!addon.getMobDrops().containsKey(e.getEntityType())) return;
        double chance = cfg.getDouble(addon.configPath + "chances." + e.getEntityType());
        org.bukkit.entity.Player killer = e.getEntity().getKiller();
        if (killer != null && io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.isItemSimilar(killer.getInventory().getItemInMainHand(), io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.SWORD_OF_BEHEADING, true))
            chance *= cfg.getDouble(addon.configPath + "sword-of-beheading-multiplier");
        if (Math.random() * 100 < chance)
            e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), addon.getMobDrops().get(e.getEntityType()));
    }
}