package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Listeners;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.HotbarPet;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem.getById;
import static io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.isItemSimilar;
import static org.bukkit.Bukkit.getScheduler;

public class FoodListener implements org.bukkit.event.Listener {
    final HotbarPet pig;
    final HotbarPet zombie;

    public FoodListener() {
        pig = (HotbarPet) getById("HOTBAR_PET_PIG");
        zombie = (HotbarPet) getById("HOTBAR_PET_ZOMBIE");
    }

    @org.bukkit.event.EventHandler
    public void onEat(org.bukkit.event.player.PlayerItemConsumeEvent e) {
        org.bukkit.entity.Player p = e.getPlayer();
        for (int i = 0; i < 9; ++i) {
            ItemStack item = p.getInventory().getItem(i);
            if (pig != null && isItemSimilar(item, pig.getItem(), true)) {
                if (!p.getInventory().containsAtLeast(pig.getFavouriteFood(), 1)) {
                    p.sendMessage(id.universenetwork.utilities.Bukkit.Utils.Color.Translate("&9Your &5Pig Pet &9would have helped you if you did not neglect it by not feeding it :("));
                    return;
                }
                getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                    p.getInventory().removeItem(pig.getFavouriteFood());
                    p.setSaturation(p.getSaturation() + 8.0F);
                    p.removePotionEffect(PotionEffectType.POISON);
                    p.getWorld().playSound(p.getLocation(), org.bukkit.Sound.ENTITY_PIG_AMBIENT, 1.0F, 2.0F);
                }, 2L);
            } else if (zombie != null && isItemSimilar(e.getItem(), new ItemStack(org.bukkit.Material.ROTTEN_FLESH), true) && isItemSimilar(item, zombie.getItem(), true))
                getScheduler().scheduleSyncDelayedTask(plugin, () -> p.removePotionEffect(PotionEffectType.HUNGER), 2L);
        }
    }
}