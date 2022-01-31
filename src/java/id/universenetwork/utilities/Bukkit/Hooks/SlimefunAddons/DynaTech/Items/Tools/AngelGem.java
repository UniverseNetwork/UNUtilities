package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Tools;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import org.bukkit.GameMode;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.math.BigDecimal;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTechItems.ANGEL_GEM;
import static org.bukkit.ChatColor.GRAY;

public class AngelGem extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem implements io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable, org.bukkit.event.Listener {
    final ItemSetting<Double> maxFlightSpeed = new ItemSetting<>(this, "max-flight-speed", 1.0d);
    final ItemSetting<Boolean> hasMaxFlightSpeed = new ItemSetting<>(this, "has-max-flight-speed", false);
    float flySpeed = 0.1f;

    public AngelGem(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemSetting(maxFlightSpeed, hasMaxFlightSpeed);
        addItemHandler(onRightClick(), onItemDrop());
        id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListeners(this);
    }

    io.github.thebusybiscuit.slimefun4.core.handlers.ItemDropHandler onItemDrop() {
        return (e, p, itemEntity) -> {
            ItemStack item = itemEntity.getItemStack();
            if (e.getPlayer().getGameMode() != GameMode.CREATIVE && item.getType() == ANGEL_GEM.getType() && io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.isItemSimilar(item, ANGEL_GEM, false, false)) {
                if (e.getPlayer().hasPermission("unutilities.use.angelgem")) {
                    e.getPlayer().setFlying(false);
                    e.getPlayer().setAllowFlight(false);
                    e.getPlayer().setFlySpeed(0.1f);
                } else {
                    e.getPlayer().removePotionEffect(PotionEffectType.LEVITATION);
                    e.getPlayer().removePotionEffect(PotionEffectType.SLOW_FALLING);
                }
                e.getPlayer().setFallDistance(0f);
            } else return false;
            return true;
        };
    }

    io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler onRightClick() {
        return e -> {
            if (e.getPlayer().hasPermission("unutilities.use.angelgem")) {
                if (e.getPlayer().isSneaking()) {
                    e.getPlayer().setFlying(false);
                    e.getPlayer().setAllowFlight(false);
                    e.getPlayer().setFallDistance(0f);
                    e.getItem().setItemMeta(updateLore(e.getItem(), e.getPlayer()));
                }
                if (!e.getPlayer().getAllowFlight()) {
                    e.getPlayer().setAllowFlight(true);
                    setFlySpeed(0.10f);
                } else {
                    if (hasMaxFlightSpeed.getValue()) {
                        if (getFlySpeed() < maxFlightSpeed.getValue()) {
                            if (getFlySpeed() + 0.10f > maxFlightSpeed.getValue())
                                setFlySpeed(maxFlightSpeed.getValue().floatValue());
                            else setFlySpeed(getFlySpeed() + 0.10f);
                        } else setFlySpeed(0.10f);
                    } else {
                        if (getFlySpeed() < 1f) setFlySpeed(getFlySpeed() + 0.10f);
                        else setFlySpeed(0.10f);
                    }
                }
                e.getPlayer().setFlySpeed(getFlySpeed());
                e.getItem().setItemMeta(updateLore(e.getItem(), e.getPlayer()));
            } else {
                if (e.getPlayer().isSneaking()) {
                    e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100000, 0));
                    e.getPlayer().removePotionEffect(PotionEffectType.LEVITATION);
                    e.getItem().setItemMeta(updateLore(e.getItem(), e.getPlayer(), false));
                } else {
                    e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 100000, 0));
                    e.getPlayer().removePotionEffect(PotionEffectType.SLOW_FALLING);
                    e.getItem().setItemMeta(updateLore(e.getItem(), e.getPlayer(), true));
                }
            }
            e.cancel();
        };
    }

    @org.bukkit.event.EventHandler
    public void getItemClicked(org.bukkit.event.inventory.InventoryClickEvent e) {
        List<HumanEntity> views = e.getViewers();
        if (isItem(e.getCursor()) || isItem(e.getCurrentItem())) for (HumanEntity he : views)
            if (he instanceof Player) {
                Player p = (Player) he;
                if (p.hasPermission("unutilities.use.angelgem")) {
                    p.setFlying(false);
                    p.setAllowFlight(false);
                } else {
                    p.removePotionEffect(PotionEffectType.LEVITATION);
                    p.removePotionEffect(PotionEffectType.SLOW_FALLING);
                }
                p.setFallDistance(0f);
            }
    }


    protected ItemMeta updateLore(ItemStack item, Player p) {
        ItemMeta im = item.getItemMeta();
        if (!im.hasLore()) throw new IllegalArgumentException("This item does not have any lore!");
        List<String> lore = im.getLore();
        for (int line = 0; line < lore.size(); line++) {
            if (lore.get(line).contains("Flight: "))
                lore.set(line, GRAY + "Flight: " + (p.getAllowFlight() ? "Enabled" : "Disabled"));
            if (lore.get(line).contains("Flight Speed: "))
                lore.set(line, lore.get(line).replaceFirst(".*", GRAY + "Flight Speed: " + getFlySpeed()));
        }
        im.setLore(lore);
        return im;
    }

    protected ItemMeta updateLore(ItemStack item, Player p, boolean flyeff) {
        ItemMeta im = item.getItemMeta();
        if (!im.hasLore()) throw new IllegalArgumentException("This item does not have any lore!");
        List<String> lore = im.getLore();
        for (int line = 0; line < lore.size(); line++) {
            if (lore.get(line).contains("Flight: "))
                lore.set(line, GRAY + "Flight: " + (flyeff ? "Levitation" : "Slow Falling"));
            if (lore.get(line).contains("Flight Speed: "))
                lore.set(line, lore.get(line).replaceFirst(".*", GRAY + "Flight Speed: " + getFlySpeed()));
        }
        im.setLore(lore);
        return im;
    }

    public float getFlySpeed() {
        return flySpeed;
    }

    public void setFlySpeed(float newFlySpeed) {
        org.apache.commons.lang.Validate.isTrue(newFlySpeed > 0, "Must be greater then 0");
        BigDecimal bd = new BigDecimal(Float.toString(newFlySpeed));
        bd = bd.setScale(1, java.math.RoundingMode.DOWN);
        flySpeed = bd.floatValue();
    }
}