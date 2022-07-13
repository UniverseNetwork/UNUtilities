package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.AdvancedBan.Expressions;

import ch.njol.skript.lang.Expression;
import id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.AdvancedBan.AdvancedBanHook;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("AdvancedBan - Punishments")
@ch.njol.skript.doc.Description("Returns the active punishments of a player.")
@ch.njol.skript.doc.Examples({"send \"Your punishments: %active punishments of player%\""})
public class ExprPunishments extends ch.njol.skript.lang.util.SimpleExpression<String> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprPunishments.class, String.class, ch.njol.skript.lang.ExpressionType.COMBINED, "[(all [[of] the]|the)] [(current|active)] [AdvancedBan] punishments of %offlineplayer%", "[all of] %offlineplayer%'s [(current|active)] [AdvancedBan] punishments");
    }

    Expression<OfflinePlayer> player;

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the punishments of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return AdvancedBanHook.punishmentManager.getPunishments(AdvancedBanHook.uuidManager.getUUID(player.getSingle(e).getName()), null, true).stream().map(me.leoko.advancedban.utils.Punishment::getName).toArray(String[]::new);
    }
}