package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkUniversal.AdvancedBan.Expressions;

import ch.njol.skript.lang.Expression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkUniversal.AdvancedBan.AdvancedBanHook;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("AdvancedBan - Mute Reason")
@ch.njol.skript.doc.Description("Returns the mute reason of a player.")
@ch.njol.skript.doc.Examples({"send \"Your mute reason is %mute reason of player%.\""})
public class ExprMuteReason extends ch.njol.skript.lang.util.SimpleExpression<String> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprMuteReason.class, String.class, ch.njol.skript.lang.ExpressionType.COMBINED, "[the] [AdvancedBan] mute reason of %offlineplayer%", "%offlineplayer%'s [AdvancedBan] mute reason");
    }

    Expression<OfflinePlayer> player;

    @Override
    public boolean isSingle() {
        return true;
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
        return "the mute reason of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        me.leoko.advancedban.utils.Punishment mute = AdvancedBanHook.punishmentManager.getMute(AdvancedBanHook.uuidManager.getUUID(player.getSingle(e).getName()));
        return mute == null ? null : new String[]{mute.getReason()};
    }
}