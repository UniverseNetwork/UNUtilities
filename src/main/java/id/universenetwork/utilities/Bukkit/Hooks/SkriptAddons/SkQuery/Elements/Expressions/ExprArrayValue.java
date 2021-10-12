package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.event.Event;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.newArray;

@Patterns("index %number% of %objects%")
public class ExprArrayValue extends SimpleExpression<Object> {
    Expression<Number> index;
    Expression<Object> objects;
    Class<?> returnType = Object.class;

    @Override
    protected Object[] get(Event e) {
        Object[] array = newArray(returnType, 1);
        Number n = index.getSingle(e);
        if (n == null) return null;
        int index = n.intValue();
        array[0] = objects.getAll(e)[index];
        try {
            return array;
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<?> getReturnType() {
        return returnType;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "index";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        index = (Expression<Number>) exprs[0];
        objects = (Expression<Object>) exprs[1];
        returnType = objects.getReturnType();
        return true;
    }
}