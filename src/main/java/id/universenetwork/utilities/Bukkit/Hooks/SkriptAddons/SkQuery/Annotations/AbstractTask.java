package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations;

import org.bukkit.event.Event;

/**
 * A runnable with a set of tools that help for registering events.
 */
public abstract class AbstractTask implements Runnable {
    protected void registerEvent(String name, Class<? extends Event> event, String... patterns) {
        registerEvent(name, ch.njol.skript.lang.util.SimpleEvent.class, event, patterns);
    }

    protected void registerEvent(String name, Class<? extends ch.njol.skript.lang.SkriptEvent> handler, Class<? extends Event> event, final String... patterns) {
        id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Documentation.addEvent(event, patterns);
        ch.njol.skript.Skript.registerEvent(name, handler, event, patterns);
    }
}