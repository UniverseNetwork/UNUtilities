package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.parser.ParserInstance;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Events.Bukkit.AttachedTabCompleteEvent;
import org.bukkit.event.Event;

import static ch.njol.skript.log.ErrorQuality.SEMANTIC_ERROR;
import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;

@Patterns("arg[ument] at %number%")
public class ExprTabCompleteArgument extends SimpleExpression<String> {
    Expression<Number> num;

    @Override
    protected String[] get(Event e) {
        Number n = num.getSingle(e);
        if (n == null) return null;
        try {
            return asArray(((AttachedTabCompleteEvent) e).getArgs()[n.intValue() - 1]);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
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
    public String toString(Event e, boolean debug) {
        return "tab results";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        if (!ParserInstance.get().isCurrentEvent(AttachedTabCompleteEvent.class)) {
            Skript.error("Tab completers can only be accessed from tab complete events.", SEMANTIC_ERROR);
            return false;
        }
        num = (Expression<Number>) exprs[0];
        return true;
    }
}