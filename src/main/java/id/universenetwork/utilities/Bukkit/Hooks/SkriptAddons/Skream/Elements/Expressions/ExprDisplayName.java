package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("Display Name of Team")
@ch.njol.skript.doc.Description({"Sets/gets the display-name of the specified team shown in /team list.", "NOTE: The original name of the team will still need to be used when modifying the team in any way."})
@ch.njol.skript.doc.Examples("set display name of team red to \"happypockets\", broadcast \"%displayname of team red%\"")
@ch.njol.skript.doc.Since("1.0")
public class ExprDisplayName extends ch.njol.skript.lang.util.SimpleExpression<String> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprDisplayName.class, String.class, ch.njol.skript.lang.ExpressionType.COMBINED, "display[( |-)]name of team %team%");
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
        return "Display-name expression with expression team: " + team.toString(event, debug);
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        if (team.getSingle(event) != null) return new String[]{team.getSingle(event).getDisplayName()};
        return null;
    }

    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode) {
        Team t = team.getSingle(event);
        if (t != null) if (mode == Changer.ChangeMode.SET) {
            String x = ((String) delta[0]);
            t.setDisplayName(x);
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) return ch.njol.util.coll.CollectionUtils.array(String.class);
        return null;
    }
}