package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.AdvancedBan.Expressions;

import ch.njol.skript.Skript;
import me.leoko.advancedban.bukkit.event.PunishmentEvent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("AdvancedBan - Punisher")
@ch.njol.skript.doc.Description("Returns the punisher on Punishment event.\n" + "**Note:** Return type of this expression is string because the punisher can be console.")
public class ExprPunisher extends ch.njol.skript.lang.util.SimpleExpression<String> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprPunisher.class, String.class, ch.njol.skript.lang.ExpressionType.SIMPLE, "[the] [AdvancedBan] punisher");
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
    public boolean init(ch.njol.skript.lang.Expression<?>[] e, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult pr) {
        if (!ch.njol.skript.ScriptLoader.isCurrentEvent(PunishmentEvent.class)) {
            Skript.error("You can not use punisher expression in any event but on punish.");
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the punisher";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return new String[]{((PunishmentEvent) e).getPunishment().getOperator()};
    }
}