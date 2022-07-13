package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkWhitelist;

import org.bukkit.event.Event;

/**
 * Created by tim740 on 05/04/16
 */
public class EffReloadWhitelist extends ch.njol.skript.lang.Effect {
    @Override
    protected void execute(Event e) {
        org.bukkit.Bukkit.reloadWhitelist();
    }

    @Override
    public boolean init(ch.njol.skript.lang.Expression<?>[] e, int i, ch.njol.util.Kleenean k, ch.njol.skript.lang.SkriptParser.ParseResult p) {
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event e, boolean b) {
        return getClass().getName();
    }
}