package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Listeners;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.HotbarPet;
import org.bukkit.inventory.ItemStack;

public class SoulPieListener implements org.bukkit.event.Listener {
    final HotbarPet eyamaz;

    public SoulPieListener() {
        eyamaz = (HotbarPet) io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem.getById("HOTBAR_PET_EYAMAZ");
    }

    @org.bukkit.event.EventHandler
    public void onSoulHarvest(org.bukkit.event.entity.EntityDeathEvent e) {
        if (e.getEntity().getKiller() != null) {
            org.bukkit.entity.Player p = e.getEntity().getKiller();
            for (int i = 0; i < 9; ++i) {
                ItemStack item = p.getInventory().getItem(i);
                if (eyamaz != null && io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.isItemSimilar(item, eyamaz.getItem(), true))
                    e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), new io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack(new ItemStack(org.bukkit.Material.PUMPKIN_PIE), "&bSoul Pie"));
            }
        }
    }
}