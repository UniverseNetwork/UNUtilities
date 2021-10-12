package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyFrom;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyTo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.UsePropertyPatterns;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Explosive;
import org.bukkit.event.Event;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;

@UsePropertyPatterns
@PropertyFrom("entities")
@PropertyTo("(blast|explosion) (radius|size|yield)")
public class ExprBlastRadius extends SimplePropertyExpression<Entity, Number> {
    @Override
    protected String getPropertyName() {
        return "blast size";
    }

    @Override
    public Number convert(Entity entity) {
        return entity instanceof Explosive ? ((Explosive) entity).getYield() : null;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.SET) return asArray(Number.class);
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        Number n = delta[0] == null || ((Number) delta[0]).doubleValue() < 0 ? 0 : (Number) delta[0];
        switch (mode) {
            case SET:
                for (Entity en : getExpr().getAll(e))
                    if (en instanceof Explosive) ((Explosive) en).setYield(n.floatValue());
                break;
            case RESET:
                for (Entity en : getExpr().getAll(e)) if (en instanceof Explosive) ((Explosive) en).setYield(0);
            case ADD:
            case REMOVE:
            case REMOVE_ALL:
            case DELETE:
                assert false;
        }
    }
}