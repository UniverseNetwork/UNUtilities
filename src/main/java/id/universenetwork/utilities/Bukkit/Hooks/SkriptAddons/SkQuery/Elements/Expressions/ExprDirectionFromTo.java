package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Direction;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.Location;
import org.bukkit.event.Event;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;


@Patterns("[the] direction from %location% to %location%")
public class ExprDirectionFromTo extends SimpleExpression<Direction> {
    Expression<Location> from, to;

    @Override
    protected Direction[] get(Event event) {
        Location f = from.getSingle(event);
        Location t = to.getSingle(event);
        if (f == null || t == null) return null;
        return asArray(new Direction(t.toVector().subtract(f.toVector())));
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Direction> getReturnType() {
        return Direction.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "dir fr to";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        from = (Expression<Location>) expressions[0];
        to = (Expression<Location>) expressions[1];
        return true;
    }
}