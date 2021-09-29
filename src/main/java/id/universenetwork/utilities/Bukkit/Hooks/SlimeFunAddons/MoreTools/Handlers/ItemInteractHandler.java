package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.MoreTools.Handlers;

import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.annotation.Nonnull;

@FunctionalInterface
public interface ItemInteractHandler extends ItemHandler {
    void onInteract(PlayerInteractEvent e, SlimefunItem sfItem);

    @Nonnull
    @Override
    default Class<? extends ItemHandler> getIdentifier() {
        return ItemInteractHandler.class;
    }
}