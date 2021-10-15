package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MoreTools.Handlers;

import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.event.player.PlayerInteractEvent;

@FunctionalInterface
public interface ItemInteractHandler extends ItemHandler {
    void onInteract(PlayerInteractEvent e, SlimefunItem sfItem);

    @NotNull
    @Override
    default Class<? extends ItemHandler> getIdentifier() {
        return ItemInteractHandler.class;
    }
}