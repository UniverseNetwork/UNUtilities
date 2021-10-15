package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Listeners;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.HotbarPet;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.concurrent.ThreadLocalRandom;

import static id.universenetwork.utilities.Bukkit.Manager.Color.Translator;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem.getById;
import static io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.isItemSimilar;
import static org.bukkit.Bukkit.getPluginManager;
import static org.bukkit.Sound.*;

public class DamageListener implements Listener {
    final HotbarPet creeper;
    final HotbarPet magmacube;
    final HotbarPet slime;
    final HotbarPet mrCookieSlime;
    final HotbarPet wither;
    final HotbarPet walshrus;
    final HotbarPet blaze;

    public DamageListener() {
        creeper = (HotbarPet) getById("HOTBAR_PET_CREEPER");
        magmacube = (HotbarPet) getById("HOTBAR_PET_MAGMA_CUBE");
        slime = (HotbarPet) getById("HOTBAR_PET_SLIME");
        mrCookieSlime = (HotbarPet) getById("HOTBAR_PET_MRCOOKIESLIME");
        wither = (HotbarPet) getById("HOTBAR_PET_WITHER");
        walshrus = (HotbarPet) getById("HOTBAR_PET_WALSHRUS");
        blaze = (HotbarPet) getById("HOTBAR_PET_BLAZE");
        getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            for (int i = 0; i < 9; ++i) {
                ItemStack item = p.getInventory().getItem(i);
                switch (e.getCause()) {
                    case ENTITY_EXPLOSION:
                    case BLOCK_EXPLOSION:
                        if (creeper != null && isItemSimilar(item, creeper.getItem(), true)) {
                            if (!creeper.checkAndConsumeFood(p)) return;
                            e.setCancelled(true);
                            p.getWorld().playSound(p.getLocation(), ENTITY_CREEPER_PRIMED, 1.0F, 2.0F);
                        }
                        break;
                    case LAVA:
                        if (magmacube != null && isItemSimilar(item, magmacube.getItem(), true)) {
                            if (!p.getInventory().containsAtLeast(magmacube.getFavouriteFood(), 1)) {
                                p.sendMessage(Translator("&9Your &4Magma Cube Pet &9would have helped you if you did not neglect it by not feeding it :("));
                                return;
                            }
                            if (ThreadLocalRandom.current().nextInt(100) < 30)
                                p.getInventory().removeItem(magmacube.getFavouriteFood());
                            e.setCancelled(true);
                            p.getWorld().playSound(p.getLocation(), ENTITY_MAGMA_CUBE_JUMP, 1.0F, 2.0F);
                        }
                        break;
                    case FALL:
                        if (slime != null && isItemSimilar(item, slime.getItem(), true)) {
                            if (!slime.checkAndConsumeFood(p)) return;
                            e.setCancelled(true);
                            p.getWorld().playSound(p.getLocation(), BLOCK_SLIME_BLOCK_STEP, 1.0F, 2.0F);
                        }
                        if (mrCookieSlime != null && isItemSimilar(item, mrCookieSlime.getItem(), true)) {
                            if (!mrCookieSlime.checkAndConsumeFood(p)) return;
                            e.setCancelled(true);
                            p.getWorld().playSound(p.getLocation(), BLOCK_SLIME_BLOCK_STEP, 1.0F, 2.0F);
                        }
                        break;
                    case WITHER:
                        if (wither != null && isItemSimilar(item, wither.getItem(), true)) {
                            if (!wither.checkAndConsumeFood(p)) return;
                            e.setCancelled(true);
                            p.removePotionEffect(PotionEffectType.WITHER);
                            p.getWorld().playSound(p.getLocation(), ENTITY_WITHER_AMBIENT, 1.0F, 2.0F);
                        }
                        break;
                    case DROWNING:
                        if (walshrus != null && isItemSimilar(item, walshrus.getItem(), true)) {
                            if (!walshrus.checkAndConsumeFood(p)) return;
                            e.setCancelled(true);
                        }
                        break;
                    case FIRE:
                    case FIRE_TICK:
                        if (blaze != null && isItemSimilar(item, blaze.getItem(), true)) {
                            if (!blaze.checkAndConsumeFood(p)) return;
                            e.setCancelled(true);
                            p.setFireTicks(0);
                            p.getWorld().playSound(p.getLocation(), ENTITY_BLAZE_AMBIENT, 1.0F, 2.0F);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }
}