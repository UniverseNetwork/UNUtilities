package id.universenetwork.utilities.Bukkit.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class HatListener implements Listener {
    @EventHandler
    public void onHatEquip(InventoryClickEvent e) {
        if (e.getInventory().getType() == InventoryType.CRAFTING && e.getRawSlot() == 5 && e.getWhoClicked().getItemOnCursor().getType() != Material.AIR && e.getWhoClicked().getItemOnCursor().getType().getEquipmentSlot() != EquipmentSlot.HEAD) {
            Player p = (Player) e.getWhoClicked();
            ItemStack ci = p.getItemOnCursor();
            
        }
    }
}