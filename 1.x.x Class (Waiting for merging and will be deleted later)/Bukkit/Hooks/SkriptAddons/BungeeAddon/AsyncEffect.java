package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.BungeeAddon;

import ch.njol.skript.lang.TriggerItem;

import static ch.njol.skript.Skript.getInstance;
import static org.bukkit.Bukkit.getScheduler;

/**
 * Effects that extend this class are ran asynchronously. Next trigger item will
 * be ran in main server thread, as if there had been a delay before.
 * <p>
 * Majority of Skript and Minecraft APIs are not thread-safe, so be careful.
 */
public abstract class AsyncEffect extends DelayFork {
    @Override
    @org.jetbrains.annotations.Nullable
    protected TriggerItem walk(org.bukkit.event.Event e) {
        debug(e, true);
        TriggerItem next = getNext();
        // if (e.getEventName().equals("SkriptStopEvent")) {
        if (e.getClass().isAssignableFrom(ch.njol.skript.events.bukkit.SkriptStopEvent.class)) { // Because a bukkit task can't be created on server stop
            execute(e);
            if (next != null) TriggerItem.walk(next, e);
        } else {
            addDelayedEvent(e);
            // @SuppressWarnings("synthetic-access")
            getScheduler().runTaskAsynchronously(getInstance(), () -> {
                execute(e); // Execute this effect
                if (next != null)
                    getScheduler().runTask(getInstance(), () -> TriggerItem.walk(next, e)); // Walk to next item synchronously // walk(next, e);
            });
        }
        return null;
    }
}