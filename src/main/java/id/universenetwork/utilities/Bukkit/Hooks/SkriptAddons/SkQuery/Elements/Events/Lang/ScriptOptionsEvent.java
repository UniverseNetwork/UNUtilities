package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Events.Lang;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ScriptOptionsEvent extends Event {
    static HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}