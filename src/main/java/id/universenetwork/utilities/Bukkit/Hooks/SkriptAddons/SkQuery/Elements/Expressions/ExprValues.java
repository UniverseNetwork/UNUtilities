package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Examples;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.event.Event;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.toFriendlyStringArray;

@Patterns("[all ]enum values of %classinfo%")
@Examples("message \"%all enum values of particle%\"")
public class ExprValues extends SimpleExpression<String> {
    @SuppressWarnings("rawtypes")
    Expression<ClassInfo> cInfo;

    @SuppressWarnings("rawtypes")
    @Override
    protected String[] get(Event event) {
        ClassInfo c = cInfo.getSingle(event);
        return c.getC().isEnum() ? toFriendlyStringArray(c.getC().getEnumConstants()) : null;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "enum values";
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        cInfo = (Expression<ClassInfo>) expressions[0];
        return true;
    }
}