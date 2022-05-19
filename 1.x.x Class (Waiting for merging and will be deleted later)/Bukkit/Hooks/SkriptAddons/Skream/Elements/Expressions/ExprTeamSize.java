package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Expressions;

import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("Size of Team")
@ch.njol.skript.doc.Description({"Returns the integer value of the size of the specified team."})
@ch.njol.skript.doc.Examples("broadcast \"%size of team happypockets%\"")
@ch.njol.skript.doc.Since("1.0")
public class ExprTeamSize extends ch.njol.skript.lang.util.SimpleExpression<Integer> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprTeamSize.class, Integer.class, ch.njol.skript.lang.ExpressionType.COMBINED, "size of team %team%");
    }

    Expression<Team> team;

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        team = (Expression<Team>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Size of team expression with expression team: " + team.toString(event, debug);
    }

    @Override
    @Nullable
    protected Integer[] get(Event event) {
        if (team.getSingle(event) != null) return new Integer[]{team.getSingle(event).getSize()};
        return null;
    }
}