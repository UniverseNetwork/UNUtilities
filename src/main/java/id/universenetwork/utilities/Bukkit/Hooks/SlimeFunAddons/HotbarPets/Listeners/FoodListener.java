package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Listeners;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.HotbarPet;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Manager.Color.Translator;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem.getById;
import static io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.isItemSimilar;
import static org.bukkit.Bukkit.getScheduler;
import static org.bukkit.Material.ROTTEN_FLESH;
import static org.bukkit.Sound.ENTITY_PIG_AMBIENT;
import static org.bukkit.potion.PotionEffectType.HUNGER;
import static org.bukkit.potion.PotionEffectType.POISON;

public class FoodListener implements Listener {
    final HotbarPet pig;
    final HotbarPet zombie;

    public FoodListener() {
        pig = (HotbarPet) getById("HOTBAR_PET_PIG");
        zombie = (HotbarPet) getById("HOTBAR_PET_ZOMBIE");
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        for (int i = 0; i < 9; ++i) {
            ItemStack item = p.getInventory().getItem(i);
            if (pig != null && isItemSimilar(item, pig.getItem(), true)) {
                if (!p.getInventory().containsAtLeast(pig.getFavouriteFood(), 1)) {
                    p.sendMessage(Translator("&9Your &5Pig Pet &9would have helped you if you did not neglect it by not feeding it :("));
                    return;
                }
                getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                    p.getInventory().removeItem(pig.getFavouriteFood());
                    p.setSaturation(p.getSaturation() + 8.0F);
                    p.removePotionEffect(POISON);
                    p.getWorld().playSound(p.getLocation(), ENTITY_PIG_AMBIENT, 1.0F, 2.0F);
                }, 2L);
            } else if (zombie != null && isItemSimilar(e.getItem(), new ItemStack(ROTTEN_FLESH), true) && isItemSimilar(item, zombie.getItem(), true))
                getScheduler().scheduleSyncDelayedTask(plugin, () -> p.removePotionEffect(HUNGER), 2L);
        }
    }
}