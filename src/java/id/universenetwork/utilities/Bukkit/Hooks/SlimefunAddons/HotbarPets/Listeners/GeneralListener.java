package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Listeners;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.HotbarPet;
import org.bukkit.event.EventHandler;

import static io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem.getByItem;

public class GeneralListener implements org.bukkit.event.Listener {
    @EventHandler(priority = org.bukkit.event.EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlace(org.bukkit.event.block.BlockPlaceEvent e) {
        if (getByItem(e.getItemInHand()) instanceof HotbarPet) e.setCancelled(true);
    }

    @EventHandler
    public void onEquip(org.bukkit.event.inventory.InventoryClickEvent e) {
        if (e.getSlotType().equals(org.bukkit.event.inventory.InventoryType.SlotType.ARMOR) && getByItem(e.getCursor()) instanceof HotbarPet)
            e.setCancelled(true);
    }

    @EventHandler
    public void onQuit(org.bukkit.event.player.PlayerQuitEvent e) {
        id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.HotbarPet.getMessageDelay().remove(e.getPlayer().getUniqueId());
    }
}