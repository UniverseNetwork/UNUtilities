package id.universenetwork.utilities.Bukkit.Events;

import org.bukkit.event.HandlerList;

public class ReloadConfigEvent extends org.bukkit.event.Event {
    static final HandlerList handler = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handler;
    }

    public static HandlerList getHandlerList() {
        return handler;
    }
}
