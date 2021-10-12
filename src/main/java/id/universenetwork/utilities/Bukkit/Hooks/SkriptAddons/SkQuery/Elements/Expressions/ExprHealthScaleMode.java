package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyFrom;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyTo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.UsePropertyPatterns;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;


@UsePropertyPatterns
@PropertyFrom("players")
@PropertyTo("scaled health (state|ability|mode)")
public class ExprHealthScaleMode extends SimplePropertyExpression<Player, Boolean> {
    @Override
    protected String getPropertyName() {
        return "scaled health state";
    }

    @Override
    public Boolean convert(Player player) {
        return player.isHealthScaled();
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public Class<?>[] acceptChange(ChangeMode mode) {
        if (mode == ChangeMode.RESET || mode == ChangeMode.SET) return asArray(Boolean.class);
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, ChangeMode mode) {
        boolean b = delta != null && (Boolean) delta[0];
        switch (mode) {
            case SET:
                for (Player p : getExpr().getAll(e)) p.setHealthScaled(b);
                break;
            case RESET:
                for (Player p : getExpr().getAll(e)) p.setHealthScaled(false);
            case ADD:
            case REMOVE:
            case REMOVE_ALL:
            case DELETE:
                assert false;
        }
    }
}