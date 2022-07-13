package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Expressions;

import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("Random Server Idea")
@ch.njol.skript.doc.Description({"Returns a random server idea"})
@ch.njol.skript.doc.Examples("broadcast \"%random server idea%\"")
@ch.njol.skript.doc.Since("1.0")
public class ExprRandomIdea extends ch.njol.skript.lang.util.SimpleExpression<String> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprRandomIdea.class, String.class, ch.njol.skript.lang.ExpressionType.COMBINED, "random server idea");
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(ch.njol.skript.lang.Expression<?>[] exprs, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Returns a random server idea";
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        return new String[]{id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Utils.ServerIdeas.getRandom()};
    }
}