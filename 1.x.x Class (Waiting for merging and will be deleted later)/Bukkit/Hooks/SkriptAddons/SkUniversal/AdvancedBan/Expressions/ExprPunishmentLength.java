package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.AdvancedBan.Expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import me.leoko.advancedban.bukkit.event.PunishmentEvent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("AdvancedBan - Punish Duration")
@ch.njol.skript.doc.Description("Returns the punish duration on Punishment event.")
public class ExprPunishmentLength extends ch.njol.skript.lang.util.SimpleExpression<String> {
    static {
        Skript.registerExpression(ExprPunishmentLength.class, String.class, ch.njol.skript.lang.ExpressionType.SIMPLE, "[the] [AdvancedBan] punish[ment] (length|duration)");
    }

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
        if (!ch.njol.skript.ScriptLoader.isCurrentEvent(PunishmentEvent.class)) {
            Skript.error("You can not use punishment length expression in any event but on punish.");
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the punishment length";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return new String[]{((PunishmentEvent) e).getPunishment().getDuration(true)};
    }
}