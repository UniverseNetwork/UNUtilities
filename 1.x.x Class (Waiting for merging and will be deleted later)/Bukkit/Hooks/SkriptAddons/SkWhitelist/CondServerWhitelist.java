package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkWhitelist;

import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;

/**
 * Created by tim740 on 05/04/2016
 */
public class CondServerWhitelist extends ch.njol.skript.lang.Condition {
    @Override
    public boolean check(Event e) {
        boolean chk = org.bukkit.Bukkit.hasWhitelist();
        return (isNegated() != chk);
    }

    @Override
    public boolean init(Expression<?>[] e, int i, ch.njol.util.Kleenean k, ch.njol.skript.lang.SkriptParser.ParseResult p) {
        setNegated(i == 1);
        return true;
    }

    @Override
    public String toString(Event e, boolean b) {
        return getClass().getName();
    }
}