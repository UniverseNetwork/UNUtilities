package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Expressions;

import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("Team by Name")
@ch.njol.skript.doc.Description({"Allows you to get the team type via an expression."})
@ch.njol.skript.doc.Examples("set {team} to team \"hello\"")
@ch.njol.skript.doc.Since("1.0")
public class ExprTeamByName extends ch.njol.skript.lang.util.SimpleExpression<Team> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprTeamByName.class, Team.class, ch.njol.skript.lang.ExpressionType.COMBINED, "team %string%");
    }

    Expression<String> name;

    @Override
    public Class<? extends Team> getReturnType() {
        return Team.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        name = (Expression<String>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Team expression with expression string: " + name.toString(event, debug);
    }

    @Override
    @Nullable
    protected Team[] get(Event event) {
        if (name.getSingle(event) != null)
            return new Team[]{org.bukkit.Bukkit.getServer().getScoreboardManager().getMainScoreboard().getTeam(name.getSingle(event))};
        return null;
    }

    @Override
    public Class<?>[] acceptChange(final ch.njol.skript.classes.Changer.ChangeMode mode) {
        return null;
    }
}