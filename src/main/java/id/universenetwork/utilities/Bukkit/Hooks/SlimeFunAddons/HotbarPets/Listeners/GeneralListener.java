package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Listeners;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.HotbarPet;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.HotbarPet.getMessageDelay;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem.getByItem;
import static org.bukkit.Bukkit.getPluginManager;
import static org.bukkit.event.EventPriority.LOWEST;
import static org.bukkit.event.inventory.InventoryType.SlotType.ARMOR;

public class GeneralListener implements Listener {
    public GeneralListener() {
        getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = LOWEST, ignoreCancelled = true)
    public void onPlace(BlockPlaceEvent e) {
        if (getByItem(e.getItemInHand()) instanceof HotbarPet) e.setCancelled(true);
    }

    @EventHandler
    public void onEquip(InventoryClickEvent e) {
        if (e.getSlotType() == ARMOR && getByItem(e.getCursor()) instanceof HotbarPet)
            e.setCancelled(true);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        getMessageDelay().remove(e.getPlayer().getUniqueId());
    }
}