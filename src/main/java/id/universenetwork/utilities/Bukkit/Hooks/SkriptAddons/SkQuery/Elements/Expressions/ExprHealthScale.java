package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.classes.Changer;
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
@PropertyTo("scaled health")
public class ExprHealthScale extends SimplePropertyExpression<Player, Number> {

    @Override
    protected String getPropertyName() {
        return "scaled health";
    }

    @Override
    public Number convert(Player player) {
        return player.getHealthScale();
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == ChangeMode.RESET || mode == ChangeMode.SET) return asArray(Number.class);
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, ChangeMode mode) {
        Number n = delta[0] == null || ((Number) delta[0]).doubleValue() < 0 ? 1 : (Number) delta[0];
        switch (mode) {
            case SET:
                for (Player p : getExpr().getAll(e)) p.setHealthScale(n.doubleValue() * 2);
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