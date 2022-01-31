package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Listeners;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.UUID;

import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListeners;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getProtectionManager;
import static io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction.ATTACK_PLAYER;
import static io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction.BREAK_BLOCK;
import static org.bukkit.Bukkit.getOfflinePlayer;
import static org.bukkit.Bukkit.getPlayer;
import static org.bukkit.ChatColor.DARK_RED;
import static org.bukkit.entity.EntityType.PRIMED_TNT;
import static org.bukkit.event.EventPriority.LOW;

public class TNTListener implements org.bukkit.event.Listener {
    static final String METADATA_KEY = "hotbarpets_player";

    public TNTListener() {
        registerListeners(this);
    }

    @EventHandler
    public void onTNTDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof TNTPrimed && e.getDamager().hasMetadata(METADATA_KEY)) {
            Player attacker = getPlayer((UUID) e.getDamager().getMetadata(METADATA_KEY).get(0).value());
            if (attacker == null) e.setCancelled(true);
            else if (!getProtectionManager().hasPermission(attacker, e.getEntity().getLocation(), ATTACK_PLAYER)) {
                e.setCancelled(true);
                attacker.sendMessage(DARK_RED + "You cannot harm Players in here!");
            }
        }
    }

    @EventHandler(priority = LOW, ignoreCancelled = true)
    public void onTNTExplode(EntityExplodeEvent e) {
        if (e.getEntityType().equals(PRIMED_TNT) && e.getEntity().hasMetadata(METADATA_KEY)) {
            OfflinePlayer player = getOfflinePlayer((UUID) e.getEntity().getMetadata(METADATA_KEY).get(0).value());
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> e.getEntity().removeMetadata(METADATA_KEY, plugin), 4);
            e.blockList().removeIf(b -> !getProtectionManager().hasPermission(player, b, BREAK_BLOCK));

            // This is pretty much cancelled if all blocks were protected
            if (e.blockList().isEmpty()) e.setCancelled(true);
        }
    }
}