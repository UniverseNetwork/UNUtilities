package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.event.Event;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;

@Patterns("radix %number% of %number%")
public class ExprRadix extends SimpleExpression<String> {
    Expression<Number> radix, from;

    @Override
    protected String[] get(Event event) {
        Number r = radix.getSingle(event);
        Number f = from.getSingle(event);
        if (r == null || f == null) return null;
        return asArray(Integer.toString(f.intValue(), r.intValue()));
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "rad yo";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        radix = (Expression<Number>) expressions[0];
        from = (Expression<Number>) expressions[1];
        return true;
    }
}