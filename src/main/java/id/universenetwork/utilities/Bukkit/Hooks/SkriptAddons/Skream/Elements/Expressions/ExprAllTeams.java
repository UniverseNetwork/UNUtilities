package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Expressions;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

@ch.njol.skript.doc.Name("All Teams")
@ch.njol.skript.doc.Description({"Returns a list containing the teams. (Loop-able)"})
@ch.njol.skript.doc.Examples({"broadcast \"%all teams%\"", "set {_} to a random element out of all teams", "loop all teams:"})
@ch.njol.skript.doc.Since("1.0")
public class ExprAllTeams extends ch.njol.skript.lang.util.SimpleExpression<String> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprAllTeams.class, String.class, ch.njol.skript.lang.ExpressionType.COMBINED, "[all] teams");
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public boolean init(ch.njol.skript.lang.Expression<?>[] exprs, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Returns a list of teams";
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        ArrayList<String> teams = new ArrayList<>();
        for (org.bukkit.scoreboard.Team team : Bukkit.getScoreboardManager().getMainScoreboard().getTeams())
            teams.add(team.getName());
        if (teams.size() > 0) return teams.toArray(new String[0]);
        else return null;
    }
}