package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Listeners;

import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import java.util.UUID;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getProtectionManager;
import static org.bukkit.Bukkit.*;

public class TNTListener implements org.bukkit.event.Listener {
    static final String METADATA_KEY = "hotbarpets_player";

    @EventHandler
    public void onTNTDamage(org.bukkit.event.entity.EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof org.bukkit.entity.TNTPrimed && e.getDamager().hasMetadata(METADATA_KEY)) {
            Player attacker = getPlayer((UUID) e.getDamager().getMetadata(METADATA_KEY).get(0).value());
            if (attacker == null) e.setCancelled(true);
            else if (!getProtectionManager().hasPermission(attacker, e.getEntity().getLocation(), Interaction.ATTACK_PLAYER)) {
                e.setCancelled(true);
                attacker.sendMessage(org.bukkit.ChatColor.DARK_RED + "You cannot harm Players in here!");
            }
        }
    }

    @EventHandler(priority = org.bukkit.event.EventPriority.LOW, ignoreCancelled = true)
    public void onTNTExplode(org.bukkit.event.entity.EntityExplodeEvent e) {
        if (e.getEntityType().equals(org.bukkit.entity.EntityType.PRIMED_TNT) && e.getEntity().hasMetadata(METADATA_KEY)) {
            org.bukkit.OfflinePlayer player = getOfflinePlayer((UUID) e.getEntity().getMetadata(METADATA_KEY).get(0).value());
            getScheduler().scheduleSyncDelayedTask(plugin, () -> e.getEntity().removeMetadata(METADATA_KEY, plugin), 4);
            e.blockList().removeIf(b -> !getProtectionManager().hasPermission(player, b, Interaction.BREAK_BLOCK));

            // This is pretty much cancelled if all blocks were protected
            if (e.blockList().isEmpty()) e.setCancelled(true);
        }
    }
}