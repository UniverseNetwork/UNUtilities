package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkUniversal.Slimefun.Expressions;

import ch.njol.skript.lang.Expression;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("Slimefun - Research Name")
@ch.njol.skript.doc.Description("Returns the name of a Slimefun research")
@ch.njol.skript.doc.Examples({"send \"%name of the slimefun research with ID \"cool_research\"%\""})
public class ExprResearchName extends ch.njol.skript.lang.util.SimpleExpression<String> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprResearchName.class, String.class, ch.njol.skript.lang.ExpressionType.COMBINED, "[the] name of [the] [Slimefun] research [with ID] %string% for %player%");
    }

    Expression<String> ID;
    Expression<Player> player;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult pr) {
        ID = (Expression<String>) e[0];
        player = (Expression<Player>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the name of the Slimefun research with ID " + ID.getSingle(e);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (ID.getSingle(e) == null || player.getSingle(e) == null) return null;
        return new String[]{id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkUniversal.Slimefun.SlimefunHook.getResearch(ID.getSingle(e)).getName(player.getSingle(e))};
    }
}