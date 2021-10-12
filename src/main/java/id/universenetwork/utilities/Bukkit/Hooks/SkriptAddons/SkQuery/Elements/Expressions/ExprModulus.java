package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.event.Event;

import javax.annotation.Nonnull;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;

@Patterns("%number% mod %number%")
public class ExprModulus extends SimpleExpression<Number> {
    Expression<Number> first, second;

    @Override
    protected Number[] get(Event event) {
        Number f = first.getSingle(event);
        Number s = second.getSingle(event);
        if (f == null || s == null) return null;
        return asArray(f.floatValue() % s.floatValue());
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "maths";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(@Nonnull Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        first = (Expression<Number>) expressions[0];
        second = (Expression<Number>) expressions[1];
        return true;
    }
}