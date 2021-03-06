package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("Suffix of Team")
@ch.njol.skript.doc.Description({"Sets/gets the prefix of the specified team."})
@ch.njol.skript.doc.Examples({"set suffix of team red to \"happypockets\"", "broadcast \"%suffix of team happypockets%\""})
@ch.njol.skript.doc.Since("1.0")
public class ExprTeamSuffix extends ch.njol.skript.lang.util.SimpleExpression<String> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprTeamSuffix.class, String.class, ch.njol.skript.lang.ExpressionType.COMBINED, "suffix of team %team%");
    }

    Expression<Team> team;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
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
        return "Suffix of team expression with expression team: " + team.toString(event, debug);
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        if (team.getSingle(event) != null) return new String[]{team.getSingle(event).getSuffix()};
        return null;
    }

    @Override
    public void change(Event event, Object[] delta, ChangeMode mode) {
        Team t = team.getSingle(event);
        if (t != null) {
            if (mode == ChangeMode.SET) {
                String x = ((String) delta[0]);
                t.setSuffix(x);
            } else if (mode == ChangeMode.RESET) t.setSuffix("");
        }
    }

    @Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.RESET)
            return ch.njol.util.coll.CollectionUtils.array(String.class);
        return null;
    }
}