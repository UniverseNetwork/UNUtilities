package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkUniversal.Slimefun.Expressions;

import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("Slimefun - Item IDs")
@ch.njol.skript.doc.Description("Returns the IDs of all Slimefun items")
@ch.njol.skript.doc.Examples({"send \"%the IDs of all Slimefun items%\""})
public class ExprItemNames extends ch.njol.skript.lang.util.SimpleExpression<String> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprItemNames.class, String.class, ch.njol.skript.lang.ExpressionType.SIMPLE, "[(all [[of] the]|the)] [IDs of [all] [the]] Slimefun items");
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
        return "all of the slimefun items";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getRegistry().getAllSlimefunItems().stream().map(io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem::getId).toArray(String[]::new);
    }
}