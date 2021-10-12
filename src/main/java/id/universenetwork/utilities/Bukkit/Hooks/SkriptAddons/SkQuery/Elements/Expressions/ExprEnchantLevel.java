package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.parser.ParserInstance;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.event.Event;
import org.bukkit.event.enchantment.EnchantItemEvent;

import static ch.njol.skript.log.ErrorQuality.SEMANTIC_ERROR;

@Patterns("[the] enchant(ing|ment|) level")
public class ExprEnchantLevel extends SimpleExpression<Integer> {
    @Override
    protected Integer[] get(Event event) {
        return new Integer[]{((EnchantItemEvent) event).getExpLevelCost()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "enchant level";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        if (!ParserInstance.get().isCurrentEvent(EnchantItemEvent.class)) {
            Skript.error("Cannot use 'enchanting level' outside of an enchant event", SEMANTIC_ERROR);
            return false;
        }
        return true;
    }
}