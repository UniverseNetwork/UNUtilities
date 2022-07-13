package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Effects;

import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;

@ch.njol.skript.doc.Name("Register Team")
@ch.njol.skript.doc.Description({"Registers/creates a team"})
@ch.njol.skript.doc.Examples("register team \"red\"")
@ch.njol.skript.doc.Since("1.0")
public class EffRegTeam extends ch.njol.skript.lang.Effect {
    static {
        ch.njol.skript.Skript.registerEffect(EffRegTeam.class, "(create|make|register) [a] [new] team %string%");
    }

    Expression<String> team;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        this.team = (Expression<String>) expressions[0];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event event, boolean debug) {
        return "Register team effect with expression team: " + team.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        if (team != null) if (!(team.getSingle(event).contains(" ")))
            org.bukkit.Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(team.getSingle(event));
    }
}