package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer;

import org.bukkit.entity.LivingEntity;

public class PelletListener implements org.bukkit.event.Listener {
    final MobCapturer Main;

    public PelletListener(MobCapturer Main) {
        this.Main = Main;
    }

    @org.bukkit.event.EventHandler(ignoreCancelled = true, priority = org.bukkit.event.EventPriority.HIGH)
    public void onProjectileHit(org.bukkit.event.entity.EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof org.bukkit.entity.Snowball && e.getEntity() instanceof LivingEntity && e.getDamager().hasMetadata("mob_capturing_cannon")) {
            java.util.Optional<org.bukkit.inventory.ItemStack> optional = Main.capture((LivingEntity) e.getEntity());
            if (optional.isPresent()) {
                e.getDamager().removeMetadata("mob_capturing_cannon", id.universenetwork.utilities.Bukkit.UNUtilities.plugin);
                e.getEntity().remove();
                e.getEntity().getWorld().dropItemNaturally(((LivingEntity) e.getEntity()).getEyeLocation(), optional.get());
            }
        }
    }
}