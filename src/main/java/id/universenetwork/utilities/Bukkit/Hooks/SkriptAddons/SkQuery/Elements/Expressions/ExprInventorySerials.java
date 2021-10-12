package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyFrom;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyTo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.UsePropertyPatterns;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Serialization.InventorySerialUtils;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;
import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Serialization.InventorySerialUtils.fromBase64;

@UsePropertyPatterns
@PropertyFrom("inventory")
@PropertyTo("serialized contents")
public class ExprInventorySerials extends SimplePropertyExpression<Inventory, String> {
    @Override
    protected String getPropertyName() {
        return "inventory serial";
    }

    @Override
    public String convert(Inventory inventory) {
        return InventorySerialUtils.toBase64(inventory);
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return mode == Changer.ChangeMode.SET ? asArray(String.class) : null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        String s = delta[0] == null ? "" : (String) delta[0];
        Inventory i = getExpr().getSingle(e);
        try {
            i.setContents(fromBase64(s).getContents());
        } catch (Exception ignored) {
        }
    }
}