package id.universenetwork.utilities.Bukkit.Events;

import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ReloadConfigEvent extends org.bukkit.event.Event {
    private static final HandlerList handler = new HandlerList();

    @Override
    public @NotNull HandlerList getHandlers() {
        return handler;
    }

    public static HandlerList getHandlerList() {
        return handler;
    }
}
