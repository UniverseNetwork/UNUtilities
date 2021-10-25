package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Tools;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.math.BigDecimal;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTechItems.ANGEL_GEM;

public class AngelGem extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem implements io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable, org.bukkit.event.Listener {
    final ItemSetting<Double> maxFlightSpeed = new ItemSetting<>(this, "max-flight-speed", 1.0d);
    final ItemSetting<Boolean> hasMaxFlightSpeed = new ItemSetting<>(this, "has-max-flight-speed", false);
    float flySpeed = 0.1f;

    public AngelGem(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemSetting(maxFlightSpeed, hasMaxFlightSpeed);
        addItemHandler(onRightClick(), onItemDrop());
        id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListener(this);
    }

    io.github.thebusybiscuit.slimefun4.core.handlers.ItemDropHandler onItemDrop() {
        return (e, p, itemEntity) -> {
            ItemStack item = itemEntity.getItemStack();
            if (e.getPlayer().getGameMode() != GameMode.CREATIVE && item.getType() == ANGEL_GEM.getType() && io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.isItemSimilar(item, ANGEL_GEM, false, false)) {
                e.getPlayer().setFlying(false);
                e.getPlayer().setAllowFlight(false);
                e.getPlayer().setFlySpeed(0.1f);
                e.getPlayer().setFallDistance(0.0f);
            } else return false;
            return true;
        };
    }

    io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler onRightClick() {
        return e -> {
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
                        if (getFlySpeed() + 0.10f > maxFlightSpeed.getValue()) {
                            setFlySpeed(maxFlightSpeed.getValue().floatValue());
                        } else {
                            setFlySpeed(getFlySpeed() + 0.10f);
                        }
                    } else {
                        setFlySpeed(0.10f);
                    }
                } else {
                    if (getFlySpeed() < 1f) {
                        setFlySpeed(getFlySpeed() + 0.10f);
                    } else {
                        setFlySpeed(0.10f);
                    }
                }
            }
            e.getPlayer().setFlySpeed(getFlySpeed());
            e.getItem().setItemMeta(updateLore(e.getItem(), e.getPlayer()));
            e.cancel();
        };
    }

    @org.bukkit.event.EventHandler
    public void getItemClicked(org.bukkit.event.inventory.InventoryClickEvent e) {
        List<HumanEntity> views = e.getViewers();
        if (isItem(e.getCursor()) || isItem(e.getCurrentItem())) for (HumanEntity he : views)
            if (he instanceof Player) {
                Player p = (Player) he;
                p.setFlying(false);
                p.setAllowFlight(false);
                p.setFallDistance(0f);
            }
    }


    protected ItemMeta updateLore(ItemStack item, Player p) {
        ItemMeta im = item.getItemMeta();

        if (!im.hasLore()) {
            throw new IllegalArgumentException("This item does not have any lore!");
        }

        List<String> lore = im.getLore();

        for (int line = 0; line < lore.size(); line++) {
            if (lore.get(line).contains("Flight: <enabled>"))
                lore.set(line, lore.get(line).replace("<enabled>", p.getAllowFlight() ? "Enabled" : "Disabled"));
            if (lore.get(line).contains(ChatColor.GRAY + "Flight Speed: ")) {
                lore.set(line, lore.get(line).replaceFirst(".*", ChatColor.GRAY + "Flight Speed: " + getFlySpeed()));
            }
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