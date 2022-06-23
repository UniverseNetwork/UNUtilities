package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common;

import id.universenetwork.utilities.Bukkit.UNUtilities;
import id.universenetwork.utilities.Bukkit.Utils.Logger;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.function.Consumer;
import java.util.logging.Level;

/**
 * A class for registering listeners and event handlers
 *
 * @author Mooy1
 * @author ARVIN3108 ID
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Events implements Listener {
    /**
     * Calls the given event
     */
    public static <T extends Event> T call(T event) {
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    /**
     * Registers the given listener class
     */
    public static void registerListeners(Listener... Listeners) {
        for (Listener l : Listeners)
            try {
                Bukkit.getPluginManager().registerEvents(l, UNUtilities.plugin);
            } catch (Exception e) {
                Logger.log(Level.SEVERE, "Failed to register listener! [" + l.toString() + "]", e);
            }
    }

    /**
     * Registers the given handler to the given event
     */
    public static <T extends Event> void addHandler(Class<T> eventClass, EventPriority priority, boolean ignoreCancelled, Consumer<T> handler) {
        Bukkit.getPluginManager().registerEvent(eventClass, new Events(), priority, (listener, event) -> handler.accept((T) event), UNUtilities.plugin, ignoreCancelled);
    }
}
