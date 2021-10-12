package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyFrom;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyTo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.UsePropertyPatterns;
import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;

@UsePropertyPatterns
@PropertyFrom("worldborders")
@PropertyTo("[world[ ]border[s]] center")
public class ExprBorderCenter extends SimplePropertyExpression<WorldBorder, Location> {

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
    }

    @Override
    protected String getPropertyName() {
        return "world border";
    }

    @Override
    @Nullable
    public Location convert(WorldBorder border) {
        return border.getCenter();
    }

    @Override
    public Class<?>[] acceptChange(ChangeMode mode) {
        if (mode == ChangeMode.SET) return asArray(Location.class);
        return null;
    }

    @Override
    public void change(Event event, Object[] delta, ChangeMode mode) {
        if (delta[0] == null) return;
        Location location = (Location) delta[0];
        for (WorldBorder border : getExpr().getArray(event)) border.setCenter(location);
    }
}