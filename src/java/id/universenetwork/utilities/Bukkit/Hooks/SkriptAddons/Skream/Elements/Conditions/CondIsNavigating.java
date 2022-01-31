package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Conditions;

import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.NonNull;

@ch.njol.skript.doc.Name("NPC Is Navigating")
@ch.njol.skript.doc.Description({"Checks if an npc is pathfinding/navigating."})
@ch.njol.skript.doc.Examples({"if npc last spawned npc is navigating:"})
@ch.njol.skript.doc.RequiredPlugins("Citizens")

public class CondIsNavigating extends ch.njol.skript.lang.Condition {
    static {
        ch.njol.skript.Skript.registerCondition(CondIsNavigating.class, "npc %integer% (1¦is|2¦is(n't| not)) (navigating|pathfinding)");
    }

    Expression<Integer> id;

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        this.id = (Expression<Integer>) expressions[0];
        setNegated(parser.mark == 1);
        return true;
    }

    @Override
    public @NonNull String toString(@org.jetbrains.annotations.Nullable Event event, boolean debug) {
        return "Integer expression: " + id.toString(event, debug);
    }

    @Override
    public boolean check(@NonNull Event event) {
        return net.citizensnpcs.api.CitizensAPI.getNPCRegistry().getById(id.getSingle(event)).getNavigator().isNavigating() == isNegated();
    }
}