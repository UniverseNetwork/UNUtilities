package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Conditions;

import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;

@id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns("%booleans%")
public class CondBoolean extends ch.njol.skript.lang.Condition {
    Expression<Boolean> condition;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parseResult) {
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