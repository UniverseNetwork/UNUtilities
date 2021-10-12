package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.conditions.base.PropertyCondition.PropertyType;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.event.Event;

@Name("Within Border")
@Description("Checks whether or not a location is within the WorldBorder.")
@Patterns({"%locations% is [with]in [world[ ]border[s]] %worldborder%", "%locations% (isn't|is not) [with]in [world[ ]border[s]] %worldborder%"})
public class CondWithinBorder extends Condition {
    Expression<WorldBorder> worldBorder;
    Expression<Location> locations;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        locations = (Expression<Location>) expressions[0];
        worldBorder = (Expression<WorldBorder>) expressions[1];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public boolean check(Event event) {
        WorldBorder border = worldBorder.getSingle(event);
        return locations.check(event, border::isInside, isNegated());
    }

    @Override
    public String toString(Event event, boolean debug) {
        return PropertyCondition.toString(this, PropertyType.CAN, event, debug, locations, " are within border " + worldBorder.toString(event, debug));
    }
}