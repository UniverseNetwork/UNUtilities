package id.universenetwork.utilities.Bukkit.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.annotation.Nonnull;

public class UNUtilitiesDisableEvent extends Event {
    static final HandlerList handler = new HandlerList();

    @Nonnull
    @Override
    public HandlerList getHandlers() {
        return handler;
    }

    public static HandlerList getHandlerList() {
        return handler;
    }
}