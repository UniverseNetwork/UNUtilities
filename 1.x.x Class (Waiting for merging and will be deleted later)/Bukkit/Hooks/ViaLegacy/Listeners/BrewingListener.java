package id.universenetwork.utilities.bukkit.Hooks.ViaLegacy.Listeners;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BrewingListener implements org.bukkit.event.Listener {
    @org.bukkit.event.EventHandler(priority = org.bukkit.event.EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerInteract(org.bukkit.event.player.PlayerInteractEvent e) {
        if (!e.hasBlock() || e.getClickedBlock().getType() != Material.BREWING_STAND) return;
        org.bukkit.entity.Player player = e.getPlayer();
        if (com.viaversion.viaversion.api.Via.getAPI().getPlayerVersion(player) > 79) return;
        ItemStack blazePowder = new ItemStack(Material.BLAZE_POWDER);
        ItemStack playerItem = e.getItem();
        if (playerItem == null) playerItem = new ItemStack(Material.AIR);
        org.bukkit.inventory.BrewerInventory inventory = ((org.bukkit.block.BrewingStand) e.getClickedBlock().getState()).getInventory();
        ItemStack fuel = inventory.getFuel();
        if (fuel == null) fuel = new ItemStack(Material.AIR);
        if (e.getAction() == org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK) {
            if (!blazePowder.isSimilar(playerItem)) return;
            if (fuel.getType() != Material.AIR && !fuel.isSimilar(playerItem)) return;
            if (fuel.getAmount() >= 64) return;
            int amount = player.isSneaking() ? Math.min(playerItem.getAmount(), 64 - fuel.getAmount()) : 1;
            if (playerItem.getAmount() == amount) playerItem = new ItemStack(Material.AIR);
            else playerItem.setAmount(playerItem.getAmount() - amount);
            if (fuel.getType() == Material.AIR) fuel = new ItemStack(Material.BLAZE_POWDER, amount);
            else fuel.setAmount(fuel.getAmount() + amount);
        } else {
            if (!blazePowder.isSimilar(fuel)) return;
            if (!blazePowder.isSimilar(playerItem) && playerItem.getType() != Material.AIR) return;
            if (playerItem.getAmount() >= 64) return;
            int amount = player.isSneaking() ? Math.min(fuel.getAmount(), 64 - playerItem.getAmount()) : 1;
            if (fuel.getAmount() == amount) fuel = new ItemStack(Material.AIR);
            else fuel.setAmount(fuel.getAmount() - amount);
            if (playerItem.getType() == Material.AIR) playerItem = new ItemStack(Material.BLAZE_POWDER, amount);
            else playerItem.setAmount(playerItem.getAmount() + amount);
        }
        inventory.setFuel(fuel);
        if (e.getHand() == org.bukkit.inventory.EquipmentSlot.HAND) player.getInventory().setItemInMainHand(playerItem);
        else player.getInventory().setItemInOffHand(playerItem);
        e.setCancelled(true);
    }
}