package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Listeners;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTech;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Events.PicnicBasketFeedPlayerEvent;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Backpacks.PicnicBasket;
import io.github.thebusybiscuit.exoticgarden.items.CustomFood;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Sound.ENTITY_PLAYER_BURP;

public class PicnicBasketListener implements org.bukkit.event.Listener {
    final PicnicBasket picnicBasket;

    public PicnicBasketListener(@NotNull PicnicBasket picnicBasket) {
        id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListener(this);
        this.picnicBasket = picnicBasket;
    }

    @EventHandler
    public void onHungerLoss(org.bukkit.event.entity.FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (e.getFoodLevel() < p.getFoodLevel()) checkAndConsume((Player) e.getEntity());
        }
    }

    @EventHandler
    public void onHungerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.STARVATION)
            checkAndConsume((Player) e.getEntity());
    }

    void checkAndConsume(Player p) {
        if (picnicBasket == null || picnicBasket.isDisabled()) return;
        for (ItemStack item : p.getInventory().getContents())
            if (item != null && item.getType() == picnicBasket.getItem().getType() && item.hasItemMeta() && picnicBasket.isItem(item)) {
                if (io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.canPlayerUseItem(p, picnicBasket.getItem(), true))
                    takeFoodFromPicnicBasket(p, item);
                else return;
            }
    }

    void takeFoodFromPicnicBasket(@NotNull Player p, @NotNull ItemStack picnicBasket) {
        io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile.getBackpack(picnicBasket, backpack -> {
            if (backpack != null) DynaTech.runSync(() -> consumeFood(p, picnicBasket, backpack));
        });
    }

    void consumeFood(Player p, ItemStack picnicBasketItem, io.github.thebusybiscuit.slimefun4.api.player.PlayerBackpack backpack) {
        org.bukkit.inventory.Inventory inv = backpack.getInventory();
        int slot = -1;
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);
            if (item != null) slot = i;
        }
        if (slot >= 0) {
            ItemStack item = inv.getItem(slot);
            PicnicBasketFeedPlayerEvent event = new PicnicBasketFeedPlayerEvent(p, picnicBasket, picnicBasketItem, item);
            org.bukkit.Bukkit.getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                boolean itemConsumed = false;
                SlimefunItem sfItem = SlimefunItem.getByItem(item);
                if (DynaTech.isExoticGardenInstalled && sfItem != null) {
                    if (sfItem instanceof CustomFood) {
                        CustomFood cfItem = (CustomFood) sfItem;
                        if (cfItem.getFoodValue() + p.getFoodLevel() <= 20) {
                            p.setFoodLevel(p.getFoodLevel() + cfItem.getFoodValue());
                            itemConsumed = true;
                        }
                    }
                } else {
                    Material material = item.getType();
                    if (material == Material.COOKED_PORKCHOP || material == Material.PUMPKIN_PIE || material == Material.COOKED_BEEF) {
                        if (p.getFoodLevel() <= 12) {
                            p.setFoodLevel(p.getFoodLevel() + 8);
                            itemConsumed = true;
                        }
                    } else if (material == Material.COOKED_CHICKEN || material == Material.COOKED_MUTTON || material == Material.COOKED_SALMON || material == Material.GOLDEN_CARROT) {
                        if (p.getFoodLevel() <= 14) {
                            p.setFoodLevel(p.getFoodLevel() + 6);
                            itemConsumed = true;
                        }
                    } else if (material == Material.BAKED_POTATO || material == Material.COOKED_RABBIT || material == Material.COOKED_COD || material == Material.BREAD) {
                        if (p.getFoodLevel() <= 15) {
                            p.setFoodLevel(p.getFoodLevel() + 5);
                            itemConsumed = true;
                        }
                    } else if (material == Material.APPLE) {
                        if (p.getFoodLevel() <= 16) {
                            p.setFoodLevel(p.getFoodLevel() + 4);
                            itemConsumed = true;
                        }
                    } else if (material == Material.CARROT || material == Material.BEEF || material == Material.PORKCHOP || material == Material.RABBIT) {
                        if (p.getFoodLevel() <= 17) {
                            p.setFoodLevel(p.getFoodLevel() + 3);
                            itemConsumed = true;
                        }
                    } else if (material == Material.COOKIE || material == Material.MELON_SLICE || material == Material.CHICKEN || material == Material.COD || material == Material.MUTTON || material == Material.SALMON || material == Material.SWEET_BERRIES) {
                        if (p.getFoodLevel() <= 18) {
                            p.setFoodLevel(p.getFoodLevel() + 2);
                            itemConsumed = true;
                        }
                    } else if (material == Material.BEETROOT || material == Material.DRIED_KELP || material == Material.POTATO || material == Material.TROPICAL_FISH) {
                        if (p.getFoodLevel() <= 19) {
                            p.setFoodLevel(p.getFoodLevel() + 1);
                            itemConsumed = true;
                        }
                    }
                }
                if (item.getAmount() > 1 && itemConsumed) {
                    item.setAmount(item.getAmount() - 1);
                    p.playSound(p.getLocation(), ENTITY_PLAYER_BURP, 1F, 1F);
                    p.setSaturation(p.getSaturation() + 4F);
                } else if (itemConsumed) {
                    inv.setItem(slot, null);
                    p.playSound(p.getLocation(), ENTITY_PLAYER_BURP, 1F, 1F);
                    p.setSaturation(p.getSaturation() + 4F);
                }
                backpack.markDirty();
            }
        }
    }
}