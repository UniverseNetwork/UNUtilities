package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

@Name("Make Sleep")
@Description("Attempts to make the player sleep at the given location.")
@Patterns("(make|force) %player% [to] sleep at %location%")
public class EffMakeSleep extends Effect {
    Expression<Location> location;
    Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        location = (Expression<Location>) expressions[1];
        player = (Expression<Player>) expressions[0];
        return true;
    }

    @Override
    protected void execute(Event event) {
        if (player == null || location == null) return;
        player.getSingle(event).sleep(location.getSingle(event), true);
    }

    @Override
    public String toString(Event event, boolean debug) {
        if (debug) return "force sleep";
        return "force sleep for " + player.toString(event, debug) + " at " + location.toString(event, debug);
    }
}