package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.util.Time;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyFrom;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyTo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.UsePropertyPatterns;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;

@UsePropertyPatterns
@PropertyFrom("players")
@PropertyTo("time")
public class ExprTimeAbs extends SimplePropertyExpression<Player, Time> {
    @Override
    protected String getPropertyName() {
        return "time";
    }

    @Override
    public Time convert(Player player) {
        return new Time(Long.valueOf(player.getPlayerTime()).intValue());
    }

    @Override
    public Class<? extends Time> getReturnType() {
        return Time.class;
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.SET) return asArray(Time.class);
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        Time n = delta[0] == null ? new Time() : (Time) delta[0];
        switch (mode) {
            case SET:
                for (Player p : getExpr().getAll(e)) p.setPlayerTime(n.getTicks(), false);
                break;
            case RESET:
                for (Player p : getExpr().getAll(e)) p.resetPlayerTime();
            case ADD:
            case REMOVE:
            case REMOVE_ALL:
            case DELETE:
                assert false;
        }
    }
}