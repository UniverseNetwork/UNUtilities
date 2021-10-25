package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Listeners;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Tools.InventoryFilter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class InventoryFilterListener implements org.bukkit.event.Listener {
    final InventoryFilter inventoryFilter;
    final java.util.Set<Material> blacklistedMaterials = java.util.EnumSet.noneOf(Material.class);

    public InventoryFilterListener(@NotNull InventoryFilter inventoryFilter) {
        id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListener(this);
        this.inventoryFilter = inventoryFilter;
    }

    @org.bukkit.event.EventHandler
    public void onItemPickup(org.bukkit.event.entity.EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) checkAndFilter((Player) e.getEntity());
    }

    void checkAndFilter(Player p) {
        if (inventoryFilter != null && !inventoryFilter.isDisabled()) {
            for (ItemStack item : p.getInventory().getContents())
                if (item != null && item.getType() == inventoryFilter.getItem().getType() && item.hasItemMeta() && inventoryFilter.isItem(item))
                    if (io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.canPlayerUseItem(p, item, true))
                        io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile.getBackpack(item, backpack -> id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTech.runSync(() -> filterInventory(p, backpack)));
        }
    }

    void filterInventory(@NotNull Player p, @NotNull io.github.thebusybiscuit.slimefun4.api.player.PlayerBackpack backpack) {
        java.util.List<String> blacklistedStrings = new java.util.ArrayList<>();
        if (backpack.getInventory().isEmpty()) blacklistedMaterials.clear();
        for (ItemStack item : backpack.getInventory().getContents())
            if (item != null && item.getType() != Material.AIR) {
                // Leaving this here to dont have to deal with checking if its using a default or custom name ie if they are farming custom items like SfItems.
                blacklistedMaterials.add(item.getType());
                blacklistedStrings.add(item.getItemMeta().getDisplayName());
            }

        // CANT DROP AIR SO HAVE TO ITERATE THROUGH THE INVENTORY
        org.bukkit.inventory.Inventory inv = p.getInventory();
        for (ItemStack item : inv.getStorageContents())
            if (item != null && blacklistedMaterials.contains(item.getType()) && blacklistedStrings.contains(item.getItemMeta().getDisplayName())) {
                inv.remove(item);
                break;
            }
    }
}