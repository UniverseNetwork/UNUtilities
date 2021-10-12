package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Examples;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

@Name("Entity Homing")
@Description("Cause an entity to home towards a locations. Specifying 'normally' reduces bugs caused by varying distances, but makes it less accurate.")
@Examples("make targeted entity home towards player normally")
@Patterns({"make %entity% home towards %location%", "make %entities% home towards %location% normally"})
public class EffHoming extends Effect {
    Expression<Entity> entities;
    Expression<Location> location;
    boolean isNormal;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        entities = (Expression<Entity>) expressions[0];
        location = (Expression<Location>) expressions[1];
        isNormal = matchedPattern == 1;
        return true;
    }

    @Override
    protected void execute(Event event) {
        Location loc = location.getSingle(event);
        if (loc == null) return;
        for (Entity entity : entities.getArray(event)) {
            Vector vector = loc.toVector().subtract(entity.getLocation().toVector());
            entity.setVelocity(isNormal ? vector.normalize() : vector);
        }
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "home from " + entities.toString(event, debug) + " to " + location.toString(event, debug);
    }
}