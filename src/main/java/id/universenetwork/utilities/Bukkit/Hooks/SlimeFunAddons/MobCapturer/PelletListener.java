package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.MobCapturer;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static org.bukkit.Bukkit.getPluginManager;
import static org.bukkit.event.EventPriority.HIGH;

public class PelletListener implements Listener {
    final MobCapturer Main;

    public PelletListener(MobCapturer Main) {
        this.Main = Main;
        getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(ignoreCancelled = true, priority = HIGH)
    public void onProjectileHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Snowball && e.getEntity() instanceof LivingEntity && e.getDamager().hasMetadata("mob_capturing_cannon")) {
            Optional<ItemStack> optional = Main.capture((LivingEntity) e.getEntity());
            if (optional.isPresent()) {
                e.getDamager().removeMetadata("mob_capturing_cannon", plugin);
                e.getEntity().remove();
                e.getEntity().getWorld().dropItemNaturally(((LivingEntity) e.getEntity()).getEyeLocation(), optional.get());
            }
        }
    }
}