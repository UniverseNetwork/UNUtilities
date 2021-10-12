package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript;

import org.bukkit.event.Event;

import java.util.HashMap;

public class TransEventObjects {
    static final HashMap<Event, Object> limbo = new HashMap<>();

    public static void store(Event key, Object value) {
        limbo.put(key, value);
    }

    public static Object retrieve(Event key) {
        Object value = limbo.get(key);
        limbo.remove(key);
        return value;
    }
}