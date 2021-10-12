package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Conditions;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.event.Event;

@Patterns("%booleans%")
public class CondBoolean extends Condition {
    Expression<Boolean> condition;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        condition = (Expression<Boolean>) expressions[0];
        return true;
    }

    @Override
    public boolean check(Event event) {
        return condition.check(event, object -> object);
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "Boolean condition";
    }
}