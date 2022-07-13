package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.AdvancedBan.Expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import me.leoko.advancedban.bukkit.event.PunishmentEvent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("AdvancedBan - Punishment Reason")
@ch.njol.skript.doc.Description("Returns the punish reason on Punishment event.")
public class ExprPunishReason extends ch.njol.skript.lang.util.SimpleExpression<String> {
    static {
        Skript.registerExpression(ExprPunishReason.class, String.class, ch.njol.skript.lang.ExpressionType.SIMPLE, "[the] [AdvancedBan] punish[ment] reason");
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
            Skript.error("You can not use punish reason expression in any event but on punish.");
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the punishment reason";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return new String[]{((PunishmentEvent) e).getPunishment().getReason()};
    }
}