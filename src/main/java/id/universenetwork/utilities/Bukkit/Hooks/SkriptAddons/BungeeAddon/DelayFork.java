package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.BungeeAddon;

// Forked to provide backwards compatibility with older skript versions ie. dev25
public abstract class DelayFork extends ch.njol.skript.effects.Delay {
    public static void addDelayedEvent(org.bukkit.event.Event event) {
        delayed.add(event);
    }
}