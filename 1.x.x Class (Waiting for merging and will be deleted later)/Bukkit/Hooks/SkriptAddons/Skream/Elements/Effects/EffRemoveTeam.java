package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Effects;

import ch.njol.skript.lang.Expression;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

import static org.bukkit.entity.EntityType.PLAYER;

@ch.njol.skript.doc.Name("Remove Entity from Team")
@ch.njol.skript.doc.Description({"Removes an entity from a team.", "NOTE: Entities are removed from the team via their ID and players are removed via their name."})
@ch.njol.skript.doc.Examples("remove player from team red")
@ch.njol.skript.doc.Since("1.0")
public class EffRemoveTeam extends ch.njol.skript.lang.Effect {
    static {
        ch.njol.skript.Skript.registerEffect(EffRemoveTeam.class, "(remove|delete) %entities% from team %team%");
    }

    Expression<Entity> entity;
    Expression<Team> team;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        this.entity = (Expression<Entity>) expressions[0];
        this.team = (Expression<Team>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Remove entity from team effect with expression entity: " + entity.toString(event, debug) + " and expression team: " + team.toString(event, debug);
    }

    @Nullable
    @Override
    protected void execute(Event event) {
        if (team != null) {
            for (org.bukkit.entity.Entity e : entity.getAll(event)) {
                if (e.getType() == PLAYER)
                    if (team.getSingle(event).hasEntry(e.getName())) team.getSingle(event).removeEntry(e.getName());
                if (!(e.getType() == PLAYER))
                    if (team.getSingle(event).hasEntry(String.valueOf(e.getUniqueId())))
                        team.getSingle(event).removeEntry(String.valueOf(e.getUniqueId()));
            }
        }
    }
}