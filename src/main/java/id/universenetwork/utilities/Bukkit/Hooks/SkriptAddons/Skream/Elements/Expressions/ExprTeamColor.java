package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("Color of Team")
@ch.njol.skript.doc.Description({"Sets/gets the color of the specified team.", "COLORS: red, dark_red, blue, dark_blue, aqua, dark_aqua, dark_purple, light_purple, black, white, yellow, gold, gray, dark_gray, green and dark_green"})
@ch.njol.skript.doc.Examples({"set color of team happypockets to \"red\"", "broadcast \"%color of team happypockets%Hello\" # Broadcasts \"Hello\" in the same color of the team specified in the expression"})
@ch.njol.skript.doc.Since("1.0")
public class ExprTeamColor extends ch.njol.skript.lang.util.SimpleExpression<String> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprTeamColor.class, String.class, ch.njol.skript.lang.ExpressionType.COMBINED, "color of team %team%");
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
        return "Color of team expression with expression team: " + team.toString(event, debug);
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        if (team.getSingle(event) != null) return new String[]{String.valueOf(team.getSingle(event).getColor())};
        return null;
    }

    @Override
    public void change(Event event, Object[] delta, ChangeMode mode) {
        Team t = team.getSingle(event);
        if (t != null) if (mode == ChangeMode.SET) {
            String x = ((String) delta[0]).toUpperCase();
            ChatColor c = ChatColor.valueOf(x);
            t.setColor(c);
        } else if (mode == ChangeMode.RESET) t.setColor(ChatColor.WHITE);
    }

    @Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.RESET)
            return ch.njol.util.coll.CollectionUtils.array(String.class);
        return null;
    }
}