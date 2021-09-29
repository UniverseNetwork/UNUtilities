package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders.Skript.Elements.Expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders.Placeholders.PlaceholderEvent;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprPlaceholder extends SimpleExpression<String> {
    static {
        Skript.registerExpression(ExprPlaceholder.class, String.class, ExpressionType.SIMPLE, "[the] [event(-| )]placeholder", "[the] [[event(-| )]placeholder] (1¦prefix|2¦identifier)");
    }

    static final int PLACEHOLDER = 0, PREFIX = 1, IDENTIFIER = 2;
    int part;

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        if (!ScriptLoader.isCurrentEvent(PlaceholderEvent.class)) {
            Skript.error("The placeholder can only be used in a placeholder request event", ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        this.part = parseResult.mark;
        return true;
    }

    @Override
    protected String[] get(Event e) {
        switch (part) {
            case PLACEHOLDER:
                return new String[]{((PlaceholderEvent) e).getPlaceholder()};
            case PREFIX:
                return new String[]{((PlaceholderEvent) e).getPrefix()};
            case IDENTIFIER:
                return new String[]{((PlaceholderEvent) e).getIdentifier()};
        }
        return new String[]{};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        switch (part) {
            case PLACEHOLDER:
                return "the placeholder";
            case PREFIX:
                return "the placeholder prefix";
            case IDENTIFIER:
                return "the placeholder identifier";
            default:
                assert false;
                return "placeholder";
        }
    }
}