package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.AdvancedBan.Expressions;

import ch.njol.skript.lang.Expression;
import id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.AdvancedBan.AdvancedBanHook;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("AdvancedBan - Warn Number")
@ch.njol.skript.doc.Description("Returns number of the warns of a player.")
@ch.njol.skript.doc.Examples({"send \"You have %warns of player% warns!\""})
public class ExprWarns extends ch.njol.skript.lang.util.SimpleExpression<Number> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprWarns.class, Number.class, ch.njol.skript.lang.ExpressionType.COMBINED, "[the] [(number|amount) of] [AdvancedBan] warns of %offlineplayer%", "%offlineplayer%'s [(number|amount) of] [AdvancedBan] warns");
    }

    Expression<OfflinePlayer> player;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the warns of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return new Number[]{AdvancedBanHook.punishmentManager.getCurrentWarns(AdvancedBanHook.uuidManager.getUUID(player.getSingle(e).getName()))};
    }
}