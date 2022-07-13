package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Expressions;

import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

@ch.njol.skript.doc.Name("Entries of Team")
@ch.njol.skript.doc.Description({"Returns a list containing the entries in the specified team. (Loop-able)"})
@ch.njol.skript.doc.Examples("broadcast \"%entries of team happypockets%\"")
@ch.njol.skript.doc.Since("1.0")
public class ExprTeamEntries extends ch.njol.skript.lang.util.SimpleExpression<String> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprTeamEntries.class, String.class, ch.njol.skript.lang.ExpressionType.COMBINED, "entries of team %team%");
    }

    Expression<Team> team;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        team = (Expression<Team>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Entries of team expression with expression team: " + team.toString(event, debug);
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        if (team.getSingle(event) != null) {
            ArrayList<String> entries = new ArrayList<>(team.getSingle(event).getEntries());
            return entries.toArray(new String[0]);
        }
        return null;
    }
}