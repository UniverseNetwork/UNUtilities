package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Events;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Backpacks.PicnicBasket;
import org.apache.commons.lang.Validate;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PicnicBasketFeedPlayerEvent extends org.bukkit.event.player.PlayerEvent implements org.bukkit.event.Cancellable {
    public static final HandlerList handlers = new HandlerList();
    final PicnicBasket picnicBasket;
    final ItemStack picnicBasketItem;
    ItemStack itemConsumed;
    boolean cancelled;

    @javax.annotation.ParametersAreNonnullByDefault
    public PicnicBasketFeedPlayerEvent(org.bukkit.entity.Player player, PicnicBasket picnicBasket, ItemStack picnicBasketItem, ItemStack itemConsumed) {
        super(player);
        this.picnicBasket = picnicBasket;
        this.picnicBasketItem = picnicBasketItem;
        this.itemConsumed = itemConsumed;
    }

    @NotNull
    public PicnicBasket getPicnicBasket() {
        return picnicBasket;
    }

    @NotNull
    public ItemStack getPicnicBasketItem() {
        return picnicBasketItem;
    }

    @NotNull
    public ItemStack getItemConsumed() {
        return itemConsumed.clone();
    }

    public void setConsumedItem(@NotNull ItemStack item) {
        Validate.notNull(item, "Consumed item can not be null.");
        Validate.isTrue(item.getType().isEdible(), "Item must be edible.");
        this.itemConsumed = item;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return getHandlerList();
    }
}