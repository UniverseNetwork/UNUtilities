package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Listeners;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.HotbarPet;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListener;
import static io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem.getById;
import static io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.isItemSimilar;
import static org.bukkit.Material.PUMPKIN_PIE;

public class SoulPieListener implements org.bukkit.event.Listener {
    final HotbarPet eyamaz;

    public SoulPieListener() {
        eyamaz = (HotbarPet) getById("HOTBAR_PET_EYAMAZ");
        registerListener(this);
    }

    @EventHandler
    public void onSoulHarvest(EntityDeathEvent e) {
        if (e.getEntity().getKiller() != null) {
            Player p = e.getEntity().getKiller();
            for (int i = 0; i < 9; ++i) {
                ItemStack item = p.getInventory().getItem(i);
                if (eyamaz != null && isItemSimilar(item, eyamaz.getItem(), true))
                    e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), new CustomItemStack(new ItemStack(PUMPKIN_PIE), "&bSoul Pie"));
            }
        }
    }
}