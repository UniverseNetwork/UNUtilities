package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyFrom;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyTo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.UsePropertyPatterns;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;

@UsePropertyPatterns
@PropertyFrom("inventories")
@PropertyTo("[global] [max] stack size")
public class ExprItemStackSize extends SimplePropertyExpression<Inventory, Number> {
    @Override
    protected String getPropertyName() {
        return "max stack";
    }

    @Override
    public Number convert(Inventory inventory) {
        return inventory.getMaxStackSize();
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.SET)
            return asArray(Number.class);
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        Number b = delta == null ? 64 : (Number) delta[0];
        switch (mode) {
            case SET:
                for (Inventory i : getExpr().getArray(e)) i.setMaxStackSize(b.intValue());
                break;
            case RESET:
                for (Inventory i : getExpr().getArray(e)) i.setMaxStackSize(64);
            case ADD:
            case REMOVE:
            case REMOVE_ALL:
            case DELETE:
                assert false;
        }
    }
}