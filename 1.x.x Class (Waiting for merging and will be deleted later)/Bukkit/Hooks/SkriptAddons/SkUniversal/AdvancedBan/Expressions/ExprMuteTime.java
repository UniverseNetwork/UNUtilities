package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.AdvancedBan.Expressions;

import ch.njol.skript.lang.Expression;
import id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.AdvancedBan.AdvancedBanHook;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("AdvancedBan - Mute Time")
@ch.njol.skript.doc.Description("Returns the mute time of a player.")
@ch.njol.skript.doc.Examples({"send \"You can continue talking after %remaining mute time of player%.\""})
public class ExprMuteTime extends ch.njol.skript.lang.util.SimpleExpression<String> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprMuteTime.class, String.class, ch.njol.skript.lang.ExpressionType.COMBINED, "[the] remaining [AdvancedBan] mute time of %offlineplayer%", "%offlineplayer%'s remaining [AdvancedBan] mute time");
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
        return "the remaining mute time of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        me.leoko.advancedban.utils.Punishment mute = AdvancedBanHook.punishmentManager.getMute(AdvancedBanHook.uuidManager.getUUID(player.getSingle(e).getName()));
        return mute == null ? null : new String[]{mute.getDuration(false)};
    }
}