package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders.Skript.Elements.Expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders.Skript.Util.PlaceholderUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ExprParsePlaceholder extends SimpleExpression<String> {
    static {
        Skript.registerExpression(ExprParsePlaceholder.class, String.class, ExpressionType.SIMPLE, "[the] ([value of] placeholder[s]|placeholder [value] [of]) %strings% [(from|of) %-players/offlineplayers%] [(1¦without color)]", "parse placeholder[s] %strings% [(for|as) %-players/offlineplayers%] [(1¦without color)]");
    }

    @SuppressWarnings("NotNullFieldNotInitialized")
    Expression<String> placeholders;
    @Nullable
    Expression<OfflinePlayer> players;
    boolean colorize;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        placeholders = (Expression<String>) exprs[0];
        players = (Expression<OfflinePlayer>) exprs[1];
        colorize = parseResult.mark != 1;
        return true;
    }

    @Override
    protected String[] get(Event e) {
        List<String> values = new ArrayList<>();
        String[] placeholders = this.placeholders.getArray(e);
        if (this.players != null) {
            Object[] players = this.players.getArray(e);
            for (String placeholder : placeholders)
                for (Object player : players)
                    if (player instanceof OfflinePlayer)
                        values.add(PlaceholderUtils.getPlaceholder(placeholder, (OfflinePlayer) player, colorize));
        } else for (String placeholder : placeholders)
            values.add(PlaceholderUtils.getPlaceholder(placeholder, null, colorize));
        return values.toArray(new String[0]);
    }

    @Override
    public boolean isSingle() {
        return players != null ? (placeholders.isSingle() && players.isSingle()) : placeholders.isSingle();
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        if (players != null)
            return "the value of placeholder(s) " + placeholders.toString(e, debug) + " from " + players.toString(e, debug);
        return "the value of placeholder(s) " + placeholders.toString(e, debug);
    }
}