package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Effects;

import ch.njol.skript.lang.Expression;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

import static org.bukkit.entity.EntityType.PLAYER;

@ch.njol.skript.doc.Name("Add Entity to Team")
@ch.njol.skript.doc.Description({"Adds an entity to a team.", "NOTE: Entities are added to the team via their ID and players are added via their name."})
@ch.njol.skript.doc.Examples("add player to team red")
@ch.njol.skript.doc.Since("1.0")
public class EffAddTeam extends ch.njol.skript.lang.Effect {
    static {
        ch.njol.skript.Skript.registerEffect(EffAddTeam.class, "add %entities% to team %team%");
    }

    Expression<Entity> ets;
    Expression<Team> t;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int m, ch.njol.util.Kleenean i, ch.njol.skript.lang.SkriptParser.ParseResult p) {
        this.ets = (Expression<Entity>) e[0];
        this.t = (Expression<Team>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean d) {
        return "Add entity to team effect with expression entity: " + ets.toString(e, d) + " and expression team: " + t.toString(e, d);
    }

    @Nullable
    @Override
    protected void execute(Event e) {
        if (t != null) for (Entity et : ets.getAll(e)) {
            if (et.getType() == PLAYER) t.getSingle(e).addEntry(et.getName());
            if (!(et.getType() == PLAYER)) t.getSingle(e).addEntry(String.valueOf(et.getUniqueId()));
        }
    }
}