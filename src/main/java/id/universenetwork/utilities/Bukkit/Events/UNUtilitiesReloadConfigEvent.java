package id.universenetwork.utilities.Bukkit.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class UNUtilitiesReloadConfigEvent extends Event {
    static final HandlerList handler = new HandlerList();

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handler;
    }

    public static HandlerList getHandlerList() {
        return handler;
    }
}
