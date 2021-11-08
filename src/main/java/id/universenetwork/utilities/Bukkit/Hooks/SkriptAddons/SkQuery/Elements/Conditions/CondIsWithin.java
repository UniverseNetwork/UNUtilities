package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Conditions;

import ch.njol.skript.lang.Expression;
import org.bukkit.Location;
import org.bukkit.event.Event;

@ch.njol.skript.doc.Name("Is Within Cuboid")
@id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description("Checks whether or not a certain location is included in a 3d cube with 2 endpoints.")
@id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns({"%locations% (is|are) within %location% (to|and) %location%", "%locations% (are|is)(n't| not) within %location% (to|and) %location%"})
public class CondIsWithin extends ch.njol.skript.lang.Condition {
    Expression<Location> locations, pos1, pos2;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parseResult) {
        locations = (Expression<Location>) expressions[0];
        pos1 = (Expression<Location>) expressions[1];
        pos2 = (Expression<Location>) expressions[2];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public boolean check(Event event) {
        Location p1 = pos1.getSingle(event);
        Location p2 = pos2.getSingle(event);
        return locations.check(event, location -> {
            if (location == null || p1 == null || p2 == null) return false;
            return new id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Region.CuboidRegion(p1, p2).checkHas(location.toVector());
        }, isNegated());
    }

    @Override
    public String toString(Event event, boolean debug) {
        return locations.toString(event, debug) + " are within cuboid from " + pos1.toString(event, debug) + " to " + pos2.toString(event, debug);
    }
}