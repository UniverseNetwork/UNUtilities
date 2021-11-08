package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;

@ch.njol.skript.doc.Name("Is Divisible")
@id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description("Checks whether or not a number can be divided into another number.")
@id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns({"%numbers% (is|are) divisible by %number%", "%number% (isn't|is not|aren't|are not) divisible by %number%"})
public class CondIsDivisible extends ch.njol.skript.lang.Condition {
    Expression<Number> numbers, divisible;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parseResult) {
        numbers = (Expression<Number>) expressions[0];
        divisible = (Expression<Number>) expressions[1];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public boolean check(Event event) {
        float div = divisible.getSingle(event).floatValue();
        return numbers.check(event, number -> ((number.floatValue() % div) == 0), isNegated());
    }

    @Override
    public String toString(Event event, boolean debug) {
        return PropertyCondition.toString(this, PropertyCondition.PropertyType.BE, event, debug, numbers, "divisible by " + divisible.toString(event, debug));
    }
}