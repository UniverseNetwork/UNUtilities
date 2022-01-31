package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkUniversal.AdvancedBan.Expressions;

import ch.njol.skript.lang.Expression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkUniversal.AdvancedBan.AdvancedBanHook;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("AdvancedBan - Banner")
@ch.njol.skript.doc.Description("Returns the banner of a player.")
@ch.njol.skript.doc.Examples({"send \"%{_player}% was banned by %banner of {_player}%.\""})
public class ExprBanner extends ch.njol.skript.lang.util.SimpleExpression<String> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprBanner.class, String.class, ch.njol.skript.lang.ExpressionType.COMBINED, "[the] [AdvancedBan] banner of %offlineplayer%", "%offlineplayer%'s [AdvancedBan] banner");
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
        return "the banner of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        me.leoko.advancedban.utils.Punishment ban = AdvancedBanHook.punishmentManager.getBan(AdvancedBanHook.uuidManager.getUUID(player.getSingle(e).getName()));
        return ban == null ? null : new String[]{ban.getOperator()};
    }
}