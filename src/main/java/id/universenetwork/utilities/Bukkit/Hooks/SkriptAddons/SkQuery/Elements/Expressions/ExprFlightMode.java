package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyFrom;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyTo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.UsePropertyPatterns;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;

@UsePropertyPatterns
@PropertyFrom("players")
@PropertyTo("fl(y|ight) (state|ability|mode)")
public class ExprFlightMode extends SimplePropertyExpression<Player, Boolean> {
    @Override
    protected String getPropertyName() {
        return "fly state";
    }

    @Override
    public Boolean convert(Player player) {
        return player.getAllowFlight();
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.SET) return asArray(Boolean.class);
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        boolean b = delta != null && (Boolean) delta[0];
        switch (mode) {
            case SET:
                for (Player p : getExpr().getAll(e)) p.setAllowFlight(b);
                break;
            case RESET:
                for (Player p : getExpr().getAll(e)) p.setAllowFlight(p.getGameMode() == GameMode.CREATIVE);
            case ADD:
            case REMOVE:
            case REMOVE_ALL:
            case DELETE:
                assert false;
        }
    }
}