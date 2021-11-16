package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common;

import lombok.NoArgsConstructor;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static lombok.AccessLevel.PRIVATE;
import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getPluginManager;

/**
 * A class for registering listeners and event handlers
 *
 * @author Mooy1
 */
@ParametersAreNonnullByDefault
@NoArgsConstructor(access = PRIVATE)
public class Events implements Listener {
    static final Listener LISTENER = new Events();

    /**
     * Calls the given event
     */
    public static <T extends Event> T call(T event) {
        getPluginManager().callEvent(event);
        return event;
    }

    /**
     * Registers the given listener class
     */
    public static void registerListeners(Listener... Listeners) {
        for (Listener l : Listeners)
            try {
                getPluginManager().registerEvents(l, plugin);
            } catch (Exception e) {
                getLogger().severe(prefix + " §cFailed to register listener! [" + l.toString() + "]");
                e.printStackTrace();
            }
    }

    /**
     * Registers the given handler to the given event
     */
    @SuppressWarnings("unchecked")
    public static <T extends Event> void addHandler(Class<T> eventClass, EventPriority priority, boolean ignoreCancelled, Consumer<T> handler) {
        getPluginManager().registerEvent(eventClass, LISTENER, priority, (listener, event) -> handler.accept((T) event), plugin, ignoreCancelled);
    }
}
