package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.UUID;

import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListener;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class PermissionsHandler implements Listener {
    static boolean enabled = false;
    static final HashMap<UUID, PermissionAttachment> permissions = new HashMap<>();

    public static void enable() {
        if (!enabled) registerListener(new PermissionsHandler());
        enabled = true;
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static PermissionAttachment getPermissions(Player player) {
        return permissions.get(player.getUniqueId());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event) {
        permissions.put(event.getPlayer().getUniqueId(), event.getPlayer().addAttachment(plugin));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.getPlayer().removeAttachment(getPermissions(event.getPlayer()));
        permissions.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        event.getPlayer().removeAttachment(getPermissions(event.getPlayer()));
        permissions.remove(event.getPlayer().getUniqueId());
    }
}