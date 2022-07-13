package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.Slimefun.Expressions;

import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("Slimefun - Research Cost")
@ch.njol.skript.doc.Description("Returns the cost of a Slimefun research")
@ch.njol.skript.doc.Examples({"send \"%cost of the slimefun research with ID \"cool_research\"%\""})
public class ExprResearchCost extends ch.njol.skript.lang.util.SimpleExpression<Number> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprResearchCost.class, Number.class, ch.njol.skript.lang.ExpressionType.COMBINED, "[the] (cost|level) of [the] [Slimefun] research [with ID] %string%");
    }

    Expression<String> ID;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult pr) {
        ID = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the cost of the Slimefun research with ID " + ID.getSingle(e);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (ID.getSingle(e) == null) return null;
        return new Number[]{id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.Slimefun.SlimefunHook.getResearch(ID.getSingle(e)).getCost()};
    }
}