package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("Sprint State of Player")
@ch.njol.skript.doc.Description({"Sets/gets the sprint state of the specified player(s)"})
@ch.njol.skript.doc.Examples({"set sprint state of player to true", "broadcast \"sprint state of player\""})
@ch.njol.skript.doc.Since("1.0")
public class ExprSprint extends ch.njol.skript.lang.util.SimpleExpression<Boolean> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprSprint.class, Boolean.class, ch.njol.skript.lang.ExpressionType.COMBINED, "(sprint|run)[ing] [state] of %players%");
    }

    Expression<Player> player;

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        player = (Expression<Player>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Sprint state expression with expression player: " + player.toString(event, debug);
    }

    @Override
    @Nullable
    protected Boolean[] get(Event event) {
        if (player.getSingle(event) != null) return new Boolean[]{player.getSingle(event).isSprinting()};
        return null;
    }

    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode) {
        if (player != null) for (Player p : player.getAll(event))
            if (mode == Changer.ChangeMode.SET) {
                Boolean x = ((Boolean) delta[0]);
                p.setSprinting(x);
            }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) return ch.njol.util.coll.CollectionUtils.array(Boolean.class);
        return null;
    }
}