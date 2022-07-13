package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.Slimefun.Expressions;

import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("Slimefun - All Researches")
@ch.njol.skript.doc.Description("Returns the IDs of all Slimefun researches")
@ch.njol.skript.doc.Examples({"send \"%all slimefun researches%\""})
public class ExprAllResearches extends ch.njol.skript.lang.util.SimpleExpression<String> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprAllResearches.class, String.class, ch.njol.skript.lang.ExpressionType.SIMPLE, "[(all [[of] the]|the)] [IDs of all [the]] [Slimefun] researches");
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(ch.njol.skript.lang.Expression<?>[] e, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the IDs of all Slimefun researches";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getRegistry().getResearches().stream().map(r -> r.getKey().getKey()).toArray(String[]::new);
    }
}