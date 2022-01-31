package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkUniversal.AdvancedBan.Expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import me.leoko.advancedban.bukkit.event.PunishmentEvent;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("AdvancedBan - Punished Player")
@ch.njol.skript.doc.Description("Returns the punished player on Punishment event.")
public class ExprPunishedPlayer extends ch.njol.skript.lang.util.SimpleExpression<OfflinePlayer> {
    static {
        Skript.registerExpression(ExprPunishedPlayer.class, OfflinePlayer.class, ch.njol.skript.lang.ExpressionType.SIMPLE, "[the] [AdvancedBan] punished player");
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult pr) {
        if (!ch.njol.skript.ScriptLoader.isCurrentEvent(PunishmentEvent.class)) {
            Skript.error("You can not use punished player expression in any event but on punish.");
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the punished player";
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        return new OfflinePlayer[]{org.bukkit.Bukkit.getOfflinePlayer(((PunishmentEvent) e).getPunishment().getName())};
    }
}