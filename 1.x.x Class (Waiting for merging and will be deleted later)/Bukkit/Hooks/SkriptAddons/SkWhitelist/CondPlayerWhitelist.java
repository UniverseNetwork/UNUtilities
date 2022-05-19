package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkWhitelist;

import ch.njol.skript.lang.Expression;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

/**
 * Created by tim740 on 05/04/2016
 */
public class CondPlayerWhitelist extends ch.njol.skript.lang.Condition {
    Expression<Player> usr;

    @Override
    public boolean check(Event e) {
        boolean chk = usr.getSingle(e).isWhitelisted();
        return (isNegated() != chk);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, ch.njol.util.Kleenean k, ch.njol.skript.lang.SkriptParser.ParseResult p) {
        usr = (Expression<Player>) e[0];
        setNegated(i == 1);
        return true;
    }

    @Override
    public String toString(Event e, boolean b) {
        return getClass().getName();
    }
}