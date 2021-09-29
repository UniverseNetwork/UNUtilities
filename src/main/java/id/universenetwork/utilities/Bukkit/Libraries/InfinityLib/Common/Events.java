package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static org.bukkit.Bukkit.getPluginManager;

@ParametersAreNonnullByDefault
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Events implements Listener {
    static final Listener LISTENER = new Events();

    public static <T extends Event> T call(T event) {
        getPluginManager().callEvent(event);
        return event;
    }

    public static void registerListener(Listener listener) {
        getPluginManager().registerEvents(listener, plugin);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Event> void addHandler(Class<T> eventClass, EventPriority priority, boolean ignoreCancelled, Consumer<T> handler) {
        getPluginManager().registerEvent(eventClass, LISTENER, priority, (listener, event) -> handler.accept((T) event), plugin, ignoreCancelled);
    }
}