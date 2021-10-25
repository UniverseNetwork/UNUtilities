package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Tasks;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Misc.ItemBand;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTech.runSync;

public class ItemBandTask implements Runnable {
    // The value if not null will be a SlIMEFUN_ID that is an Item
    @Override
    public void run() {
        for (Player p : org.bukkit.Bukkit.getOnlinePlayers()) {
            if (!p.isValid() || p.isDead()) continue;
            for (ItemStack item : p.getEquipment().getArmorContents()) testItemBand(p, item);
            testItemBand(p, p.getEquipment().getItemInMainHand());
        }
    }

    static void testItemBand(@org.jetbrains.annotations.NotNull Player p, @org.jetbrains.annotations.Nullable ItemStack item) {
        if (item != null && item.getType() != org.bukkit.Material.AIR && item.hasItemMeta()) {
            String id = io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI.getString(item.getItemMeta(), ItemBand.KEY);
            if (id != null) {
                SlimefunItem sfItem = SlimefunItem.getById(id);
                if (sfItem instanceof ItemBand) {
                    ItemBand itemBand = (ItemBand) sfItem;
                    runSync(() -> {
                        for (org.bukkit.potion.PotionEffect pe : itemBand.getPotionEffects())
                            if (pe.getType() == org.bukkit.potion.PotionEffectType.HEALTH_BOOST) {
                                double health = p.getHealth();
                                p.addPotionEffect(pe);
                                p.setHealth(Math.min(health, p.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getValue()));
                            } else p.addPotionEffect(pe);
                    });
                }
            }
        }
    }
}