package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.MoreTools.Listeners;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.MoreTools.Handlers.ItemInteractHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class PlayerListener implements Listener {
    public PlayerListener() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (SlimefunUtils.canPlayerUseItem(e.getPlayer(), e.getItem(), true)) {
            SlimefunItem sfItem = SlimefunItem.getByItem(e.getItem());
            if (sfItem == null) return;
            sfItem.callItemHandler(ItemInteractHandler.class, handler -> handler.onInteract(e, sfItem));
        }
    }
}