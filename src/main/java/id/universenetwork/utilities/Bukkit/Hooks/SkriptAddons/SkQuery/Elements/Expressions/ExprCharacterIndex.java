package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Patterns("char[acter][s] at [index] %numbers% (within|in) %strings%")
public class ExprCharacterIndex extends SimpleExpression<String> {
    Expression<Number> indices;
    Expression<String> strings;

    @Override
    public boolean isSingle() {
        return indices.isSingle() && strings.isSingle();
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        indices = (Expression<Number>) exprs[0];
        strings = (Expression<String>) exprs[1];
        return true;
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        List<String> characters = new ArrayList<>();
        for (String string : strings.getArray(event))
            for (Number index : indices.getArray(event)) characters.add("" + string.charAt(index.intValue()));
        return characters.toArray(new String[0]);
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "characters at " + indices.toString(event, debug) + " within " + strings.toString(event, debug);
    }
}