package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.WorldBorder;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;

@Patterns("(size|diameter) of world[ ]border[s] %worldborders% [over [a [(time|period) of]] %-timespan%]")
public class ExprBorderSize extends SimpleExpression<Number> {
    Expression<WorldBorder> borders;
    Expression<Timespan> timespan;

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public boolean isSingle() {
        return borders.isSingle();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        borders = (Expression<WorldBorder>) exprs[0];
        timespan = (Expression<Timespan>) exprs[1];
        return true;
    }

    @Override
    @Nullable
    protected Number[] get(Event event) {
        List<Number> sizes = new ArrayList<>();
        for (WorldBorder border : borders.getArray(event)) sizes.add(border.getSize());
        return sizes.toArray(new Number[0]);
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "size of world borders " + borders.toString(event, debug) + " over time " + timespan.toString(event, debug);
    }

    @Override
    public Class<?>[] acceptChange(ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.REMOVE || mode == ChangeMode.ADD) return asArray(Number.class);
        return null;
    }

    @Override
    public void change(Event event, Object[] delta, ChangeMode mode) {
        if (delta[0] == null || timespan == null) return;
        Timespan time = timespan.getSingle(event);
        double amount = ((Number) delta[0]).doubleValue();
        long milliseconds = time == null ? -1L : time.getMilliSeconds() / 1000L;
        switch (mode) {
            case SET:
                for (WorldBorder border : borders.getArray(event)) {
                    if (milliseconds == -1L) border.setSize(amount);
                    else border.setSize(amount, milliseconds);
                }
                break;
            case ADD:
                for (WorldBorder border : borders.getArray(event)) {
                    double size = border.getSize();
                    if (milliseconds == -1L) border.setSize(size + amount);
                    else border.setSize(size + amount, milliseconds);
                }
                break;
            case REMOVE:
                for (WorldBorder border : borders.getArray(event)) {
                    double size = border.getSize();
                    if (milliseconds == -1L) border.setSize(size - amount);
                    else border.setSize(size - amount, milliseconds);
                }
                break;
            case REMOVE_ALL:
            case DELETE:
            case RESET:
            default:
                break;
        }
    }
}