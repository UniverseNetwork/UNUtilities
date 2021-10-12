package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyFrom;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyTo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.UsePropertyPatterns;
import org.bukkit.WorldBorder;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;

@UsePropertyPatterns
@PropertyFrom("worldborders")
@PropertyTo("[world[ ]border[s]] damage [amount]")
public class ExprBorderDamageAmount extends SimplePropertyExpression<WorldBorder, Number> {
    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    protected String getPropertyName() {
        return "damage";
    }

    @Override
    @Nullable
    public Number convert(WorldBorder border) {
        return border.getDamageAmount();
    }

    @Override
    public Class<?>[] acceptChange(ChangeMode mode) {
        if (mode == ChangeMode.SET) return asArray(Number.class);
        return null;
    }

    @Override
    public void change(Event event, Object[] delta, ChangeMode mode) {
        if (delta[0] == null) return;
        Number amount = (Number) delta[0];
        for (WorldBorder border : getExpr().getArray(event)) border.setDamageAmount(amount.doubleValue());
    }
}