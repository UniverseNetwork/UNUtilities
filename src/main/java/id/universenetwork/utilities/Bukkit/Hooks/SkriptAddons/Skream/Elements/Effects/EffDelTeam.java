package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Effects;

import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;

@ch.njol.skript.doc.Name("Delete/Unregister Team")
@ch.njol.skript.doc.Description({"Unregisters/Deletes a team"})
@ch.njol.skript.doc.Examples("unregister team red")
@ch.njol.skript.doc.Since("1.0")
public class EffDelTeam extends ch.njol.skript.lang.Effect {
    static {
        ch.njol.skript.Skript.registerEffect(EffDelTeam.class, "(delete|remove|unregister) [the] team %team%");
    }

    Expression<Team> team;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        this.team = (Expression<Team>) expressions[0];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event event, boolean debug) {
        return "Unregister team effect with expression team: " + team.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        if (team != null) team.getSingle(event).unregister();
    }
}