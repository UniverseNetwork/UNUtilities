package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Listeners;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.HotbarPet;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.HotbarPet.getMessageDelay;
import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListeners;
import static io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem.getByItem;
import static org.bukkit.event.EventPriority.LOWEST;
import static org.bukkit.event.inventory.InventoryType.SlotType.ARMOR;

public class GeneralListener implements org.bukkit.event.Listener {
    public GeneralListener() {
        registerListeners(this);
    }

    @EventHandler(priority = LOWEST, ignoreCancelled = true)
    public void onPlace(BlockPlaceEvent e) {
        if (getByItem(e.getItemInHand()) instanceof HotbarPet) e.setCancelled(true);
    }

    @EventHandler
    public void onEquip(InventoryClickEvent e) {
        if (e.getSlotType().equals(ARMOR) && getByItem(e.getCursor()) instanceof HotbarPet)
            e.setCancelled(true);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        getMessageDelay().remove(e.getPlayer().getUniqueId());
    }
}