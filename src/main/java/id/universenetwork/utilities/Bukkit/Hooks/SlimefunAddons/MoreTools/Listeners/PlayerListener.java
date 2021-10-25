package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MoreTools.Listeners;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MoreTools.Handlers.ItemInteractHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListener;

public class PlayerListener implements Listener {
    public PlayerListener() {
        registerListener(this);
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