package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.util.Timespan;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyFrom;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyTo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.UsePropertyPatterns;
import org.bukkit.WorldBorder;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;

@UsePropertyPatterns
@PropertyFrom("worldborders")
@PropertyTo("[world[ ]border[s]] warning time")
public class ExprBorderWarningTime extends SimplePropertyExpression<WorldBorder, Number> {

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    protected String getPropertyName() {
        return "warning time";
    }

    @Override
    @Nullable
    public Number convert(WorldBorder border) {
        return border.getWarningTime();
    }

    @Override
    public Class<?>[] acceptChange(ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.REMOVE || mode == ChangeMode.ADD)
            return asArray(Timespan.class);
        return null;
    }

    @Override
    public void change(Event event, Object[] delta, ChangeMode mode) {
        if (delta[0] == null) return;
        Timespan timespan = (Timespan) delta[0];
        Number time = timespan.getMilliSeconds() / 1000L;
        switch (mode) {
            case SET:
                for (WorldBorder border : getExpr().getArray(event))
                    border.setWarningTime(time.intValue());
                break;
            case ADD:
                for (WorldBorder border : getExpr().getArray(event)) {
                    int warning = border.getWarningTime();
                    border.setWarningTime(warning + time.intValue());
                }
                break;
            case REMOVE:
                for (WorldBorder border : getExpr().getArray(event)) {
                    int warning = border.getWarningTime();
                    border.setWarningTime(warning - time.intValue());
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